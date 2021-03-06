package com.achievement;

import com.achievement.service.ScoreUserInfoService;
import com.achievement.service.StudentInfoService;
import com.achievement.service.SubjectInfoService;
import com.achievement.service.SubjectScoreInfoService;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author weiQiang
 * @date 2018/10/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseTest {

  @Resource
  public RedisTemplate redisTemplate;
  @Resource
  public ScoreUserInfoService scoreUserInfoService;
  @Resource
  public StudentInfoService studentInfoService;
  @Resource
  public SubjectInfoService subjectInfoService;
  @Resource
  public SubjectScoreInfoService subjectScoreInfoService;
}
