package com.achievement.controller;

import com.achievement.entity.ScoreUserInfo;
import com.achievement.service.ScoreUserInfoService;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ObjectInfo;
import com.achievement.vo.ResultEntity;
import com.achievement.vo.ScoreUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.achievement.constants.GlobalConstants.TOKEN_HEADER;

/**
 * (ScoreUserInfo)表控制层
 *
 * @author 魏强
 * @since 2018-10-12 16:54:15
 */
@RestController
@RequestMapping("user")
public class ScoreUserInfoController {

  /**
   * 成绩管理系统用户信息
   */
  @Autowired
  private ScoreUserInfoService scoreUserInfoService;

  /**
   * 根据主键集合删除用户(ScoreUserInfo)
   *
   * @param objectInfo 主键集合
   * @return ResultEntity
   */
  @RequestMapping(value = "deleteByIds", method = RequestMethod.POST)
  public ResultEntity delete(@Valid @RequestBody ObjectInfo objectInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return scoreUserInfoService.delete(objectInfo.getIds());
  }

  /**
   * 删除用户(ScoreUserInfo)
   *
   * @param subjectId 主键
   * @return ResultEntity
   */
  @RequestMapping(path = "{subjectId}", method = RequestMethod.DELETE)
  public ResultEntity delete(@PathVariable String subjectId) {
    return scoreUserInfoService.delete(new ArrayList<String>() {{
      add(subjectId);
    }});
  }

  /**
   * 增加用户(ScoreUserInfo)
   *
   * @param scoreUserInfo 插入参数
   * @param bindingResult 参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "insert", method = RequestMethod.POST)
  public ResultEntity insert(@Valid @RequestBody ScoreUserInfo scoreUserInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return scoreUserInfoService.insert(new ArrayList<ScoreUserInfo>() {{
      add(scoreUserInfo);
    }});
  }

  /**
   * 根据条件分页查询
   *
   * @param scoreUserInfo 查询参数
   * @param pageNum       开始页数      @param pageSize  每页显示的数据条数
   * @return ResultEntity
   */
  @RequestMapping(value = "/listByPage", method = RequestMethod.GET)
  public ResultEntity list(ScoreUserInfo scoreUserInfo, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "30") Integer pageSize) {
    return scoreUserInfoService.list(scoreUserInfo, pageNum, pageSize);
  }

  /**
   * 根据条件查询
   *
   * @param scoreUserInfo 查询参数
   * @return ResultEntity
   */
  @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
  public ResultEntity list(ScoreUserInfo scoreUserInfo) {
    return scoreUserInfoService.list(scoreUserInfo);
  }

  /**
   * 重置用户密码
   *
   * @param scoreUser     用户信息
   * @param bindingResult 校验信息
   * @param request       请求
   * @param response      响应
   * @return ResultEntity
   */
  @RequestMapping(value = {"resetPassword"}, method = RequestMethod.POST)
  public ResultEntity resetPassword(@Valid @RequestBody ScoreUser scoreUser, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }

    return scoreUserInfoService.resetPassword(scoreUser, request, response);
  }

  /**
   * 更新用户(ScoreUserInfo)
   *
   * @param scoreUserInfos 更新参数
   * @param bindingResult  参数绑定校验
   * @return ResultEntity
   */
  @RequestMapping(value = "update", method = RequestMethod.POST)
  public ResultEntity update(@Valid @RequestBody List<ScoreUserInfo> scoreUserInfos, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return scoreUserInfoService.update(scoreUserInfos);
  }

  /**
   * 用户登陆
   *
   * @param userInfo      用户信息
   * @param bindingResult 校验信息
   * @param request       请求
   * @param response      响应
   * @return ResultEntity
   */
  @RequestMapping(value = "login", method = RequestMethod.POST)
  public ResultEntity userLogin(@Valid @RequestBody ScoreUserInfo userInfo, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
    }
    return scoreUserInfoService.login(userInfo, request, response);
  }

  /**
   * 用户登出
   *
   * @param request 请求
   * @return ResultEntity
   */
  @RequestMapping(value = "logout", method = RequestMethod.GET)
  public ResultEntity userLogout(HttpServletRequest request) {
    String token = request.getHeader(TOKEN_HEADER);
    return scoreUserInfoService.logout(token);
  }
}