package com.achievement.service.impl;

import com.achievement.entity.ConfTeacherClass;
import com.achievement.entity.ConfTeacherSubject;
import com.achievement.entity.TeacherInfo;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.TeacherInfoMapper;
import com.achievement.service.ConfTeacherClassService;
import com.achievement.service.ConfTeacherSubjectService;
import com.achievement.service.TeacherInfoService;
import com.achievement.utils.GloabalUtils;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ResultEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
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

/**
 * 教师(TeacherInfo)ServiceImpl
 *
 * @author weiQiang
 * @since 2018-10-02 17:52:27
 */
@Slf4j
@Service("teacherInfoService")
public class TeacherInfoServiceImpl implements TeacherInfoService {
  @Autowired
  private ConfTeacherClassService confTeacherClassService;
  @Autowired
  private ConfTeacherSubjectService confTeacherSubjectService;
  @Resource
  private TeacherInfoMapper teacherInfoMapper;

  /**
   * 教师(TeacherInfo)信息Map
   *
   * @param teacherInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public Map<String, TeacherInfo> convertRecordToMap(TeacherInfo teacherInfo) {
    List<TeacherInfo> teacherInfoList = teacherInfoMapper.list(teacherInfo);
    Map<String, TeacherInfo> teacherInfoMap = teacherInfoList.stream().filter(info -> StringUtils.isNotBlank(info.getTeacherId()))
        .collect(Collectors.toMap(TeacherInfo::getTeacherId, Function.identity(), (oldValue, newValue) -> newValue));
    return teacherInfoMap;
  }

  /**
   * 删除教师(TeacherInfo)
   *
   * @param teacherIds 主键集合
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity delete(List<String> teacherIds) {
    if (null == teacherIds || teacherIds.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    teacherInfoMapper.delete(teacherIds);
    confTeacherSubjectService.deleteByTeacherId(teacherIds);
    return ResultUtil.success(GlobalEnum.DeleteSuccess, teacherIds);
  }

  /**
   * 增加教师(TeacherInfo)
   *
   * @param teacherInfoList 参数
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity insert(List<TeacherInfo> teacherInfoList) {
    if (null == teacherInfoList || teacherInfoList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, TeacherInfo> teacherInfoMap = convertRecordToMap(TeacherInfo.builder().build()).values().stream()
        .filter(info -> StringUtils.isNotBlank(info.getTeacherNum()))
        .collect(Collectors.toMap(TeacherInfo::getTeacherNum, Function.identity(), (oldValue, newValue) -> newValue));
    teacherInfoList.stream().forEach(teacherInfo -> {
      String teacherNum = teacherInfo.getTeacherNum();
      if (teacherInfoMap.containsKey(teacherNum)) {
        String teacherName = teacherInfoMap.get(teacherNum).getTeacherName();
        GloabalUtils.convertMessage(GlobalEnum.TeacherNumInUsed, teacherName, teacherNum);
      }
      teacherInfo.setTeacherId("teacher_" + GloabalUtils.ordinaryId());
    });
    Integer insertCount = teacherInfoMapper.insert(teacherInfoList);
    if (insertCount > 0) {
      ResultEntity resultEntity = confTeacherSubjectService.insertOrUpdateTeacherSubject(teacherInfoList);
      log.info("增加教师与科目信息:{}", resultEntity);
      return ResultUtil.success(GlobalEnum.InsertSuccess, teacherInfoList);
    }
    return ResultUtil.error(GlobalEnum.InsertError);
  }

  /**
   * 根据条件分页查询
   *
   * @param teacherInfo 查询参数
   * @param pageNum     开始页数
   * @param pageSize    每页显示的数据条数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(TeacherInfo teacherInfo, int pageNum, int pageSize) {
    teacherInfo = convertQueryParam(teacherInfo);
    PageHelper.startPage(pageNum, pageSize);
    List<TeacherInfo> teacherInfoPage = teacherInfoMapper.list(teacherInfo);
    convertTeacherInfo(teacherInfoPage);
    PageInfo pageInfo = new PageInfo(teacherInfoPage);
    return ResultUtil.success(GlobalEnum.QuerySuccess, pageInfo);
  }

  /**
   * 转换请求参数
   *
   * @param teacherInfo 教师信息
   * @return TeacherInfo
   */
  private TeacherInfo convertQueryParam(TeacherInfo teacherInfo) {
    if (null == teacherInfo) {
      teacherInfo = TeacherInfo.builder().build();
    }
    String subjectId = teacherInfo.getSubjectId();
    if (StringUtils.isNotBlank(subjectId)) {
      Map<String, List<ConfTeacherSubject>> subjectOfTeacherMap = confTeacherSubjectService.convertSubjectOfTeacherMap(ConfTeacherSubject.builder().subjectId(subjectId).build());
      List<String> teacherIds = subjectOfTeacherMap.get(subjectId).stream().filter(info -> StringUtils.isNotBlank(info.getTeacherId()))
          .map(ConfTeacherSubject::getTeacherId)
          .distinct().collect(Collectors.toList());
      teacherInfo.setTeacherIds(teacherIds);
    }
    return teacherInfo;
  }

  /**
   * 教师信息
   *
   * @param teacherInfos 教师信息
   */
  private void convertTeacherInfo(List<TeacherInfo> teacherInfos) {
    if (null == teacherInfos || teacherInfos.size() < 1) {
      return;
    }
    Map<String, List<ConfTeacherClass>> teacherOfClassMap = confTeacherClassService.convertTeacherOfClassMap(ConfTeacherClass.builder().build());
    teacherInfos.stream().forEach(teacherInfo -> {
      String teacherId = teacherInfo.getTeacherId();
      if (teacherOfClassMap.containsKey(teacherId)) {
        List<ConfTeacherClass> confTeacherClasses = teacherOfClassMap.get(teacherId);
        teacherInfo.setConfTeacherClasses(confTeacherClasses);
      }
    });
  }

  /**
   * 根据条件查询
   *
   * @param teacherInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(TeacherInfo teacherInfo) {
    teacherInfo = convertQueryParam(teacherInfo);
    List<TeacherInfo> teacherInfos = teacherInfoMapper.list(teacherInfo);
    convertTeacherInfo(teacherInfos);
    return ResultUtil.success(GlobalEnum.QuerySuccess, teacherInfos);
  }

  /**
   * 更新教师(TeacherInfo)
   *
   * @param teacherInfoList 更新参数
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity update(List<TeacherInfo> teacherInfoList) {
    if (null == teacherInfoList || teacherInfoList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, TeacherInfo> teacherInfoMap = convertRecordToMap(TeacherInfo.builder().build()).values().stream()
        .filter(info -> StringUtils.isNotBlank(info.getTeacherNum()))
        .collect(Collectors.toMap(TeacherInfo::getTeacherNum, Function.identity(), (oldValue, newValue) -> newValue));
    teacherInfoList.stream().forEach(teacherInfo -> {
      String teacherId = teacherInfo.getTeacherId();
      if (StringUtils.isBlank(teacherId)) {
        GloabalUtils.convertMessage(GlobalEnum.PkIdEmpty);
      }
      String teacherNum = teacherInfo.getTeacherNum();
      if (teacherInfoMap.containsKey(teacherNum) && !Objects.equals(teacherId, teacherInfoMap.get(teacherNum).getTeacherId())) {
        String teacherName = teacherInfoMap.get(teacherNum).getTeacherName();
        GloabalUtils.convertMessage(GlobalEnum.TeacherNumInUsed, teacherName, teacherNum);
      }
    });
    Integer updateCount = teacherInfoMapper.update(teacherInfoList);
    if (updateCount > 0) {
      ResultEntity resultEntity = confTeacherSubjectService.insertOrUpdateTeacherSubject(teacherInfoList);
      log.info("更新教师与科目信息:{}", resultEntity);
      return ResultUtil.success(GlobalEnum.UpdateSuccess, teacherInfoList);
    }
    return ResultUtil.error(GlobalEnum.UpdateError);
  }
}