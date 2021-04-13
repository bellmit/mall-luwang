package com.mall.luwang.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author luWang
 * @date 2021/4/13 - 19:39
 * @day_of_week: 星期二
 */
@Component
public class OrderTimeOutCancelTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);

    /**
     * 每十分钟扫描一次
     */
    @Scheduled(cron = "0 0/10 * ? * ?")
    private void cancelTimeOutOrder(){
        LOGGER.info("取消订单,并根据sku编号释放锁定库存");
    }
}
