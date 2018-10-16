package com.achievement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 成绩管理中心用户信息
 *
 * @author weiQiang
 * @date 2018/10/12
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ScoreUserInfo implements Serializable {

  private static final long serialVersionUID = 3778777049882402948L;
  /**
   * 首次登陆时间
   */
  private Date firstLogin;
  /**
   * 添加时间
   */
  private Date insertTime;
  /**
   * 登陆ip
   */
  private String ipAddress;
  /**
   * 登陆次数
   */
  private Integer loginCount;
  /**
   * 用户登陆名称
   */
  @NotBlank(message = "用户登陆名称不能为空!")
  private String loginName;
  /**
   * 登陆密码
   */
  @NotBlank(message = "登陆密码不能为空!")
  private String password;
  /**
   * 状态
   * 0：不可用
   * 1：可用
   */
  private Integer status;
  /**
   * 更新时间
   */
  private Date updateTime;
  /**
   * 用户主键
   */
  private String userId;
  /**
   * 用户类型
   * 0：管理员
   * 1：班主任
   * 2：教师
   * 3：学生
   * 4：家长
   * 5：其他
   */
  @NotBlank(message = "用户角色不能为空!")
  private String userType;
}
