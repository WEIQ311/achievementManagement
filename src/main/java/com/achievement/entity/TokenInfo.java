package com.achievement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * token信息
 *
 * @author weiQiang
 * @date 2018/10/12
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class TokenInfo implements Serializable {
  private static final long serialVersionUID = 4855503467585352933L;
  /**
   * token过期时间
   */
  private Date expiration;
  /**
   * token主键
   */
  private String id;
  /**
   * token产生时间
   */
  private Date issuedAt;
  /**
   * token拥有者
   */
  private String issuer;
  /**
   * token内容
   */
  private String subject;
  /**
   * 校验token状态
   */
  private boolean success;
  /**
   * 校验token类型
   */
  private String token;
  /**
   * 校验token类型
   */
  private String tokenType;
}
