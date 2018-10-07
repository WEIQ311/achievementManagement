package com.achievement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 成绩
 *
 * @author weiQiang
 * @date 2018/9/26
 */
@Data

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ScoreInfo implements Serializable {
  private static final long serialVersionUID = 311146311988636636L;
  /**
   * 班级ID
   */
  @NotBlank(message = "班级ID不能为空!")
  private String classId;
  /**
   * 班级名称
   */
  private String className;
  /**
   * 年级名称
   */
  private String gradeName;
  /**
   * 创建时间
   */
  private Date insertTime;
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
   * 备注
   */
  @Length(max = 4000, message = "备注不能多于4000个字符")
  private String remark;
  /**
   * 主键
   */
  private String scoreId;
  /**
   * 成绩
   */
  @NotBlank(message = "成绩不能为空!")
  @Length(max = 300, message = "成绩不能多于300个字符")
  private Double scoreNumber;
  /**
   * 学期ID
   */
  @NotBlank(message = "学期ID不能为空!")
  private String semesterId;
  /**
   * 学期名称
   */
  private String semesterName;
  /**
   * 状态
   * 0:不可用;1:可用
   */
  private Integer status;
  /**
   * 学生ID
   */
  @NotBlank(message = "学生ID不能为空!")
  private String studentId;
  /**
   * 学生姓名
   */
  private String studentName;
  /**
   * 科目ID
   */
  @NotBlank(message = "科目ID不能为空!")
  private String subjectId;
  /**
   * 科目名称
   */
  private String subjectName;
  /**
   * 教师ID
   */
  @NotBlank(message = "教师ID不能为空!")
  private String teacherId;
  /**
   * 教师姓名
   */
  private String teacherName;
  /**
   * 更新时间
   */
  private Date updateTime;
  /**
   * 学年名称
   */
  private String yearName;

}
