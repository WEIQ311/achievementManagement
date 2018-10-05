package com.achievement.service.impl;

import com.achievement.entity.ClassInfo;
import com.achievement.entity.ConfTeacherClass;
import com.achievement.entity.TeacherInfo;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.ConfTeacherClassMapper;
import com.achievement.service.ClassInfoService;
import com.achievement.service.ConfTeacherClassService;
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
  private TeacherInfoService teacherInfoService;

  /**
   * 家长与学生关系信息Map
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
   * @param confTeacherClassList 教师与班级关系信息
   * @return ResultEntity
   */
  @Override
  public ResultEntity delete(List<ConfTeacherClass> confTeacherClassList) {
    if (null == confTeacherClassList || confTeacherClassList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    confTeacherClassMapper.delete(confTeacherClassList);
    return ResultUtil.success(GlobalEnum.DeleteSuccess, confTeacherClassList);
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
    return null;
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
      if (StringUtils.isBlank(teacherId)) {
        GloabalUtils.convertMessage(GlobalEnum.TeacherIdEmpty);
      }
      if (StringUtils.isBlank(classId)) {
        GloabalUtils.convertMessage(GlobalEnum.ClassIdEmpty);
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
   * 查询家长与学生关系信息
   *
   * @param confTeacherClass 家长与学生关系信息
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(ConfTeacherClass confTeacherClass) {
    List<ConfTeacherClass> confTeacherClassList = confTeacherClassMapper.list(confTeacherClass);
    return ResultUtil.success(GlobalEnum.QuerySuccess, confTeacherClassList);
  }
}