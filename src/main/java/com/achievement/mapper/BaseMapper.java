package com.achievement.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.List;

/**
 * @author weiQiang
 * @date 2018/10/3
 */
@Mapper
public interface BaseMapper<T, E extends Serializable> {
  /**
   * 删除对象
   *
   * @param records 对象信息
   */
  void delete(List<E> records);

  /**
   * 增加对象
   *
   * @param records 对象信息
   * @return Integer
   */
  Integer insert(List<T> records);

  /**
   * 查询对象
   *
   * @param record 对象信息
   * @return List<T>
   */
  List<T> list(T record);

  /**
   * 更新对象
   *
   * @param records 对象信息
   * @return Integer
   */
  Integer update(List<T> records);
}
