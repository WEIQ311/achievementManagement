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
   * 配置班级科目信息
   *
   * @param confTeacherClass 班级科目信息
   * @return ResultEntity
   */
  ResultEntity confTeacherSubject(ConfTeacherClass confTeacherClass);

  /**
   * 班级班主任
   *
   * @param confTeacherClass 班级与教师关系信息
   * @return Map
   */
  Map<String, ConfTeacherClass> convertClassIdAdminMap(ConfTeacherClass confTeacherClass);

  /**
   * 教师与班级关系信息Map
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
   * 增加或编辑班级班主任
   *
   * @param confTeacherClasses 教师与班级关系信息
   * @return ResultEntity
   */
  ResultEntity insertOrUpdateClassHeadTeacher(List<ConfTeacherClass> confTeacherClasses);

  /**
   * 查询教师与班级关系信息
   *
   * @param confTeacherClass 教师与班级关系信息
   * @return ResultEntity
   */
  ResultEntity list(ConfTeacherClass confTeacherClass);

  /**
   * 更新教师与班级关系信息
   *
   * @param confTeacherClassList 教师与班级关系信息
   * @return ResultEntity
   */
  ResultEntity update(List<ConfTeacherClass> confTeacherClassList);
}