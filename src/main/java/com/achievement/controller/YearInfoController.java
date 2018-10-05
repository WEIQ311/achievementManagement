package com.achievement.controller;

import com.achievement.entity.YearInfo;
import com.achievement.service.YearInfoService;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ObjectInfo;
import com.achievement.vo.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * 学年(YearInfo)表控制层
 *
 * @author weiQiang
 * @since 2018-10-02 17:52:27
 */
@RestController
@RequestMapping("yearInfo")
@CrossOrigin
public class YearInfoController {
  /**
   * Service
   */
  @Autowired
  private YearInfoService yearInfoService;

  /**
   * 根据主键集合删除学年(YearInfo)
   *
   * @param objectInfo 主键集合
   * @return ResultEntity
   */
  @RequestMapping(value = "deleteByIds", method = RequestMethod.POST)
  public ResultEntity delete(@Valid ObjectInfo objectInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return yearInfoService.delete(objectInfo.getIds());
  }

  /**
   * 删除学年(YearInfo)
   *
   * @param yearId 主键
   * @return ResultEntity
   */
  @RequestMapping(path = "{yearId}", method = RequestMethod.DELETE)
  public ResultEntity delete(@PathVariable String yearId) {
    return yearInfoService.delete(new ArrayList<String>() {{
      add(yearId);
    }});
  }

  /**
   * 增加学年(YearInfo)
   *
   * @param yearInfo      插入参数
   * @param bindingResult 参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "insert", method = RequestMethod.POST)
  public ResultEntity insert(@Valid YearInfo yearInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return yearInfoService.insert(new ArrayList<YearInfo>() {{
      add(yearInfo);
    }});
  }

  /**
   * 根据条件分页查询
   *
   * @param yearInfo 查询参数
   * @param pageNum  开始页数
   * @param pageSize 每页显示的数据条数
   * @return ResultEntity
   */
  @RequestMapping(value = "/listByPage", method = RequestMethod.GET)
  public ResultEntity list(YearInfo yearInfo, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "30") Integer pageSize) {
    return yearInfoService.list(yearInfo, pageNum, pageSize);
  }

  /**
   * 根据条件查询
   *
   * @param yearInfo 查询参数
   * @return ResultEntity
   */
  @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
  public ResultEntity list(YearInfo yearInfo) {
    return yearInfoService.list(yearInfo);
  }

  /**
   * 更新学年(YearInfo)
   *
   * @param yearInfo      更新参数
   * @param bindingResult 参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "update", method = RequestMethod.POST)
  public ResultEntity update(@Valid YearInfo yearInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return yearInfoService.update(new ArrayList<YearInfo>() {{
      add(yearInfo);
    }});
  }
}