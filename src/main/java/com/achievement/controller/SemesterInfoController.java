package com.achievement.controller;

import com.achievement.entity.SemesterInfo;
import com.achievement.service.SemesterInfoService;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ObjectInfo;
import com.achievement.vo.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;


/**
 * 学期(SemesterInfo)表控制层
 *
 * @author weiQiang
 * @since 2018-10-02 17:52:27
 */
@RestController
@RequestMapping("semesterInfo")
@CrossOrigin
public class SemesterInfoController {
  /**
   * Service
   */
  @Autowired
  private SemesterInfoService semesterInfoService;

  /**
   * 根据主键集合删除学期(SemesterInfo)
   *
   * @param objectInfo 主键集合
   * @return ResultEntity
   */
  @RequestMapping(value = "deleteByIds", method = RequestMethod.POST)
  public ResultEntity delete(@Valid ObjectInfo objectInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return semesterInfoService.delete(objectInfo.getIds());
  }

  /**
   * 删除学期(SemesterInfo)
   *
   * @param semesterId 主键
   * @return ResultEntity
   */
  @RequestMapping(path = "{semesterId}", method = RequestMethod.DELETE)
  public ResultEntity delete(@PathVariable String semesterId) {
    return semesterInfoService.delete(new ArrayList<String>() {{
      add(semesterId);
    }});
  }

  /**
   * 增加学期(SemesterInfo)
   *
   * @param semesterInfo  插入参数
   * @param bindingResult 参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "insert", method = RequestMethod.POST)
  public ResultEntity insert(@Valid SemesterInfo semesterInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return semesterInfoService.insert(new ArrayList<SemesterInfo>() {{
      add(semesterInfo);
    }});
  }

  /**
   * 根据条件分页查询
   *
   * @param semesterInfo 查询参数
   * @param pageNum      开始页数      @param pageSize  每页显示的数据条数
   * @return ResultEntity
   */
  @RequestMapping(value = "/listByPage", method = RequestMethod.GET)
  public ResultEntity list(SemesterInfo semesterInfo, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "30") Integer pageSize) {
    return semesterInfoService.list(semesterInfo, pageNum, pageSize);
  }

  /**
   * 根据条件查询
   *
   * @param semesterInfo 查询参数
   * @return ResultEntity
   */
  @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
  public ResultEntity list(SemesterInfo semesterInfo) {
    return semesterInfoService.list(semesterInfo);
  }

  /**
   * 更新学期(SemesterInfo)
   *
   * @param semesterInfo  更新参数
   * @param bindingResult 参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "update", method = RequestMethod.POST)
  public ResultEntity update(@Valid SemesterInfo semesterInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return semesterInfoService.update(new ArrayList<SemesterInfo>() {{
      add(semesterInfo);
    }});
  }
}