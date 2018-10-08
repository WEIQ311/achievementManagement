package com.achievement.service.impl;

import com.achievement.entity.SemesterInfo;
import com.achievement.entity.YearInfo;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.SemesterInfoMapper;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.achievement.constants.GlobalConstants.INTERVAL_NUMBER;

/**
 * 学期(SemesterInfo)ServiceImpl
 *
 * @author weiQiang
 * @since 2018-10-02 17:52:27
 */
@Service("semesterInfoService")
public class SemesterInfoServiceImpl implements SemesterInfoService {
  @Resource
  private SemesterInfoMapper semesterInfoMapper;
  @Autowired
  private YearInfoService yearInfoService;

  /**
   * 学期(SemesterInfo)信息Map
   *
   * @param semesterInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public Map<String, SemesterInfo> convertRecordToMap(SemesterInfo semesterInfo) {
    List<SemesterInfo> scoreInfos = semesterInfoMapper.list(semesterInfo);
    Map<String, SemesterInfo> semesterInfoMap = scoreInfos.stream().filter(info -> StringUtils.isNotBlank(info.getSemesterId()))
        .collect(Collectors.toMap(SemesterInfo::getSemesterId, Function.identity(), (oldValue, newValue) -> newValue));
    return semesterInfoMap;
  }

  /**
   * 删除学期(SemesterInfo)
   *
   * @param semesterIds 主键集合
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity delete(List<String> semesterIds) {
    if (null == semesterIds || semesterIds.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    semesterInfoMapper.delete(semesterIds);
    return ResultUtil.success(GlobalEnum.DeleteSuccess, semesterIds);
  }

  /**
   * 增加学期(SemesterInfo)
   *
   * @param semesterInfoList 参数
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity insert(List<SemesterInfo> semesterInfoList) {
    if (null == semesterInfoList || semesterInfoList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, YearInfo> yearInfoMap = yearInfoService.convertRecordToMap(YearInfo.builder().build());
    Map<String, SemesterInfo> semesterInfoMap = convertSemesterNameAndYearIdMap(SemesterInfo.builder().build());
    semesterInfoList.stream().forEach(semesterInfo -> {
      String yearId = semesterInfo.getYearId();
      String semesterName = semesterInfo.getSemesterName();
      if (!yearInfoMap.containsKey(yearId)) {
        GloabalUtils.convertMessage(GlobalEnum.YearIdError, semesterName);
      }
      String yearName = yearInfoMap.get(yearId).getYearName();
      String key = semesterName + INTERVAL_NUMBER + yearId;
      if (semesterInfoMap.containsKey(key)) {
        GloabalUtils.convertMessage(GlobalEnum.SemesterNameInUsed, semesterName, yearName);
      }
      convertDeadLineTime(semesterInfo);
      semesterInfo.setSemesterId("semester_" + GloabalUtils.ordinaryId());
    });
    Integer insertCount = semesterInfoMapper.insert(semesterInfoList);
    if (insertCount > 0) {
      return ResultUtil.success(GlobalEnum.InsertSuccess, semesterInfoList);
    }
    return ResultUtil.error(GlobalEnum.InsertError);
  }

  /**
   * 学期(SemesterInfo)信息Map
   *
   * @param semesterInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public Map<String, SemesterInfo> convertSemesterNameAndYearIdMap(SemesterInfo semesterInfo) {
    List<SemesterInfo> scoreInfos = semesterInfoMapper.list(semesterInfo);
    Map<String, SemesterInfo> semesterInfoMap = scoreInfos.stream()
        .filter(info -> StringUtils.isNotBlank(info.getSemesterName()) && StringUtils.isNotBlank(info.getYearId()))
        .collect(Collectors.toMap(info -> info.getSemesterName() + INTERVAL_NUMBER + info.getYearId(), Function.identity(), (oldValue, newValue) -> newValue));
    return semesterInfoMap;
  }

  /**
   * 判断添加考试成绩开始与截至时间是否合法
   *
   * @param semesterInfo 学期信息
   */
  private void convertDeadLineTime(SemesterInfo semesterInfo) {
    Date scoreEndDeadline = semesterInfo.getScoreEndDeadline();
    Date scoreBeginDeadline = semesterInfo.getScoreBeginDeadline();
    long endDeadlineTime = scoreEndDeadline.getTime();
    long beginDeadlineTime = scoreBeginDeadline.getTime();
    long timeMillis = System.currentTimeMillis();
    if (timeMillis > endDeadlineTime) {
      GloabalUtils.convertMessage(GlobalEnum.EndDeadlineTime);
    }
    if (beginDeadlineTime >= endDeadlineTime) {
      GloabalUtils.convertMessage(GlobalEnum.BeginDeadlineTime);
    }
  }

  /**
   * 根据条件分页查询
   *
   * @param semesterInfo 查询参数
   * @param pageNum      开始页数
   * @param pageSize     每页显示的数据条数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(SemesterInfo semesterInfo, int pageNum, int pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    List<SemesterInfo> semesterInfos = semesterInfoMapper.list(semesterInfo);
    convertResult(semesterInfos);
    PageInfo pageInfo = new PageInfo(semesterInfos);
    return ResultUtil.success(GlobalEnum.QuerySuccess, pageInfo);
  }

  /**
   * 根据条件查询
   *
   * @param semesterInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(SemesterInfo semesterInfo) {
    List<SemesterInfo> semesterInfos = semesterInfoMapper.list(semesterInfo);
    convertResult(semesterInfos);
    return ResultUtil.success(GlobalEnum.QuerySuccess, semesterInfos);
  }

  /**
   * 更新学期(SemesterInfo)
   *
   * @param semesterInfoList 更新参数
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity update(List<SemesterInfo> semesterInfoList) {
    if (null == semesterInfoList || semesterInfoList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, YearInfo> yearInfoMap = yearInfoService.convertRecordToMap(YearInfo.builder().build());
    Map<String, SemesterInfo> semesterInfoMap = convertSemesterNameAndYearIdMap(SemesterInfo.builder().build());
    semesterInfoList.stream().forEach(semesterInfo -> {
      String semesterId = semesterInfo.getSemesterId();
      String yearId = semesterInfo.getYearId();
      String semesterName = semesterInfo.getSemesterName();
      if (StringUtils.isBlank(semesterId)) {
        GloabalUtils.convertMessage(GlobalEnum.PkIdEmpty);
      }
      if (!yearInfoMap.containsKey(yearId)) {
        GloabalUtils.convertMessage(GlobalEnum.YearIdError, semesterName);
      }
      String yearName = yearInfoMap.get(yearId).getYearName();
      String key = semesterName + INTERVAL_NUMBER + yearId;
      if (semesterInfoMap.containsKey(key) && !Objects.equals(semesterId, semesterInfoMap.get(key).getSemesterId())) {
        GloabalUtils.convertMessage(GlobalEnum.SemesterNameInUsed, semesterName, yearName);
      }
      convertDeadLineTime(semesterInfo);
    });
    Integer updateCount = semesterInfoMapper.update(semesterInfoList);
    if (updateCount > 0) {
      return ResultUtil.success(GlobalEnum.UpdateSuccess, semesterInfoList);
    }
    return ResultUtil.error(GlobalEnum.UpdateError);
  }

  /**
   * 转换查询结果
   *
   * @param semesterInfoList 学期信息
   */
  private void convertResult(List<SemesterInfo> semesterInfoList) {
    if (null == semesterInfoList || semesterInfoList.size() < 1) {
      return;
    }
    Map<String, YearInfo> yearInfoMap = yearInfoService.convertRecordToMap(YearInfo.builder().build());
    semesterInfoList.stream().forEach(semesterInfo -> {
      String yearId = semesterInfo.getYearId();
      if (yearInfoMap.containsKey(yearId)) {
        semesterInfo.setYearInfo(yearInfoMap.get(yearId));
      }
    });
  }
}