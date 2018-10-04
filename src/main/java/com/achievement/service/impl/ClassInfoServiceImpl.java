package com.achievement.service.impl;

import com.achievement.entity.ClassInfo;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.ClassInfoMapper;
import com.achievement.service.ClassInfoService;
import com.achievement.utils.GloabalUtils;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ResultEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

  /**
   * 班级信息Map
   *
   * @param classInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public Map<String, ClassInfo> convertRecordToMap(ClassInfo classInfo) {
    List<ClassInfo> classInfoList = classInfoMapper.list(classInfo);
    Map<String, ClassInfo> classInfoMap = classInfoList.stream().filter(info -> !StringUtils.isEmpty(info.getClassId()))
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
    List<ClassInfo> classInfos = new ArrayList<ClassInfo>(classIds.size()) {{
      classIds.forEach(classId -> {
        add(ClassInfo.builder().classId(classId).build());
      });
    }};
    classInfoMapper.delete(classInfos);
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
    classInfos.stream().forEach(classInfo -> classInfo.setClassId("class_" + GloabalUtils.ordinaryId()));
    Integer insertCount = classInfoMapper.insert(classInfos);
    if (insertCount > 0) {
      return ResultUtil.success(GlobalEnum.InsertSuccess, classInfos);
    }
    return ResultUtil.error(GlobalEnum.InsertError);
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
    Integer updateCount = classInfoMapper.update(classInfos);
    if (updateCount > 0) {
      return ResultUtil.success(GlobalEnum.UpdateSuccess, classInfos);
    }
    return ResultUtil.error(GlobalEnum.UpdateError);
  }
}