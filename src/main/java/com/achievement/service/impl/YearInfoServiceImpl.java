package com.achievement.service.impl;

import com.achievement.entity.YearInfo;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.YearInfoMapper;
import com.achievement.service.YearInfoService;
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
 * 学年(YearInfo)ServiceImpl
 *
 * @author weiQiang
 * @since 2018-10-02 17:52:27
 */
@Service("yearInfoService")
public class YearInfoServiceImpl implements YearInfoService {
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
    List<YearInfo> yearInfoList = new ArrayList<YearInfo>() {{
      yearIds.forEach(yearId -> add(YearInfo.builder().yearId(yearId).build()));
    }};
    yearInfoMapper.delete(yearInfoList);
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
    yearInfoList.stream().forEach(yearInfo -> yearInfo.setYearId("year_" + GloabalUtils.ordinaryId()));
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
    Integer updateCount = yearInfoMapper.update(yearInfoList);
    if (updateCount > 0) {
      return ResultUtil.success(GlobalEnum.UpdateSuccess, yearInfoList);
    }
    return ResultUtil.error(GlobalEnum.UpdateError);
  }
}