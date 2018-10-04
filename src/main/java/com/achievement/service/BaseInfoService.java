package com.achievement.service;

import com.achievement.vo.ResultEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 基础服务接口
 *
 * @author weiQiang
 * @since 2018-10-02 15:20:56
 */
public interface BaseInfoService<T, E extends Serializable> {

  /**
   * 对象信息Map
   *
   * @param record 查询参数
   * @return Map
   */
  Map<E, T> convertRecordToMap(T record);

  /**
   * 删除对象
   *
   * @param pkIds 对象主键集合
   * @return ResultEntity
   */
  ResultEntity delete(List<E> pkIds);

  /**
   * 增加对象
   *
   * @param records 对象参数
   * @return ResultEntity
   */
  ResultEntity insert(List<T> records);

  /**
   * 根据条件分页查询对象
   *
   * @param record   查询参数
   * @param pageNum  开始页数
   * @param pageSize 每页显示的数据条数
   * @return ResultEntity
   */
  ResultEntity list(T record, int pageNum, int pageSize);

  /**
   * 根据条件查询对象
   *
   * @param record 查询参数
   * @return ResultEntity
   */
  ResultEntity list(T record);

  /**
   * 更新对象
   *
   * @param records 更新参数
   * @return ResultEntity
   */
  ResultEntity update(List<T> records);

}