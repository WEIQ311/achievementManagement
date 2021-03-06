package com.achievement.service.impl;

import com.achievement.entity.*;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.ScoreUserInfoMapper;
import com.achievement.service.*;
import com.achievement.utils.GloabalUtils;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ResultEntity;
import com.achievement.vo.ScoreUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.achievement.constants.GlobalConstants.*;

/**
 * @author weiQiang
 * @date 2018/10/12
 */
@Slf4j
@Service(value = "scoreUserInfoService")
public class ScoreUserInfoServiceImpl implements ScoreUserInfoService {
  @Autowired
  private ParentInfoService parentInfoService;
  @Autowired
  private RedisTemplate redisTemplate;
  @Resource
  private ScoreUserInfoMapper scoreUserInfoMapper;
  @Autowired
  private StudentInfoService studentInfoService;
  @Autowired
  private TeacherInfoService teacherInfoService;
  @Autowired
  private TokenInfoService tokenInfoService;

  /**
   * 对象信息Map
   *
   * @param scoreUserInfo 查询参数
   * @return Map
   */
  @Override
  public Map<String, ScoreUserInfo> convertRecordToMap(ScoreUserInfo scoreUserInfo) {
    scoreUserInfo = convertQueryParam(scoreUserInfo);
    return scoreUserInfoMapper.list(scoreUserInfo).stream().filter(info -> StringUtils.isNotBlank(info.getUserId()))
        .collect(Collectors.toMap(ScoreUserInfo::getUserId, Function.identity(), (oldValue, newValue) -> newValue));
  }

  /**
   * 转换请求参数
   *
   * @param scoreUserInfo 用户信息
   * @return ScoreUserInfo
   */
  private ScoreUserInfo convertQueryParam(ScoreUserInfo scoreUserInfo) {
    if (null == scoreUserInfo) {
      scoreUserInfo = ScoreUserInfo.builder().build();
    }
    Integer status = scoreUserInfo.getStatus();
    if (null == status) {
      scoreUserInfo.setStatus(BOOLEAN_TRUE);
    }
    return scoreUserInfo;
  }

  /**
   * 删除对象
   *
   * @param pkIds 对象主键集合
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity delete(List<String> pkIds) {
    if (null == pkIds || pkIds.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    scoreUserInfoMapper.delete(pkIds);
    tokenInfoService.delete(pkIds);
    return ResultUtil.success(GlobalEnum.DeleteSuccess);
  }

  /**
   * 增加对象
   *
   * @param scoreUserInfos 对象参数
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity insert(List<ScoreUserInfo> scoreUserInfos) {
    return insertOrUpdateScoreUserInfo(scoreUserInfos, OPERATE_TYPE_INSERT);
  }

  /**
   * 增加或更新用户信息
   *
   * @param scoreUserInfos 用户信息
   * @param operateType    操作类型
   * @return
   */
  private ResultEntity insertOrUpdateScoreUserInfo(List<ScoreUserInfo> scoreUserInfos, String operateType) {
    if (null == scoreUserInfos || scoreUserInfos.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, ScoreUserInfo> scoreUserInfoMap = convertUserNameAndType(ScoreUserInfo.builder().build());
    Map<String, ScoreUserInfo> userInfoMap = scoreUserInfoMap.values().stream().collect(Collectors.toList()).stream()
        .filter(scoreUserInfo -> StringUtils.isNotBlank(scoreUserInfo.getUserId()))
        .collect(Collectors.toMap(ScoreUserInfo::getUserId, Function.identity(), (oldValue, newValue) -> newValue));
    List<ScoreUserInfo> insertScoreUserInfos = new ArrayList<>();
    List<ScoreUserInfo> updateScoreUserInfos = new ArrayList<>();
    scoreUserInfos.stream().forEach(scoreUserInfo -> {
      String loginName = scoreUserInfo.getLoginName();
      String password = scoreUserInfo.getPassword();
      String userType = scoreUserInfo.getUserType();
      String key = loginName + INTERVAL_NUMBER + userType;
      if (Objects.equals(OPERATE_TYPE_INSERT, operateType)) {
        if (StringUtils.isBlank(loginName)) {
          GloabalUtils.convertMessage(GlobalEnum.UserLoginNameEmpty);
        }
        if (StringUtils.isBlank(password)) {
          GloabalUtils.convertMessage(GlobalEnum.UserPasswordEmpty);
        }
        if (scoreUserInfoMap.containsKey(key)) {
          GloabalUtils.convertMessage(GlobalEnum.UserNameInUsed, loginName);
        }
        scoreUserInfo.setUserId("user_" + GloabalUtils.ordinaryId());
        scoreUserInfo.setPassword(GloabalUtils.md5(password, TOKEN_ISSUER));
        scoreUserInfo.setStatus(null == scoreUserInfo.getStatus() ? BOOLEAN_TRUE : scoreUserInfo.getStatus());
        insertScoreUserInfos.add(scoreUserInfo);
      } else {
        String userId = scoreUserInfo.getUserId();
        if (StringUtils.isBlank(userId)) {
          GloabalUtils.convertMessage(GlobalEnum.PkIdEmpty);
        }
        if (!userInfoMap.containsKey(userId)) {
          GloabalUtils.convertMessage(GlobalEnum.UserInfoEmpty, loginName);
        }
        if (scoreUserInfoMap.containsKey(key) && !Objects.equals(scoreUserInfoMap.get(key).getUserId(), userId)) {
          GloabalUtils.convertMessage(GlobalEnum.UserNameInUsed, loginName);
        }
        scoreUserInfo.setPassword(null);
        updateScoreUserInfos.add(scoreUserInfo);
      }
    });
    if (null != insertScoreUserInfos && insertScoreUserInfos.size() > 0) {
      Integer updateCount = scoreUserInfoMapper.insert(insertScoreUserInfos);
      log.info("增加用户:{}条", updateCount);
      if (updateCount > 0) {
        return ResultUtil.success(GlobalEnum.UpdateSuccess, insertScoreUserInfos);
      }
    }
    if (null != updateScoreUserInfos && updateScoreUserInfos.size() > 0) {
      Integer updateCount = scoreUserInfoMapper.update(updateScoreUserInfos);
      log.info("更新用户:{}条", updateCount);
      if (updateCount > 0) {
        return ResultUtil.success(GlobalEnum.UpdateSuccess, updateScoreUserInfos);
      }
    }
    return ResultUtil.success(GlobalEnum.UpdateError, scoreUserInfos);
  }

  /**
   * 对象信息Map
   *
   * @param scoreUserInfo 查询参数
   * @return Map
   */
  @Override
  public Map<String, ScoreUserInfo> convertUserNameAndType(ScoreUserInfo scoreUserInfo) {
    return scoreUserInfoMapper.list(scoreUserInfo).stream().filter(info -> StringUtils.isNotBlank(info.getLoginName())
        && StringUtils.isNotBlank(info.getUserType()))
        .collect(Collectors.toMap(info -> {
          return info.getLoginName() + INTERVAL_NUMBER + info.getUserType();
        }, Function.identity(), (oldValue, newValue) -> newValue));
  }

  /**
   * 通过登陆名称删除用户
   *
   * @param scoreUserInfoList 用户信息
   * @return ResultEntity
   */
  @Override
  public ResultEntity deleteByLoginName(List<ScoreUserInfo> scoreUserInfoList) {
    if (null == scoreUserInfoList || scoreUserInfoList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, ScoreUserInfo> scoreUserInfoMap = convertUserNameAndType(ScoreUserInfo.builder().build());
    List<String> pkIds = new ArrayList<>();
    scoreUserInfoList.stream().forEach(scoreUserInfo -> {
      String loginName = scoreUserInfo.getLoginName();
      String userType = scoreUserInfo.getUserType();
      if (StringUtils.isBlank(loginName)) {
        GloabalUtils.convertMessage(GlobalEnum.UserLoginNameEmpty);
      }
      if (StringUtils.isBlank(userType)) {
        GloabalUtils.convertMessage(GlobalEnum.UserRoleEmpty);
      }
      String key = loginName + INTERVAL_NUMBER + userType;
      if (!scoreUserInfoMap.containsKey(key)) {
        GloabalUtils.convertMessage(GlobalEnum.UserInfoEmpty, loginName);
      } else {
        pkIds.add(scoreUserInfoMap.get(key).getUserId());
      }
    });
    return delete(pkIds);
  }

  /**
   * 用户登陆
   *
   * @param userInfo 用户信息
   * @param request  请求
   * @param response 响应
   * @return ResultEntity
   */
  @Override
  public ResultEntity login(ScoreUserInfo userInfo, HttpServletRequest request, HttpServletResponse response) {
    userInfo = convertQueryParam(userInfo);
    String loginName = userInfo.getLoginName();
    String password = userInfo.getPassword();
    String userType = userInfo.getUserType();
    String key = loginName + INTERVAL_NUMBER + userType;
    String realIpAddress = checkFrequentlyLogin(request, userInfo);
    Map<String, ScoreUserInfo> userInfoMap = convertUserNameAndType(ScoreUserInfo.builder().loginName(loginName).userType(userType).build());
    if (userInfoMap.isEmpty() || !userInfoMap.containsKey(key)) {
      GloabalUtils.convertMessage(GlobalEnum.UserNameError, loginName);
    }
    ScoreUserInfo scoreUserInfo = userInfoMap.get(key);
    if (Objects.equals(BOOLEAN_FALSE, scoreUserInfo.getStatus())) {
      GloabalUtils.convertMessage(GlobalEnum.UserNoLogin, loginName);
    }
    String infoPassword = scoreUserInfo.getPassword();
    boolean verify = false;
    try {
      verify = GloabalUtils.verify(password, TOKEN_ISSUER, infoPassword);
    } catch (Exception e) {
      GloabalUtils.convertMessage(GlobalEnum.PasswordError);
    }
    if (!verify) {
      GloabalUtils.convertMessage(GlobalEnum.PasswordError);
    }
    List<?> resultInfoList = convertLoginUser(scoreUserInfo);
    tokenInfoService.initToken(request, scoreUserInfo, response);
    redisTemplate.delete(realIpAddress);
    return ResultUtil.success(GlobalEnum.LoginSuccess, resultInfoList);
  }

  /**
   * 检测是否频繁登陆
   *
   * @param request       请求
   * @param scoreUserInfo 用户信息
   */
  private String checkFrequentlyLogin(HttpServletRequest request, ScoreUserInfo scoreUserInfo) {
    String ipAddress = GloabalUtils.getIpAddress(request);
    String realIpAddress = ipAddress.replaceAll(INTERVAL_POINT, "");
    ValueOperations<String, ScoreUserInfo> valueOperations = redisTemplate.opsForValue();
    ScoreUserInfo info = null;
    if (null != valueOperations.get(realIpAddress)) {
      info = valueOperations.get(realIpAddress);
    }
    Date current = new Date();
    boolean frequentlyLogin = false;
    if (null != info) {
      Integer loginCount = info.getLoginCount();
      Date firstLogin = info.getFirstLogin();
      long currentTime = current.getTime();
      long firstLoginTime = firstLogin.getTime();
      long frequentlyTime = currentTime - firstLoginTime;
      loginCount += 1;
      frequentlyLogin = (FREQUENTLY_LOGIN_COUNT <= loginCount && frequentlyTime < SECONDS_TWO_MINUTE) || FREQUENTLY_MAX_LOGIN_ERROR_COUNT <= loginCount;
      info.setLoginCount(loginCount);
    } else {
      info = scoreUserInfo;
      info.setFirstLogin(current);
      info.setLoginCount(1);
    }
    info.setIpAddress(ipAddress);
    valueOperations.set(realIpAddress, info);
    if (frequentlyLogin) {
      GloabalUtils.convertMessage(GlobalEnum.FrequentlyLogin, scoreUserInfo.getLoginName(), info.getLoginCount().toString());
    }
    return realIpAddress;
  }

  /**
   * 登陆成功之后返回用户信息
   *
   * @param scoreUserInfo 用户信息
   * @return List
   */
  private List<?> convertLoginUser(ScoreUserInfo scoreUserInfo) {
    List results = new ArrayList();
    Map<String, ?> userObject = new HashMap<>(1);
    String userNum = scoreUserInfo.getLoginName();
    switch (scoreUserInfo.getUserType()) {
      case USER_ROLE_ADMIN:
      case USER_ROLE_TEACHER:
      case USER_ROLE_TEACHER_HEAD:
        userObject = teacherInfoService.convertRecordToMap(TeacherInfo.builder().teacherNum(userNum).build());
        break;
      case USER_ROLE_STUDENT:
        userObject = studentInfoService.convertRecordToMap(StudentInfo.builder().studentNum(userNum).build());
        break;
      case USER_ROLE_PARENT:
        userObject = parentInfoService.convertRecordToMap(ParentInfo.builder().telPhone(userNum).build());
        break;
      default:
        GloabalUtils.convertMessage(GlobalEnum.UserNoLogin, userNum);
        break;
    }
    if (userObject.isEmpty()) {
      GloabalUtils.convertMessage(GlobalEnum.UserNoLogin, userNum);
    }
    results = userObject.values().stream().collect(Collectors.toList());
    return results;
  }

  /**
   * 登出
   *
   * @param token   token
   * @param request 请求
   * @return ResultEntity
   */
  @Override
  public ResultEntity logout(String token, HttpServletRequest request) {
    ResultEntity resultEntity = tokenInfoService.tokenValid(token, request);
    if (!resultEntity.isSuccess()) {
      return resultEntity;
    }
    resultEntity = tokenInfoService.deleteToken(token);
    if (resultEntity.isSuccess()) {
      resultEntity.setMessage(GlobalEnum.LogoutSuccess.getMessage());
    }
    return resultEntity;
  }

  /**
   * 重置用户密码
   *
   * @param scoreUser 用户信息
   * @param request   请求
   * @param response  响应
   * @return ResultEntity
   */
  @Override
  public ResultEntity resetPassword(ScoreUser scoreUser, HttpServletRequest request, HttpServletResponse response) {
    String token = request.getHeader(TOKEN_HEADER);
    TokenInfo tokenInfo = GloabalUtils.parseJWT(token);
    String userId = scoreUser.getUserId();
    String id = tokenInfo.getId();
    if (!Objects.equals(userId, id)) {
      return ResultUtil.error(GlobalEnum.NoResetPassword);
    }
    ResultEntity resultEntity = tokenInfoService.tokenValid(token, request);
    if (!resultEntity.isSuccess()) {
      return resultEntity;
    }
    List<ScoreUserInfo> scoreUserInfos = scoreUserInfoMapper.list(ScoreUserInfo.builder().userId(userId).build());
    Map<String, ScoreUserInfo> userInfoMap = scoreUserInfos.stream()
        .collect(Collectors.toMap(ScoreUserInfo::getUserId, Function.identity(), (oldValue, newValue) -> newValue));
    if (userInfoMap.isEmpty() || !userInfoMap.containsKey(userId)) {
      GloabalUtils.convertMessage(GlobalEnum.UserInfoEmpty, userId);
    }
    ScoreUserInfo scoreUserInfo = userInfoMap.get(userId);
    String password = scoreUserInfo.getPassword();
    String oldPassword = scoreUser.getOldPassword();
    boolean verify = false;
    try {
      verify = GloabalUtils.verify(oldPassword, TOKEN_ISSUER, password);
    } catch (Exception e) {
      return ResultUtil.error(GlobalEnum.PasswordError);
    }
    if (!verify) {
      return ResultUtil.error(GlobalEnum.OldPasswordError);
    }
    scoreUserInfo.setPassword(GloabalUtils.md5(scoreUser.getNewPassword(), TOKEN_ISSUER));
    List<ScoreUserInfo> updateScoreUserInfos = new ArrayList<ScoreUserInfo>() {{
      add(scoreUserInfo);
    }};
    Integer updateCount = scoreUserInfoMapper.update(updateScoreUserInfos);
    log.info("更新用户:{}条", updateCount);
    if (updateCount > 0) {
      String updateToken = tokenInfoService.updateToken(token);
      response.setHeader(TOKEN_NEW_HEADER, updateToken);
      return ResultUtil.success(GlobalEnum.UpdateSuccess, updateScoreUserInfos);
    }
    return ResultUtil.error(GlobalEnum.UpdateError);
  }

  /**
   * 根据条件分页查询对象
   *
   * @param scoreUserInfo 查询参数
   * @param pageNum       开始页数
   * @param pageSize      每页显示的数据条数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(ScoreUserInfo scoreUserInfo, int pageNum, int pageSize) {
    scoreUserInfo = convertQueryParam(scoreUserInfo);
    PageHelper.startPage(pageNum, pageSize);
    List<ScoreUserInfo> scoreUserInfos = scoreUserInfoMapper.list(scoreUserInfo);
    PageInfo pageInfo = new PageInfo(scoreUserInfos);
    return ResultUtil.success(GlobalEnum.QuerySuccess, pageInfo);
  }

  /**
   * 根据条件查询对象
   *
   * @param scoreUserInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(ScoreUserInfo scoreUserInfo) {
    scoreUserInfo = convertQueryParam(scoreUserInfo);
    List<ScoreUserInfo> scoreUserInfos = scoreUserInfoMapper.list(scoreUserInfo);
    return ResultUtil.success(GlobalEnum.QuerySuccess, scoreUserInfos);
  }

  /**
   * 更新对象
   *
   * @param scoreUserInfos 更新参数
   * @return ResultEntity
   */
  @Override
  public ResultEntity update(List<ScoreUserInfo> scoreUserInfos) {
    return insertOrUpdateScoreUserInfo(scoreUserInfos, OPERATE_TYPE_UPDATE);
  }


}
