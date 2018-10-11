package com.achievement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
  @NotBlank(message = "学期ID不能为空")
  private String semesterId;

  /**
   * 学期名称
   */
  private String semesterName;

  /**
   * 年级ID
   */
  private String gradeId;

  /**
   * 班级ID
   */
  private String classId;

  /**
   * 班级名称
   */
  private String className;

  /**
   * 班级类型
   */
  private String classType;

  /**
   * 学生ID
   */
  private String studentId;

  /**
   * 教师ID
   */
  private String teacherId;

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

  /**
   * 最高成绩
   */
  private Double maxScore;
  /**
   * 最低成绩
   */
  private Double minScore;
  /**
   * 平均成绩
   */
  private Double avgScore;

  /**
   * 年级平均成绩
   */
  private Double gradeAvgScore;
  /**
   * 年级最高成绩
   */
  private Double gradeMaxScore;
  /**
   * 年级最低成绩
   */
  private Double gradeMinScore;
  /**
   * 年级名称
   */
  private String gradeName;
  /**
   * 年级名称
   */
  private String gradeClassName;
  /**
   * 总成绩
   */
  private Double subScoreSum;
  /**
   * 学生学号
   */
  private String studentNum;
  /**
   * 学生姓名
   */
  private String studentName;
  /**
   * 科目ID
   */
  @JsonIgnore
  private String subjectId;
  /**
   * 成绩
   */
  @JsonIgnore
  private Double scoreNumber;
  /**
   * 排序字段
   */
  @JsonIgnore
  private String orderColumn;
  /**
   * 排序方向
   */
  @JsonIgnore
  private String orderDirection;
  /**
   * 学生ID集合
   */
  @JsonIgnore
  private List<String> studentIds;
  /**
   * 添加时间
   */
  private Date insertTime;

  /**
   * 更新时间
   */
  private Date updateTime;

}
