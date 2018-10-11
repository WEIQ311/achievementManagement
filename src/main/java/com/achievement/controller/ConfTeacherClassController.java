package com.achievement.controller;

import com.achievement.entity.ConfTeacherClass;
import com.achievement.service.ConfTeacherClassService;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ObjectInfo;
import com.achievement.vo.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * 教师与班级配置关系
 *
 * @author weiQiang
 * @date 2018/10/5
 */
@RestController
@RequestMapping(value = "confTeacherClass")
@CrossOrigin
public class ConfTeacherClassController {

  @Autowired
  private ConfTeacherClassService confTeacherClassService;

  /**
   * 配置班级科目信息
   *
   * @param confTeacherClass 班级科目信息
   * @return ResultEntity
   */
  @RequestMapping(value = "confTeacherSubject", method = RequestMethod.GET)
  public ResultEntity confTeacherSubject(ConfTeacherClass confTeacherClass) {
    return confTeacherClassService.confTeacherSubject(confTeacherClass);
  }

  /**
   * 根据主键集合删除教师与班级关系
   *
   * @param objectInfo    主键集合
   * @param bindingResult 参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "deleteByIds", method = RequestMethod.POST)
  public ResultEntity delete(@Valid @RequestBody ObjectInfo objectInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return confTeacherClassService.delete(objectInfo.getIds());
  }

  /**
   * 增加教师与班级关系信息
   *
   * @param confTeacherClass 教师与班级关系信息
   * @param bindingResult    教师与班级关系信息
   * @return ResultEntity
   */
  @RequestMapping(value = "insert", method = RequestMethod.POST)
  public ResultEntity insert(@Valid @RequestBody ConfTeacherClass confTeacherClass, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return confTeacherClassService.insert(new ArrayList<ConfTeacherClass>() {{
      add(confTeacherClass);
    }});
  }

  /**
   * 查询教师与班级关系
   *
   * @param confTeacherClass 教师与班级关系
   * @return ResultEntity
   */
  @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
  public ResultEntity list(ConfTeacherClass confTeacherClass) {
    return confTeacherClassService.list(confTeacherClass);
  }
}
