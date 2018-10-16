package com.achievement.service;

import com.achievement.entity.ScoreUserInfo;
import com.achievement.vo.ResultEntity;
import com.achievement.vo.ScoreUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author weiQiang
 * @date 2018/10/12
 */
public interface ScoreUserInfoService extends BaseInfoService<ScoreUserInfo, String> {

  /**
   * 对象信息Map
   *
   * @param scoreUserInfo 查询参数
   * @return Map
   */
  Map<String, ScoreUserInfo> convertUserNameAndType(ScoreUserInfo scoreUserInfo);

  /**
   * 通过登陆名称删除用户
   *
   * @param scoreUserInfoList 用户信息
   * @return ResultEntity
   */
  ResultEntity deleteByLoginName(List<ScoreUserInfo> scoreUserInfoList);

  /**
   * 用户登陆
   *
   * @param userInfo 用户信息
   * @param request  请求
   * @param response 响应
   * @return ResultEntity
   */
  ResultEntity login(ScoreUserInfo userInfo, HttpServletRequest request, HttpServletResponse response);

  /**
   * 登出
   *
   * @param token   token
   * @param request 请求
   * @return ResultEntity
   */
  ResultEntity logout(String token, HttpServletRequest request);

  /**
   * 重置用户密码
   *
   * @param scoreUser 用户信息
   * @param request   请求
   * @param response  响应
   * @return ResultEntity
   */
  ResultEntity resetPassword(ScoreUser scoreUser, HttpServletRequest request, HttpServletResponse response);
}
