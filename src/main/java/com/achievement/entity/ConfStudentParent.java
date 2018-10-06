package com.achievement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

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
   * 关系类型
   */
  private String connectionType;
  /**
   * 家长ID
   */
  @NotBlank(message = "家长ID不能为空!")
  private String parentId;
  /**
   * 家长ID集合
   */
  @JsonIgnore
  private List<String> parentIds;
  /**
   * 学生ID
   */
  @NotBlank(message = "学生ID不能为空!")
  private String studentId;
  /**
   * 学生ID集合
   */
  @JsonIgnore
  private List<String> studentIds;

}
