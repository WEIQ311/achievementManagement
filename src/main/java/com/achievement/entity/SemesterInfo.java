package com.achievement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 学期
 *
 * @author weiQiang
 * @date 2018/10/2
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SemesterInfo implements Serializable {
  private static final long serialVersionUID = -63253345008351707L;
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
   * 主键
   */
  private String semesterId;
  /**
   * 学期名称
   */
  @Length(max = 500, message = "学期名称不能多于4000个字符")
  private String semesterName;
  /**
   * 状态
   * 0:不可用;1:可用
   */
  private Integer status;
  /**
   * 更新时间
   */
  private Date updateTime;
  /**
   * 学年ID
   */
  private String yearId;
  /**
   * 学年ID集合
   */
  @JsonIgnore
  private List<String> yearIds;
  /**
   * 学年信息
   */
  private YearInfo yearInfo;
}
