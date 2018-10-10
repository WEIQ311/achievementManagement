package com.achievement.mapper;

import com.achievement.entity.ScoreInfo;
import com.achievement.entity.SubjectScoreInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SubjectScoreInfo)表数据库访问层
 *
 * @author 魏强
 * @since 2018-10-10 16:15:15
 */
public interface SubjectScoreInfoMapper extends BaseMapper<SubjectScoreInfo, String> {
  /**
   * 查询班级最高、平均、最低成绩
   *
   * @param subjectScoreInfo 成绩信息
   * @return List
   */
  List<SubjectScoreInfo> listClassScoreInfo(SubjectScoreInfo subjectScoreInfo);

  /**
   * 班级排名信息
   *
   * @param subjectScoreInfo 成绩信息
   * @return List
   */
  List<SubjectScoreInfo> listClassScoreRanking(SubjectScoreInfo subjectScoreInfo);

  /**
   * 查询年级最高、平均、最低成绩
   *
   * @param subjectScoreInfo 成绩信息
   * @return List
   */
  List<SubjectScoreInfo> listGradeScoreInfo(SubjectScoreInfo subjectScoreInfo);

}