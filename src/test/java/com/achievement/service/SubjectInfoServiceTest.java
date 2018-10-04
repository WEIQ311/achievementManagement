package com.achievement.service;

import com.achievement.BaseTest;
import com.achievement.entity.SubjectInfo;
import com.achievement.vo.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author weiQiang
 * @date 2018/10/3
 */
@Slf4j
public class SubjectInfoServiceTest extends BaseTest {

  @Test
  public void convertSubjectInfo() {
    Map<String, SubjectInfo> subjectInfoMap = subjectInfoService.convertRecordToMap(SubjectInfo.builder().build());
    log.info("科目信息:{}", subjectInfoMap);
  }

  @Test
  public void delete() {
    List<String> subjectIds = subjectInfoService.convertRecordToMap(SubjectInfo.builder().build()).values().stream().map(SubjectInfo::getSubjectId)
        .collect(Collectors.toList());
    ResultEntity resultEntity = subjectInfoService.delete(subjectIds);
    log.info("删除学科:{}", resultEntity);
  }

  @Test
  public void insert() {
    ResultEntity resultEntity = subjectInfoService.insert(new ArrayList<SubjectInfo>() {{
      add(SubjectInfo.builder().subjectName("语文").insertTime(new Date()).status(1).build());
      add(SubjectInfo.builder().subjectName("数学").insertTime(new Date()).status(1).build());
      add(SubjectInfo.builder().subjectName("英语").insertTime(new Date()).status(1).build());
    }});
    log.info("增加学科:{}", resultEntity);
  }

  @Test
  public void list() {

  }

  @Test
  public void update() {
    List<SubjectInfo> subjectInfoList = subjectInfoService.convertRecordToMap(SubjectInfo.builder().build()).values().stream()
        .collect(Collectors.toList());
    subjectInfoList.stream().forEach(subjectInfo -> subjectInfo.setUpdateTime(new Date()));
    ResultEntity resultEntity = subjectInfoService.update(subjectInfoList);
    log.info("更新学科:{}", resultEntity);
  }
}