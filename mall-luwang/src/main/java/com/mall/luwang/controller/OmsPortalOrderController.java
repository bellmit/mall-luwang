package com.mall.luwang.controller;

import com.mall.luwang.dto.OrderParam;
import com.mall.luwang.service.OmsPortalOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author luWang
 * @date 2021/4/14 - 13:42
 * @day_of_week: 星期三
 */
@Api(tags = "OmsPortalOrderController", description = "订单管理")
@Controller
@RequestMapping("/order")
public class OmsPortalOrderController {
    @Autowired
    private OmsPortalOrderService omsPortalOrderService;

    @ApiOperation("根据购物车信息生成订单")
    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    @ResponseBody
    public Object generateOrder(@RequestBody OrderParam param) {
        return omsPortalOrderService.generateOrder(param);
    }
}
