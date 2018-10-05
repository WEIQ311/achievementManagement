package com.achievement.controller;

import com.achievement.entity.SchoolInfo;
import com.achievement.service.SchoolInfoService;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ObjectInfo;
import com.achievement.vo.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;


/**
 * 学校(SchoolInfo)表控制层
 *
 * @author weiQiang
 * @since 2018-10-02 17:52:27
 */
@RestController
@RequestMapping("schoolInfo")
@CrossOrigin
public class SchoolInfoController {
  /**
   * Service
   */
  @Autowired
  private SchoolInfoService schoolInfoService;

  /**
   * 根据主键集合删除学校(SchoolInfo)
   *
   * @param objectInfo 主键集合
   * @return ResultEntity
   */
  @RequestMapping(value = "deleteByIds", method = RequestMethod.POST)
  public ResultEntity delete(@Valid ObjectInfo objectInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return schoolInfoService.delete(objectInfo.getIds());
  }

  /**
   * 删除学校(SchoolInfo)
   *
   * @param scId 主键
   * @return ResultEntity
   */
  @RequestMapping(path = "{scId}", method = RequestMethod.DELETE)
  public ResultEntity delete(@PathVariable String scId) {
    return schoolInfoService.delete(new ArrayList<String>() {{
      add(scId);
    }});
  }

  /**
   * 增加学校(SchoolInfo)
   *
   * @param schoolInfo    插入参数
   * @param bindingResult 参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "insert", method = RequestMethod.POST)
  public ResultEntity insert(@Valid SchoolInfo schoolInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return schoolInfoService.insert(new ArrayList<SchoolInfo>() {{
      add(schoolInfo);
    }});
  }

  /**
   * 根据条件分页查询
   *
   * @param schoolInfo 查询参数
   * @param pageNum    开始页数    * @param pageSize  每页显示的数据条数
   * @return ResultEntity
   */
  @RequestMapping(value = "/listByPage", method = RequestMethod.GET)
  public ResultEntity list(SchoolInfo schoolInfo, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "30") Integer pageSize) {
    return schoolInfoService.list(schoolInfo, pageNum, pageSize);
  }

  /**
   * 根据条件查询
   *
   * @param schoolInfo 查询参数
   * @return ResultEntity
   */
  @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
  public ResultEntity list(SchoolInfo schoolInfo) {
    return schoolInfoService.list(schoolInfo);
  }

  /**
   * 更新学校(SchoolInfo)
   *
   * @param schoolInfo    更新参数
   * @param bindingResult 参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "update", method = RequestMethod.POST)
  public ResultEntity update(@Valid SchoolInfo schoolInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return schoolInfoService.update(new ArrayList<SchoolInfo>() {{
      add(schoolInfo);
    }});
  }
}