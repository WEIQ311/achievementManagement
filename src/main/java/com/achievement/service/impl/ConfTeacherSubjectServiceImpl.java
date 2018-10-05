package com.achievement.service.impl;

import com.achievement.entity.ConfTeacherSubject;
import com.achievement.entity.SubjectInfo;
import com.achievement.entity.TeacherInfo;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.ConfTeacherSubjectMapper;
import com.achievement.service.ConfTeacherSubjectService;
import com.achievement.service.SubjectInfoService;
import com.achievement.service.TeacherInfoService;
import com.achievement.utils.GloabalUtils;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ResultEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.achievement.constants.GlobalConstants.OPERATE_TYPE_INSERT;

/**
 * (ConfTeacherSubject)ServiceImpl
 *
 * @author 魏强
 * @since 2018-10-05 16:02:50
 */
@Service("confTeacherSubjectService")
public class ConfTeacherSubjectServiceImpl implements ConfTeacherSubjectService {
  @Resource
  private ConfTeacherSubjectMapper confTeacherSubjectMapper;
  @Autowired
  private SubjectInfoService subjectInfoService;
  @Autowired
  private TeacherInfoService teacherInfoService;

  /**
   * 家长与学生关系信息Map
   *
   * @param confTeacherSubject 科目与教师关系信息
   * @return Map
   */
  @Override
  public Map<String, List<ConfTeacherSubject>> convertSubjectOfTeacherMap(ConfTeacherSubject confTeacherSubject) {
    List<ConfTeacherSubject> confTeacherSubjectList = confTeacherSubjectMapper.list(confTeacherSubject);
    return confTeacherSubjectList.stream().filter(info -> StringUtils.isNotBlank(info.getSubjectId()))
        .collect(Collectors.groupingBy(ConfTeacherSubject::getSubjectId));
  }

  /**
   * 教师与科目关系信息Map
   *
   * @param confTeacherSubject 教师与科目关系信息
   * @return Map
   */
  @Override
  public Map<String, List<ConfTeacherSubject>> convertTeacherOfSubjectMap(ConfTeacherSubject confTeacherSubject) {
    List<ConfTeacherSubject> confTeacherSubjectList = confTeacherSubjectMapper.list(confTeacherSubject);
    return confTeacherSubjectList.stream().filter(info -> StringUtils.isNotBlank(info.getTeacherId()))
        .collect(Collectors.groupingBy(ConfTeacherSubject::getTeacherId));
  }

  /**
   * 删除教师与科目关系信息
   *
   * @param confTeacherSubjectList 教师与科目关系信息
   * @return ResultEntity
   */
  @Override
  public ResultEntity delete(List<ConfTeacherSubject> confTeacherSubjectList) {
    if (null == confTeacherSubjectList || confTeacherSubjectList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    confTeacherSubjectMapper.delete(confTeacherSubjectList);
    return ResultUtil.success(GlobalEnum.DeleteSuccess, confTeacherSubjectList);
  }

  /**
   * 增加教师与科目关系信息
   *
   * @param confTeacherSubjectList 教师与科目关系信息
   * @return ResultEntity
   */
  @Override
  public ResultEntity insert(List<ConfTeacherSubject> confTeacherSubjectList) {
    if (null == confTeacherSubjectList || confTeacherSubjectList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    checkConfInfo(confTeacherSubjectList, OPERATE_TYPE_INSERT);
    return null;
  }

  /**
   * 关系信息中的参数是否合法
   *
   * @param confTeacherSubjectes 关系信息
   * @param operateType          操作类型
   */
  private void checkConfInfo(List<ConfTeacherSubject> confTeacherSubjectes, String operateType) {
    Map<String, TeacherInfo> teacherInfoMap = teacherInfoService.convertRecordToMap(TeacherInfo.builder().build());
    Map<String, SubjectInfo> subjectInfoMap = subjectInfoService.convertRecordToMap(SubjectInfo.builder().build());
    confTeacherSubjectes.stream().forEach(confTeacherSubject -> {
      String teacherId = confTeacherSubject.getTeacherId();
      String subjectId = confTeacherSubject.getSubjectId();
      if (StringUtils.isBlank(teacherId)) {
        GloabalUtils.convertMessage(GlobalEnum.TeacherIdEmpty);
      }
      if (StringUtils.isBlank(subjectId)) {
        GloabalUtils.convertMessage(GlobalEnum.SubjectIdEmpty);
      }
      if (!teacherInfoMap.containsKey(teacherId)) {
        GloabalUtils.convertMessage(GlobalEnum.TeacherInfoEmpty, teacherId);
      }
      if (!subjectInfoMap.containsKey(subjectId)) {
        GloabalUtils.convertMessage(GlobalEnum.SubjectInfoEmpty, subjectId);
      }
      if (Objects.equals(OPERATE_TYPE_INSERT, operateType)) {
        confTeacherSubject.setConfId("conf_ts_" + GloabalUtils.ordinaryId());
      } else {
        String scoreId = confTeacherSubject.getConfId();
        if (StringUtils.isBlank(scoreId)) {
          GloabalUtils.convertMessage(GlobalEnum.PkIdEmpty);
        }
      }
    });
  }

  /**
   * 查询家长与学生关系信息
   *
   * @param confTeacherSubject 家长与学生关系信息
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(ConfTeacherSubject confTeacherSubject) {
    List<ConfTeacherSubject> confTeacherSubjectList = confTeacherSubjectMapper.list(confTeacherSubject);
    return ResultUtil.success(GlobalEnum.QuerySuccess, confTeacherSubjectList);
  }
}