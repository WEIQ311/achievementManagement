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
  ImportSuccess("导入成功!"),
  ImportError("导入失败!"),
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
  GradeNameInUsed("该年级\"%s\"已经存在!"),
  SchoolNameInUsed("该学校\"%s\"已经存在!"),
  ClassNameInUsed("该班级中存在学生!"),
  ClassNameInGrade("班级\"%s\"已经在年级\"%s\"中存在!"),
  YearIdError("该学期\"%s\"中的学年信息错误!"),
  YearNameInUsed("该学年\"%s\"已经存在!"),
  YearNameInSemester("该学年中存在学期!"),
  SemesterNameInUsed("该学期\"%s\"在学年\"%s\"中已经存在!"),
  TeacherNumInUsed("教师编码\"%s\"已经被教师\"%s\"使用!"),
  SubjectNameInUsed("学科\"%s\"已经被使用!"),
  ClassIdEmpty("班级ID为空!"),
  StudentIdEmpty("学生ID为空!"),
  SubjectIdEmpty("学科ID为空!"),
  TeacherIdEmpty("教师ID为空!"),
  TeacherHeadInUsed("该班级班主任已经选配,为:\"%s\"!"),
  TeacherNoHead("该教师\"%s\"不具有班主任职责!"),
  SemesterIdEmpty("学期ID为空!"),
  ParentIdEmpty("家长ID为空!"),
  ParentInfoQueryEmpty("家长信息为空!"),
  ParentNameEmpty("家长姓名不能为空!"),
  ParentAndStudentTypeEmpty("家长\"%s\"与学生\"%s\"的关系不能为空!"),
  StudentInfoQueryEmpty("学生信息为空!"),
  ParentNoStudentInfo("该家长下不存在学生信息!"),
  TeacherNoSubject("教师\"%s\"不存在所授科目"),
  TeacherNoClass("教师\"%s\"不存在所授班级"),
  ClassInfoEmpty("班级ID\"%s\"不存在,请检查!"),
  StudentInfoEmpty("学生ID\"%s\"不存在,请检查!"),
  SubjectInfoEmpty("学科ID\"%s\"不存在,请检查!"),
  SubjectNameInfoEmpty("学科\"%s\"不存在,请检查!"),
  TeacherInfoEmpty("教师ID\"%s\"不存在,请检查!"),
  SemesterInfoEmpty("学期ID\"%s\"不存在,请检查!"),
  ParentInfoEmpty("家长ID\"%s\"不存在,请检查!"),
  ScoreInfoNoMatchFirstRow("学科\"%s\"下的第%s行与表头列数不一致,请检查!"),
  ScoreInfoHasInUsed("学生\"%s\"的\"%s\"成绩已经存在!"),
  ImportScoreInfoEmpty("学科\"%s\"下的第%s行成绩为空,请检查!"),
  ImportScoreInfoNoMatch("学科\"%s\"下的第%s行成绩不合法,请检查!"),
  ImportStudentInfoEmpty("学科\"%s\"下的第%s行学生姓名为空,请检查!"),
  ImportStudentInfoNoMatch("学科\"%s\"下的第%s行学生\"%s\"不存在,请检查!"),
  ImportScoreInfoDataEmpty("学科\"%s\"下的数据为空,请检查!"),
  ExceptionMessage("发生了错误:%s"),
  EndDeadlineTime("成绩录入截至时间不能小于当前时间"),
  BeginDeadlineTime("成绩录入开始时间不能大于截至时间"),
  BeginDeadlineExamTime("考试时间不能大于成绩录入开始时间"),
  NoBeginTime("还没有到录入成绩时间(\"%s\")!"),
  NoEndTime("成绩录入时间(\"%s\")已经过!"),
  ;
  private String message;

  GlobalEnum(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
