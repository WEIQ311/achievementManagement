package com.achievement.controller;

import com.achievement.entity.ParentInfo;
import com.achievement.service.ParentInfoService;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ObjectInfo;
import com.achievement.vo.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;


/**
 * 家长(ParentInfo)表控制层
 *
 * @author weiQiang
 * @since 2018-10-02 17:47:55
 */
@RestController
@RequestMapping("parentInfo")
@CrossOrigin
public class ParentInfoController {
  /**
   * Service
   */
  @Autowired
  private ParentInfoService parentInfoService;

  /**
   * 根据主键集合删除家长(ParentInfo)
   *
   * @param objectInfo    主键集合
   * @param bindingResult 参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "deleteByIds", method = RequestMethod.POST)
  public ResultEntity delete(@Valid ObjectInfo objectInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return parentInfoService.delete(objectInfo.getIds());
  }

  /**
   * 删除家长(ParentInfo)
   *
   * @param parentId 主键
   * @return ResultEntity
   */
  @RequestMapping(path = "{parentId}", method = RequestMethod.DELETE)
  public ResultEntity delete(@PathVariable String parentId) {
    return parentInfoService.delete(new ArrayList<String>() {{
      add(parentId);
    }});
  }

  /**
   * 增加家长(ParentInfo)
   *
   * @param parentInfo    插入参数
   * @param bindingResult 参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "insert", method = RequestMethod.POST)
  public ResultEntity insert(@Valid ParentInfo parentInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return parentInfoService.insert(new ArrayList<ParentInfo>() {{
      add(parentInfo);
    }});
  }

  /**
   * 根据条件分页查询
   *
   * @param parentInfo 查询参数
   * @param pageNum    开始页数    * @param pageSize  每页显示的数据条数
   * @return ResultEntity
   */
  @RequestMapping(value = "/listByPage", method = RequestMethod.GET)
  public ResultEntity list(ParentInfo parentInfo, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "30") Integer pageSize) {
    return parentInfoService.list(parentInfo, pageNum, pageSize);
  }

  /**
   * 根据条件查询
   *
   * @param parentInfo 查询参数
   * @return ResultEntity
   */
  @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
  public ResultEntity list(ParentInfo parentInfo) {
    return parentInfoService.list(parentInfo);
  }

  /**
   * 更新家长(ParentInfo)
   *
   * @param parentInfo    更新参数
   * @param bindingResult 参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "update", method = RequestMethod.POST)
  public ResultEntity update(@Valid ParentInfo parentInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return parentInfoService.update(new ArrayList<ParentInfo>() {{
      add(parentInfo);
    }});
  }
}