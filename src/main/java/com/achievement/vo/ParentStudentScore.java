package com.achievement.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * 家长查询学生成绩信息
 *
 * @author weiQiang
 * @date 2018/10/7
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ParentStudentScore implements Serializable {
  private static final long serialVersionUID = -3208671898516365269L;
  /**
   * 班级ID
   */
  private String classId;
  /**
   * 班级名称
   */
  private String className;
  /**
   * 年级ID
   */
  private String gradeId;
  /**
   * 年级名称
   */
  private String gradeName;
  /**
   * 家长ID
   */
  private String parentId;
  /**
   * 家长姓名
   */
  private String parentName;
  /**
   * 学期ID
   */
  private String semesterId;
  /**
   * 学期名称
   */
  private String semesterName;
  /**
   * 学生ID
   */
  private String studentId;
  /**
   * 学生名称
   */
  private String studentName;
  /**
   * 科目ID
   */
  private String subjectId;
  /**
   * 科目名称
   */
  private String subjectName;
  /**
   * 学年ID
   */
  private String yearId;
  /**
   * 学年名称
   */
  private String yearName;
}
