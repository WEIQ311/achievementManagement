package com.achievement.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static com.achievement.constants.GlobalConstants.TOKEN_ISSUER;

/**
 * 工具类测试
 *
 * @author weiQiang
 * @date 2018/10/12
 */
@Slf4j
public class GloabalUtilsTest {

  @Test
  public void createJWT() {
    String token = GloabalUtils.createJWT("1", "campus.picp.net/score/", "{\"count\":2}", 1000 * 60 * 60 * 24 * 2 + 60000);//2天+60秒
    log.info("token:{}", token);
    Object parseJWT = GloabalUtils.parseJWT(token);
    log.info("parseJWT:{}", parseJWT);
  }

  @Test
  public void md5() {
    String password = GloabalUtils.md5("123", TOKEN_ISSUER);
    log.info("password:{}", password);
  }
}