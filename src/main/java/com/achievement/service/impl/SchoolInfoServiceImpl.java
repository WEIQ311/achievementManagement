package com.achievement.service.impl;

import com.achievement.entity.SchoolInfo;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.SchoolInfoMapper;
import com.achievement.service.SchoolInfoService;
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
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 学校(SchoolInfo)ServiceImpl
 *
 * @author weiQiang
 * @since 2018-10-02 17:52:27
 */
@Service("schoolInfoService")
public class SchoolInfoServiceImpl implements SchoolInfoService {
  @Resource
  private SchoolInfoMapper schoolInfoMapper;

  /**
   * 学校(SchoolInfo)信息Map
   *
   * @param schoolInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public Map<String, SchoolInfo> convertRecordToMap(SchoolInfo schoolInfo) {
    List<SchoolInfo> schoolInfos = schoolInfoMapper.list(schoolInfo);
    Map<String, SchoolInfo> schoolInfoMap = schoolInfos.stream().filter(info -> StringUtils.isNotBlank(info.getScId()))
        .collect(Collectors.toMap(SchoolInfo::getScId, Function.identity(), (oldValue, newValue) -> newValue));
    return schoolInfoMap;
  }

  /**
   * 删除学校(SchoolInfo)
   *
   * @param scIds 主键集合
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity delete(List<String> scIds) {
    if (null == scIds || scIds.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    List<SchoolInfo> schoolInfoList = new ArrayList<SchoolInfo>() {{
      scIds.forEach(scId -> add(SchoolInfo.builder().scId(scId).build()));
    }};
    schoolInfoMapper.delete(schoolInfoList);
    return ResultUtil.success(GlobalEnum.DeleteSuccess, scIds);
  }

  /**
   * 增加学校(SchoolInfo)
   *
   * @param schoolInfoList 参数
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity insert(List<SchoolInfo> schoolInfoList) {
    if (null == schoolInfoList || schoolInfoList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, SchoolInfo> schoolInfoMap = convertRecordToMap(SchoolInfo.builder().build());
    schoolInfoList.stream().forEach(schoolInfo -> {
      String name = schoolInfo.getName();
      long doubleCount = schoolInfoMap.values().stream()
          .filter(info -> Objects.equals(name, info.getName()))
          .count();
      if (doubleCount > 0) {
        GloabalUtils.convertMessage(GlobalEnum.SchoolNameInUsed, name);
      }
      schoolInfo.setScId("sc_" + GloabalUtils.ordinaryId());
    });
    Integer insertCount = schoolInfoMapper.insert(schoolInfoList);
    if (insertCount > 0) {
      return ResultUtil.success(GlobalEnum.InsertSuccess, schoolInfoList);
    }
    return ResultUtil.error(GlobalEnum.InsertError);
  }

  /**
   * 根据条件分页查询
   *
   * @param schoolInfo 查询参数
   * @param pageNum    开始页数
   * @param pageSize   每页显示的数据条数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(SchoolInfo schoolInfo, int pageNum, int pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    List<SchoolInfo> schoolInfoPage = schoolInfoMapper.list(schoolInfo);
    PageInfo pageInfo = new PageInfo(schoolInfoPage);
    return ResultUtil.success(GlobalEnum.QuerySuccess, pageInfo);
  }

  /**
   * 根据条件查询
   *
   * @param schoolInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(SchoolInfo schoolInfo) {
    List<SchoolInfo> schoolInfos = schoolInfoMapper.list(schoolInfo);
    return ResultUtil.success(GlobalEnum.QuerySuccess, schoolInfos);
  }

  /**
   * 更新学校(SchoolInfo)
   *
   * @param schoolInfoList 更新参数
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity update(List<SchoolInfo> schoolInfoList) {
    if (null == schoolInfoList || schoolInfoList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, SchoolInfo> schoolInfoMap = convertRecordToMap(SchoolInfo.builder().build());
    schoolInfoList.stream().forEach(schoolInfo -> {
      String scId = schoolInfo.getScId();
      String name = schoolInfo.getName();
      if (StringUtils.isBlank(scId)) {
        GloabalUtils.convertMessage(GlobalEnum.PkIdEmpty);
      }
      long doubleCount = schoolInfoMap.values().stream()
          .filter(info -> Objects.equals(name, info.getName()) && !Objects.equals(scId, info.getScId()))
          .count();
      if (doubleCount > 0) {
        GloabalUtils.convertMessage(GlobalEnum.SchoolNameInUsed, name);
      }
    });
    Integer updateCount = schoolInfoMapper.update(schoolInfoList);
    if (updateCount > 0) {
      return ResultUtil.success(GlobalEnum.UpdateSuccess, schoolInfoList);
    }
    return ResultUtil.error(GlobalEnum.UpdateError);
  }
}