package com.achievement.service;

import com.achievement.entity.SemesterInfo;

import java.util.Map;

/**
 * 学期(SemesterInfo)Service
 *
 * @author weiQiang
 * @since 2018-10-02 17:52:27
 */
public interface SemesterInfoService extends BaseInfoService<SemesterInfo, String> {
  /**
   * 学期(SemesterInfo)信息Map
   *
   * @param semesterInfo 查询参数
   * @return ResultEntity
   */
  Map<String, SemesterInfo> convertSemesterNameAndYearIdMap(SemesterInfo semesterInfo);
}