package com.achievement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 教师科目信息
 *
 * @author weiQiang
 * @date 2018/10/5
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ConfTeacherSubject implements Serializable {
  private static final long serialVersionUID = 6699933994382694932L;

  /**
   * 主键ID
   */
  private String confId;
  /**
   * 学科ID
   */
  @NotBlank(message = "学科ID不能为空!")
  private String subjectId;
  /**
   * 教师ID
   */
  @NotBlank(message = "教师ID不能为空!")
  private String teacherId;
}
