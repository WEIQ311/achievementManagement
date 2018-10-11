package com.achievement.service;

import com.achievement.entity.SubjectScoreInfo;
import com.achievement.entity.TeacherInfo;
import com.achievement.vo.ParentStudentScore;
import com.achievement.vo.ResultEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * (SubjectScoreInfo)表服务接口
 *
 * @author 魏强
 * @since 2018-10-10 16:15:15
 */
public interface SubjectScoreInfoService extends BaseInfoService<SubjectScoreInfo, String> {

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
   * @param scoreFile        成绩文件
   * @param subjectScoreInfo 成绩信息
   * @return ResultEntity
   */
  ResultEntity importScoreByFile(MultipartFile scoreFile, SubjectScoreInfo subjectScoreInfo);

  /**
   * 班级成绩信息
   *
   * @param scoreInfo 成绩信息
   * @return ResultEntity
   */
  ResultEntity listByClass(SubjectScoreInfo scoreInfo);

  /**
   * 家长查询学生成绩
   *
   * @param parentStudentScore 查询成绩信息
   * @return ResultEntity
   */
  ResultEntity listByParent(ParentStudentScore parentStudentScore);

  /**
   * 排名信息
   *
   * @param scoreInfo 成绩信息
   * @return ResultEntity
   */
  Map<String, SubjectScoreInfo> listRankingMap(SubjectScoreInfo scoreInfo);

  /**
   * 更新总成绩
   *
   * @param subjectScoreInfo 成绩信息
   * @return ResultEntity
   */
  ResultEntity updateSubjectScoreSum(SubjectScoreInfo subjectScoreInfo);
}