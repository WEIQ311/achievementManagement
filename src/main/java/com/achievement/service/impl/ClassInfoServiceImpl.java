package com.achievement.service.impl;

import com.achievement.entity.ClassInfo;
import com.achievement.entity.GradeInfo;
import com.achievement.entity.StudentInfo;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.ClassInfoMapper;
import com.achievement.service.ClassInfoService;
import com.achievement.service.GradeInfoService;
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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.achievement.constants.GlobalConstants.INTERVAL_NUMBER;

/**
 * (ClassInfo)表服务实现类
 *
 * @author weiqiang
 * @since 2018-10-02 15:20:56
 */
@Service("classInfoService")
public class ClassInfoServiceImpl implements ClassInfoService {

  @Resource
  private ClassInfoMapper classInfoMapper;

  @Autowired
  private GradeInfoService gradeInfoService;

  @Autowired
  private StudentInfoService studentInfoService;

  /**
   * 班级信息Map
   *
   * @param classInfo 查询参数
   * @return Map
   */
  @Override
  public Map<String, ClassInfo> convertRecordToMap(ClassInfo classInfo) {
    List<ClassInfo> classInfoList = classInfoMapper.list(classInfo);
    Map<String, ClassInfo> classInfoMap = classInfoList.stream().filter(info -> StringUtils.isNotEmpty(info.getClassId()))
        .collect(Collectors.toMap(ClassInfo::getClassId, Function.identity(), (oldValue, newValue) -> newValue));
    return classInfoMap;
  }

  /**
   * 删除班级
   *
   * @param classIds 班级主键集合
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity delete(List<String> classIds) {
    if (null == classIds || classIds.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, StudentInfo> studentInfoMap = studentInfoService.convertRecordToMap(StudentInfo.builder().classIds(classIds).build());
    if (null != studentInfoMap && studentInfoMap.size() > 0) {
      GloabalUtils.convertMessage(GlobalEnum.ClassNameInUsed);
    }
    classInfoMapper.delete(classIds);
    return ResultUtil.success(GlobalEnum.DeleteSuccess, classIds);
  }

  /**
   * 增加班级
   *
   * @param classInfos 班级参数
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity insert(List<ClassInfo> classInfos) {
    if (null == classInfos || classInfos.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, ClassInfo> classInfoMap = convertClassNameAndGradeIdMap(ClassInfo.builder().build());
    Map<String, GradeInfo> gradeInfoMap = gradeInfoService.convertRecordToMap(GradeInfo.builder().build());
    classInfos.stream().forEach(classInfo -> {
      String gradeId = classInfo.getGradeId();
      String className = classInfo.getClassName();
      if (!gradeInfoMap.containsKey(gradeId)) {
        GloabalUtils.convertMessage(GlobalEnum.GradeIdError, className, gradeId);
      }
      String key = className + INTERVAL_NUMBER + gradeId;
      GradeInfo gradeInfo = gradeInfoMap.get(gradeId);
      if (classInfoMap.containsKey(key)) {
        GloabalUtils.convertMessage(GlobalEnum.ClassNameInGrade, className, gradeInfo.getGradeName());
      }
      classInfo.setClassId("class_" + GloabalUtils.ordinaryId());
    });
    Integer insertCount = classInfoMapper.insert(classInfos);
    if (insertCount > 0) {
      return ResultUtil.success(GlobalEnum.InsertSuccess, classInfos);
    }
    return ResultUtil.error(GlobalEnum.InsertError);
  }

  /**
   * 班级信息Map
   *
   * @param classInfo
   * @return Map
   */
  @Override
  public Map<String, ClassInfo> convertClassNameAndGradeIdMap(ClassInfo classInfo) {
    List<ClassInfo> classInfoList = classInfoMapper.list(classInfo);
    Map<String, ClassInfo> classInfoMap = classInfoList.stream()
        .filter(info -> StringUtils.isNotBlank(info.getClassName()) && StringUtils.isNotEmpty(info.getGradeId()))
        .collect(Collectors.toMap(info -> info.getClassName() + INTERVAL_NUMBER + info.getGradeId(),
            Function.identity(), (oldValue, newValue) -> newValue)
        );
    return classInfoMap;
  }

  /**
   * 根据条件分页查询班级
   *
   * @param classInfo 查询参数
   * @param pageNum   开始页数
   * @param pageSize  每页显示的数据条数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(ClassInfo classInfo, int pageNum, int pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    List<ClassInfo> classInfoList = classInfoMapper.list(classInfo);
    convertResult(classInfoList);
    PageInfo pageInfo = new PageInfo(classInfoList);
    return ResultUtil.success(GlobalEnum.QuerySuccess, pageInfo);
  }

  /**
   * 根据条件查询班级
   *
   * @param classInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(ClassInfo classInfo) {
    List<ClassInfo> classInfoList = classInfoMapper.list(classInfo);
    convertResult(classInfoList);
    return ResultUtil.success(GlobalEnum.QuerySuccess, classInfoList);
  }

  /**
   * 更新班级
   *
   * @param classInfos 更新参数
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity update(List<ClassInfo> classInfos) {
    if (null == classInfos || classInfos.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, ClassInfo> classInfoMap = convertClassNameAndGradeIdMap(ClassInfo.builder().build());
    Map<String, GradeInfo> gradeInfoMap = gradeInfoService.convertRecordToMap(GradeInfo.builder().build());
    classInfos.stream().forEach(classInfo -> {
      String classId = classInfo.getClassId();
      String gradeId = classInfo.getGradeId();
      String className = classInfo.getClassName();
      if (StringUtils.isBlank(classId)) {
        GloabalUtils.convertMessage(GlobalEnum.PkIdEmpty, className);
      }
      if (!gradeInfoMap.containsKey(gradeId)) {
        GloabalUtils.convertMessage(GlobalEnum.GradeIdError, className, gradeId);
      }
      String key = className + INTERVAL_NUMBER + gradeId;
      GradeInfo gradeInfo = gradeInfoMap.get(gradeId);
      if (classInfoMap.containsKey(key) && !Objects.equals(classId, classInfoMap.get(key).getClassId())) {
        GloabalUtils.convertMessage(GlobalEnum.ClassNameInGrade, className, gradeInfo.getGradeName());
      }
    });
    Integer updateCount = classInfoMapper.update(classInfos);
    if (updateCount > 0) {
      return ResultUtil.success(GlobalEnum.UpdateSuccess, classInfos);
    }
    return ResultUtil.error(GlobalEnum.UpdateError);
  }

  /**
   * 转换查询结果
   *
   * @param classInfoList 班级信息
   */
  public void convertResult(List<ClassInfo> classInfoList) {
    if (null == classInfoList || classInfoList.size() < 1) {
      return;
    }
    Map<String, GradeInfo> gradeInfoMap = gradeInfoService.convertRecordToMap(GradeInfo.builder().build());
    classInfoList.stream().forEach(classInfo -> {
      String gradeId = classInfo.getGradeId();
      if (StringUtils.isNotBlank(gradeId) && gradeInfoMap.containsKey(gradeId)) {
        classInfo.setGradeInfo(gradeInfoMap.get(gradeId));
      }
    });
  }
}