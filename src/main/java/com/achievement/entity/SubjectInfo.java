package com.achievement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 科目
 *
 * @author weiQiang
 * @date 2018/9/26
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SubjectInfo implements Serializable {
  private static final long serialVersionUID = -5906340546002194449L;
  /**
   * 创建时间
   */
  private Date insertTime;
  /**
   * 备注
   */
  @Length(max = 4000, message = "备注不能多于4000个字符")
  private String remark;
  /**
   * 状态
   * 0:不可用;1:可用
   */
  private Integer status;
  /**
   * 主键
   */
  private String subjectId;

  /**
   * 科目IDS
   */
  @JsonIgnore
  private List<String> subjectIds;
  /**
   * 科目名称
   */
  @NotBlank(message = "科目名称不能为空!")
  @Length(max = 300, message = "科目名称不能多于300个字符")
  private String subjectName;
  /**
   * 学科类型
   * 0:通用,
   * 1:文科,
   * 2:理科,
   * 3:艺术
   */
  @NotNull(message = "学科类型不能为空!")
  private Integer subjectType;
  /**
   * 学科类型
   */
  @JsonIgnore
  private List<Integer> subjectTypes;
  /**
   * 更新时间
   */
  private Date updateTime;
}
