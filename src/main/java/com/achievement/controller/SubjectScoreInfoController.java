package com.achievement.controller;

import com.achievement.entity.SubjectScoreInfo;
import com.achievement.entity.TeacherInfo;
import com.achievement.service.SubjectScoreInfoService;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ObjectInfo;
import com.achievement.vo.ParentStudentScore;
import com.achievement.vo.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;

/**
 * (SubjectScoreInfo)表控制层
 *
 * @author 魏强
 * @since 2018-10-10 16:15:35
 */
@RestController
@RequestMapping("scoreInfo")
@CrossOrigin
public class SubjectScoreInfoController {
  /**
   * 服务对象
   */
  @Autowired
  private SubjectScoreInfoService subjectScoreInfoService;

  /**
   * 根据主键集合删除成绩(SubjectScoreInfo)
   *
   * @param objectInfo 主键集合
   * @return ResultEntity
   */
  @RequestMapping(value = "deleteByIds", method = RequestMethod.POST)
  public ResultEntity delete(@Valid @RequestBody ObjectInfo objectInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return subjectScoreInfoService.delete(objectInfo.getIds());
  }

  /**
   * 删除成绩(SubjectScoreInfo)
   *
   * @param scoreId 主键
   * @return ResultEntity
   */
  @RequestMapping(path = "{scoreId}", method = RequestMethod.DELETE)
  public ResultEntity delete(@PathVariable String scoreId) {
    return subjectScoreInfoService.delete(new ArrayList<String>() {{
      add(scoreId);
    }});
  }

  /**
   * 导出当前教师所带班级科目的学生成绩模板
   *
   * @param teacherInfo 教师信息
   * @param response    响应
   */
  @RequestMapping(value = "exportScoreTemplate", method = RequestMethod.GET)
  public void exportScoreTemplate(TeacherInfo teacherInfo, HttpServletResponse response) {
    subjectScoreInfoService.exportScoreTemplate(teacherInfo, response);
  }

  /**
   * 上传学生成绩
   *
   * @param scoreFile 成绩文件
   * @param subjectScoreInfo 成绩信息
   * @return ResultEntity
   */
  @RequestMapping(value = "importScore", method = RequestMethod.POST)
  public ResultEntity importScoreByFile(MultipartFile scoreFile, SubjectScoreInfo subjectScoreInfo) {
    return subjectScoreInfoService.importScoreByFile(scoreFile, subjectScoreInfo);
  }

  /**
   * 增加成绩(SubjectScoreInfo)
   *
   * @param subjectScoreInfo 插入参数
   * @param bindingResult    参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "insert", method = RequestMethod.POST)
  public ResultEntity insert(@Valid @RequestBody SubjectScoreInfo subjectScoreInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return subjectScoreInfoService.insert(new ArrayList<SubjectScoreInfo>() {{
      add(subjectScoreInfo);
    }});
  }

  /**
   * 根据条件查询
   *
   * @param subjectScoreInfo 查询参数
   * @return ResultEntity
   */
  @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
  public ResultEntity list(SubjectScoreInfo subjectScoreInfo) {
    return subjectScoreInfoService.list(subjectScoreInfo);
  }

  /**
   * 根据条件分页查询
   *
   * @param subjectScoreInfo 查询参数
   * @param pageNum          开始页数
   * @param pageSize         每页显示的数据条数
   * @return ResultEntity
   */
  @RequestMapping(value = "/listByPage", method = RequestMethod.GET)
  public ResultEntity list(SubjectScoreInfo subjectScoreInfo, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "30") Integer pageSize) {
    return subjectScoreInfoService.list(subjectScoreInfo, pageNum, pageSize);
  }

  /**
   * 班级成绩信息
   *
   * @param subjectScoreInfo 成绩信息
   * @return ResultEntity
   */
  @RequestMapping(value = "listByClass", method = RequestMethod.GET)
  public ResultEntity listByClass(SubjectScoreInfo subjectScoreInfo) {
    return subjectScoreInfoService.listByClass(subjectScoreInfo);
  }

  /**
   * 家长查询学生成绩
   *
   * @param parentStudentScore 查询成绩信息
   * @return ResultEntity
   */
  @RequestMapping(value = "listByParent", method = RequestMethod.GET)
  public ResultEntity listByParent(ParentStudentScore parentStudentScore) {
    return subjectScoreInfoService.listByParent(parentStudentScore);
  }

  /**
   * 更新成绩(SubjectScoreInfo)
   *
   * @param subjectScoreInfo 更新参数
   * @param bindingResult    参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "update", method = RequestMethod.POST)
  public ResultEntity update(@Valid @RequestBody SubjectScoreInfo subjectScoreInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return subjectScoreInfoService.update(new ArrayList<SubjectScoreInfo>() {{
      add(subjectScoreInfo);
    }});
  }
}