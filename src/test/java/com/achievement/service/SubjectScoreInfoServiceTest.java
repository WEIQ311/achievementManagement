package com.achievement.service;

import com.achievement.BaseTest;
import com.achievement.entity.SubjectScoreInfo;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * @author weiQiang
 * @date 2018/10/11
 */
public class SubjectScoreInfoServiceTest extends BaseTest {

  @Test
  public void listRankingMap() {
    String string = JSON.toJSONString(subjectScoreInfoService.listRankingMap(SubjectScoreInfo.builder().build()));
    System.out.println(string);
  }
}