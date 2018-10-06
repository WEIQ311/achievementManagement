package com.achievement.service.impl;

import com.achievement.entity.SemesterInfo;
import com.achievement.entity.YearInfo;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.YearInfoMapper;
import com.achievement.service.SemesterInfoService;
import com.achievement.service.YearInfoService;
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

/**
 * 学年(YearInfo)ServiceImpl
 *
 * @author weiQiang
 * @since 2018-10-02 17:52:27
 */
@Service("yearInfoService")
public class YearInfoServiceImpl implements YearInfoService {
  @Autowired
  private SemesterInfoService semesterInfoService;
  @Resource
  private YearInfoMapper yearInfoMapper;

  /**
   * 学年(YearInfo)信息Map
   *
   * @param yearInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public Map<String, YearInfo> convertRecordToMap(YearInfo yearInfo) {
    List<YearInfo> yearInfoList = yearInfoMapper.list(yearInfo);
    Map<String, YearInfo> yearInfoMap = yearInfoList.stream().filter(info -> StringUtils.isNotBlank(info.getYearId()))
        .collect(Collectors.toMap(YearInfo::getYearId, Function.identity(), (oldValue, newValue) -> newValue));
    return yearInfoMap;
  }

  /**
   * 删除学年(YearInfo)
   *
   * @param yearIds 主键集合
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity delete(List<String> yearIds) {
    if (null == yearIds || yearIds.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, SemesterInfo> semesterInfoMap = semesterInfoService.convertRecordToMap(SemesterInfo.builder().yearIds(yearIds).build());
    if (null != semesterInfoMap && semesterInfoMap.size() > 0) {
      GloabalUtils.convertMessage(GlobalEnum.YearNameInSemester);
    }
    yearInfoMapper.delete(yearIds);
    return ResultUtil.success(GlobalEnum.DeleteSuccess, yearIds);
  }

  /**
   * 增加学年(YearInfo)
   *
   * @param yearInfoList 参数
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity insert(List<YearInfo> yearInfoList) {
    if (null == yearInfoList || yearInfoList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, YearInfo> yearInfoMap = convertRecordToMap(YearInfo.builder().build()).values().stream()
        .filter(info -> StringUtils.isNotBlank(info.getYearName()))
        .collect(Collectors.toMap(YearInfo::getYearName, Function.identity(), (oldValue, newValue) -> newValue));
    yearInfoList.stream().forEach(yearInfo -> {
      String yearName = yearInfo.getYearName();
      if (yearInfoMap.containsKey(yearName)) {
        GloabalUtils.convertMessage(GlobalEnum.YearNameInUsed, yearName);
      }
      yearInfo.setYearId("year_" + GloabalUtils.ordinaryId());
    });
    Integer insertCount = yearInfoMapper.insert(yearInfoList);
    if (insertCount > 0) {
      return ResultUtil.success(GlobalEnum.InsertSuccess, yearInfoList);
    }
    return ResultUtil.error(GlobalEnum.InsertError);
  }

  /**
   * 根据条件分页查询
   *
   * @param yearInfo 查询参数
   * @param pageNum  开始页数
   * @param pageSize 每页显示的数据条数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(YearInfo yearInfo, int pageNum, int pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    List<YearInfo> yearInfoPage = yearInfoMapper.list(yearInfo);
    PageInfo pageInfo = new PageInfo(yearInfoPage);
    return ResultUtil.success(GlobalEnum.QuerySuccess, pageInfo);
  }

  /**
   * 根据条件查询
   *
   * @param yearInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(YearInfo yearInfo) {
    List<YearInfo> yearInfos = yearInfoMapper.list(yearInfo);
    return ResultUtil.success(GlobalEnum.QuerySuccess, yearInfos);
  }

  /**
   * 更新学年(YearInfo)
   *
   * @param yearInfoList 更新参数
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity update(List<YearInfo> yearInfoList) {
    if (null == yearInfoList || yearInfoList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, YearInfo> yearInfoMap = convertRecordToMap(YearInfo.builder().build()).values().stream()
        .filter(info -> StringUtils.isNotBlank(info.getYearName()))
        .collect(Collectors.toMap(YearInfo::getYearName, Function.identity(), (oldValue, newValue) -> newValue));
    yearInfoList.stream().forEach(yearInfo -> {
      String yearId = yearInfo.getYearId();
      if (StringUtils.isBlank(yearId)) {
        GloabalUtils.convertMessage(GlobalEnum.PkIdEmpty);
      }
      String yearName = yearInfo.getYearName();
      if (yearInfoMap.containsKey(yearName) && !Objects.equals(yearId, yearInfoMap.get(yearName).getYearId())) {
        GloabalUtils.convertMessage(GlobalEnum.TeacherNumInUsed, yearName);
      }
    });
    Integer updateCount = yearInfoMapper.update(yearInfoList);
    if (updateCount > 0) {
      return ResultUtil.success(GlobalEnum.UpdateSuccess, yearInfoList);
    }
    return ResultUtil.error(GlobalEnum.UpdateError);
  }
}