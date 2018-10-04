package com.achievement.vo;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author weiQiang
 * @date 2018/3/24
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@Builder
public class ResultEntity implements Serializable {

  private static final long serialVersionUID = -5159330866402406443L;
  /**
   * 返回结果
   */
  private List<?> data;
  /**
   * 返回信息
   */
  private String message;
  /**
   * 是否分页
   */
  private boolean pageable;
  /**
   * 返回成功与否
   */
  private boolean success;
  /**
   * 数据条数
   */
  @Builder.Default
  private Long total = 0L;
  /**
   * 耗时
   */
  @Builder.Default
  private Long totalTime = 0L;

}
