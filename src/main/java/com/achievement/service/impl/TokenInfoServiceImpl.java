package com.achievement.service.impl;

import com.achievement.entity.ScoreUserInfo;
import com.achievement.entity.TokenInfo;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.TokenInfoMapper;
import com.achievement.service.TokenInfoService;
import com.achievement.utils.GloabalUtils;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ResultEntity;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.achievement.constants.GlobalConstants.*;

/**
 * @author weiQiang
 * @date 2018/10/12
 */
@Slf4j
@Service(value = "tokenInfoService")
public class TokenInfoServiceImpl implements TokenInfoService {

  @Resource
  private TokenInfoMapper tokenInfoMapper;

  /**
   * 删除Token
   *
   * @param pkIds 主键
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity delete(List<String> pkIds) {
    if (null == pkIds || pkIds.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    tokenInfoMapper.delete(pkIds);
    return ResultUtil.success(GlobalEnum.DeleteSuccess);
  }

  /**
   * 删除token
   *
   * @param token token
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity deleteToken(String token) {
    TokenInfo tokenInfo = GloabalUtils.parseJWT(token);
    String id = tokenInfo.getId();
    if (StringUtils.isBlank(id)) {
      return ResultUtil.error(GlobalEnum.PkIdEmpty);
    }
    tokenInfoMapper.delete(new ArrayList<String>() {{
      add(id);
    }});
    return ResultUtil.success(GlobalEnum.DeleteSuccess);
  }

  /**
   * 生成token
   *
   * @param request  请求
   * @param userInfo 用户信息
   * @param response 响应
   * @return ResultEntity
   */
  @Override
  public ResultEntity initToken(HttpServletRequest request, ScoreUserInfo userInfo, HttpServletResponse response) {
    String id = userInfo.getUserId();
    String ipAddress = GloabalUtils.getIpAddress(request);
    Map<String, Object> subjectMap = new HashMap<>(2);
    subjectMap.put(CLIENT_IP, ipAddress);
    subjectMap.put(CLIENT_INFO, userInfo);
    String subject = JSON.toJSONString(subjectMap);
    String token = GloabalUtils.createJWT(id, TOKEN_ISSUER, subject, TOKEN_TIME_OUT);
    TokenInfo tokenInfo = GloabalUtils.parseJWT(token);
    deleteToken(token);
    tokenInfoMapper.insert(new ArrayList<TokenInfo>() {{
      add(tokenInfo);
    }});
    response.setHeader(TOKEN_HEADER, token);
    return ResultUtil.success(GlobalEnum.InsertSuccess);
  }

  /**
   * 检测token是否有效
   *
   * @param token   token
   * @param request 请求
   * @return ResultEntity
   */
  @Override
  public ResultEntity tokenValid(String token, HttpServletRequest request) {
    String ipAddress = GloabalUtils.getIpAddress(request);
    TokenInfo tokenInfo = GloabalUtils.parseJWT(token);
    List<TokenInfo> tokenInfos = tokenInfoMapper.list(TokenInfo.builder().id(tokenInfo.getId()).build());
    if (null == tokenInfos || tokenInfos.size() < 1) {
      return ResultUtil.error(GlobalEnum.TokenOvertime);
    }
    String subject = tokenInfo.getSubject();
    String clientIp = JSON.parseObject(subject).getString(CLIENT_IP);
    boolean success = Objects.equals(ipAddress, clientIp);
    success = success ? Objects.equals(token, tokenInfos.get(0).getToken()) : success;
    String message = success ? GlobalEnum.QuerySuccess.getMessage() : GlobalEnum.UserLoginOtherIp.getMessage();
    return ResultEntity.builder().success(success).message(message).build();
  }

  /**
   * 更新token
   *
   * @param token token
   * @return String
   */
  @Override
  public String updateToken(String token) {
    TokenInfo tokenInfo = GloabalUtils.parseJWT(token);
    String newToken = GloabalUtils.createJWT(tokenInfo.getId(), TOKEN_ISSUER, tokenInfo.getSubject(), TOKEN_TIME_OUT);
    TokenInfo newTokenInfo = GloabalUtils.parseJWT(newToken);
    ResultEntity resultEntity = deleteToken(token);
    log.info("删除用户登陆token:{}", resultEntity);
    Integer updateCount = tokenInfoMapper.update(new ArrayList<TokenInfo>() {{
      add(newTokenInfo);
    }});
    if (updateCount > 0) {
      log.info("更新用户登陆Token:{}条", updateCount);
    }
    return newToken;
  }
}
