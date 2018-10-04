package com.achievement.controller;

import com.achievement.entity.TeacherInfo;
import com.achievement.service.TeacherInfoService;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ObjectInfo;
import com.achievement.vo.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;


/**
 * 教师(TeacherInfo)表控制层
 *
 * @author weiQiang
 * @since 2018-10-02 17:52:27
 */
@RestController
@RequestMapping("teacherInfo")
@CrossOrigin
public class TeacherInfoController {
  /**
   * Service
   */
  @Autowired
  private TeacherInfoService teacherInfoService;

  /**
   * 根据主键集合删除教师(TeacherInfo)
   *
   * @param objectInfo 主键集合
   * @return ResultEntity
   */
  @RequestMapping(value = "deleteByIds", method = RequestMethod.POST)
  public ResultEntity delete(@Valid ObjectInfo objectInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return teacherInfoService.delete(objectInfo.getIds());
  }

  /**
   * 删除教师(TeacherInfo)
   *
   * @param teacherId 主键
   * @return ResultEntity
   */
  @RequestMapping(path = "{teacherId}", method = RequestMethod.DELETE)
  public ResultEntity delete(@PathVariable String teacherId) {
    return teacherInfoService.delete(new ArrayList<String>() {{
      add(teacherId);
    }});
  }

  /**
   * 增加教师(TeacherInfo)
   *
   * @param teacherInfo   插入参数
   * @param bindingResult 参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "insert", method = RequestMethod.POST)
  public ResultEntity insert(@Valid TeacherInfo teacherInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return teacherInfoService.insert(new ArrayList<TeacherInfo>() {{
      add(teacherInfo);
    }});
  }

  /**
   * 根据条件分页查询
   *
   * @param teacherInfo 查询参数
   * @param pageNum     开始页数      @param pageSize  每页显示的数据条数
   * @return ResultEntity
   */
  @RequestMapping(value = "/listByPage", method = RequestMethod.GET)
  public ResultEntity list(TeacherInfo teacherInfo, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "30") Integer pageSize) {
    return teacherInfoService.list(teacherInfo, pageNum, pageSize);
  }

  /**
   * 根据条件查询
   *
   * @param teacherInfo 查询参数
   * @return ResultEntity
   */
  @RequestMapping(value = "list", method = RequestMethod.GET)
  public ResultEntity list(TeacherInfo teacherInfo) {
    return teacherInfoService.list(teacherInfo);
  }

  /**
   * 更新教师(TeacherInfo)
   *
   * @param teacherInfo   更新参数
   * @param bindingResult 参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "update", method = RequestMethod.POST)
  public ResultEntity update(@Valid TeacherInfo teacherInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return teacherInfoService.update(new ArrayList<TeacherInfo>() {{
      add(teacherInfo);
    }});
  }
}