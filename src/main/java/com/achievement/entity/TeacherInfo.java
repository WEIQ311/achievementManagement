package com.achievement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 老师
 *
 * @author weiQiang
 * @date 2018/9/26
 */
@Data

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class TeacherInfo implements Serializable {
  private static final long serialVersionUID = -5760954050112830182L;
  /**
   * 教师班级信息
   */
  private List<ConfTeacherClass> confTeacherClasses;
  /**
   * 创建时间
   */
  private Date insertTime;
  /**
   * 头像url
   */
  private String phoneUrl;
  /**
   * 备注
   */
  @Length(max = 4000, message = "备注不能多于4000个字符")
  private String remark;
  /**
   * 性别
   */
  @Length(max = 2, min = 2, message = "性别不能多于2个字符")
  private String sex;
  /**
   * 状态
   * 0:不可用;1:可用
   */
  private Integer status;
  /**
   * 科目ID
   */
  private String subjectId;
  /**
   * 学科类型
   */
  private Integer subjectType;
  /**
   * 教师职责
   * 0：管理员
   * 1：班主任
   * 2：教师
   */
  @NotBlank(message = "教师职责不能为空!")
  private String teacherDuty;
  /**
   * 主键
   */
  private String teacherId;
  /**
   * 教师名称
   */
  @Length(max = 500, message = "教师名称不能多于500个字符")
  private String teacherName;
  /**
   * 教师编号
   */
  @Length(max = 500, message = "教师编号不能多于500个字符")
  private String teacherNum;
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
