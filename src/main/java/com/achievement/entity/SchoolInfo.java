package com.achievement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 学校
 *
 * @author weiQiang
 * @date 2018/9/23
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SchoolInfo implements Serializable {
  private static final long serialVersionUID = -3530316153629631995L;
  /**
   * 创建时间
   */
  private Date insertTime;
  /**
   * 名称
   */
  @NotBlank(message = "学校名称不能为空!")
  @Length(max = 300, message = "学校名称不能多于300个字符")
  private String name;
  /**
   * 备注
   */
  @Length(max = 4000, message = "备注不能多于4000个字符")
  private String remark;
  /**
   * 主键
   */

  private String scId;
  /**
   * 状态
   * 0:不可用;1:可用
   */
  private Integer status;
  /**
   * 更新时间
   */
  private Date updateTime;
}
