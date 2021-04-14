package com.mall.luwang.service;

import com.mall.luwang.common.CommonResult;
import com.mall.luwang.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * 前台订单管理service
 *
 * @author luWang
 * @date 2021/4/14 - 11:58
 * @day_of_week: 星期三
 */
public interface OmsPortalOrderService {
    /**
     * 根据提交信息生成订单
     *
     * @param orderParam
     * @return
     */
    @Transactional
    CommonResult generateOrder(OrderParam orderParam);

    /**
     * 取消单个超时订单
     *
     * @param orderId
     */
    @Transactional
    void cancelOrder(Long orderId);
}
