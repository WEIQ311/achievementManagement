package com.achievement.controller;

import com.achievement.entity.ConfStudentParent;
import com.achievement.service.ConfStudentParentService;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ObjectInfo;
import com.achievement.vo.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * 学生家长关系Controller
 *
 * @author weiQiang
 * @date 2018/10/5
 */
@RestController
@RequestMapping(value = "confStudentParent")
@CrossOrigin
public class ConfStudentParentController {

  @Autowired
  private ConfStudentParentService studentParentService;

  /**
   * 根据主键集合删除学生与家长关系
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
    return studentParentService.delete(objectInfo.getIds());
  }

  /**
   * 增加学生与家长关系信息
   *
   * @param confStudentParent 学生与家长关系信息
   * @param bindingResult     学生与家长关系信息
   * @return ResultEntity
   */
  @RequestMapping(value = "insert", method = RequestMethod.POST)
  public ResultEntity insert(@Valid ConfStudentParent confStudentParent, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return studentParentService.insert(new ArrayList<ConfStudentParent>() {{
      add(confStudentParent);
    }});
  }

  /**
   * 查询学生与家长关系
   *
   * @param confStudentParent 学生与家长关系
   * @return ResultEntity
   */
  @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
  public ResultEntity list(ConfStudentParent confStudentParent) {
    return studentParentService.list(confStudentParent);
  }
}
