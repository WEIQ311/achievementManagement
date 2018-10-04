package com.achievement.service.impl;

import com.achievement.entity.TeacherInfo;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.TeacherInfoMapper;
import com.achievement.service.TeacherInfoService;
import com.achievement.utils.GloabalUtils;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ResultEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 教师(TeacherInfo)ServiceImpl
 *
 * @author weiQiang
 * @since 2018-10-02 17:52:27
 */
@Service("teacherInfoService")
public class TeacherInfoServiceImpl implements TeacherInfoService {
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
    List<TeacherInfo> teacherInfoList = new ArrayList<TeacherInfo>() {{
      teacherIds.stream().forEach(teacherId -> add(TeacherInfo.builder().teacherId(teacherId).build()));
    }};
    teacherInfoMapper.delete(teacherInfoList);
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
    teacherInfoList.stream().forEach(teacherInfo -> teacherInfo.setTeacherId("teacher_" + GloabalUtils.ordinaryId()));
    Integer insertCount = teacherInfoMapper.insert(teacherInfoList);
    if (insertCount > 0) {
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
    PageHelper.startPage(pageNum, pageSize);
    List<TeacherInfo> teacherInfoPage = teacherInfoMapper.list(teacherInfo);
    PageInfo pageInfo = new PageInfo(teacherInfoPage);
    return ResultUtil.success(GlobalEnum.QuerySuccess, pageInfo);
  }

  /**
   * 根据条件查询
   *
   * @param teacherInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(TeacherInfo teacherInfo) {
    List<TeacherInfo> teacherInfos = teacherInfoMapper.list(teacherInfo);
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
    Integer updateCount = teacherInfoMapper.update(teacherInfoList);
    if (updateCount > 0) {
      return ResultUtil.success(GlobalEnum.UpdateSuccess, teacherInfoList);
    }
    return ResultUtil.error(GlobalEnum.UpdateError);
  }
}