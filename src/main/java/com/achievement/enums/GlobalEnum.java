package com.achievement.enums;

/**
 * 全局枚举信息
 *
 * @author weiQiang
 */

public enum GlobalEnum {
  /**
   * 全局状态信息
   */
  QuerySuccess("查询成功!"),
  QueryError("查询失败!"),
  InsertSuccess("增加成功!"),
  InsertError("增加失败!"),
  DataEmpty("数据为空!"),
  DeleteSuccess("删除成功!"),
  DeleteError("删除失败!"),
  UpdateSuccess("更新成功!"),
  UpdateError("更新失败!"),
  SendSuccess("发送成功!"),
  SendError("发送成功!"),
  BadTel("请输入正确的手机号!"),
  ServerUsed("服务忙，请稍后重试!"),
  IdError("ID为空!");
  private String message;

  GlobalEnum(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
