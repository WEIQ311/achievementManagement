package com.achievement.service.impl;

import com.achievement.entity.*;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.SubjectScoreInfoMapper;
import com.achievement.service.*;
import com.achievement.utils.GloabalUtils;
import com.achievement.utils.PoiUtil;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ParentStudentScore;
import com.achievement.vo.ResultEntity;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.achievement.constants.GlobalConstants.*;

/**
 * (SubjectScoreInfo)表服务实现类
 *
 * @author 魏强
 * @since 2018-10-10 16:15:15
 */
@Slf4j
@Service("subjectScoreInfoService")
public class SubjectScoreInfoServiceImpl implements SubjectScoreInfoService {
  @Autowired
  private ClassInfoService classInfoService;
  @Autowired
  private ConfStudentParentService confStudentParentService;
  @Autowired
  private ConfTeacherClassService confTeacherClassService;
  @Value("${achievement.delete.score}")
  private boolean deleteScore;
  @Autowired
  private GradeInfoService gradeInfoService;
  @Autowired
  private ParentInfoService parentInfoService;
  @Autowired
  private SemesterInfoService semesterInfoService;
  @Autowired
  private StudentInfoService studentInfoService;
  @Autowired
  private SubjectInfoService subjectInfoService;
  @Resource
  private SubjectScoreInfoMapper subjectScoreInfoMapper;
  @Autowired
  private TeacherInfoService teacherInfoService;

  /**
   * 对象信息Map
   *
   * @param subjectScoreInfo 查询参数
   * @return Map
   */
  @Override
  public Map<String, SubjectScoreInfo> convertRecordToMap(SubjectScoreInfo subjectScoreInfo) {
    List<SubjectScoreInfo> subjectScoreInfos = subjectScoreInfoMapper.list(subjectScoreInfo);
    Map<String, SubjectScoreInfo> scoreInfoMap = subjectScoreInfos.stream().filter(info -> StringUtils.isNotBlank(info.getId()))
        .collect(Collectors.toMap(SubjectScoreInfo::getId, Function.identity(), (oldValue, newValue) -> newValue));
    return scoreInfoMap;
  }

  /**
   * 删除对象
   *
   * @param pkIds 对象主键集合
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity delete(List<String> pkIds) {
    if (null == pkIds || pkIds.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    if (!deleteScore) {
      return ResultUtil.error(GlobalEnum.DeleteNoSupport);
    }
    subjectScoreInfoMapper.delete(pkIds);
    return ResultUtil.success(GlobalEnum.DeleteSuccess, pkIds);
  }

  /**
   * 增加对象
   *
   * @param subjectScoreInfos 对象参数
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity insert(List<SubjectScoreInfo> subjectScoreInfos) {
    if (null == subjectScoreInfos || subjectScoreInfos.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    return insertOrUpdateScoreInfo(subjectScoreInfos, OPERATE_TYPE_INSERT);
  }

  /**
   * 根据条件分页查询对象
   *
   * @param scoreInfo 查询参数
   * @param pageNum   开始页数
   * @param pageSize  每页显示的数据条数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(SubjectScoreInfo scoreInfo, int pageNum, int pageSize) {
    scoreInfo = convertQueryParam(scoreInfo);
    PageHelper.startPage(pageNum, pageSize);
    if (null != scoreInfo && StringUtils.isNotBlank(scoreInfo.getOrderColumn())) {
      PageHelper.orderBy(GloabalUtils.changeColumn(scoreInfo.getOrderColumn(), scoreInfo.getOrderDirection()));
    }
    List<SubjectScoreInfo> subjectScoreInfos = subjectScoreInfoMapper.list(scoreInfo);
    convertScoreInfo(subjectScoreInfos, scoreInfo);
    PageInfo pageInfo = new PageInfo(subjectScoreInfos);
    return ResultUtil.success(GlobalEnum.QuerySuccess, pageInfo);
  }

  /**
   * 转换请求参数
   *
   * @param subjectScoreInfo 请求参数
   * @return SubjectScoreInfo
   */
  public SubjectScoreInfo convertQueryParam(SubjectScoreInfo subjectScoreInfo) {
    if (null == subjectScoreInfo) {
      subjectScoreInfo = SubjectScoreInfo.builder().build();
    }
    String studentName = subjectScoreInfo.getStudentName();
    String studentNum = subjectScoreInfo.getStudentNum();
    if (StringUtils.isNotBlank(studentName) || StringUtils.isNotBlank(studentNum)) {
      List<String> studentIds = studentInfoService.convertRecordToMap(StudentInfo.builder().studentName(studentName).studentNum(studentNum).build())
          .keySet().stream().collect(Collectors.toList());
      subjectScoreInfo.setStudentIds(studentIds);
    }
    return subjectScoreInfo;
  }

  /**
   * 转换班级内最高、平均、最低成绩信息
   *
   * @param subjectScoreInfos 成绩结果
   * @param scoreInfo         查询成绩信息
   */
  private void convertScoreInfo(List<SubjectScoreInfo> subjectScoreInfos, SubjectScoreInfo scoreInfo) {
    if (null == subjectScoreInfos || subjectScoreInfos.size() < 1) {
      return;
    }
    Map<String, SubjectScoreInfo> scoreInfoMap = listRankingMap(scoreInfo);
    Map<String, StudentInfo> studentInfoMap = studentInfoService.convertRecordToMap(StudentInfo.builder().build());
    List<SubjectScoreInfo> classScoreInfo = subjectScoreInfoMapper.listClassScoreInfo(scoreInfo);
    List<SubjectScoreInfo> gradeScoreInfo = subjectScoreInfoMapper.listGradeScoreInfo(scoreInfo);
    Map<String, GradeInfo> gradeInfoMap = gradeInfoService.convertRecordToMap(GradeInfo.builder().build());
    Map<String, ClassInfo> classInfoMap = classInfoService.convertRecordToMap(ClassInfo.builder().build());
    Map<String, SubjectScoreInfo> classScoreMap = classScoreInfo.stream()
        .collect(Collectors.toMap(info -> {
          String classId = info.getClassId();
          String semesterId = info.getSemesterId();
          return classId + INTERVAL_NUMBER + semesterId;
        }, Function.identity(), (oldValue, newValue) -> newValue));
    Map<String, SubjectScoreInfo> gradeScoreMap = gradeScoreInfo.stream()
        .collect(Collectors.toMap(info -> {
          String gradeId = info.getGradeId();
          String semesterId = info.getSemesterId();
          return gradeId + INTERVAL_NUMBER + semesterId;
        }, Function.identity(), (oldValue, newValue) -> newValue));
    subjectScoreInfos.stream().forEach(info -> {
      String scoreId = info.getId();
      String classId = info.getClassId();
      String gradeId = info.getGradeId();
      info.setClassName(classInfoMap.getOrDefault(classId, ClassInfo.builder().className(classId).build()).getClassName());
      info.setGradeName(gradeInfoMap.getOrDefault(gradeId, GradeInfo.builder().gradeName(gradeId).build()).getGradeName());
      String semesterId = info.getSemesterId();
      String classKey = classId + INTERVAL_NUMBER + semesterId;
      String gradeKey = gradeId + INTERVAL_NUMBER + semesterId;
      if (classScoreMap.containsKey(classKey)) {
        SubjectScoreInfo subjectScoreInfo = classScoreMap.get(classKey);
        Double maxScore = subjectScoreInfo.getMaxScore();
        Double minScore = subjectScoreInfo.getMinScore();
        Double avgScore = subjectScoreInfo.getAvgScore();
        info.setMaxScore(maxScore);
        info.setMinScore(minScore);
        info.setAvgScore(avgScore);
      }
      if (gradeScoreMap.containsKey(gradeKey)) {
        SubjectScoreInfo subjectScoreInfo = gradeScoreMap.get(gradeKey);
        Double gradeMaxScore = subjectScoreInfo.getGradeMaxScore();
        Double gradeMinScore = subjectScoreInfo.getGradeMinScore();
        Double gradeAvgScore = subjectScoreInfo.getGradeAvgScore();
        info.setGradeMaxScore(gradeMaxScore);
        info.setGradeMinScore(gradeMinScore);
        info.setGradeAvgScore(gradeAvgScore);
      }
      Integer classRanking = scoreInfoMap.get(scoreId).getClassRanking();
      Integer gradeRanking = scoreInfoMap.get(scoreId).getGradeRanking();
      info.setClassRanking(classRanking);
      info.setGradeRanking(gradeRanking);
      String studentId = info.getStudentId();
      StudentInfo studentInfo = studentInfoMap.getOrDefault(studentId, StudentInfo.builder().studentNum(studentId).studentName(studentId).build());
      info.setStudentNum(studentInfo.getStudentNum());
      info.setStudentName(studentInfo.getStudentName());
      info.setGradeClassName(info.getGradeName() + DEFAULT_PREFIX + info.getClassName() + DEFAULT_SUFFIX);
    });
  }

  /**
   * 根据条件查询对象
   *
   * @param subjectScoreInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(SubjectScoreInfo subjectScoreInfo) {
    subjectScoreInfo = convertQueryParam(subjectScoreInfo);
    List<SubjectScoreInfo> subjectScoreInfos = subjectScoreInfoMapper.list(subjectScoreInfo);
    convertScoreInfo(subjectScoreInfos, subjectScoreInfo);
    return ResultUtil.success(GlobalEnum.QuerySuccess, subjectScoreInfos);

  }

  /**
   * 更新对象
   *
   * @param subjectScoreInfos 更新参数
   * @return ResultEntity
   */
  @Override
  public ResultEntity update(List<SubjectScoreInfo> subjectScoreInfos) {
    if (null == subjectScoreInfos || subjectScoreInfos.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    return insertOrUpdateScoreInfo(subjectScoreInfos, OPERATE_TYPE_UPDATE);
  }

  /**
   * 增加或更新学生成绩
   *
   * @param scoreInfoList 学生成绩
   * @param operateType   操作类型
   * @return
   */
  private ResultEntity insertOrUpdateScoreInfo(List<SubjectScoreInfo> scoreInfoList, String operateType) {
    if (null == scoreInfoList || scoreInfoList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    List<SubjectScoreInfo> insertSubjectScoreInfoList = new ArrayList<>();
    List<SubjectScoreInfo> updateSubjectScoreInfoList = new ArrayList<>();
    long currentTimeMillis = System.currentTimeMillis();
    List<SubjectScoreInfo> subjectScoreInfos = subjectScoreInfoMapper.list(SubjectScoreInfo.builder().build());
    Map<String, ClassInfo> classInfoMap = classInfoService.convertRecordToMap(ClassInfo.builder().build());
    Map<String, StudentInfo> studentInfoMap = studentInfoService.convertRecordToMap(StudentInfo.builder().build());
    Map<String, SubjectInfo> subjectInfoMap = subjectInfoService.convertRecordToMap(SubjectInfo.builder().build());
    Map<String, TeacherInfo> teacherInfoMap = teacherInfoService.convertRecordToMap(TeacherInfo.builder().build());
    Map<String, SemesterInfo> semesterInfoMap = semesterInfoService.convertRecordToMap(SemesterInfo.builder().build());
    Map<String, SubjectScoreInfo> scoreInfoMap = subjectScoreInfos.stream().collect(Collectors.toMap(subjectScoreInfo -> {
      String semesterId = subjectScoreInfo.getSemesterId();
      String classId = subjectScoreInfo.getClassId();
      String studentId = subjectScoreInfo.getStudentId();
      return semesterId + INTERVAL_NUMBER + classId + INTERVAL_NUMBER + studentId;
    }, Function.identity(), (oldValue, newValue) -> newValue));
    scoreInfoList.stream().forEach(scoreInfo -> {
      CheckSubjectScoreInfo checkSubjectScoreInfo = new CheckSubjectScoreInfo(classInfoMap, studentInfoMap, subjectInfoMap, teacherInfoMap, semesterInfoMap, scoreInfo).invoke();
      String classId = checkSubjectScoreInfo.getClassId();
      String studentId = checkSubjectScoreInfo.getStudentId();
      String semesterId = checkSubjectScoreInfo.getSemesterId();
      SemesterInfo semesterInfo = semesterInfoMap.get(semesterId);
      ClassInfo classInfo = classInfoMap.get(classId);
      scoreInfo.setClassType(classInfo.getClassType());
      scoreInfo.setGradeId(classInfo.getGradeId());
      scoreInfo.setYearId(semesterInfo.getYearId());
      Date scoreEndDeadline = semesterInfo.getScoreEndDeadline();
      Date scoreBeginDeadline = semesterInfo.getScoreBeginDeadline();
      if (null != scoreBeginDeadline) {
        long beginDeadlineTime = scoreBeginDeadline.getTime();
        if (beginDeadlineTime > currentTimeMillis) {
          String beginTime = DateFormatUtils.format(scoreBeginDeadline, DATE_TIME_FORMAT);
          GloabalUtils.convertMessage(GlobalEnum.NoBeginTime, beginTime);
        }
      }
      if (null != scoreEndDeadline) {
        long endDeadlineTime = scoreEndDeadline.getTime();
        if (currentTimeMillis > endDeadlineTime) {
          String enndTime = DateFormatUtils.format(scoreEndDeadline, DATE_TIME_FORMAT);
          GloabalUtils.convertMessage(GlobalEnum.NoEndTime, enndTime);
        }
      }
      if (Objects.equals(OPERATE_TYPE_INSERT, operateType)) {
        String key = semesterId + INTERVAL_NUMBER + classId + INTERVAL_NUMBER + studentId;
        if (scoreInfoMap.containsKey(key)) {
          String id = scoreInfoMap.get(key).getId();
          scoreInfo.setId(id);
          updateSubjectScoreInfoList.add(scoreInfo);
        } else {
          insertSubjectScoreInfoList.add(scoreInfo);
        }
      } else {
        String scoreId = scoreInfo.getId();
        if (StringUtils.isBlank(scoreId)) {
          GloabalUtils.convertMessage(GlobalEnum.PkIdEmpty);
        }
        updateSubjectScoreInfoList.add(scoreInfo);
      }
    });
    List<SubjectScoreInfo> insertSubjectScoreInfos = convertInsertSubjectScoreInfo(insertSubjectScoreInfoList);
    if (null != insertSubjectScoreInfos && insertSubjectScoreInfos.size() > 0) {
      Integer updateCount = subjectScoreInfoMapper.insert(insertSubjectScoreInfos);
      log.info("增加学生成绩:{}条", updateCount);
      if (updateCount > 0) {
        updateSubjectScoreSum(SubjectScoreInfo.builder().build());
        return ResultUtil.success(GlobalEnum.UpdateSuccess, insertSubjectScoreInfos);
      }
    }
    if (null != updateSubjectScoreInfoList && updateSubjectScoreInfoList.size() > 0) {
      Integer updateCount = subjectScoreInfoMapper.update(updateSubjectScoreInfoList);
      log.info("更新学生成绩:{}条", updateCount);
      if (updateCount > 0) {
        updateSubjectScoreSum(SubjectScoreInfo.builder().build());
        return ResultUtil.success(GlobalEnum.UpdateSuccess, updateSubjectScoreInfoList);
      }
    }
    return ResultUtil.success(GlobalEnum.UpdateError, scoreInfoList);
  }

  /**
   * 增加成绩时将成绩合并
   *
   * @param insertSubjectScoreInfos 成绩数据
   * @return List
   */
  private List<SubjectScoreInfo> convertInsertSubjectScoreInfo(List<SubjectScoreInfo> insertSubjectScoreInfos) {
    List<SubjectScoreInfo> subjectScoreInfos = new ArrayList<>();
    if (null != insertSubjectScoreInfos && insertSubjectScoreInfos.size() > 0) {
      Map<String, List<SubjectScoreInfo>> listMap = insertSubjectScoreInfos.stream().collect(Collectors.groupingBy(subjectScoreInfo -> {
        String classId = subjectScoreInfo.getClassId();
        String studentId = subjectScoreInfo.getStudentId();
        String semesterId = subjectScoreInfo.getSemesterId();
        String key = semesterId + INTERVAL_NUMBER + classId + INTERVAL_NUMBER + studentId;
        return key;
      }));
      listMap.forEach((key, subjectScoreInfoList) -> {
        final SubjectScoreInfo[] subjectScoreInfo = {SubjectScoreInfo.builder().build()};
        final int[] count = {0};
        subjectScoreInfoList.stream().forEach(info -> {
          if (count[0] == 0) {
            subjectScoreInfo[0] = JSON.parseObject(JSON.toJSONString(info), SubjectScoreInfo.class);
          } else {
            if (null != info.getSubLanguage()) {
              subjectScoreInfo[0].setSubLanguage(info.getSubLanguage());
            }
            if (null != info.getSubMathematics()) {
              subjectScoreInfo[0].setSubMathematics(info.getSubMathematics());
            }
            if (null != info.getSubEnglish()) {
              subjectScoreInfo[0].setSubEnglish(info.getSubEnglish());
            }
            if (null != info.getSubPhysical()) {
              subjectScoreInfo[0].setSubPhysical(info.getSubPhysical());
            }
            if (null != info.getSubHistory()) {
              subjectScoreInfo[0].setSubHistory(info.getSubHistory());
            }
            if (null != info.getSubGeography()) {
              subjectScoreInfo[0].setSubGeography(info.getSubGeography());
            }
            if (null != info.getSubBiological()) {
              subjectScoreInfo[0].setSubBiological(info.getSubBiological());
            }
            if (null != info.getSubChemistry()) {
              subjectScoreInfo[0].setSubChemistry(info.getSubChemistry());
            }
            if (null != info.getSubPolitical()) {
              subjectScoreInfo[0].setSubPolitical(info.getSubPolitical());
            }
            if (null != info.getSubComputer()) {
              subjectScoreInfo[0].setSubComputer(info.getSubComputer());
            }
            if (null != info.getSubSports()) {
              subjectScoreInfo[0].setSubSports(info.getSubSports());
            }
            if (null != info.getSubArt()) {
              subjectScoreInfo[0].setSubArt(info.getSubArt());
            }
            if (null != info.getSubMusic()) {
              subjectScoreInfo[0].setSubMusic(info.getSubMusic());
            }
          }
          count[0]++;
        });
        subjectScoreInfos.add(subjectScoreInfo[0]);
      });
      subjectScoreInfos.stream().forEach(subjectScoreInfo -> subjectScoreInfo.setId("score_" + GloabalUtils.ordinaryId()));
    }
    return subjectScoreInfos;
  }

  /**
   * 导出当前教师所带班级科目的学生成绩模板
   *
   * @param teacherInfo 教师信息
   * @param response    响应
   */
  @Override
  public void exportScoreTemplate(TeacherInfo teacherInfo, HttpServletResponse response) {
    String teacherId = teacherInfo.getTeacherId();
    StringBuilder fileNameBuilder = new StringBuilder("学生成绩信息");
    fileNameBuilder.append(UNDER_LINE);
    HSSFWorkbook workbook = new HSSFWorkbook();
    if (StringUtils.isNotBlank(teacherId)) {
      Map<String, SubjectInfo> subjectInfoMap = subjectInfoService.convertRecordToMap(SubjectInfo.builder().build());
      Map<String, TeacherInfo> teacherInfoMap = teacherInfoService.convertRecordToMap(TeacherInfo.builder().teacherId(teacherId).build());
      Map<String, ClassInfo> classInfoMap = classInfoService.convertRecordToMap(ClassInfo.builder().build());
      Map<String, GradeInfo> gradeInfoMap = gradeInfoService.convertRecordToMap(GradeInfo.builder().build());
      if (null == teacherInfoMap || teacherInfoMap.isEmpty()) {
        GloabalUtils.convertMessage(GlobalEnum.TeacherInfoEmpty, teacherId);
      }
      String teacherName = teacherInfoMap.get(teacherId).getTeacherName();
      Map<String, List<ConfTeacherClass>> teacherOfClassMap = confTeacherClassService.convertTeacherOfClassMap(ConfTeacherClass.builder().teacherId(teacherId).build());
      if (null == teacherOfClassMap || teacherOfClassMap.isEmpty()) {
        GloabalUtils.convertMessage(GlobalEnum.TeacherNoSubject, teacherName);
      }
      List<ConfTeacherClass> confTeacherClasses = teacherOfClassMap.get(teacherId);
      List<String> classIds = confTeacherClasses.stream().filter(confTeacherClass -> StringUtils.isNotBlank(confTeacherClass.getClassId()))
          .map(ConfTeacherClass::getClassId)
          .distinct().collect(Collectors.toList());
      Map<String, StudentInfo> studentInfoMap = studentInfoService.convertRecordToMap(StudentInfo.builder().classIds(classIds).build());
      Map<String, List<List<String>>> studentListMap = new HashMap<>(16);
      studentInfoMap.forEach((studentId, studentInfo) -> {
        List<List<String>> contentList = new ArrayList<>();
        String classId = studentInfo.getClassId();
        List<String> studentNameList = new ArrayList<String>() {{
          add(studentInfo.getStudentNum());
          add(studentInfo.getStudentName());
          add(new String());
        }};
        contentList.add(studentNameList);
        if (studentListMap.containsKey(classId)) {
          List<List<String>> lists = studentListMap.get(classId);
          contentList.addAll(lists);
          studentListMap.put(classId, contentList);
        } else {
          studentListMap.put(classId, contentList);
        }
      });
      for (int i = 0; i < confTeacherClasses.size(); i++) {
        ConfTeacherClass confTeacherClass = confTeacherClasses.get(i);
        String subjectId = confTeacherClass.getSubjectId();
        if (!subjectInfoMap.containsKey(subjectId)) {
          GloabalUtils.convertMessage(GlobalEnum.SubjectInfoEmpty, subjectId);
        }
        String subjectName = subjectInfoMap.getOrDefault(subjectId, SubjectInfo.builder().subjectName(subjectId).build()).getSubjectName();
        String classId = confTeacherClass.getClassId();
        if (!classInfoMap.containsKey(classId)) {
          GloabalUtils.convertMessage(GlobalEnum.ClassInfoEmpty, classId);
        }
        ClassInfo classInfo = classInfoMap.get(classId);
        String className = classInfo.getClassName();
        String gradeId = classInfo.getGradeId();
        if (!gradeInfoMap.containsKey(gradeId)) {
          GloabalUtils.convertMessage(GlobalEnum.GradeIdError, className, gradeId);
        }
        String gradeName = gradeInfoMap.get(gradeId).getGradeName();
        Integer sheetNum = i;
        String sheetName = subjectName + INTERVAL_NUMBER + gradeName + INTERVAL_NUMBER + className;
        List<List<String>> contentList = studentListMap.getOrDefault(classId, new ArrayList<>());
        PoiUtil.exportExcel(workbook, sheetNum, sheetName, EXCEL_HEADER_TITLE, contentList);
      }
      fileNameBuilder.append(teacherName);
    } else {
      Integer sheetNum = 0;
      String sheetName = "请填写科目";
      PoiUtil.exportExcel(workbook, sheetNum, sheetName, EXCEL_HEADER_TITLE, new ArrayList<>());
      fileNameBuilder.append(System.currentTimeMillis());
    }
    fileNameBuilder.append(INTERVAL_POINT);
    fileNameBuilder.append(EXCEL_TYPE_XLS);
    String fileName = fileNameBuilder.toString();
    try {
      GloabalUtils.setResponseHeader(response, fileName);
      OutputStream os = response.getOutputStream();
      workbook.write(os);
      os.flush();
      os.close();
    } catch (Exception e) {
      GloabalUtils.convertMessage(GlobalEnum.ExceptionMessage, e.getMessage());
    }
  }

  /**
   * 上传学生成绩
   *
   * @param scoreFile        成绩文件
   * @param subjectScoreInfo 成绩信息
   * @return ResultEntity
   */
  @Override
  public ResultEntity importScoreByFile(MultipartFile scoreFile, SubjectScoreInfo subjectScoreInfo) {
    Map<String, List<String[]>> readExcelMap = new HashMap<>(16);
    try {
      readExcelMap = PoiUtil.readExcel(scoreFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    List<SubjectScoreInfo> scoreInfoList = convertImportToScoreInfo(readExcelMap, subjectScoreInfo);
    Map<String, SubjectInfo> subjectInfoMap = subjectInfoService.convertRecordToMap(SubjectInfo.builder().build());
    scoreInfoList.stream().forEach(scoreInfo -> {
      String subjectId = scoreInfo.getSubjectId();
      convertScoreNumber(subjectInfoMap, scoreInfo, subjectId);
    });
    List<SubjectScoreInfo> insertSubjectScoreInfos = convertInsertSubjectScoreInfo(scoreInfoList);
    ResultEntity resultEntity = insert(insertSubjectScoreInfos);
    if (resultEntity.isSuccess()) {
      return ResultUtil.success(GlobalEnum.ImportSuccess, scoreInfoList);
    } else {
      return resultEntity;
    }
  }

  /**
   * 班级成绩信息
   *
   * @param subjectScoreInfo 成绩信息
   * @return ResultEntity
   */
  @Override
  public ResultEntity listByClass(SubjectScoreInfo subjectScoreInfo) {
    if (null == subjectScoreInfo) {
      subjectScoreInfo = SubjectScoreInfo.builder().build();
    }
    String classId = subjectScoreInfo.getClassId();
    List<SubjectScoreInfo> subjectScoreInfos = subjectScoreInfoMapper.listClassScoreRanking(subjectScoreInfo);
    Map<String, Integer> rankingMap = new HashMap<>(16);
    subjectScoreInfos.stream()
        .collect(Collectors.toMap(info -> {
          String gradeId = info.getGradeId();
          String semesterId = info.getSemesterId();
          String classType = info.getClassType();
          String key = gradeId + INTERVAL_NUMBER + semesterId + INTERVAL_NUMBER + classType;
          return key;
        }, Function.identity(), (oldValue, newValue) -> newValue)).forEach((key, info) -> {
      rankingMap.put(key, DEFAULT_RANKING);
    });
    subjectScoreInfos.stream().forEach(info -> {
      String gradeId = info.getGradeId();
      String semesterId = info.getSemesterId();
      String classType = info.getClassType();
      String key = gradeId + INTERVAL_NUMBER + semesterId + INTERVAL_NUMBER + classType;
      if (rankingMap.containsKey(key)) {
        info.setClassRanking(rankingMap.get(key));
        Integer ranking = rankingMap.get(key);
        ranking += 1;
        rankingMap.put(key, ranking);
      } else {
        info.setClassRanking(DEFAULT_RANKING);
      }
      info.setGradeClassName(info.getGradeName() + DEFAULT_PREFIX + info.getClassName() + DEFAULT_SUFFIX);
    });
    if (StringUtils.isNotBlank(classId)) {
      subjectScoreInfos = subjectScoreInfos.stream().filter(info -> Objects.equals(classId, info.getClassId()))
          .collect(Collectors.toList());
    }
    return ResultUtil.success(GlobalEnum.QuerySuccess, subjectScoreInfos);
  }

  /**
   * 家长查询学生成绩
   *
   * @param parentStudentScore 查询成绩信息
   * @return ResultEntity
   */
  @Override
  public ResultEntity listByParent(ParentStudentScore parentStudentScore) {
    if (null == parentStudentScore) {
      parentStudentScore = ParentStudentScore.builder().build();
    }
    String parentId = parentStudentScore.getParentId();
    String parentName = parentStudentScore.getParentName();
    if (StringUtils.isBlank(parentId) && StringUtils.isBlank(parentName)) {
      return ResultUtil.error(GlobalEnum.ParentInfoQueryEmpty);
    }
    String studentName = parentStudentScore.getStudentName();
    if (StringUtils.isBlank(studentName)) {
      return ResultUtil.error(GlobalEnum.StudentInfoQueryEmpty);
    }
    ParentInfo parentInfo = ParentInfo.builder().parentId(parentId).parentName(parentName).build();
    Map<String, StudentInfo> studentInfoMap = studentInfoService.convertRecordToMap(StudentInfo.builder().studentName(studentName).build());
    if (studentInfoMap.isEmpty()) {
      return ResultUtil.error(GlobalEnum.ParentNoStudentInfo);
    }
    List<String> studentIds = studentInfoMap.keySet().stream().collect(Collectors.toList());
    String studentId = studentIds.get(0);
    if (StringUtils.isBlank(parentId)) {
      Map<String, ParentInfo> parentInfoMap = parentInfoService.convertRecordToMap(parentInfo);
      List<String> parentIds = parentInfoMap.values().stream().filter(info -> Objects.equals(parentName, info.getParentName()))
          .map(ParentInfo::getParentId)
          .collect(Collectors.toList());
      if (null == parentIds || parentIds.size() < 1) {
        return ResultUtil.error(GlobalEnum.ParentInfoQueryEmpty);
      }
      parentId = parentIds.get(0);
    }
    Map<String, List<ConfStudentParent>> parentOfStudentMap = confStudentParentService
        .convertParentOfStudentMap(ConfStudentParent.builder().parentId(parentId).studentId(studentId).build());
    if (parentOfStudentMap.isEmpty()) {
      return ResultUtil.error(GlobalEnum.ParentNoStudentInfo);
    }
    SubjectScoreInfo scoreInfo = JSON.parseObject(JSON.toJSONString(parentStudentScore), SubjectScoreInfo.class);
    scoreInfo.setStudentId(studentId);
    return list(scoreInfo);
  }

  /**
   * 排名信息
   * 班级排名:班级、学期
   * 年级排名:年级、学期
   *
   * @param scoreInfo 成绩信息
   * @return ResultEntity
   */
  @Override
  public Map<String, SubjectScoreInfo> listRankingMap(SubjectScoreInfo scoreInfo) {
    List<SubjectScoreInfo> scoreInfoList = subjectScoreInfoMapper.list(SubjectScoreInfo.builder().build());
    Map<String, List<SubjectScoreInfo>> classScoreInfoListMap = scoreInfoList.stream().collect(Collectors.groupingBy(info -> {
      String classId = info.getClassId();
      String semesterId = info.getSemesterId();
      return classId + INTERVAL_NUMBER + semesterId;
    }));
    Map<String, List<SubjectScoreInfo>> gradeScoreInfoListMap = scoreInfoList.stream().collect(Collectors.groupingBy(info -> {
      String gradeId = info.getGradeId();
      String classType = info.getClassType();
      String semesterId = info.getSemesterId();
      return gradeId + INTERVAL_NUMBER + classType + INTERVAL_NUMBER + semesterId;
    }));

    classScoreInfoListMap.forEach((key, subjectScoreInfos) -> {
      final Integer[] rankingCount = {1};
      subjectScoreInfos.stream().sorted(new Comparator<SubjectScoreInfo>() {
        @Override
        public int compare(SubjectScoreInfo o1, SubjectScoreInfo o2) {
          return ((int) (o2.getSubScoreSum() - o1.getSubScoreSum()));
        }
      }).collect(Collectors.toList()).stream().forEach(info -> {
        info.setClassRanking(rankingCount[0]);
        rankingCount[0]++;
      });
    });
    gradeScoreInfoListMap.forEach((key, subjectScoreInfos) -> {
      final Integer[] rankingCount = {1};
      subjectScoreInfos.stream().sorted(new Comparator<SubjectScoreInfo>() {
        @Override
        public int compare(SubjectScoreInfo o1, SubjectScoreInfo o2) {
          return ((int) (o2.getSubScoreSum() - o1.getSubScoreSum()));
        }
      }).collect(Collectors.toList()).stream().forEach(info -> {
        info.setGradeRanking(rankingCount[0]);
        rankingCount[0]++;
      });
    });
    scoreInfoList.stream().forEach(info -> {
      String scoreId = info.getId();
      String classId = info.getClassId();
      String gradeId = info.getGradeId();
      String classType = info.getClassType();
      String semesterId = info.getSemesterId();
      String classKey = classId + INTERVAL_NUMBER + semesterId;
      String gradeKey = gradeId + INTERVAL_NUMBER + classType + INTERVAL_NUMBER + semesterId;
      Integer classRanking = classScoreInfoListMap.get(classKey).stream().filter(subjectScoreInfo -> Objects.equals(scoreId, subjectScoreInfo.getId()))
          .collect(Collectors.toList()).get(0).getClassRanking();
      Integer gradeRanking = gradeScoreInfoListMap.get(gradeKey).stream().filter(subjectScoreInfo -> Objects.equals(scoreId, subjectScoreInfo.getId()))
          .collect(Collectors.toList()).get(0).getGradeRanking();
      info.setGradeRanking(gradeRanking);
      info.setClassRanking(classRanking);
    });
    Map<String, SubjectScoreInfo> scoreInfoMap = scoreInfoList.stream().filter(info -> StringUtils.isNotBlank(info.getId()))
        .collect(Collectors.toMap(SubjectScoreInfo::getId, Function.identity(), (oldValue, newValue) -> newValue));
    return scoreInfoMap;
  }

  /**
   * 更新总成绩
   *
   * @param subjectScoreInfo 成绩信息
   * @return ResultEntity
   */
  @Override
  public ResultEntity updateSubjectScoreSum(SubjectScoreInfo subjectScoreInfo) {
    List<SubjectScoreInfo> subjectScoreInfos = subjectScoreInfoMapper.list(subjectScoreInfo);
    List<SubjectScoreInfo> updateScoreInfos = new ArrayList<>();
    subjectScoreInfos.stream().forEach(info -> {
      Double scoreSum = (Double) (info.getSubLanguage() +
          info.getSubMathematics() +
          info.getSubEnglish() +
          info.getSubHistory() +
          info.getSubGeography() +
          info.getSubBiological() +
          info.getSubChemistry() +
          info.getSubPhysical() +
          info.getSubPolitical() +
          info.getSubComputer() +
          info.getSubSports() +
          info.getSubArt() +
          info.getSubMusic());
      if (!Objects.equals(info.getSubScoreSum(), scoreSum)) {
        info.setSubScoreSum(scoreSum);
        updateScoreInfos.add(info);
      }
    });
    Integer updateCount = subjectScoreInfoMapper.update(updateScoreInfos);
    log.info("更新学生总成绩:{}条", updateCount);
    if (updateCount > 0) {
      return ResultUtil.success(GlobalEnum.UpdateSuccess, updateScoreInfos);
    } else {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
  }

  /**
   * 将excel文件数据转为成绩数据
   *
   * @param scoreInfoMap excel文件数据
   * @param scoreInfo    成绩信息
   * @return List<ScoreInfo>
   */
  private List<SubjectScoreInfo> convertImportToScoreInfo(Map<String, List<String[]>> scoreInfoMap, SubjectScoreInfo scoreInfo) {
    List<SubjectScoreInfo> scoreInfoList = new ArrayList<>();
    if (null != scoreInfoMap && !scoreInfoMap.isEmpty()) {
      String teacherId = scoreInfo.getTeacherId();
      final String[] classId = {scoreInfo.getClassId()};
      String semesterId = scoreInfo.getSemesterId();
      Map<String, SubjectInfo> subjectInfoMap = subjectInfoService.convertRecordToMap(SubjectInfo.builder().build()).values().stream()
          .filter(subjectInfo -> StringUtils.isNotBlank(subjectInfo.getSubjectName()))
          .collect(Collectors.toMap(SubjectInfo::getSubjectName, Function.identity(),
              (oldValue, newValue) -> newValue));
      Map<String, ClassInfo> classInfoMap = classInfoService.convertClassNameAndGradeNameMap(ClassInfo.builder().build());
      Map<String, StudentInfo> studentInfoMap = studentInfoService.convertRecordToMap(StudentInfo.builder().build()).values().stream()
          .filter(studentInfo -> StringUtils.isNotBlank(studentInfo.getStudentNum()))
          .collect(Collectors.toMap(StudentInfo::getStudentNum, Function.identity(),
              (oldValue, newValue) -> newValue));
      scoreInfoMap.forEach((subjectAndGradeAndClassName, subjectScoreInfos) -> {
        String subjectName = "";
        String gradeName = "";
        String className = "";
        String classAndGradeName = "";
        if (!subjectAndGradeAndClassName.contains(INTERVAL_NUMBER)) {
          subjectName = subjectAndGradeAndClassName;
        } else {
          String[] strings = subjectAndGradeAndClassName.split(INTERVAL_NUMBER);
          if (strings.length != EXCEL_HEADER_TITLE.length) {
            GloabalUtils.convertMessage(GlobalEnum.SheetNameError, subjectAndGradeAndClassName);
          } else {
            subjectName = strings[0];
            gradeName = strings[1];
            className = strings[2];
            classAndGradeName = className + INTERVAL_NUMBER + gradeName;
          }
        }
        if (!subjectInfoMap.containsKey(subjectName)) {
          GloabalUtils.convertMessage(GlobalEnum.SubjectNameInfoEmpty, subjectName);
        }
        if (StringUtils.isNotBlank(classAndGradeName)) {
          if (!classInfoMap.containsKey(classAndGradeName)) {
            GloabalUtils.convertMessage(GlobalEnum.ClassNameInGrade, className, gradeName);
          } else {
            classId[0] = classInfoMap.get(classAndGradeName).getClassId();
          }
        }
        String subjectId = subjectInfoMap.get(subjectName).getSubjectId();
        Integer fileDataSize = 2;
        if (null == subjectScoreInfos || subjectScoreInfos.size() < fileDataSize) {
          GloabalUtils.convertMessage(GlobalEnum.ImportScoreInfoDataEmpty, subjectName);
        }
        String[] headers = subjectScoreInfos.get(0);
        for (int i = 1; i < subjectScoreInfos.size(); i++) {
          String[] bodies = subjectScoreInfos.get(i);
          String message = String.valueOf(i + 1);
          if (headers.length != bodies.length) {
            GloabalUtils.convertMessage(GlobalEnum.ScoreInfoNoMatchFirstRow, subjectName, message);
          } else {
            SubjectScoreInfo newScoreInfo = SubjectScoreInfo.builder().teacherId(teacherId).classId(classId[0]).semesterId(semesterId).subjectId(subjectId).build();
            for (int j = 0; j < headers.length; j++) {
              String header = headers[j];
              String body = bodies[j];
              switch (header) {
                case "学号":
                  if (StringUtils.isBlank(body)) {
                    GloabalUtils.convertMessage(GlobalEnum.ImportStudentInfoEmpty, subjectName, message);
                  }
                  if (!studentInfoMap.containsKey(body)) {
                    GloabalUtils.convertMessage(GlobalEnum.ImportStudentInfoNoMatch, subjectName, message, body);
                  }
                  String studentId = studentInfoMap.get(body).getStudentId();
                  newScoreInfo.setStudentId(studentId);
                  break;
                case "成绩":
                  if (StringUtils.isBlank(body)) {
                    GloabalUtils.convertMessage(GlobalEnum.ImportScoreInfoEmpty, subjectName, message);
                  }
                  Double scoreNumber = 0.0;
                  try {
                    scoreNumber = Double.valueOf(body);
                  } catch (Exception e) {
                    GloabalUtils.convertMessage(GlobalEnum.ImportScoreInfoNoMatch, body, message);
                  }
                  newScoreInfo.setScoreNumber(scoreNumber);
                  break;
                default:
                  break;
              }
            }
            scoreInfoList.add(newScoreInfo);
          }
        }
      });
    }
    return scoreInfoList;
  }

  /**
   * 导入时转换科目成绩
   *
   * @param subjectInfoMap 科目信息
   * @param scoreInfo      成绩信息
   * @param subjectId      科目ID
   */
  private void convertScoreNumber(Map<String, SubjectInfo> subjectInfoMap, SubjectScoreInfo scoreInfo, String subjectId) {
    if (StringUtils.isNotBlank(subjectId)) {
      Double scoreNumber = scoreInfo.getScoreNumber();
      SubjectInfo subjectInfo = subjectInfoMap.get(subjectId);
      switch (subjectInfo.getSubjectName()) {
        case "语文":
          scoreInfo.setSubLanguage(scoreNumber);
          break;
        case "数学":
          scoreInfo.setSubMathematics(scoreNumber);
          break;
        case "英语":
          scoreInfo.setSubEnglish(scoreNumber);
          break;
        case "物理":
          scoreInfo.setSubPhysical(scoreNumber);
          break;
        case "历史":
          scoreInfo.setSubHistory(scoreNumber);
          break;
        case "地理":
          scoreInfo.setSubGeography(scoreNumber);
          break;
        case "生物":
          scoreInfo.setSubBiological(scoreNumber);
          break;
        case "化学":
          scoreInfo.setSubChemistry(scoreNumber);
          break;
        case "政治":
          scoreInfo.setSubPolitical(scoreNumber);
          break;
        case "计算机":
          scoreInfo.setSubComputer(scoreNumber);
          break;
        case "体育":
          scoreInfo.setSubSports(scoreNumber);
          break;
        case "美术":
          scoreInfo.setSubArt(scoreNumber);
          break;
        case "音乐":
          scoreInfo.setSubMusic(scoreNumber);
          break;
        default:
          GloabalUtils.convertMessage(GlobalEnum.SubjectScoreInfoEmpty);
          break;
      }
    }
  }

  /**
   * 检测学生成绩数据是否合法
   *
   * @author weiQiang
   * @since
   */
  private class CheckSubjectScoreInfo implements Serializable {
    private String classId;
    private Map<String, ClassInfo> classInfoMap;
    private SubjectScoreInfo scoreInfo;
    private String semesterId;
    private Map<String, SemesterInfo> semesterInfoMap;
    private String studentId;
    private Map<String, StudentInfo> studentInfoMap;
    private String subjectId;
    private Map<String, SubjectInfo> subjectInfoMap;
    private Map<String, TeacherInfo> teacherInfoMap;

    public CheckSubjectScoreInfo(Map<String, ClassInfo> classInfoMap, Map<String, StudentInfo> studentInfoMap, Map<String, SubjectInfo> subjectInfoMap, Map<String, TeacherInfo> teacherInfoMap, Map<String, SemesterInfo> semesterInfoMap, SubjectScoreInfo scoreInfo) {
      this.classInfoMap = classInfoMap;
      this.studentInfoMap = studentInfoMap;
      this.subjectInfoMap = subjectInfoMap;
      this.teacherInfoMap = teacherInfoMap;
      this.semesterInfoMap = semesterInfoMap;
      this.scoreInfo = scoreInfo;
    }

    public String getClassId() {
      return classId;
    }

    public String getSemesterId() {
      return semesterId;
    }

    public String getStudentId() {
      return studentId;
    }

    public String getSubjectId() {
      return subjectId;
    }

    public CheckSubjectScoreInfo invoke() {
      classId = scoreInfo.getClassId();
      studentId = scoreInfo.getStudentId();
      subjectId = scoreInfo.getSubjectId();
      String teacherId = scoreInfo.getTeacherId();
      semesterId = scoreInfo.getSemesterId();
      if (StringUtils.isBlank(classId)) {
        GloabalUtils.convertMessage(GlobalEnum.ClassIdEmpty);
      }
      if (StringUtils.isBlank(studentId)) {
        GloabalUtils.convertMessage(GlobalEnum.StudentIdEmpty);
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
      if (StringUtils.isNotBlank(subjectId) && !subjectInfoMap.containsKey(subjectId)) {
        GloabalUtils.convertMessage(GlobalEnum.SubjectInfoEmpty, subjectId);
      }
      if (!teacherInfoMap.containsKey(teacherId)) {
        GloabalUtils.convertMessage(GlobalEnum.TeacherInfoEmpty, teacherId);
      }
      if (!semesterInfoMap.containsKey(semesterId)) {
        GloabalUtils.convertMessage(GlobalEnum.SemesterInfoEmpty, semesterId);
      }
      return this;
    }
  }
}