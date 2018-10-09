package com.achievement.controller;

import com.achievement.entity.SubjectInfo;
import com.achievement.service.SubjectInfoService;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ObjectInfo;
import com.achievement.vo.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


/**
 * 学科(SubjectInfo)表控制层
 *
 * @author weiQiang
 * @since 2018-10-02 17:52:27
 */
@RestController
@RequestMapping("subjectInfo")
@CrossOrigin
public class SubjectInfoController {
  /**
   * Service
   */
  @Autowired
  private SubjectInfoService subjectInfoService;

  /**
   * 根据主键集合删除学科(SubjectInfo)
   *
   * @param objectInfo 主键集合
   * @return ResultEntity
   */
  @RequestMapping(value = "deleteByIds", method = RequestMethod.POST)
  public ResultEntity delete(@Valid @RequestBody ObjectInfo objectInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return subjectInfoService.delete(objectInfo.getIds());
  }

  /**
   * 删除学科(SubjectInfo)
   *
   * @param subjectId 主键
   * @return ResultEntity
   */
  @RequestMapping(path = "{subjectId}", method = RequestMethod.DELETE)
  public ResultEntity delete(@PathVariable String subjectId) {
    return subjectInfoService.delete(new ArrayList<String>() {{
      add(subjectId);
    }});
  }

  /**
   * 增加学科(SubjectInfo)
   *
   * @param subjectInfo   插入参数
   * @param bindingResult 参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "insert", method = RequestMethod.POST)
  public ResultEntity insert(@Valid @RequestBody SubjectInfo subjectInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return subjectInfoService.insert(new ArrayList<SubjectInfo>() {{
      add(subjectInfo);
    }});
  }

  /**
   * 根据条件分页查询
   *
   * @param subjectInfo 查询参数
   * @param pageNum     开始页数      @param pageSize  每页显示的数据条数
   * @return ResultEntity
   */
  @RequestMapping(value = "/listByPage", method = RequestMethod.GET)
  public ResultEntity list(SubjectInfo subjectInfo, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "30") Integer pageSize) {
    return subjectInfoService.list(subjectInfo, pageNum, pageSize);
  }

  /**
   * 根据条件查询
   *
   * @param subjectInfo 查询参数
   * @return ResultEntity
   */
  @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
  public ResultEntity list(SubjectInfo subjectInfo) {
    return subjectInfoService.list(subjectInfo);
  }

  /**
   * 更新学科(SubjectInfo)
   *
   * @param subjectInfos  更新参数
   * @param bindingResult 参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "update", method = RequestMethod.POST)
  public ResultEntity update(@Valid @RequestBody List<SubjectInfo> subjectInfos, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return subjectInfoService.update(subjectInfos);
  }
}