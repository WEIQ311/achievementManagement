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
   * 查询班级最高、平均、最低成绩
   *
   * @param scoreInfo 成绩信息
   * @return List
   */
  List<ScoreInfo> listClassScoreInfo(ScoreInfo scoreInfo);

  /**
   * 班级排名信息
   *
   * @param scoreInfo 成绩信息
   * @return List
   */
  List<ScoreInfo> listClassScoreRanking(ScoreInfo scoreInfo);

  /**
   * 查询年级最高、平均、最低成绩
   *
   * @param scoreInfo 成绩信息
   * @return List
   */
  List<ScoreInfo> listGradeScoreInfo(ScoreInfo scoreInfo);
}