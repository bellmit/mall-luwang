package com.mall.luwang.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author luWang
 * @date 2021/4/12 - 14:11
 * @day_of_week: 星期一
 */
@Configuration
@MapperScan("com.mall.luwang.mbg.mapper")
public class MyBatisConfig {
}
