package com.achievement.service.impl;

import com.achievement.entity.*;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.ConfTeacherClassMapper;
import com.achievement.service.*;
import com.achievement.utils.GloabalUtils;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ResultEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.achievement.constants.GlobalConstants.*;

/**
 * (ConfTeacherClass)ServiceImpl
 *
 * @author 魏强
 * @since 2018-10-05 16:02:41
 */
@Service("confTeacherClassService")
public class ConfTeacherClassServiceImpl implements ConfTeacherClassService {
  @Autowired
  private ClassInfoService classInfoService;
  @Resource
  private ConfTeacherClassMapper confTeacherClassMapper;
  @Autowired
  private SubjectInfoService subjectInfoService;
  @Autowired
  private TeacherInfoService teacherInfoService;
  @Autowired
  private ConfTeacherSubjectService teacherSubjectService;

  /**
   * 配置班级科目信息
   *
   * @param confTeacherClass 班级科目信息
   * @return ResultEntity
   */
  @Override
  public ResultEntity confTeacherSubject(ConfTeacherClass confTeacherClass) {
    if (null == confTeacherClass) {
      confTeacherClass = ConfTeacherClass.builder().build();
    }
    String classType = confTeacherClass.getClassType();
    if (StringUtils.isBlank(classType)) {
      GloabalUtils.convertMessage(GlobalEnum.ClassTypeEmpty);
    }
    String classId = confTeacherClass.getClassId();
    if (StringUtils.isBlank(classId)) {
      GloabalUtils.convertMessage(GlobalEnum.ClassIdEmpty);
    }
    Integer subjectType = Integer.valueOf(classType);
    List<Integer> subjectTypes = new ArrayList<Integer>() {{
      add(subjectType);
      add(0);
    }};
    Map<String, List<ConfTeacherSubject>> subjectOfTeacherMap = teacherSubjectService.convertSubjectOfTeacherMap(ConfTeacherSubject.builder().build());
    Map<String, TeacherInfo> teacherInfoMap = teacherInfoService.convertRecordToMap(TeacherInfo.builder().build());
    Map<String, ConfTeacherClass> teacherClassMap = confTeacherClassMapper.list(confTeacherClass).stream()
        .filter(info -> StringUtils.isNotBlank(info.getSubjectId()))
        .collect(Collectors.toMap(info -> {
          return info.getClassId() + INTERVAL_NUMBER + info.getSubjectId();
        }, Function.identity(), (oldValue, newValue) -> newValue));
    List<SubjectInfo> subjectInfoList = subjectInfoService.convertRecordToMap(SubjectInfo.builder().subjectTypes(subjectTypes).build())
        .values().stream().collect(Collectors.toList());
    List<ConfTeacherClass> confTeacherClassList = new ArrayList<ConfTeacherClass>() {{
      subjectInfoList.forEach(subjectInfo -> {
        String subjectId = subjectInfo.getSubjectId();
        String subjectName = subjectInfo.getSubjectName();
        String key = classId + INTERVAL_NUMBER + subjectId;
        List<ConfTeacherSubject> teacherSubjects = subjectOfTeacherMap.getOrDefault(subjectId, new ArrayList<>());
        List<TeacherInfo> teacherInfos = new ArrayList<TeacherInfo>() {{
          teacherSubjects.stream().forEach(confTeacherSubject -> {
            String teacherId = confTeacherSubject.getTeacherId();
            if (StringUtils.isNotBlank(teacherId) && teacherInfoMap.containsKey(teacherId)) {
              add(teacherInfoMap.get(teacherId));
            }
          });
        }};
        String confId = "";
        String teacherId = "";
        String teacherName = "";
        String teacherDuty = "";
        if (teacherClassMap.containsKey(key)) {
          ConfTeacherClass teacherClass = teacherClassMap.get(key);
          confId = teacherClass.getConfId();
          teacherId = teacherClass.getTeacherId();
          TeacherInfo teacherInfo = teacherInfoMap.getOrDefault(teacherId, TeacherInfo.builder().teacherName(teacherId).teacherDuty(DEFAULT_TEACHER_DUTY).build());
          teacherName = teacherInfo.getTeacherName();
          teacherDuty = teacherInfo.getTeacherDuty();
        }
        add(ConfTeacherClass.builder().confId(confId).subjectId(subjectId).teacherDuty(teacherDuty).teacherId(teacherId).teacherName(teacherName).classId(classId).subjectName(subjectName).teacherInfos(teacherInfos).build());
      });
    }};
    return ResultUtil.success(GlobalEnum.QuerySuccess, confTeacherClassList);
  }

  /**
   * 教师与班级关系信息Map
   *
   * @param confTeacherClass 班级与教师关系信息
   * @return Map
   */
  @Override
  public Map<String, List<ConfTeacherClass>> convertClassOfTeacherMap(ConfTeacherClass confTeacherClass) {
    List<ConfTeacherClass> confTeacherClassList = confTeacherClassMapper.list(confTeacherClass);
    return confTeacherClassList.stream().filter(info -> StringUtils.isNotBlank(info.getClassId()))
        .collect(Collectors.groupingBy(ConfTeacherClass::getClassId));
  }

  /**
   * 教师与班级关系信息Map
   *
   * @param confTeacherClass 教师与班级关系信息
   * @return Map
   */
  @Override
  public Map<String, List<ConfTeacherClass>> convertTeacherOfClassMap(ConfTeacherClass confTeacherClass) {
    List<ConfTeacherClass> confTeacherClassList = confTeacherClassMapper.list(confTeacherClass);
    return confTeacherClassList.stream().filter(info -> StringUtils.isNotBlank(info.getTeacherId()))
        .collect(Collectors.groupingBy(ConfTeacherClass::getTeacherId));
  }

  /**
   * 删除教师与班级关系信息
   *
   * @param confIds 教师与班级关系信息
   * @return ResultEntity
   */
  @Override
  public ResultEntity delete(List<String> confIds) {
    if (null == confIds || confIds.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    confTeacherClassMapper.delete(confIds);
    return ResultUtil.success(GlobalEnum.DeleteSuccess, confIds);
  }

  /**
   * 增加教师与班级关系信息
   *
   * @param confTeacherClassList 教师与班级关系信息
   * @return ResultEntity
   */
  @Override
  public ResultEntity insert(List<ConfTeacherClass> confTeacherClassList) {
    if (null == confTeacherClassList || confTeacherClassList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    checkConfInfo(confTeacherClassList, OPERATE_TYPE_INSERT);
    Integer insertCount = confTeacherClassMapper.insert(confTeacherClassList);
    if (insertCount > 0) {
      return ResultUtil.success(GlobalEnum.InsertSuccess, confTeacherClassList);
    }
    return ResultUtil.error(GlobalEnum.InsertError);
  }

  /**
   * 增加或编辑班级班主任
   *
   * @param confTeacherClasses 教师与班级关系信息
   * @return ResultEntity
   */
  @Override
  public ResultEntity insertOrUpdateClassHeadTeacher(List<ConfTeacherClass> confTeacherClasses) {
    if (null == confTeacherClasses || confTeacherClasses.size() < 1) {
      return ResultUtil.success(GlobalEnum.DataEmpty);
    }
    List<ConfTeacherClass> teacherClasses = confTeacherClassMapper.list(ConfTeacherClass.builder().teacherDuty(TEACHER_ROLE_HEAD).build());
    Map<String, TeacherInfo> teacherInfoMap = teacherInfoService.convertRecordToMap(TeacherInfo.builder().build());
    Map<String, ConfTeacherClass> teacherClassMap = teacherClasses.stream()
        .filter(conf -> StringUtils.isNotBlank(conf.getClassId()) && StringUtils.isNotBlank(conf.getTeacherId()))
        .collect(Collectors.toMap(ConfTeacherClass::getClassId, Function.identity(), (oldValue, newValue) -> newValue));
    List<ConfTeacherClass> insertList = new ArrayList<>();
    List<ConfTeacherClass> updateList = new ArrayList<>();
    confTeacherClasses.stream().forEach(confTeacherClass -> {
      String classId = confTeacherClass.getClassId();
      String teacherId = confTeacherClass.getTeacherId();
      if (StringUtils.isBlank(classId)) {
        GloabalUtils.convertMessage(GlobalEnum.ClassIdEmpty);
      }
      if (StringUtils.isBlank(teacherId)) {
        GloabalUtils.convertMessage(GlobalEnum.TeacherIdEmpty);
      }
      if (!teacherInfoMap.containsKey(teacherId)) {
        GloabalUtils.convertMessage(GlobalEnum.TeacherInfoEmpty, teacherId);
      }
      String teacherDuty = teacherInfoMap.get(teacherId).getTeacherDuty();
      String teacherName = teacherInfoMap.get(teacherId).getTeacherName();
      if (!Objects.equals(TEACHER_ROLE_HEAD, teacherDuty)) {
        GloabalUtils.convertMessage(GlobalEnum.TeacherNoHead, teacherName);
      }
      if (teacherClassMap.containsKey(classId)) {
        ConfTeacherClass teacherClass = teacherClassMap.get(classId);
        teacherClass.setTeacherId(teacherId);
        teacherClass.setTeacherDuty(TEACHER_ROLE_HEAD);
        updateList.add(teacherClass);
      } else {
        insertList.add(ConfTeacherClass.builder().classId(classId).teacherId(teacherId).teacherDuty(TEACHER_ROLE_HEAD).build());
      }
    });
    if (null != insertList && insertList.size() > 0) {
      return insert(insertList);
    } else {
      return update(insertList);
    }
  }

  /**
   * 关系信息中的参数是否合法
   *
   * @param confTeacherClasses 关系信息
   * @param operateType        操作类型
   */
  private void checkConfInfo(List<ConfTeacherClass> confTeacherClasses, String operateType) {
    Map<String, TeacherInfo> teacherInfoMap = teacherInfoService.convertRecordToMap(TeacherInfo.builder().build());
    Map<String, ClassInfo> classInfoMap = classInfoService.convertRecordToMap(ClassInfo.builder().build());
    confTeacherClasses.stream().forEach(confTeacherClass -> {
      String teacherId = confTeacherClass.getTeacherId();
      String classId = confTeacherClass.getClassId();
      String teacherDuty = confTeacherClass.getTeacherDuty();
      if (StringUtils.isBlank(teacherId)) {
        GloabalUtils.convertMessage(GlobalEnum.TeacherIdEmpty);
      }
      if (StringUtils.isBlank(classId)) {
        GloabalUtils.convertMessage(GlobalEnum.ClassIdEmpty);
      }
      if (StringUtils.isBlank(teacherDuty)) {
        GloabalUtils.convertMessage(GlobalEnum.TeacherDutyEmpty);
      }
      if (!teacherInfoMap.containsKey(teacherId)) {
        GloabalUtils.convertMessage(GlobalEnum.TeacherInfoEmpty, teacherId);
      }
      if (!classInfoMap.containsKey(classId)) {
        GloabalUtils.convertMessage(GlobalEnum.ClassInfoEmpty, classId);
      }
      if (Objects.equals(OPERATE_TYPE_INSERT, operateType)) {
        confTeacherClass.setConfId("conf_tc_" + GloabalUtils.ordinaryId());
      } else {
        String scoreId = confTeacherClass.getConfId();
        if (StringUtils.isBlank(scoreId)) {
          GloabalUtils.convertMessage(GlobalEnum.PkIdEmpty);
        }
      }
    });
  }

  /**
   * 查询教师与班级关系信息
   *
   * @param confTeacherClass 教师与班级关系信息
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(ConfTeacherClass confTeacherClass) {
    List<ConfTeacherClass> confTeacherClassList = confTeacherClassMapper.list(confTeacherClass);
    return ResultUtil.success(GlobalEnum.QuerySuccess, confTeacherClassList);
  }

  /**
   * 更新教师与班级关系信息
   *
   * @param confTeacherClassList 教师与班级关系信息
   * @return ResultEntity
   */
  @Override
  public ResultEntity update(List<ConfTeacherClass> confTeacherClassList) {
    if (null == confTeacherClassList || confTeacherClassList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    checkConfInfo(confTeacherClassList, OPERATE_TYPE_UPDATE);
    Integer updateCount = confTeacherClassMapper.update(confTeacherClassList);
    if (updateCount > 0) {
      return ResultUtil.success(GlobalEnum.UpdateSuccess, confTeacherClassList);
    }
    return ResultUtil.error(GlobalEnum.UpdateError);
  }
}