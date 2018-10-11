package com.achievement.mapper;

import com.achievement.entity.ConfTeacherSubject;

import java.util.List;

/**
 * (ConfTeacherSubject)表数据库访问层
 *
 * @author 魏强
 * @since 2018-10-05 16:12:21
 */
public interface ConfTeacherSubjectMapper extends BaseMapper<ConfTeacherSubject, String> {
  /**
   * 删除教师下的科目配置
   *
   * @param teacherIds 教师ID集合
   */
  void deleteByTeacherId(List<String> teacherIds);
}