package com.achievement.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 更新用户密码用户对象
 *
 * @author weiQiang
 * @date 2018/10/12
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ScoreUser implements Serializable {
  private static final long serialVersionUID = -2930406938495410966L;
  /**
   * 新密码
   */
  @NotBlank(message = "新密码不能为空!")
  private String newPassword;
  /**
   * 旧密码
   */
  @NotBlank(message = "旧密码不能为空!")
  private String oldPassword;
  /**
   * 用户主键
   */
  @NotBlank(message = "用户主键不能为空!")
  private String userId;
}
