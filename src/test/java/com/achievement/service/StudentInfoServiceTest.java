package com.achievement.service;

import com.achievement.BaseTest;
import com.achievement.entity.StudentInfo;
import com.achievement.vo.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;

/**
 * 学生
 *
 * @author weiQiang
 * @date 2018/10/4
 */
@Slf4j
public class StudentInfoServiceTest extends BaseTest {

  @Test
  public void list() {
    ResultEntity resultEntity = studentInfoService.list(StudentInfo.builder().classIds(new ArrayList<String>() {{
      add("1");
      add("2");
    }}).build());
    log.info("学生信息:{}", resultEntity);
  }
}