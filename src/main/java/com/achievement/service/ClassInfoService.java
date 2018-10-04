package com.achievement.service;

import com.achievement.entity.ClassInfo;

import java.util.Map;

/**
 * (ClassInfo)表服务接口
 *
 * @author weiQiang
 * @since 2018-10-02 15:20:56
 */
public interface ClassInfoService extends BaseInfoService<ClassInfo, String> {

  /**
   * 班级信息Map
   *
   * @param classInfo
   * @return Map
   */
  Map<String, ClassInfo> convertClassNameAndGradeIdMap(ClassInfo classInfo);
}