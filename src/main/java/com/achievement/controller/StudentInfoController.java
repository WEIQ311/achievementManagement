package com.achievement.controller;

import com.achievement.entity.StudentInfo;
import com.achievement.service.StudentInfoService;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ObjectInfo;
import com.achievement.vo.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;


/**
 * 学生(StudentInfo)表控制层
 *
 * @author weiQiang
 * @since 2018-10-02 17:52:27
 */
@RestController
@RequestMapping("studentInfo")
@CrossOrigin
public class StudentInfoController {
  /**
   * Service
   */
  @Autowired
  private StudentInfoService studentInfoService;

  /**
   * 根据主键集合删除学生(StudentInfo)
   *
   * @param objectInfo 主键集合
   * @return ResultEntity
   */
  @RequestMapping(value = "deleteByIds", method = RequestMethod.POST)
  public ResultEntity delete(@Valid ObjectInfo objectInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return studentInfoService.delete(objectInfo.getIds());
  }

  /**
   * 删除学生(StudentInfo)
   *
   * @param studentId 主键
   * @return ResultEntity
   */
  @RequestMapping(path = "{studentId}", method = RequestMethod.DELETE)
  public ResultEntity delete(@PathVariable String studentId) {
    return studentInfoService.delete(new ArrayList<String>() {{
      add(studentId);
    }});
  }

  /**
   * 增加学生(StudentInfo)
   *
   * @param studentInfo   插入参数
   * @param bindingResult 参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "insert", method = RequestMethod.POST)
  public ResultEntity insert(@Valid StudentInfo studentInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return studentInfoService.insert(new ArrayList<StudentInfo>() {{
      add(studentInfo);
    }});
  }

  /**
   * 根据条件分页查询
   *
   * @param studentInfo 查询参数
   * @param pageNum     开始页数      @param pageSize  每页显示的数据条数
   * @return ResultEntity
   */
  @RequestMapping(value = "/listByPage", method = RequestMethod.GET)
  public ResultEntity list(StudentInfo studentInfo, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "30") Integer pageSize) {
    return studentInfoService.list(studentInfo, pageNum, pageSize);
  }

  /**
   * 根据条件查询
   *
   * @param studentInfo 查询参数
   * @return ResultEntity
   */
  @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
  public ResultEntity list(StudentInfo studentInfo) {
    return studentInfoService.list(studentInfo);
  }

  /**
   * 更新学生(StudentInfo)
   *
   * @param studentInfo   更新参数
   * @param bindingResult 参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "update", method = RequestMethod.POST)
  public ResultEntity update(@Valid StudentInfo studentInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return studentInfoService.update(new ArrayList<StudentInfo>() {{
      add(studentInfo);
    }});
  }
}