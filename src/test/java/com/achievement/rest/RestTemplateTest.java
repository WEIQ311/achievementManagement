package com.achievement.rest;

import com.achievement.BaseTest;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;

/**
 * 测试 RestTemplate
 *
 * @author weiQiang
 * @date 2018/11/22
 */
@Slf4j
public class RestTemplateTest extends BaseTest {

  @Autowired
  private RestTemplate restTemplate;

  @Test
  public void restTemplateTest() {
    String url = "https://api.apiopen.top/EmailSearch?number=1012002";

    JSONObject result = restTemplate.getForEntity(url, JSONObject.class).getBody();
    log.info("请求结果:{}",result);
  }


}
