package com.achievement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * 学科成绩表
 *
 * @author weiQiang
 * @date 2018/10/10
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SubjectScoreInfo implements Serializable {
  private static final long serialVersionUID = -3431996646555158237L;

  /**
   * 主键
   */
  private String id;

  /**
   * 学年ID
   */
  private String yearId;

  /**
   * 学期ID
   */
  private String semesterId;

  /**
   * 年级ID
   */
  private String gradeId;

  /**
   * 班级ID
   */
  private String classId;

  /**
   * 班级类型
   */
  private String classType;

  /**
   * 学生ID
   */
  private String studentId;

  /**
   * 年级排名
   */
  private Integer gradeRanking;

  /**
   * 班级排名
   */
  private Integer classRanking;

  /**
   * 语文
   */
  private Double subLanguage;

  /**
   * 数学
   */
  private Double subMathematics;

  /**
   * 英语
   */
  private Double subEnglish;

  /**
   * 历史
   */
  private Double subHistory;

  /**
   * 地理
   */
  private Double subGeography;

  /**
   * 生物
   */
  private Double subBiological;

  /**
   * 化学
   */
  private Double subChemistry;

  /**
   * 物理
   */
  private Double subPhysical;

  /**
   * 政治
   */
  private Double subPolitical;

  /**
   * 计算机
   */
  private Double subComputer;

  /**
   * 体育
   */
  private Double subSports;

  /**
   * 美术
   */
  private Double subArt;

  /**
   * 音乐
   */
  private Double subMusic;

}
