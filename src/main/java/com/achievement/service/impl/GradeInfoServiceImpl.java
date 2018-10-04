package com.achievement.service.impl;

import com.achievement.entity.ClassInfo;
import com.achievement.entity.GradeInfo;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.GradeInfoMapper;
import com.achievement.service.ClassInfoService;
import com.achievement.service.GradeInfoService;
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

/**
 * (GradeInfo)ServiceImpl
 *
 * @author weiQiang
 * @since 2018-10-02 17:34:08
 */
@Service("gradeInfoService")
public class GradeInfoServiceImpl implements GradeInfoService {
  @Autowired
  private ClassInfoService classInfoService;
  @Resource
  private GradeInfoMapper gradeInfoMapper;

  /**
   * 信息Map
   *
   * @param gradeInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public Map<String, GradeInfo> convertRecordToMap(GradeInfo gradeInfo) {
    List<GradeInfo> gradeInfoList = gradeInfoMapper.list(gradeInfo);
    Map<String, GradeInfo> gradeInfoMap = gradeInfoList.stream().filter(info -> StringUtils.isNotBlank(info.getGradeId()))
        .collect(Collectors.toMap(GradeInfo::getGradeId, Function.identity(), (oldValue, newValue) -> newValue));
    return gradeInfoMap;
  }

  /**
   * 删除
   *
   * @param gradeIds 主键集合
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity delete(List<String> gradeIds) {
    if (null == gradeIds || gradeIds.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, ClassInfo> classInfoMap = classInfoService.convertRecordToMap(ClassInfo.builder().gradeIds(gradeIds).build());
    if (null != classInfoMap && classInfoMap.size() > 0) {
      GloabalUtils.convertMessage(GlobalEnum.GradeNameInClass);
    }
    List<GradeInfo> gradeInfoList = new ArrayList<GradeInfo>() {{
      gradeIds.forEach(gradeId -> GradeInfo.builder().gradeId(gradeId).build());
    }};
    gradeInfoMapper.delete(gradeInfoList);
    return ResultUtil.success(GlobalEnum.DeleteSuccess, gradeInfoList);
  }

  /**
   * 增加
   *
   * @param gradeInfoList 参数
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity insert(List<GradeInfo> gradeInfoList) {
    if (null == gradeInfoList || gradeInfoList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, GradeInfo> gradeInfoMap = convertRecordToMap(GradeInfo.builder().build()).values().stream()
        .filter(gradeInfo -> StringUtils.isNotBlank(gradeInfo.getGradeName()))
        .collect(Collectors.toMap(GradeInfo::getGradeName, Function.identity(), (oldValue, newValue) -> newValue));
    gradeInfoList.stream().forEach(gradeInfo -> {
      String gradeName = gradeInfo.getGradeName();
      if (gradeInfoMap.containsKey(gradeName)) {
        GloabalUtils.convertMessage(GlobalEnum.GradeNameInUsed, gradeName);
      }
      gradeInfo.setGradeId("grade_" + GloabalUtils.ordinaryId());
    });
    Integer insertCount = gradeInfoMapper.insert(gradeInfoList);
    if (insertCount > 0) {
      return ResultUtil.success(GlobalEnum.InsertSuccess, gradeInfoList);
    }
    return ResultUtil.error(GlobalEnum.InsertError);
  }

  /**
   * 根据条件分页查询
   *
   * @param gradeInfo 查询参数
   * @param pageNum   开始页数
   * @param pageSize  每页显示的数据条数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(GradeInfo gradeInfo, int pageNum, int pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    List<GradeInfo> gradeInfoPage = gradeInfoMapper.list(gradeInfo);
    PageInfo pageInfo = new PageInfo(gradeInfoPage);
    return ResultUtil.success(GlobalEnum.QuerySuccess, pageInfo);
  }

  /**
   * 根据条件查询
   *
   * @param gradeInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(GradeInfo gradeInfo) {
    List<GradeInfo> gradeInfoList = gradeInfoMapper.list(gradeInfo);
    return ResultUtil.success(GlobalEnum.QuerySuccess, gradeInfoList);
  }

  /**
   * 更新
   *
   * @param gradeInfoList 更新参数
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity update(List<GradeInfo> gradeInfoList) {
    if (null == gradeInfoList || gradeInfoList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, GradeInfo> gradeInfoMap = convertRecordToMap(GradeInfo.builder().build()).values().stream()
        .filter(gradeInfo -> StringUtils.isNotBlank(gradeInfo.getGradeName()))
        .collect(Collectors.toMap(GradeInfo::getGradeName, Function.identity(), (oldValue, newValue) -> newValue));
    gradeInfoList.stream().forEach(gradeInfo -> {
      String gradeId = gradeInfo.getGradeId();
      if (StringUtils.isBlank(gradeId)) {
        GloabalUtils.convertMessage(GlobalEnum.PkIdEmpty);
      }
      String gradeName = gradeInfo.getGradeName();
      if (gradeInfoMap.containsKey(gradeName) && !Objects.equals(gradeId, gradeInfoMap.get(gradeName).getGradeId())) {
        GloabalUtils.convertMessage(GlobalEnum.GradeNameInUsed, gradeName);
      }
    });
    Integer updateCount = gradeInfoMapper.update(gradeInfoList);
    if (updateCount > 0) {
      return ResultUtil.success(GlobalEnum.UpdateSuccess, gradeInfoList);
    }
    return ResultUtil.error(GlobalEnum.UpdateError);
  }
}