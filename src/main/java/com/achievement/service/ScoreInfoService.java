package com.achievement.service;

import com.achievement.entity.ScoreInfo;
import com.achievement.entity.TeacherInfo;
import com.achievement.vo.ParentStudentScore;
import com.achievement.vo.ResultEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 成绩(ScoreInfo)Service
 *
 * @author weiQiang
 * @since 2018-10-02 17:52:27
 */
public interface ScoreInfoService extends BaseInfoService<ScoreInfo, String> {
  /**
   * 导出当前教师所带班级科目的学生成绩模板
   *
   * @param teacherInfo 教师信息
   * @param response    响应
   */
  void exportScoreTemplate(TeacherInfo teacherInfo, HttpServletResponse response);

  /**
   * 上传学生成绩
   *
   * @param scoreFile 成绩文件
   * @param scoreInfo 成绩信息
   * @return ResultEntity
   */
  ResultEntity importScoreByFile(MultipartFile scoreFile, ScoreInfo scoreInfo);

  /**
   * 家长查询学生成绩
   *
   * @param parentStudentScore 查询成绩信息
   * @return ResultEntity
   */
  ResultEntity listByParent(ParentStudentScore parentStudentScore);

  /**
   * 班级最高、平均、最低成绩
   *
   * @param scoreInfo 查询成绩信息
   * @return ResultEntity
   */
  ResultEntity listClassScore(ScoreInfo scoreInfo);

  /**
   * 年级最高、平均、最低成绩
   *
   * @param scoreInfo 查询成绩信息
   * @return ResultEntity
   */
  ResultEntity listGradeScore(ScoreInfo scoreInfo);
}