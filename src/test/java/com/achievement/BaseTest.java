package com.achievement;

import com.achievement.service.SubjectInfoService;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
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
  public SubjectInfoService subjectInfoService;
}
