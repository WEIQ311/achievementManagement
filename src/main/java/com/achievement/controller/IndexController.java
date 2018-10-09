package com.achievement.controller;

import com.achievement.enums.GlobalEnum;
import com.achievement.utils.ResultUtil;
import com.achievement.vo.ResultEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weiQiang
 * @date 2018/10/9
 */
@RestController
public class IndexController {

  /**
   * 首页地址
   */
  @Value("${achievement.indexUrl}")
  private String indexUrl;

  /**
   * 获取首页信息
   *
   * @return ResultEntity
   */
  @RequestMapping(value = {"/indexInfo"}, method = RequestMethod.GET)
  public ResultEntity index() {
    List<Map<String, String>> mapList = new ArrayList<Map<String, String>>() {{
      add(new HashMap<String, String>(1) {{
        put("indexUrl", indexUrl);
      }});
    }};
    return ResultUtil.success(GlobalEnum.QuerySuccess, mapList);
  }
}
