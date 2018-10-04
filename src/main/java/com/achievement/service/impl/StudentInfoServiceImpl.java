package com.achievement.service.impl;

import com.achievement.entity.StudentInfo;
import com.achievement.enums.GlobalEnum;
import com.achievement.mapper.StudentInfoMapper;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
  private StudentInfoMapper studentInfoMapper;

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
    List<StudentInfo> studentInfoList = new ArrayList<StudentInfo>() {{
      studentIds.forEach(studentId ->
          add(StudentInfo.builder().studentId(studentId).build())
      );
    }};
    studentInfoMapper.delete(studentInfoList);
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
    studentInfoList.stream().forEach(studentInfo -> studentInfo.setStudentId("student_" + GloabalUtils.ordinaryId()));
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
    List<StudentInfo> studentInfoPage = studentInfoMapper.list(studentInfo);
    PageInfo pageInfo = new PageInfo(studentInfoPage);
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
    Integer updateCount = studentInfoMapper.update(studentInfoList);
    if (updateCount > 0) {
      return ResultUtil.success(GlobalEnum.UpdateSuccess, studentInfoList);
    }
    return ResultUtil.error(GlobalEnum.UpdateError);
  }
}