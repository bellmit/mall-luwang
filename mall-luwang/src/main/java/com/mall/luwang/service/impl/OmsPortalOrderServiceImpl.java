package com.mall.luwang.service.impl;

import com.mall.luwang.common.CommonResult;
import com.mall.luwang.component.CancelOrderSender;
import com.mall.luwang.dto.OrderParam;
import com.mall.luwang.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 前台订单管理Service
 *
 * @author luWang
 * @date 2021/4/14 - 12:01
 * @day_of_week: 星期三
 */
@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {
    private static Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);

    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public CommonResult generateOrder(OrderParam orderParam) {
        //TODO 执行一系类下单操作，具体参考mall项目
        LOGGER.info("process generateOrder");
        //下单完成后开启一个延迟消息，用于当用户没有付款时取消订单（orderId应该在下单后生成）
        sendDelayMessageCancelOrder(11L);
        return CommonResult.success(null, "下单成功");
    }

    @Override
    public void cancelOrder(Long orderId) {
        //TODO 执行一系列取消订单操作,具体参考mall项目
        LOGGER.info("process cancelOrder orderId:{}", orderId);
    }

    void sendDelayMessageCancelOrder(Long orderId) {
        //获取超时订单超时时间, 假设为60分钟
        long delayTime = 30 * 1000;
        //发送延迟消息
        cancelOrderSender.sendMessage(orderId, delayTime);
    }
}
