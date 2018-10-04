package com.achievement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主函数
 *
 * @author weiQiang
 */
@SpringBootApplication
@MapperScan("com.achievement.mapper")
public class AchievementApplication {

  public static void main(String[] args) {
    SpringApplication.run(AchievementApplication.class, args);
  }
}
