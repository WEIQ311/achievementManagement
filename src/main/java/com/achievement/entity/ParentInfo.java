package com.achievement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;

/**
 * 家长信息
 *
 * @author weiQiang
 * @date 2018/10/2
 */
@Data

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ParentInfo implements Serializable {
  private static final long serialVersionUID = 4557366128335272616L;

  /**
   * 创建时间
   */
  private Date insertTime;
  /**
   * 家长ID
   */

  private String parentId;
  /**
   * 家长名称
   */
  @Length(max = 500, message = "家长名称不能多于4000个字符")
  private String parentName;
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
   * 电话号码
   */
  @Length(max = 12, message = "电话号码不能多于12个字符")
  private String telPhone;
  /**
   * QQ
   */
  private String txQq;
  /**
   * 微信
   */
  private String txWx;
  /**
   * 更新时间
   */
  private Date updateTime;
  /**
   * 微博
   */
  private String xlWb;
}
