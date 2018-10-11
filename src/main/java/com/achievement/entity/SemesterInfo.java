package com.achievement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

import static com.achievement.constants.GlobalConstants.DATE_TIME_FORMAT;

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
   * 考试时间
   */
  @NotNull(message = "考试时间不能为空")
  @JsonFormat(pattern = DATE_TIME_FORMAT)
  private Date examTime;

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
   * 录入成绩开始时间
   */
  @NotNull(message = "录入成绩开始时间不能为空")
  @JsonFormat(pattern = DATE_TIME_FORMAT)
  private Date scoreBeginDeadline;

  /**
   * 录入成绩截至时间
   */
  @NotNull(message = "录入成绩截至时间不能为空")
  @JsonFormat(pattern = DATE_TIME_FORMAT)
  private Date scoreEndDeadline;

  /**
   * 主键
   */
  private String semesterId;

  /**
   * 学期名称
   */
  @Length(max = 500, message = "学期名称不能多于500个字符")
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

  /**
   * 年级ID
   */
  @NotBlank(message = "年级ID不能为空")
  private String gradeId;

  /**
   * 年级名称
   */
  private String gradeName;
}
