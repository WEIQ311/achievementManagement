package com.achievement.controller;

/**
 * @author weiQiang
 * @date 2018/10/5
 */

import com.achievement.entity.ConfTeacherSubject;
import com.achievement.service.ConfTeacherSubjectService;
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
import java.util.List;

/**
 * 教师与科目配置关系
 *
 * @author weiQiang
 * @date 2018/10/5
 */
@RestController
@RequestMapping(value = "confTeacherSubject")
@CrossOrigin
public class ConfTeacherSubjectController {
  @Autowired
  private ConfTeacherSubjectService confTeacherSubjectService;

  /**
   * 根据主键集合删除教师与科目关系
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
    List<ConfTeacherSubject> confTeacherSubjects = new ArrayList<ConfTeacherSubject>() {{
      objectInfo.getIds().stream().forEach(id -> add(ConfTeacherSubject.builder().confId(id).build()));
    }};
    return confTeacherSubjectService.delete(confTeacherSubjects);
  }

  /**
   * 增加教师与科目关系信息
   *
   * @param confTeacherSubject 教师与科目关系信息
   * @param bindingResult      教师与科目关系信息
   * @return ResultEntity
   */
  @RequestMapping(value = "insert", method = RequestMethod.POST)
  public ResultEntity insert(@Valid ConfTeacherSubject confTeacherSubject, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return confTeacherSubjectService.insert(new ArrayList<ConfTeacherSubject>() {{
      add(confTeacherSubject);
    }});
  }

  /**
   * 查询教师与科目关系
   *
   * @param confTeacherSubject 教师与科目关系
   * @return ResultEntity
   */
  @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
  public ResultEntity list(ConfTeacherSubject confTeacherSubject) {
    return confTeacherSubjectService.list(confTeacherSubject);
  }
}
