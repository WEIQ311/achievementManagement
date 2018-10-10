package com.achievement.service.impl;

import com.achievement.entity.ConfStudentParent;
import com.achievement.entity.ParentInfo;
import com.achievement.entity.StudentInfo;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.ConfStudentParentMapper;
import com.achievement.service.ConfStudentParentService;
import com.achievement.service.ParentInfoService;
import com.achievement.service.StudentInfoService;
import com.achievement.utils.GloabalUtils;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ResultEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.achievement.constants.GlobalConstants.OPERATE_TYPE_INSERT;
import static com.achievement.constants.GlobalConstants.OPERATE_TYPE_UPDATE;

/**
 * (ConfStudentParent)表服务实现类
 *
 * @author 魏强
 * @since 2018-10-05 16:08:51
 */
@Service("confStudentParentService")
public class ConfStudentParentServiceImpl implements ConfStudentParentService {
  @Resource
  private ConfStudentParentMapper confStudentParentMapper;
  @Autowired
  private ParentInfoService parentInfoService;
  @Autowired
  private StudentInfoService studentInfoService;

  /**
   * 家长与学生关系信息Map
   *
   * @param confStudentParent 家长与学生关系信息
   * @return Map
   */
  @Override
  public Map<String, List<ConfStudentParent>> convertParentOfStudentMap(ConfStudentParent confStudentParent) {
    List<ConfStudentParent> studentParents = confStudentParentMapper.list(confStudentParent);
    return studentParents.stream().filter(info -> StringUtils.isNotBlank(info.getParentId()))
        .collect(Collectors.groupingBy(ConfStudentParent::getParentId));
  }

  /**
   * 家长与学生关系信息Map
   *
   * @param confStudentParent 家长与学生关系信息
   * @return Map
   */
  @Override
  public Map<String, ConfStudentParent> convertRecordToMap(ConfStudentParent confStudentParent) {
    List<ConfStudentParent> studentParents = confStudentParentMapper.list(confStudentParent);
    Map<String, ConfStudentParent> studentParentMap = studentParents.stream().filter(info -> StringUtils.isNotBlank(info.getConfId()))
        .collect(Collectors.toMap(ConfStudentParent::getConfId, Function.identity(), (oldValue, newValue) -> newValue));
    return studentParentMap;
  }

  /**
   * 学生与家长关系信息Map
   *
   * @param confStudentParent 学生与家长关系信息
   * @return Map
   */
  @Override
  public Map<String, List<ConfStudentParent>> convertStudentOfParentMap(ConfStudentParent confStudentParent) {
    List<ConfStudentParent> studentParents = confStudentParentMapper.list(confStudentParent);
    return studentParents.stream().filter(info -> StringUtils.isNotBlank(info.getStudentId()))
        .collect(Collectors.groupingBy(ConfStudentParent::getStudentId));
  }

  /**
   * 删除学生与家长关系信息
   *
   * @param confIds 学生与家长关系信息
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity delete(List<String> confIds) {
    if (null == confIds || confIds.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    confStudentParentMapper.delete(confIds);
    return ResultUtil.success(GlobalEnum.DeleteSuccess, confIds);
  }

  /**
   * 删除学生与家长关系信息
   *
   * @param parentIds 家长ID集合
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity deleteByParentId(List<String> parentIds) {
    if (null == parentIds || parentIds.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    confStudentParentMapper.deleteByParentId(parentIds);
    return ResultUtil.success(GlobalEnum.DeleteSuccess, parentIds);
  }

  /**
   * 删除学生与家长关系信息
   *
   * @param studentIds 学生ID集合
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity deleteByStudentId(List<String> studentIds) {
    if (null == studentIds || studentIds.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    confStudentParentMapper.deleteByStudentId(studentIds);
    return ResultUtil.success(GlobalEnum.DeleteSuccess, studentIds);
  }

  /**
   * 增加学生与家长关系信息
   *
   * @param confStudentParents 学生与家长关系信息
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity insert(List<ConfStudentParent> confStudentParents) {
    if (null == confStudentParents || confStudentParents.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    checkConfInfo(confStudentParents, OPERATE_TYPE_INSERT);
    Integer insertCount = confStudentParentMapper.insert(confStudentParents);
    if (insertCount > 0) {
      return ResultUtil.success(GlobalEnum.InsertSuccess, confStudentParents);
    }
    return ResultUtil.error(GlobalEnum.InsertError);
  }

  /**
   * 关系信息中的参数是否合法
   *
   * @param confStudentParents 关系信息
   * @param operateType        操作类型
   */
  private void checkConfInfo(List<ConfStudentParent> confStudentParents, String operateType) {
    Map<String, StudentInfo> studentInfoMap = studentInfoService.convertRecordToMap(StudentInfo.builder().build());
    Map<String, ParentInfo> parentInfoMap = parentInfoService.convertRecordToMap(ParentInfo.builder().build());
    confStudentParents.stream().forEach(confStudentParent -> {
      String studentId = confStudentParent.getStudentId();
      String parentId = confStudentParent.getParentId();
      if (StringUtils.isBlank(studentId)) {
        GloabalUtils.convertMessage(GlobalEnum.StudentIdEmpty);
      }
      if (StringUtils.isBlank(parentId)) {
        GloabalUtils.convertMessage(GlobalEnum.ParentIdEmpty);
      }
      if (!studentInfoMap.containsKey(studentId)) {
        GloabalUtils.convertMessage(GlobalEnum.StudentInfoEmpty, studentId);
      }
      if (!parentInfoMap.containsKey(parentId)) {
        GloabalUtils.convertMessage(GlobalEnum.ParentInfoEmpty, parentId);
      }
      if (Objects.equals(OPERATE_TYPE_INSERT, operateType)) {
        confStudentParent.setConfId("conf_sp_" + GloabalUtils.ordinaryId());
      } else {
        String scoreId = confStudentParent.getConfId();
        if (StringUtils.isBlank(scoreId)) {
          GloabalUtils.convertMessage(GlobalEnum.PkIdEmpty);
        }
      }
    });
  }

  /**
   * 查询家长与学生关系信息
   *
   * @param confStudentParent 家长与学生关系信息
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(ConfStudentParent confStudentParent) {
    List<ConfStudentParent> studentParents = confStudentParentMapper.list(confStudentParent);
    return ResultUtil.success(GlobalEnum.QuerySuccess, studentParents);
  }

  /**
   * 更新学生与家长关系信息
   *
   * @param confStudentParents 学生与家长关系信息
   * @return ResultEntity
   */
  @Override
  public ResultEntity update(List<ConfStudentParent> confStudentParents) {
    if (null == confStudentParents || confStudentParents.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    checkConfInfo(confStudentParents, OPERATE_TYPE_UPDATE);
    Integer updateCount = confStudentParentMapper.update(confStudentParents);
    if (updateCount > 0) {
      return ResultUtil.success(GlobalEnum.UpdateSuccess, confStudentParents);
    }
    return ResultUtil.error(GlobalEnum.UpdateError);
  }
}