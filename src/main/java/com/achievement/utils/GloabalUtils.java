package com.achievement.utils;

import com.achievement.entity.TokenInfo;
import com.achievement.enums.GlobalEnum;
import com.achievement.service.TokenInfoService;
import com.achievement.vo.ResultEntity;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.security.Key;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

import static com.achievement.constants.GlobalConstants.*;

/**
 * @author weiQiang
 */
@Slf4j
public class GloabalUtils {
  /**
   * java字段转数据库字段
   *
   * @param column 排序字段
   * @return String
   */
  public static String changeColumn(String column, String direction) {
    if (StringUtils.isNotBlank(column)) {
      StringBuilder columnBuilder = new StringBuilder();
      char[] chars = column.toCharArray();
      for (char aChar : chars) {
        if (Character.isUpperCase(aChar)) {
          columnBuilder.append(UNDER_LINE);
          columnBuilder.append(String.valueOf(aChar).toLowerCase());
        } else {
          columnBuilder.append(String.valueOf(aChar));
        }
      }
      if (StringUtils.isBlank(direction)) {
        direction = ORDER_ASC;
      }
      return columnBuilder.toString() + SPACE + direction;
    }
    return "";
  }

  /**
   * 检测请求信息是否合法
   *
   * @param request          请求
   * @param response         响应
   * @param tokenInfoService 校验TokenService
   */
  public static void checkRequestInfo(HttpServletRequest request, HttpServletResponse response, TokenInfoService tokenInfoService) {
    String token = request.getHeader(TOKEN_HEADER);
    String requestURI = request.getRequestURI();
    for (String ignoreUri : IGNORE_URI) {
      if (requestURI.endsWith(ignoreUri)) {
        return;
      }
    }
    if (null == token || token.isEmpty()) {
      GloabalUtils.convertMessage(GlobalEnum.TokenEmpty);
    }
    TokenInfo tokenInfo = GloabalUtils.parseJWT(token);
    if (null != tokenInfo && StringUtils.isNotBlank(tokenInfo.getTokenType())) {
      boolean success = tokenInfo.isSuccess();
      String tokenType = tokenInfo.getTokenType();
      if (success) {
        ResultEntity resultEntity = tokenInfoService.tokenValid(token, request);
        if (!resultEntity.isSuccess()) {
          response.reset();
          GloabalUtils.convertMessage(resultEntity.getMessage());
        }
        if (Objects.equals(UPDATE, tokenType)) {
          String newToken = tokenInfoService.updateToken(tokenType);
          response.setHeader(TOKEN_HEADER, token);
          response.setHeader(TOKEN_NEW_HEADER, newToken);
        } else {
          log.info("用户授权认证通过!");
        }
      } else {
        response.reset();
        if (Objects.equals(ExpiredJwtException.class.getName(), tokenType)) {
          log.info("token已过期!");
          GloabalUtils.convertMessage(GlobalEnum.TokenOvertime);
        } else if (Objects.equals(SignatureException.class.getName(), tokenType)) {
          log.error("token sign解析失败!");
          GloabalUtils.convertMessage(GlobalEnum.TokenSignError);
        } else if (Objects.equals(MalformedJwtException.class.getName(), tokenType)) {
          log.error("token的head解析失败!");
          GloabalUtils.convertMessage(GlobalEnum.TokenSignError);
        } else {
          log.error("程序未捕获到的异常:{}", tokenType);
          GloabalUtils.convertMessage(tokenType);
        }
      }
    }
  }

  /**
   * 转换异常信息
   *
   * @param globalEnum 异常提示
   * @param args       参数
   */
  public static void convertMessage(GlobalEnum globalEnum, String... args) {
    String message = globalEnum.getMessage();
    convertMessage(message, args);
  }

  /**
   * 检验token是或否即将过期
   * 如快要过期,就提前更新token。
   * 如果已经过期的,会抛出ExpiredJwtException 的异常
   *
   * @param jwt token
   * @return Object
   */
  public static TokenInfo parseJWT(String jwt) {
    TokenInfo tokenInfo = TokenInfo.builder().build();
    try {
      Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(LEXICAL_XSD_BASE64_BINARY)).parseClaimsJws(jwt).getBody();
      String id = claims.getId();
      String subject = claims.getSubject();
      String issuer = claims.getIssuer();
      Date issuedAt = claims.getIssuedAt();
      Date expiration = claims.getExpiration();
      tokenInfo.setId(id);
      tokenInfo.setSubject(subject);
      tokenInfo.setIssuer(issuer);
      tokenInfo.setIssuedAt(issuedAt);
      tokenInfo.setExpiration(expiration);
      tokenInfo.setToken(jwt);
      //过期的时间
      Long exp = expiration.getTime();
      //现在的时间
      long nowMillis = System.currentTimeMillis();
      //剩余的时间 ，若剩余的时间小于1小时，就返回update,产生一个新的token给APP
      long seconds = exp - nowMillis;
      if (seconds <= SECONDS_ONE_HOUR) {
        log.info("token的有效期小于1小时，请更新token!");
        tokenInfo.setTokenType(UPDATE);
      } else {
        tokenInfo.setTokenType(SUCCESS);
      }
      tokenInfo.setSuccess(true);
      return tokenInfo;
    } catch (ExpiredJwtException e) {
      tokenInfo.setTokenType(ExpiredJwtException.class.getName());
      return tokenInfo;
    } catch (MalformedJwtException e) {
      tokenInfo.setTokenType(MalformedJwtException.class.getName());
      return tokenInfo;
    }
  }

  /**
   * 转换异常信息
   *
   * @param message 异常提示
   * @param args    参数
   */
  public static void convertMessage(String message, String... args) {
    message = String.format(message, args);
    throw new RuntimeException(message);
  }

  /**
   * 创建目录
   *
   * @param path
   * @return
   */
  public static String createDir(String path) {
    if (!path.endsWith(File.separator)) {
      path += File.separator;
    }
    path = winOrLinuxPath(path);
    File unZipFileDir = new File(path);
    if (!unZipFileDir.exists()) {
      unZipFileDir.mkdirs();
    }
    return path;
  }

  /**
   * 根据系统转换为windows格式或者linux格式
   *
   * @param path 路径
   * @return
   */
  public static String winOrLinuxPath(String path) {
    if (!isOsLinux()) {
      path = path.replace("/", "\\");
    }
    return path;
  }

  /**
   * 判断是否是linux系统
   *
   * @return
   */
  public static boolean isOsLinux() {
    Properties properties = System.getProperties();
    String os = properties.getProperty("os.name");
    if (os != null && os.toLowerCase().indexOf(LINUX_NAME) > -1) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 创建token
   * id，issuer，subject，ttlMillis都是放在payload中的，可根据自己的需要修改
   *
   * @param id        登陆产生主键
   * @param issuer    拥有者
   * @param subject   内容
   * @param ttlMillis 过期时间
   * @return
   */
  public static String createJWT(String id, String issuer, String subject, long ttlMillis) {
    //签名的算法
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    //当前的时间
    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);
    //签名算法的秘钥，解析token时的秘钥需要和此时的一样
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(LEXICAL_XSD_BASE64_BINARY);
    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    //构造
    JwtBuilder builder = Jwts.builder().setId(id)
        .setIssuedAt(now)
        .setSubject(subject)
        .setIssuer(issuer)
        .signWith(signatureAlgorithm, signingKey);
    log.info("---token生成---");
    //给token设置过期时间
    if (ttlMillis >= 0) {
      long expMillis = nowMillis + ttlMillis;
      Date exp = new Date(expMillis);
      log.info("过期时间：{}", exp);
      builder.setExpiration(exp);
    }
    return builder.compact();
  }

  /**
   * 获取IP地址
   *
   * @param request
   * @return
   */
  public static String getIpAddress(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader("HTTP_CLIENT_IP");
    }
    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }

  /**
   * 判断是否ajax请求.
   * 可以看到Ajax 请求多了个 x-requested-with ，可以利用它，
   * request.getHeader("x-requested-with"); 为 null，则为传统同步请求，为 XMLHttpRequest，则为Ajax 异步请求。
   *
   * @param request HttpServletRequest
   * @return 是否ajax请求.
   */
  public static boolean isAjaxRequest(HttpServletRequest request) {
    String xr = request.getHeader(X_REQUESTED_WIDTH);
    return (xr != null && XML_HTTP_REQUEST.equalsIgnoreCase(xr));
  }

  /**
   * 通用主键
   *
   * @return
   */
  public synchronized static String ordinaryId() {
    Integer count = 6;
    return ordinaryId(count);
  }

  /**
   * 通用主键
   *
   * @param count 随机数个数
   * @return String
   */
  public synchronized static String ordinaryId(Integer count) {
    return DateFormatUtils.format(new Date(), DATE_TIME_FORMATTER) + RandomStringUtils.randomNumeric(count);
  }

  /**
   * 发送响应流方法
   *
   * @param response 响应
   * @param fileName 文件名称
   */
  public static void setResponseHeader(HttpServletResponse response, String fileName) {
    try {
      fileName = new String(fileName.getBytes(), "ISO8859-1");
      response.setContentType("application/octet-stream;charset=ISO8859-1");
      response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
      response.addHeader("Pargam", "no-cache");
      response.addHeader("Cache-Control", "no-cache");
    } catch (Exception ex) {
      GloabalUtils.convertMessage(GlobalEnum.ExceptionMessage, ex.getMessage());
    }
  }

  /**
   * MD5验证方法
   *
   * @param text 明文
   * @param key  密钥
   * @param md5  密文
   * @return true/false
   * @throws Exception
   */
  public static boolean verify(String text, String key, String md5) throws Exception {
    //根据传入的密钥进行验证
    String md5Text = md5(text, key);
    if (md5Text.equalsIgnoreCase(md5)) {
      return true;
    }
    return false;
  }

  /**
   * MD5方法
   *
   * @param text 明文
   * @param key  密钥
   * @return 密文
   * @throws Exception
   */
  public static String md5(String text, String key) {
    //加密后的字符串
    return DigestUtils.md5Hex(text + key);
  }
}
