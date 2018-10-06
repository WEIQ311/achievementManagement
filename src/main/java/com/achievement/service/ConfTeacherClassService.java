package com.achievement.service;

import com.achievement.entity.ConfTeacherClass;
import com.achievement.vo.ResultEntity;

import java.util.List;
import java.util.Map;

/**
 * (ConfTeacherClass)Service
 *
 * @author 魏强
 * @since 2018-10-05 16:02:41
 */
public interface ConfTeacherClassService {

  /**
   * 家长与学生关系信息Map
   *
   * @param confTeacherClass 班级与教师关系信息
   * @return Map
   */
  Map<String, List<ConfTeacherClass>> convertClassOfTeacherMap(ConfTeacherClass confTeacherClass);

  /**
   * 教师与班级关系信息Map
   *
   * @param confTeacherClass 教师与班级关系信息
   * @return Map
   */
  Map<String, List<ConfTeacherClass>> convertTeacherOfClassMap(ConfTeacherClass confTeacherClass);

  /**
   * 删除教师与班级关系信息
   *
   * @param confIds 教师与班级关系信息
   * @return ResultEntity
   */
  ResultEntity delete(List<String> confIds);

  /**
   * 增加教师与班级关系信息
   *
   * @param confTeacherClassList 教师与班级关系信息
   * @return ResultEntity
   */
  ResultEntity insert(List<ConfTeacherClass> confTeacherClassList);

  /**
   * 查询家长与学生关系信息
   *
   * @param confTeacherClass 家长与学生关系信息
   * @return ResultEntity
   */
  ResultEntity list(ConfTeacherClass confTeacherClass);
}