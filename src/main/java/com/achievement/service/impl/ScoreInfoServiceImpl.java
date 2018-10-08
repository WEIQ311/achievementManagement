package com.achievement.service.impl;

import com.achievement.entity.*;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.ScoreInfoMapper;
import com.achievement.service.*;
import com.achievement.utils.GloabalUtils;
import com.achievement.utils.PoiUtil;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ParentStudentScore;
import com.achievement.vo.ResultEntity;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
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
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.achievement.constants.GlobalConstants.*;

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
  @Autowired
  private ConfStudentParentService confStudentParentService;
  @Autowired
  private ConfTeacherClassService confTeacherClassService;
  @Autowired
  private ConfTeacherSubjectService confTeacherSubjectService;
  @Value("${achievement.delete.score}")
  private boolean deleteScore;
  @Autowired
  private ParentInfoService parentInfoService;
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
    scoreInfoMapper.delete(scoreIds);
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
    convertScoreInfo(scoreInfoPage, scoreInfo);
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
    convertScoreInfo(scoreInfos, scoreInfo);
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
    List<ScoreInfo> scoreInfos = scoreInfoMapper.list(ScoreInfo.builder().build());
    Map<String, ScoreInfo> scoreInfoMap = scoreInfos.stream().collect(Collectors.toMap(scoreInfo -> {
      String classId = scoreInfo.getClassId();
      String studentId = scoreInfo.getStudentId();
      String subjectId = scoreInfo.getSubjectId();
      String semesterId = scoreInfo.getSemesterId();
      String key = semesterId + INTERVAL_NUMBER + classId + INTERVAL_NUMBER + subjectId + INTERVAL_NUMBER + studentId;
      return key;
    }, Function.identity(), (oldValue, newValue) -> newValue));
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
        String key = semesterId + INTERVAL_NUMBER + classId + INTERVAL_NUMBER + subjectId + INTERVAL_NUMBER + studentId;
        if (scoreInfoMap.containsKey(key)) {
          String studentName = studentInfoMap.get(studentId).getStudentName();
          String subjectName = subjectInfoMap.get(subjectId).getSubjectName();
          GloabalUtils.convertMessage(GlobalEnum.ScoreInfoHasInUsed, studentName, subjectName);
        }
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
   * 转换班级内最高、平均、最低成绩信息
   *
   * @param scoreInfos 成绩结果
   * @param scoreInfo  查询成绩信息
   */
  private void convertScoreInfo(List<ScoreInfo> scoreInfos, ScoreInfo scoreInfo) {
    if (null == scoreInfos || scoreInfos.size() < 1) {
      return;
    }
    List<ScoreInfo> classScoreInfo = scoreInfoMapper.listClassScoreInfo(scoreInfo);
    List<ScoreInfo> gradeScoreInfo = scoreInfoMapper.listGradeScoreInfo(scoreInfo);
    Map<String, ScoreInfo> scoreInfoMap = listRankingMap(scoreInfo);
    Map<String, ScoreInfo> classScoreMap = classScoreInfo.stream()
        .collect(Collectors.toMap(info -> {
          String classId = info.getClassId();
          String semesterId = info.getSemesterId();
          String subjectId = info.getSubjectId();
          return classId + INTERVAL_NUMBER + semesterId + INTERVAL_NUMBER + subjectId;
        }, Function.identity(), (oldValue, newValue) -> newValue));
    Map<String, ScoreInfo> gradeScoreMap = gradeScoreInfo.stream()
        .collect(Collectors.toMap(info -> {
          String gradeId = info.getGradeId();
          String semesterId = info.getSemesterId();
          String subjectId = info.getSubjectId();
          return gradeId + INTERVAL_NUMBER + semesterId + INTERVAL_NUMBER + subjectId;
        }, Function.identity(), (oldValue, newValue) -> newValue));
    scoreInfos.stream().forEach(info -> {
      String scoreId = info.getScoreId();
      String classId = info.getClassId();
      String gradeId = info.getGradeId();
      String semesterId = info.getSemesterId();
      String subjectId = info.getSubjectId();
      String classKey = classId + INTERVAL_NUMBER + semesterId + INTERVAL_NUMBER + subjectId;
      String gradeKey = gradeId + INTERVAL_NUMBER + semesterId + INTERVAL_NUMBER + subjectId;
      if (classScoreMap.containsKey(classKey)) {
        ScoreInfo score = classScoreMap.get(classKey);
        Double maxScore = score.getMaxScore();
        Double minScore = score.getMinScore();
        Double avgScore = score.getAvgScore();
        info.setMaxScore(maxScore);
        info.setMinScore(minScore);
        info.setAvgScore(avgScore);
      }
      if (gradeScoreMap.containsKey(gradeKey)) {
        ScoreInfo score = gradeScoreMap.get(gradeKey);
        Double gradeMaxScore = score.getGradeMaxScore();
        Double gradeMinScore = score.getGradeMinScore();
        Double gradeAvgScore = score.getGradeAvgScore();
        info.setGradeMaxScore(gradeMaxScore);
        info.setGradeMinScore(gradeMinScore);
        info.setGradeAvgScore(gradeAvgScore);
      }
      Integer classRanking = scoreInfoMap.get(scoreId).getClassRanking();
      Integer gradeRanking = scoreInfoMap.get(scoreId).getGradeRanking();
      info.setClassRanking(classRanking);
      info.setGradeRanking(gradeRanking);
    });
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
    List<List<String>> contentList = new ArrayList<>();
    StringBuilder fileNameBuilder = new StringBuilder("学生成绩信息");
    fileNameBuilder.append(UNDER_LINE);
    HSSFWorkbook workbook = new HSSFWorkbook();
    if (StringUtils.isNotBlank(teacherId)) {
      Map<String, SubjectInfo> subjectInfoMap = subjectInfoService.convertRecordToMap(SubjectInfo.builder().build());
      Map<String, TeacherInfo> teacherInfoMap = teacherInfoService.convertRecordToMap(TeacherInfo.builder().teacherId(teacherId).build());
      if (null == teacherInfoMap || teacherInfoMap.isEmpty()) {
        GloabalUtils.convertMessage(GlobalEnum.TeacherInfoEmpty, teacherId);
      }
      String teacherName = teacherInfoMap.get(teacherId).getTeacherName();
      Map<String, List<ConfTeacherSubject>> teacherOfSubjectMap = confTeacherSubjectService.convertTeacherOfSubjectMap(ConfTeacherSubject.builder().teacherId(teacherId).build());
      if (null == teacherOfSubjectMap || teacherOfSubjectMap.isEmpty()) {
        GloabalUtils.convertMessage(GlobalEnum.TeacherNoSubject, teacherName);
      }
      Map<String, List<ConfTeacherClass>> teacherOfClassMap = confTeacherClassService.convertTeacherOfClassMap(ConfTeacherClass.builder().teacherId(teacherId).build());
      if (null == teacherOfClassMap || teacherOfClassMap.isEmpty()) {
        GloabalUtils.convertMessage(GlobalEnum.TeacherNoClass, teacherName);
      }
      List<ConfTeacherSubject> confTeacherSubjects = teacherOfSubjectMap.get(teacherId);
      List<ConfTeacherClass> confTeacherClasses = teacherOfClassMap.get(teacherId);
      List<String> classIds = confTeacherClasses.stream().filter(confTeacherClass -> StringUtils.isNotBlank(confTeacherClass.getClassId()))
          .map(ConfTeacherClass::getClassId)
          .distinct().collect(Collectors.toList());
      Map<String, StudentInfo> studentInfoMap = studentInfoService.convertRecordToMap(StudentInfo.builder().classIds(classIds).build());
      studentInfoMap.forEach((studentId, studentInfo) -> {
        List<String> studentNameList = new ArrayList<String>() {{
          add(studentInfo.getStudentName());
          add(new String());
        }};
        contentList.add(studentNameList);
      });
      for (int i = 0; i < confTeacherSubjects.size(); i++) {
        ConfTeacherSubject confTeacherSubject = confTeacherSubjects.get(i);
        String subjectId = confTeacherSubject.getSubjectId();
        if (!subjectInfoMap.containsKey(subjectId)) {
          GloabalUtils.convertMessage(GlobalEnum.SubjectInfoEmpty, subjectId);
        }
        String subjectName = subjectInfoMap.getOrDefault(subjectId, SubjectInfo.builder().subjectName(subjectId).build()).getSubjectName();
        Integer sheetNum = i;
        String sheetName = subjectName;
        PoiUtil.exportExcel(workbook, sheetNum, sheetName, EXCEL_HEADER_TITLE, contentList);
      }
      fileNameBuilder.append(teacherName);
    } else {
      Integer sheetNum = 0;
      String sheetName = "请填写科目";
      PoiUtil.exportExcel(workbook, sheetNum, sheetName, EXCEL_HEADER_TITLE, contentList);
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
   * @param scoreFile 成绩文件
   * @param scoreInfo 成绩信息
   * @return ResultEntity
   */
  @Override
  public ResultEntity importScoreByFile(MultipartFile scoreFile, ScoreInfo scoreInfo) {
    Map<String, List<String[]>> readExcelMap = new HashMap<>(16);
    try {
      readExcelMap = PoiUtil.readExcel(scoreFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    List<ScoreInfo> scoreInfoList = convertImportToScoreInfo(readExcelMap, scoreInfo);
    ResultEntity resultEntity = insert(scoreInfoList);
    if (resultEntity.isSuccess()) {
      return ResultUtil.success(GlobalEnum.ImportSuccess, scoreInfoList);
    } else {
      return resultEntity;
    }
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
    ScoreInfo scoreInfo = JSON.parseObject(JSON.toJSONString(parentStudentScore), ScoreInfo.class);
    scoreInfo.setStudentId(studentId);
    return list(scoreInfo);
  }

  /**
   * 班级最高、平均、最低成绩
   *
   * @param scoreInfo 查询成绩信息
   * @return ResultEntity
   */
  @Override
  public ResultEntity listClassScore(ScoreInfo scoreInfo) {
    List<ScoreInfo> scoreInfos = scoreInfoMapper.listClassScoreInfo(scoreInfo);
    return ResultUtil.success(GlobalEnum.QuerySuccess, scoreInfos);
  }

  /**
   * 年级最高、平均、最低成绩
   *
   * @param scoreInfo 查询成绩信息
   * @return ResultEntity
   */
  @Override
  public ResultEntity listGradeScore(ScoreInfo scoreInfo) {
    List<ScoreInfo> scoreInfos = scoreInfoMapper.listGradeScoreInfo(scoreInfo);
    return ResultUtil.success(GlobalEnum.QuerySuccess, scoreInfos);
  }

  /**
   * 排名信息
   * 班级排名:班级、学期、科目
   * 年级排名:年级、学期、科目
   *
   * @param scoreInfo 成绩信息
   * @return ResultEntity
   */
  @Override
  public Map<String, ScoreInfo> listRankingMap(ScoreInfo scoreInfo) {
    List<ScoreInfo> scoreInfoList = scoreInfoMapper.list(ScoreInfo.builder().build());
    Map<String, List<ScoreInfo>> classScoreInfoListMap = scoreInfoList.stream().collect(Collectors.groupingBy(info -> {
      String classId = info.getClassId();
      String semesterId = info.getSemesterId();
      String subjectId = info.getSubjectId();
      return classId + INTERVAL_NUMBER + semesterId + INTERVAL_NUMBER + subjectId;
    }));
    Map<String, List<ScoreInfo>> gradeScoreInfoListMap = scoreInfoList.stream().collect(Collectors.groupingBy(info -> {
      String gradeId = info.getGradeId();
      String semesterId = info.getSemesterId();
      String subjectId = info.getSubjectId();
      return gradeId + INTERVAL_NUMBER + semesterId + INTERVAL_NUMBER + subjectId;
    }));

    classScoreInfoListMap.forEach((key, scoreInfos) -> {
      final Integer[] rankingCount = {1};
      scoreInfos.stream().sorted(new Comparator<ScoreInfo>() {
        @Override
        public int compare(ScoreInfo o1, ScoreInfo o2) {
          return ((int) (o2.getScoreNumber() - o1.getScoreNumber()));
        }
      }).collect(Collectors.toList()).stream().forEach(info -> {
        info.setClassRanking(rankingCount[0]);
        rankingCount[0]++;
      });
    });
    gradeScoreInfoListMap.forEach((key, scoreInfos) -> {
      final Integer[] rankingCount = {1};
      scoreInfos.stream().sorted(new Comparator<ScoreInfo>() {
        @Override
        public int compare(ScoreInfo o1, ScoreInfo o2) {
          return ((int) (o2.getScoreNumber() - o1.getScoreNumber()));
        }
      }).collect(Collectors.toList()).stream().forEach(info -> {
        info.setGradeRanking(rankingCount[0]);
        rankingCount[0]++;
      });
    });
    scoreInfoList.stream().forEach(info -> {
      String scoreId = info.getScoreId();
      String classId = info.getClassId();
      String gradeId = info.getGradeId();
      String semesterId = info.getSemesterId();
      String subjectId = info.getSubjectId();
      String classKey = classId + INTERVAL_NUMBER + semesterId + INTERVAL_NUMBER + subjectId;
      String gradeKey = gradeId + INTERVAL_NUMBER + semesterId + INTERVAL_NUMBER + subjectId;
      Integer classRanking = classScoreInfoListMap.get(classKey).stream().filter(score -> Objects.equals(scoreId, score.getScoreId()))
          .collect(Collectors.toList()).get(0).getClassRanking();
      Integer gradeRanking = gradeScoreInfoListMap.get(gradeKey).stream().filter(score -> Objects.equals(scoreId, score.getScoreId()))
          .collect(Collectors.toList()).get(0).getGradeRanking();
      info.setGradeRanking(gradeRanking);
      info.setClassRanking(classRanking);
    });
    Map<String, ScoreInfo> scoreInfoMap = scoreInfoList.stream().filter(info -> StringUtils.isNotBlank(info.getScoreId()))
        .collect(Collectors.toMap(ScoreInfo::getScoreId, Function.identity(), (oldValue, newValue) -> newValue));
    return scoreInfoMap;
  }

  /**
   * 将excel文件数据转为成绩数据
   *
   * @param scoreInfoMap excel文件数据
   * @param scoreInfo    成绩信息
   * @return List<ScoreInfo>
   */
  private List<ScoreInfo> convertImportToScoreInfo(Map<String, List<String[]>> scoreInfoMap, ScoreInfo scoreInfo) {
    List<ScoreInfo> scoreInfoList = new ArrayList<>();
    if (null != scoreInfoMap && !scoreInfoMap.isEmpty()) {
      String teacherId = scoreInfo.getTeacherId();
      String classId = scoreInfo.getClassId();
      String semesterId = scoreInfo.getSemesterId();
      Map<String, SubjectInfo> subjectInfoMap = subjectInfoService.convertRecordToMap(SubjectInfo.builder().build()).values().stream()
          .filter(subjectInfo -> StringUtils.isNotBlank(subjectInfo.getSubjectName()))
          .collect(Collectors.toMap(SubjectInfo::getSubjectName, Function.identity(),
              (oldValue, newValue) -> newValue));
      Map<String, StudentInfo> studentInfoMap = studentInfoService.convertRecordToMap(StudentInfo.builder().classId(classId).build()).values().stream()
          .filter(studentInfo -> StringUtils.isNotBlank(studentInfo.getStudentName()))
          .collect(Collectors.toMap(StudentInfo::getStudentName, Function.identity(),
              (oldValue, newValue) -> newValue));
      scoreInfoMap.forEach((subjectName, scoreInfos) -> {
        if (!subjectInfoMap.containsKey(subjectName)) {
          GloabalUtils.convertMessage(GlobalEnum.SubjectNameInfoEmpty, subjectName);
        }
        String subjectId = subjectInfoMap.get(subjectName).getSubjectId();
        if (null == scoreInfos || scoreInfos.size() < 2) {
          GloabalUtils.convertMessage(GlobalEnum.ImportScoreInfoDataEmpty, subjectName);
        }
        String[] headers = scoreInfos.get(0);
        for (int i = 1; i < scoreInfos.size(); i++) {
          String[] bodies = scoreInfos.get(i);
          String message = String.valueOf(i + 1);
          if (headers.length != bodies.length) {
            GloabalUtils.convertMessage(GlobalEnum.ScoreInfoNoMatchFirstRow, subjectName, message);
          } else {
            ScoreInfo newScoreInfo = ScoreInfo.builder().teacherId(teacherId).classId(classId).semesterId(semesterId).subjectId(subjectId).build();
            for (int j = 0; j < headers.length; j++) {
              String header = headers[j];
              String body = bodies[j];
              switch (header) {
                case "学生":
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
}