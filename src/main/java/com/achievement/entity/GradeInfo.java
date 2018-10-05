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
 * 年级
 *
 * @author weiQiang
 * @date 2018/9/26
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class GradeInfo implements Serializable {
  private static final long serialVersionUID = 1377889700988578315L;

  /**
   * 主键
   */
  private String gradeId;

  /**
   * 年级名称
   */
  @NotBlank(message = "年级名称不能为空!")
  @Length(max = 500, message = "年级不能多于500个字符")
  private String gradeName;
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
   * 更新时间
   */
  private Date updateTime;
}
