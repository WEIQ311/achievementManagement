package com.achievement.utils;

import com.achievement.enums.GlobalEnum;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.Properties;

import static com.achievement.constants.GlobalConstants.*;

/**
 * @author weiQiang
 */
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
}
