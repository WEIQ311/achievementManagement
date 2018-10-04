package com.achievement.service.impl;

import com.achievement.entity.SubjectInfo;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.SubjectInfoMapper;
import com.achievement.service.SubjectInfoService;
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
 * 学科(SubjectInfo)ServiceImpl
 *
 * @author weiQiang
 * @since 2018-10-02 17:52:27
 */
@Service("subjectInfoService")
public class SubjectInfoServiceImpl implements SubjectInfoService {
  @Resource
  private SubjectInfoMapper subjectInfoMapper;

  /**
   * 学科(SubjectInfo)信息Map
   *
   * @param subjectInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public Map<String, SubjectInfo> convertRecordToMap(SubjectInfo subjectInfo) {
    List<SubjectInfo> subjectInfoList = subjectInfoMapper.list(subjectInfo);
    Map<String, SubjectInfo> studentInfoMap = subjectInfoList.stream().filter(info -> StringUtils.isNotBlank(info.getSubjectId()))
        .collect(Collectors.toMap(SubjectInfo::getSubjectId, Function.identity(), (oldValue, newValue) -> newValue));
    return studentInfoMap;
  }

  /**
   * 删除学科(SubjectInfo)
   *
   * @param subjectIds 主键集合
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity delete(List<String> subjectIds) {
    if (null == subjectIds || subjectIds.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    List<SubjectInfo> subjectInfoList = new ArrayList<SubjectInfo>() {{
      subjectIds.forEach(subjectId -> add(SubjectInfo.builder().subjectId(subjectId).build()));
    }};
    subjectInfoMapper.delete(subjectInfoList);
    return ResultUtil.success(GlobalEnum.DeleteSuccess, subjectIds);
  }

  /**
   * 增加学科(SubjectInfo)
   *
   * @param subjectInfoList 参数
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity insert(List<SubjectInfo> subjectInfoList) {
    if (null == subjectInfoList || subjectInfoList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, SubjectInfo> subjectInfoMap = convertRecordToMap(SubjectInfo.builder().build()).values().stream()
        .filter(info -> StringUtils.isNotBlank(info.getSubjectName()))
        .collect(Collectors.toMap(SubjectInfo::getSubjectName, Function.identity(), (oldValue, newValue) -> newValue));
    subjectInfoList.stream().forEach(subjectInfo -> {
      String subjectName = subjectInfo.getSubjectName();
      if (subjectInfoMap.containsKey(subjectName)) {
        GloabalUtils.convertMessage(GlobalEnum.SubjectNameInUsed, subjectName);
      }
      subjectInfo.setSubjectId("subject_" + GloabalUtils.ordinaryId());
    });
    Integer insertCount = subjectInfoMapper.insert(subjectInfoList);
    if (insertCount > 0) {
      return ResultUtil.success(GlobalEnum.InsertSuccess, subjectInfoList);
    }
    return ResultUtil.error(GlobalEnum.InsertError);
  }

  /**
   * 根据条件分页查询
   *
   * @param subjectInfo 查询参数
   * @param pageNum     开始页数
   * @param pageSize    每页显示的数据条数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(SubjectInfo subjectInfo, int pageNum, int pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    List<SubjectInfo> subjectInfoPage = subjectInfoMapper.list(subjectInfo);
    PageInfo pageInfo = new PageInfo(subjectInfoPage);
    return ResultUtil.success(GlobalEnum.QuerySuccess, pageInfo);
  }

  /**
   * 根据条件查询
   *
   * @param subjectInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(SubjectInfo subjectInfo) {
    List<SubjectInfo> subjectInfos = subjectInfoMapper.list(subjectInfo);
    return ResultUtil.success(GlobalEnum.QuerySuccess, subjectInfos);
  }

  /**
   * 更新学科(SubjectInfo)
   *
   * @param subjectInfoList 更新参数
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity update(List<SubjectInfo> subjectInfoList) {
    if (null == subjectInfoList || subjectInfoList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, SubjectInfo> subjectInfoMap = convertRecordToMap(SubjectInfo.builder().build()).values().stream()
        .filter(info -> StringUtils.isNotBlank(info.getSubjectName()))
        .collect(Collectors.toMap(SubjectInfo::getSubjectName, Function.identity(), (oldValue, newValue) -> newValue));
    subjectInfoList.stream().forEach(subjectInfo -> {
      String subjectId = subjectInfo.getSubjectId();
      if (StringUtils.isBlank(subjectId)) {
        GloabalUtils.convertMessage(GlobalEnum.PkIdEmpty);
      }
      String subjectName = subjectInfo.getSubjectName();
      if (subjectInfoMap.containsKey(subjectName) && !Objects.equals(subjectId, subjectInfoMap.get(subjectName).getSubjectId())) {
        GloabalUtils.convertMessage(GlobalEnum.SubjectNameInUsed, subjectName);
      }
    });
    Integer updateCount = subjectInfoMapper.update(subjectInfoList);
    if (updateCount > 0) {
      return ResultUtil.success(GlobalEnum.UpdateSuccess, subjectInfoList);
    }
    return ResultUtil.error(GlobalEnum.UpdateError);
  }
}