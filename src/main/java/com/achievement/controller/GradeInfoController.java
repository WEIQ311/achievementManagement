package com.achievement.controller;

import com.achievement.entity.GradeInfo;
import com.achievement.service.GradeInfoService;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ObjectInfo;
import com.achievement.vo.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;


/**
 * 年级(GradeInfo)表控制层
 *
 * @author weiQiang
 * @since 2018-10-02 17:51:31
 */
@RestController
@RequestMapping("gradeInfo")
@CrossOrigin
public class GradeInfoController {
  /**
   * Service
   */
  @Autowired
  private GradeInfoService gradeInfoService;

  /**
   * 根据主键集合删除年级(GradeInfo)
   *
   * @param objectInfo 主键集合
   * @return ResultEntity
   */
  @RequestMapping(value = "deleteByIds", method = RequestMethod.POST)
  public ResultEntity delete(@Valid ObjectInfo objectInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return gradeInfoService.delete(objectInfo.getIds());
  }

  /**
   * 删除年级(GradeInfo)
   *
   * @param gradeId 主键
   * @return ResultEntity
   */
  @RequestMapping(path = "{gradeId}", method = RequestMethod.DELETE)
  public ResultEntity delete(@PathVariable String gradeId) {
    return gradeInfoService.delete(new ArrayList<String>() {{
      add(gradeId);
    }});
  }

  /**
   * 增加年级(GradeInfo)
   *
   * @param gradeInfo     插入参数
   * @param bindingResult 参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "insert", method = RequestMethod.POST)
  public ResultEntity insert(@Valid GradeInfo gradeInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return gradeInfoService.insert(new ArrayList<GradeInfo>() {{
      add(gradeInfo);
    }});
  }

  /**
   * 根据条件分页查询
   *
   * @param gradeInfo 查询参数
   * @param pageNum   开始页数    * @param pageSize  每页显示的数据条数
   * @return ResultEntity
   */
  @RequestMapping(value = "/listByPage", method = RequestMethod.GET)
  public ResultEntity list(GradeInfo gradeInfo, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "30") Integer pageSize) {
    return gradeInfoService.list(gradeInfo, pageNum, pageSize);
  }

  /**
   * 根据条件查询
   *
   * @param gradeInfo 查询参数
   * @return ResultEntity
   */
  @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
  public ResultEntity list(GradeInfo gradeInfo) {
    return gradeInfoService.list(gradeInfo);
  }

  /**
   * 更新年级(GradeInfo)
   *
   * @param gradeInfo     更新参数
   * @param bindingResult 参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "update", method = RequestMethod.POST)
  public ResultEntity update(@Valid GradeInfo gradeInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return gradeInfoService.update(new ArrayList<GradeInfo>() {{
      add(gradeInfo);
    }});
  }
}