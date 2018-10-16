package com.achievement.service;

import com.achievement.BaseTest;
import org.junit.Test;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @author weiQiang
 * @date 2018/10/16
 */
public class ScoreUserInfoServiceTest extends BaseTest {

  @Test
  public void login() {
    ValueOperations valueOperations = redisTemplate.opsForValue();
  }
}