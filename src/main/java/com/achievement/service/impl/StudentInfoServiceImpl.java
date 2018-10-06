package com.achievement.service.impl;

import com.achievement.entity.ConfStudentParent;
import com.achievement.entity.ParentInfo;
import com.achievement.entity.StudentInfo;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.StudentInfoMapper;
import com.achievement.service.ConfStudentParentService;
import com.achievement.service.ParentInfoService;
import com.achievement.service.StudentInfoService;
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
 * 学生(StudentInfo)ServiceImpl
 *
 * @author weiQiang
 * @since 2018-10-02 17:52:27
 */
@Service("studentInfoService")
public class StudentInfoServiceImpl implements StudentInfoService {
  @Autowired
  private ConfStudentParentService confStudentParentService;
  @Autowired
  private ParentInfoService parentInfoService;
  @Resource
  private StudentInfoMapper studentInfoMapper;

  /**
   * 转换请求结果
   *
   * @param studentInfos 学生信息
   */
  private void convertStudentInfo(List<StudentInfo> studentInfos) {
    if (null == studentInfos || studentInfos.size() < 1) {
      return;
    }
    List<String> studentIds = studentInfos.stream().filter(studentInfo -> StringUtils.isNotBlank(studentInfo.getStudentId()))
        .map(StudentInfo::getStudentId)
        .collect(Collectors.toList());
    Map<String, List<ConfStudentParent>> studentOfParentMap = confStudentParentService.convertStudentOfParentMap(ConfStudentParent.builder().studentIds(studentIds).build());
    List<String> parentIds = new ArrayList<>();
    studentOfParentMap.values().forEach(confStudentParents -> {
      confStudentParents.stream().forEach(confStudentParent -> {
        String parentId = confStudentParent.getParentId();
        if (StringUtils.isNotBlank(parentId)) {
          parentIds.add(parentId);
        }
      });
    });
    List<String> parentIdList = parentIds.stream().distinct().collect(Collectors.toList());
    if (null != parentIdList && parentIdList.size() > 0) {
      Map<String, ParentInfo> parentInfoMap = parentInfoService.convertRecordToMap(ParentInfo.builder().parentIds(parentIdList).build());
      studentInfos.stream().forEach(studentInfo -> {
        String studentId = studentInfo.getStudentId();
        if (StringUtils.isNotBlank(studentId) && studentOfParentMap.containsKey(studentId)) {
          List<ConfStudentParent> confStudentParents = studentOfParentMap.get(studentId);
          List<ParentInfo> parentInfos = new ArrayList<ParentInfo>() {{
            confStudentParents.stream().forEach(confStudentParent -> {
              String connectionType = confStudentParent.getConnectionType();
              String parentId = confStudentParent.getParentId();
              if (parentInfoMap.containsKey(parentId)) {
                ParentInfo parentInfo = parentInfoMap.get(parentId);
                parentInfo.setConnectionType(connectionType);
                add(parentInfo);
              }
            });
          }};
          studentInfo.setParentInfoList(parentInfos);
        }
      });
    }
  }

  /**
   * 学生(StudentInfo)信息Map
   *
   * @param studentInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public Map<String, StudentInfo> convertRecordToMap(StudentInfo studentInfo) {
    List<StudentInfo> studentInfoList = studentInfoMapper.list(studentInfo);
    Map<String, StudentInfo> studentInfoMap = studentInfoList.stream().filter(info -> StringUtils.isNotBlank(info.getStudentId()))
        .collect(Collectors.toMap(StudentInfo::getStudentId, Function.identity(), (oldValue, newValue) -> newValue));
    return studentInfoMap;
  }

  /**
   * 删除学生(StudentInfo)
   *
   * @param studentIds 主键集合
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity delete(List<String> studentIds) {
    if (null == studentIds || studentIds.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    confStudentParentService.deleteByStudentId(studentIds);
    studentInfoMapper.delete(studentIds);
    return ResultUtil.success(GlobalEnum.DeleteSuccess, studentIds);
  }

  /**
   * 增加学生(StudentInfo)
   *
   * @param studentInfoList 参数
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity insert(List<StudentInfo> studentInfoList) {
    if (null == studentInfoList || studentInfoList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, StudentInfo> studentInfoMap = convertRecordToMap(StudentInfo.builder().build()).values().stream()
        .filter(info -> StringUtils.isNotBlank(info.getStudentNum()))
        .collect(Collectors.toMap(StudentInfo::getStudentNum, Function.identity(), (oldValue, newValue) -> newValue));
    studentInfoList.stream().forEach(studentInfo -> {
      String studentNum = studentInfo.getStudentNum();
      if (studentInfoMap.containsKey(studentNum)) {
        String studentName = studentInfoMap.get(studentNum).getStudentName();
        GloabalUtils.convertMessage(GlobalEnum.TeacherNumInUsed, studentName, studentNum);
      }
      studentInfo.setStudentId("student_" + GloabalUtils.ordinaryId());
    });
    Integer insertCount = studentInfoMapper.insert(studentInfoList);
    if (insertCount > 0) {
      return ResultUtil.success(GlobalEnum.InsertSuccess, studentInfoList);
    }
    return ResultUtil.error(GlobalEnum.InsertError);
  }

  /**
   * 根据条件分页查询
   *
   * @param studentInfo 查询参数
   * @param pageNum     开始页数
   * @param pageSize    每页显示的数据条数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(StudentInfo studentInfo, int pageNum, int pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    List<StudentInfo> studentInfos = studentInfoMapper.list(studentInfo);
    convertStudentInfo(studentInfos);
    PageInfo pageInfo = new PageInfo(studentInfos);
    return ResultUtil.success(GlobalEnum.QuerySuccess, pageInfo);
  }

  /**
   * 根据条件查询
   *
   * @param studentInfo 查询参数
   * @return ResultEntity
   */
  @Override
  public ResultEntity list(StudentInfo studentInfo) {
    List<StudentInfo> studentInfos = studentInfoMapper.list(studentInfo);
    convertStudentInfo(studentInfos);
    return ResultUtil.success(GlobalEnum.QuerySuccess, studentInfos);
  }


  /**
   * 更新学生(StudentInfo)
   *
   * @param studentInfoList 更新参数
   * @return ResultEntity
   */
  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public ResultEntity update(List<StudentInfo> studentInfoList) {
    if (null == studentInfoList || studentInfoList.size() < 1) {
      return ResultUtil.error(GlobalEnum.DataEmpty);
    }
    Map<String, StudentInfo> studentInfoMap = convertRecordToMap(StudentInfo.builder().build()).values().stream()
        .filter(info -> StringUtils.isNotBlank(info.getStudentNum()))
        .collect(Collectors.toMap(StudentInfo::getStudentNum, Function.identity(), (oldValue, newValue) -> newValue));
    studentInfoList.stream().forEach(studentInfo -> {
      String studentId = studentInfo.getStudentId();
      if (StringUtils.isBlank(studentId)) {
        GloabalUtils.convertMessage(GlobalEnum.PkIdEmpty);
      }
      String studentNum = studentInfo.getStudentNum();
      if (studentInfoMap.containsKey(studentNum) && !Objects.equals(studentId, studentInfoMap.get(studentNum).getStudentId())) {
        String studentName = studentInfoMap.get(studentNum).getStudentName();
        GloabalUtils.convertMessage(GlobalEnum.TeacherNumInUsed, studentName, studentNum);
      }
    });
    Integer updateCount = studentInfoMapper.update(studentInfoList);
    if (updateCount > 0) {
      return ResultUtil.success(GlobalEnum.UpdateSuccess, studentInfoList);
    }
    return ResultUtil.error(GlobalEnum.UpdateError);
  }
}