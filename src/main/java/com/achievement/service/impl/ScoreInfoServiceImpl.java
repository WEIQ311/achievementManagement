package com.achievement.service.impl;

import com.achievement.entity.ScoreInfo;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.ScoreInfoMapper;
import com.achievement.service.ScoreInfoService;
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
 * 成绩(ScoreInfo)ServiceImpl
 *
 * @author weiQiang
 * @since 2018-10-02 17:52:27
 */
@Service("scoreInfoService")
public class ScoreInfoServiceImpl implements ScoreInfoService {
  @Resource
  private ScoreInfoMapper scoreInfoMapper;

  /**
   * 成绩(ScoreInfo)信息Map
   *
   * @param scoreInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public Map<String, ScoreInfo> convertRecordToMap(ScoreInfo scoreInfo) {
    List<ScoreInfo> scoreInfos = scoreInfoMapper.list(scoreInfo);
    Map<String, ScoreInfo> scoreInfoMap = scoreInfos.stream().filter(info -> StringUtils.isNotBlank(info.getScoreId()))
        .collect(Collectors.toMap(ScoreInfo::getScoreId, Function.identity(), (oldValue, newValue) -> newValue));
    return scoreInfoMap;
  }

  /**
   * 删除成绩(ScoreInfo)
   *
   * @param scoreIds 主键集合
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity delete(List<String> scoreIds) {
    if (null == scoreIds || scoreIds.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    List<ScoreInfo> scoreInfoList = new ArrayList<ScoreInfo>() {{
      scoreIds.forEach(scoreId -> {
        add(ScoreInfo.builder().scoreId(scoreId).build());
      });
    }};
    scoreInfoMapper.delete(scoreInfoList);
    return ResultUtil.success(GlobalEnum.DeleteSuccess, scoreIds);
  }

  /**
   * 增加成绩(ScoreInfo)
   *
   * @param scoreInfoList 参数
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity insert(List<ScoreInfo> scoreInfoList) {
    if (null == scoreInfoList || scoreInfoList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    scoreInfoList.stream().forEach(scoreInfo -> scoreInfo.setScoreId("score_" + GloabalUtils.ordinaryId()));
    Integer insertCount = scoreInfoMapper.insert(scoreInfoList);
    if (insertCount > 0) {
      return ResultUtil.success(GlobalEnum.InsertSuccess, scoreInfoList);
    }
    return ResultUtil.error(GlobalEnum.InsertError);
  }

  /**
   * 根据条件分页查询
   *
   * @param scoreInfo 查询参数
   * @param pageNum   开始页数
   * @param pageSize  每页显示的数据条数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(ScoreInfo scoreInfo, int pageNum, int pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    List<ScoreInfo> scoreInfoPage = scoreInfoMapper.list(scoreInfo);
    PageInfo pageInfo = new PageInfo(scoreInfoPage);
    return ResultUtil.success(GlobalEnum.QuerySuccess, pageInfo);
  }

  /**
   * 根据条件查询
   *
   * @param scoreInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(ScoreInfo scoreInfo) {
    List<ScoreInfo> scoreInfos = scoreInfoMapper.list(scoreInfo);
    return ResultUtil.success(GlobalEnum.QuerySuccess, scoreInfos);
  }

  /**
   * 更新成绩(ScoreInfo)
   *
   * @param scoreInfoList 更新参数
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity update(List<ScoreInfo> scoreInfoList) {
    if (null == scoreInfoList || scoreInfoList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Integer updateCount = scoreInfoMapper.update(scoreInfoList);
    if (updateCount > 0) {
      return ResultUtil.success(GlobalEnum.UpdateSuccess, scoreInfoList);
    }
    return ResultUtil.error(GlobalEnum.UpdateError);
  }
}