package com.achievement.mapper;

import com.achievement.entity.ScoreInfo;

import java.util.List;

/**
 * 成绩(ScoreInfo)表数据库访问层
 *
 * @author weiQiang
 * @since 2018-10-03 14:57:29
 */
public interface ScoreInfoMapper extends BaseMapper<ScoreInfo, String> {

  /**
   * 查询最高、平均、最低成绩
   *
   * @param scoreInfo 成绩信息
   * @return ScoreInfo
   */
  List<ScoreInfo> listScoreInfo(ScoreInfo scoreInfo);
}