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
  DeleteNoSupport("删除不被允许!"),
  UpdateSuccess("更新成功!"),
  UpdateError("更新失败!"),
  SendSuccess("发送成功!"),
  SendError("发送成功!"),
  BadTel("请输入正确的手机号!"),
  ServerUsed("服务忙，请稍后重试!"),
  PkIdEmpty("主键ID为空!"),
  GradeIdError("班级\"%s\"中的年级\"%s\"不存在!"),
  GradeNameInClass("该年级中存在班级!"),
  GradeNameInUsed("该年级\"\"已经存在!"),
  SchoolNameInUsed("该学校\"\"已经存在!"),
  ClassNameInUsed("该班级中存在学生!"),
  ClassNameInGrade("班级\"%s\"已经在年级\"%s\"中存在!"),
  YearIdError("该学期\"%s\"中的学年信息错误!"),
  YearNameInUsed("该学年\"%s\"已经存在!"),
  YearNameInSemester("该学年中存在学期!"),
  SemesterNameInUsed("该学期\"%s\"在学年\"%s\"中已经存在!"),
  TeacherNumInUsed("教师编码\"%s\"已经被教师\"%s\"使用!"),
  SubjectNameInUsed("学科\"%s\"已经被使用!"),
  ;
  private String message;

  GlobalEnum(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
