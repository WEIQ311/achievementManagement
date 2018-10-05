package com.achievement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 学生与家长关系
 *
 * @author weiQiang
 * @date 2018/10/5
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ConfStudentParent implements Serializable {

  /**
   * 主键ID
   */
  private String confId;
  /**
   * 家长ID
   */
  @NotBlank(message = "家长ID不能为空!")
  private String parentId;
  /**
   * 学生ID
   */
  @NotBlank(message = "学生ID不能为空!")
  private String studentId;
}
