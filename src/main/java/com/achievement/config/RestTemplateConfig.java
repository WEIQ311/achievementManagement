package com.achievement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate配置
 *
 * @author weiQiang
 * @date 2018/11/22
 */
@Configuration
public class RestTemplateConfig {

  /**
   * 构造restTemplate
   *
   * @param factory
   * @return RestTemplate
   */
  @Bean
  public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
    return new RestTemplate(factory);
  }


  /**
   * 构造simpleClientHttpRequestFactory
   *
   * @return ClientHttpRequestFactory
   */
  @Bean
  public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setReadTimeout(5000);
    factory.setConnectTimeout(15000);
    return factory;
  }


}
