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
 * 学生
 *
 * @author weiQiang
 * @date 2018/9/26
 */
@Data

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class StudentInfo implements Serializable {
  private static final long serialVersionUID = 4799337404652386960L;
  /**
   * 班级ID
   */
  private String classId;
  /**
   * 班级ID集合
   */
  @JsonIgnore
  private List<String> classIds;
  /**
   * 创建时间
   */
  private Date insertTime;
  /**
   * 监护人信息
   */
  private List<ParentInfo> parentInfoList;
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
   * 主键
   */
  private String studentId;
  /**
   * 学生ID集合
   */
  @JsonIgnore
  private List<String> studentIds;
  /**
   * 学生姓名
   */
  @Length(max = 500, message = "学生姓名不能多于500个字符")
  private String studentName;
  /**
   * 学号
   */
  @Length(max = 500, message = "学生学号不能多于500个字符")
  private String studentNum;
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
