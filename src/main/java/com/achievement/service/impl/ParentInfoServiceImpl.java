package com.achievement.service.impl;

import com.achievement.entity.ConfStudentParent;
import com.achievement.entity.ParentInfo;
import com.achievement.entity.ScoreUserInfo;
import com.achievement.entity.StudentInfo;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.ParentInfoMapper;
import com.achievement.service.ConfStudentParentService;
import com.achievement.service.ParentInfoService;
import com.achievement.service.ScoreUserInfoService;
import com.achievement.service.StudentInfoService;
import com.achievement.utils.GloabalUtils;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ResultEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.achievement.constants.GlobalConstants.USER_ROLE_PARENT;

/**
 * 家长(ParentInfo)ServiceImpl
 *
 * @author weiQiang
 * @since 2018-10-02 17:47:57
 */
@Service("parentInfoService")
public class ParentInfoServiceImpl implements ParentInfoService {
  @Autowired
  private ConfStudentParentService confStudentParentService;
  @Resource
  private ParentInfoMapper parentInfoMapper;
  @Autowired
  private ScoreUserInfoService scoreUserInfoService;
  @Autowired
  private StudentInfoService studentInfoService;

  /**
   * 家长(ParentInfo)信息Map
   *
   * @param parentInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public Map<String, ParentInfo> convertRecordToMap(ParentInfo parentInfo) {
    List<ParentInfo> parentInfos = parentInfoMapper.list(parentInfo);
    Map<String, ParentInfo> parentInfoMap = parentInfos.stream().filter(info -> StringUtils.isNotBlank(info.getParentId()))
        .collect(Collectors.toMap(ParentInfo::getParentId, Function.identity(), (oldValue, newValue) -> newValue));
    return parentInfoMap;
  }

  /**
   * 删除家长(ParentInfo)
   *
   * @param parentIds 主键集合
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity delete(List<String> parentIds) {
    if (null == parentIds || parentIds.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    List<ScoreUserInfo> deleteUserInfoList = new ArrayList<>();
    Map<String, ParentInfo> parentInfoMap = convertRecordToMap(ParentInfo.builder().build());
    parentIds.stream().forEach(parentId -> {
      if (!parentInfoMap.containsKey(parentId)) {
        GloabalUtils.convertMessage(GlobalEnum.ParentInfoEmpty, parentId);
      } else {
        String telPhone = parentInfoMap.get(parentId).getTelPhone();
        if (StringUtils.isNotBlank(telPhone)) {
          deleteUserInfoList.add(ScoreUserInfo.builder().loginName(telPhone).userType(USER_ROLE_PARENT).build());
        }
      }
    });
    confStudentParentService.deleteByParentId(parentIds);
    parentInfoMapper.delete(parentIds);
    scoreUserInfoService.deleteByLoginName(deleteUserInfoList);
    return ResultUtil.success(GlobalEnum.DeleteSuccess, parentIds);
  }

  /**
   * 增加家长(ParentInfo)
   *
   * @param parentInfoList 参数
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity insert(List<ParentInfo> parentInfoList) {
    if (null == parentInfoList || parentInfoList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, ParentInfo> parentInfoMap = convertRecordToMap(ParentInfo.builder().build()).values().stream()
        .filter(parentInfo -> StringUtils.isNotBlank(parentInfo.getTelPhone()))
        .collect(Collectors.toMap(ParentInfo::getTelPhone, Function.identity(), (oldValue, newValue) -> newValue));
    parentInfoList.stream().forEach(parentInfo -> {
      String telPhone = parentInfo.getTelPhone();
      if (StringUtils.isNotBlank(telPhone) && parentInfoMap.containsKey(telPhone)) {
        String parentName = parentInfoMap.get(telPhone).getParentName();
        GloabalUtils.convertMessage(GlobalEnum.TelPhoneInUsed, parentName);
      }
      if (StringUtils.isBlank(parentInfo.getParentId())) {
        parentInfo.setParentId("parent_" + GloabalUtils.ordinaryId());
      }
    });
    Integer insertCount = parentInfoMapper.insert(parentInfoList);
    if (insertCount > 0) {
      scoreUserInfoService.insert(new ArrayList<ScoreUserInfo>() {{
        parentInfoList.stream().forEach(parentInfo -> {
          String telPhone = parentInfo.getTelPhone();
          if (StringUtils.isNotBlank(telPhone)) {
            add(ScoreUserInfo.builder().loginName(telPhone).password(telPhone).userType(USER_ROLE_PARENT).build());
          }
        });
      }});
      return ResultUtil.success(GlobalEnum.InsertSuccess, parentInfoList);
    }
    return ResultUtil.error(GlobalEnum.InsertError);
  }

  /**
   * 根据条件分页查询
   *
   * @param parentInfo 查询参数
   * @param pageNum    开始页数
   * @param pageSize   每页显示的数据条数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(ParentInfo parentInfo, int pageNum, int pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    List<ParentInfo> parentInfos = parentInfoMapper.list(parentInfo);
    convertParentInfo(parentInfos);
    PageInfo pageInfo = new PageInfo(parentInfos);
    return ResultUtil.success(GlobalEnum.QuerySuccess, pageInfo);
  }

  /**
   * 转换请求结果
   *
   * @param parentInfos 家长信息
   */
  private void convertParentInfo(List<ParentInfo> parentInfos) {
    if (null == parentInfos || parentInfos.size() < 1) {
      return;
    }
    List<String> parentIds = parentInfos.stream().filter(studentInfo -> StringUtils.isNotBlank(studentInfo.getParentId()))
        .map(ParentInfo::getParentId)
        .collect(Collectors.toList());
    Map<String, List<ConfStudentParent>> parentOfStudentMap = confStudentParentService.convertParentOfStudentMap(ConfStudentParent.builder().studentIds(parentIds).build());
    List<String> studentIds = new ArrayList<>();
    parentOfStudentMap.values().forEach(confStudentParents -> {
      confStudentParents.stream().forEach(confStudentParent -> {
        String studentId = confStudentParent.getStudentId();
        if (StringUtils.isNotBlank(studentId)) {
          studentIds.add(studentId);
        }
      });
    });
    List<String> studentIdList = studentIds.stream().distinct().collect(Collectors.toList());
    if (null != studentIdList && studentIdList.size() > 0) {
      Map<String, StudentInfo> studentInfoMap = studentInfoService.convertRecordToMap(StudentInfo.builder().studentIds(studentIdList).build());
      parentInfos.stream().forEach(parentInfo -> {
        String parentId = parentInfo.getParentId();
        if (StringUtils.isNotBlank(parentId) && parentOfStudentMap.containsKey(parentId)) {
          List<ConfStudentParent> confStudentParents = parentOfStudentMap.get(parentId);
          List<StudentInfo> studentInfos = new ArrayList<StudentInfo>() {{
            confStudentParents.stream().forEach(confStudentParent -> {
              String studentId = confStudentParent.getStudentId();
              if (studentInfoMap.containsKey(studentId)) {
                add(studentInfoMap.get(studentId));
              }
            });
          }};
          parentInfo.setChildList(studentInfos);
        }
      });
    }
  }

  /**
   * 根据条件查询
   *
   * @param parentInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(ParentInfo parentInfo) {
    List<ParentInfo> parentInfos = parentInfoMapper.list(parentInfo);
    convertParentInfo(parentInfos);
    return ResultUtil.success(GlobalEnum.QuerySuccess, parentInfos);
  }

  /**
   * 更新家长(ParentInfo)
   *
   * @param parentInfoList 更新参数
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity update(List<ParentInfo> parentInfoList) {
    if (null == parentInfoList || parentInfoList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, ParentInfo> recordToMap = convertRecordToMap(ParentInfo.builder().build());
    Map<String, ParentInfo> parentInfoMap = recordToMap.values().stream()
        .filter(parentInfo -> StringUtils.isNotBlank(parentInfo.getTelPhone()))
        .collect(Collectors.toMap(ParentInfo::getTelPhone, Function.identity(), (oldValue, newValue) -> newValue));
    List<ScoreUserInfo> insertUserInfoList = new ArrayList<>();
    List<ScoreUserInfo> updateUserInfoList = new ArrayList<>();
    List<ScoreUserInfo> deleteUserInfoList = new ArrayList<>();
    parentInfoList.stream().forEach(parentInfo -> {
      String parentId = parentInfo.getParentId();
      if (StringUtils.isBlank(parentId)) {
        GloabalUtils.convertMessage(GlobalEnum.PkIdEmpty);
      }
      String telPhone = parentInfo.getTelPhone();
      if (StringUtils.isNotBlank(telPhone) && parentInfoMap.containsKey(telPhone) && !Objects.equals(parentId, parentInfoMap.get(telPhone).getParentId())) {
        String parentName = parentInfoMap.get(telPhone).getParentName();
        GloabalUtils.convertMessage(GlobalEnum.TelPhoneInUsed, parentName);
      }
      //家长更换手机号码时将已经有的家长用户登陆名更换
      String oldTelPhone = recordToMap.get(parentId).getTelPhone();
      if (StringUtils.isBlank(oldTelPhone) && StringUtils.isNotBlank(telPhone)) {
        insertUserInfoList.add(ScoreUserInfo.builder().loginName(telPhone).password(telPhone).userType(USER_ROLE_PARENT).build());
      } else if (StringUtils.isNotBlank(oldTelPhone) && StringUtils.isBlank(telPhone)) {
        deleteUserInfoList.add(ScoreUserInfo.builder().loginName(oldTelPhone).userType(USER_ROLE_PARENT).build());
      } else if (!Objects.equals(oldTelPhone, telPhone)) {
        updateUserInfoList.add(ScoreUserInfo.builder().loginName(telPhone).userType(USER_ROLE_PARENT).build());
      }
    });
    Integer updateCount = parentInfoMapper.update(parentInfoList);
    if (updateCount > 0) {
      scoreUserInfoService.insert(insertUserInfoList);
      scoreUserInfoService.update(insertUserInfoList);
      scoreUserInfoService.deleteByLoginName(deleteUserInfoList);
      return ResultUtil.success(GlobalEnum.UpdateSuccess, parentInfoList);
    }
    return ResultUtil.error(GlobalEnum.UpdateError);
  }
}