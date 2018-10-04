package com.achievement.service.impl;

import com.achievement.entity.*;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.ScoreInfoMapper;
import com.achievement.service.*;
import com.achievement.utils.GloabalUtils;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ResultEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.achievement.constants.GlobalConstants.OPERATE_TYPE_INSERT;
import static com.achievement.constants.GlobalConstants.OPERATE_TYPE_UPDATE;

/**
 * 成绩(ScoreInfo)ServiceImpl
 *
 * @author weiQiang
 * @since 2018-10-02 17:52:27
 */
@Service("scoreInfoService")
public class ScoreInfoServiceImpl implements ScoreInfoService {
  @Autowired
  private ClassInfoService classInfoService;
  @Value("${achievement.delete.score}")
  private boolean deleteScore;
  @Resource
  private ScoreInfoMapper scoreInfoMapper;
  @Autowired
  private SemesterInfoService semesterInfoService;
  @Autowired
  private StudentInfoService studentInfoService;
  @Autowired
  private SubjectInfoService subjectInfoService;
  @Autowired
  private TeacherInfoService teacherInfoService;

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
    if (!deleteScore) {
      return ResultUtil.error(GlobalEnum.DeleteNoSupport);
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
    checkScoreInfo(scoreInfoList, OPERATE_TYPE_INSERT);
    Integer insertCount = scoreInfoMapper.insert(scoreInfoList);
    if (insertCount > 0) {
      return ResultUtil.success(GlobalEnum.InsertSuccess, scoreInfoList);
    }
    return ResultUtil.error(GlobalEnum.InsertError);
  }

  /**
   * 检查成绩中的参数是否合法
   *
   * @param scoreInfoList 成绩数据
   * @param operateType   操作类型
   */
  private void checkScoreInfo(List<ScoreInfo> scoreInfoList, String operateType) {
    Map<String, ClassInfo> classInfoMap = classInfoService.convertRecordToMap(ClassInfo.builder().build());
    Map<String, StudentInfo> studentInfoMap = studentInfoService.convertRecordToMap(StudentInfo.builder().build());
    Map<String, SubjectInfo> subjectInfoMap = subjectInfoService.convertRecordToMap(SubjectInfo.builder().build());
    Map<String, TeacherInfo> teacherInfoMap = teacherInfoService.convertRecordToMap(TeacherInfo.builder().build());
    Map<String, SemesterInfo> semesterInfoMap = semesterInfoService.convertRecordToMap(SemesterInfo.builder().build());
    scoreInfoList.stream().forEach(scoreInfo -> {
      String classId = scoreInfo.getClassId();
      String studentId = scoreInfo.getStudentId();
      String subjectId = scoreInfo.getSubjectId();
      String teacherId = scoreInfo.getTeacherId();
      String semesterId = scoreInfo.getSemesterId();
      if (StringUtils.isBlank(classId)) {
        GloabalUtils.convertMessage(GlobalEnum.ClassIdEmpty);
      }
      if (StringUtils.isBlank(studentId)) {
        GloabalUtils.convertMessage(GlobalEnum.StudentIdEmpty);
      }
      if (StringUtils.isBlank(subjectId)) {
        GloabalUtils.convertMessage(GlobalEnum.SubjectIdEmpty);
      }
      if (StringUtils.isBlank(teacherId)) {
        GloabalUtils.convertMessage(GlobalEnum.TeacherIdEmpty);
      }
      if (StringUtils.isBlank(semesterId)) {
        GloabalUtils.convertMessage(GlobalEnum.SemesterIdEmpty);
      }
      if (!classInfoMap.containsKey(classId)) {
        GloabalUtils.convertMessage(GlobalEnum.ClassInfoEmpty, classId);
      }
      if (!studentInfoMap.containsKey(studentId)) {
        GloabalUtils.convertMessage(GlobalEnum.StudentInfoEmpty, studentId);
      }
      if (!subjectInfoMap.containsKey(subjectId)) {
        GloabalUtils.convertMessage(GlobalEnum.SubjectInfoEmpty, subjectId);
      }
      if (!teacherInfoMap.containsKey(teacherId)) {
        GloabalUtils.convertMessage(GlobalEnum.TeacherInfoEmpty, teacherId);
      }
      if (!semesterInfoMap.containsKey(semesterId)) {
        GloabalUtils.convertMessage(GlobalEnum.SemesterInfoEmpty, semesterId);
      }
      if (Objects.equals(OPERATE_TYPE_INSERT, operateType)) {
        scoreInfo.setScoreId("score_" + GloabalUtils.ordinaryId());
      } else {
        String scoreId = scoreInfo.getScoreId();
        if (StringUtils.isBlank(scoreId)) {
          GloabalUtils.convertMessage(GlobalEnum.PkIdEmpty);
        }
      }
    });
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
    if (null != scoreInfo && StringUtils.isNotBlank(scoreInfo.getOrderColumn())) {
      PageHelper.orderBy(GloabalUtils.changeColumn(scoreInfo.getOrderColumn(), scoreInfo.getOrderDirection()));
    }
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
    checkScoreInfo(scoreInfoList, OPERATE_TYPE_UPDATE);
    Integer updateCount = scoreInfoMapper.update(scoreInfoList);
    if (updateCount > 0) {
      return ResultUtil.success(GlobalEnum.UpdateSuccess, scoreInfoList);
    }
    return ResultUtil.error(GlobalEnum.UpdateError);
  }
}