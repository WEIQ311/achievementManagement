package com.achievement.service;

import com.achievement.entity.ConfTeacherSubject;
import com.achievement.vo.ResultEntity;

import java.util.List;
import java.util.Map;

/**
 * (ConfTeacherSubject)Service
 *
 * @author 魏强
 * @since 2018-10-05 16:02:50
 */
public interface ConfTeacherSubjectService {
  /**
   * 家长与学生关系信息Map
   *
   * @param confTeacherSubject 科目与教师关系信息
   * @return Map
   */
  Map<String, List<ConfTeacherSubject>> convertSubjectOfTeacherMap(ConfTeacherSubject confTeacherSubject);

  /**
   * 教师与科目关系信息Map
   *
   * @param confTeacherSubject 教师与科目关系信息
   * @return Map
   */
  Map<String, List<ConfTeacherSubject>> convertTeacherOfSubjectMap(ConfTeacherSubject confTeacherSubject);

  /**
   * 删除教师与科目关系信息
   *
   * @param confIds 教师与科目关系信息
   * @return ResultEntity
   */
  ResultEntity delete(List<String> confIds);

  /**
   * 增加教师与科目关系信息
   *
   * @param confTeacherSubjectList 教师与科目关系信息
   * @return ResultEntity
   */
  ResultEntity insert(List<ConfTeacherSubject> confTeacherSubjectList);

  /**
   * 查询家长与学生关系信息
   *
   * @param confTeacherSubject 家长与学生关系信息
   * @return ResultEntity
   */
  ResultEntity list(ConfTeacherSubject confTeacherSubject);

}