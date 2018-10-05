package com.achievement.service;

import com.achievement.entity.ConfStudentParent;
import com.achievement.vo.ResultEntity;

import java.util.List;
import java.util.Map;

/**
 * (ConfStudentParent)表服务接口
 *
 * @author 魏强
 * @since 2018-10-05 16:08:49
 */
public interface ConfStudentParentService {

  /**
   * 家长与学生关系信息Map
   *
   * @param confStudentParent 家长与学生关系信息
   * @return Map
   */
  Map<String, List<ConfStudentParent>> convertParentOfStudentMap(ConfStudentParent confStudentParent);

  /**
   * 学生与家长关系信息Map
   *
   * @param confStudentParent 学生与家长关系信息
   * @return Map
   */
  Map<String, List<ConfStudentParent>> convertStudentOfParentMap(ConfStudentParent confStudentParent);

  /**
   * 删除学生与家长关系信息
   *
   * @param confStudentParents 学生与家长关系信息
   * @return ResultEntity
   */
  ResultEntity delete(List<ConfStudentParent> confStudentParents);

  /**
   * 增加学生与家长关系信息
   *
   * @param confStudentParents 学生与家长关系信息
   * @return ResultEntity
   */
  ResultEntity insert(List<ConfStudentParent> confStudentParents);

  /**
   * 查询家长与学生关系信息
   *
   * @param confStudentParent 家长与学生关系信息
   * @return ResultEntity
   */
  ResultEntity list(ConfStudentParent confStudentParent);
}