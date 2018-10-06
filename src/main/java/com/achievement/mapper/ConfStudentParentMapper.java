package com.achievement.mapper;

import com.achievement.entity.ConfStudentParent;

import java.util.List;

/**
 * (ConfStudentParent)表数据库访问层
 *
 * @author 魏强
 * @since 2018-10-05 16:12:21
 */
public interface ConfStudentParentMapper extends BaseMapper<ConfStudentParent, String> {

  /**
   * 删除学生与家长关系信息
   *
   * @param parentIds 家长ID集合
   */
  void deleteByParentId(List<String> parentIds);

  /**
   * 删除学生与家长关系信息
   *
   * @param studentIds 学生ID集合
   * @return ResultEntity
   */
  void deleteByStudentId(List<String> studentIds);
}