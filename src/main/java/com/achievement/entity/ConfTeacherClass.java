package com.achievement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * 教师班级信息
 *
 * @author weiQiang
 * @date 2018/10/5
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ConfTeacherClass implements Serializable {
  private static final long serialVersionUID = 6699933994382694932L;
  /**
   * 班级ID
   */
  @NotBlank(message = "班级ID不能为空!")
  private String classId;
  /**
   * 班级类型
   */
  private String classType;
  /**
   * 主键ID
   */
  private String confId;
  /**
   * 学科ID
   */
  private String subjectId;
  /**
   * 科目名称
   */
  private String subjectName;
  /**
   * 教师职责
   */
  @NotBlank(message = "教师职责不能为空!")
  private String teacherDuty;
  /**
   * 教师ID
   */
  @NotBlank(message = "教师ID不能为空!")
  private String teacherId;
  /**
   * 教师集合
   */
  private List<TeacherInfo> teacherInfos;
  /**
   * 教师名称
   */
  private String teacherName;
}
