package com.achievement.controller;

import com.achievement.entity.ClassInfo;
import com.achievement.service.ClassInfoService;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ObjectInfo;
import com.achievement.vo.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * (ClassInfo)表控制层
 *
 * @author weiqiang
 * @since 2018-10-02 15:20:56
 */
@RestController
@RequestMapping("classInfo")
@CrossOrigin
public class ClassInfoController {

  /**
   * 班级Service
   */
  @Autowired
  private ClassInfoService classInfoService;

  /**
   * 根据主键集合删除班级
   *
   * @param objectInfo 主键集合
   * @return ResultEntity
   */
  @RequestMapping(value = "deleteByIds", method = RequestMethod.POST)
  public ResultEntity delete(@Valid ObjectInfo objectInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return classInfoService.delete(objectInfo.getIds());
  }

  /**
   * 删除班级
   *
   * @param markId
   * @return ResultEntity
   */
  @RequestMapping(path = "{markId}", method = RequestMethod.DELETE)
  public ResultEntity delete(@PathVariable String markId) {
    return classInfoService.delete(new ArrayList<String>() {{
      add(markId);
    }});
  }

  /**
   * 增加班级
   *
   * @param classInfo     插入参数
   * @param bindingResult 参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "insert", method = RequestMethod.POST)
  public ResultEntity insert(@Valid ClassInfo classInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return classInfoService.insert(new ArrayList<ClassInfo>() {{
      add(classInfo);
    }});
  }

  /**
   * 根据条件分页查询班级
   *
   * @param classInfo 查询参数
   * @param pageNum   开始页数    * @param pageSize  每页显示的数据条数
   * @return ResultEntity
   */
  @RequestMapping(value = "/listByPage", method = RequestMethod.GET)
  public ResultEntity list(ClassInfo classInfo, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "30") Integer pageSize) {
    return classInfoService.list(classInfo, pageNum, pageSize);
  }

  /**
   * 根据条件查询班级
   *
   * @param classInfo 查询参数
   * @return ResultEntity
   */
  @RequestMapping(value = "list", method = RequestMethod.GET)
  public ResultEntity list(ClassInfo classInfo) {
    return classInfoService.list(classInfo);
  }

  /**
   * 更新班级
   *
   * @param classInfo     更新参数
   * @param bindingResult 参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "update", method = RequestMethod.POST)
  public ResultEntity update(@Valid ClassInfo classInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return classInfoService.update(new ArrayList<ClassInfo>() {{
      add(classInfo);
    }});
  }

}