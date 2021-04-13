package com.mall.luwang.service;

import com.mall.luwang.common.CommonResult;

/**
 * 会员管理service
 *
 * @author luWang
 * @date 2021/4/13 - 10:58
 * @day_of_week: 星期二
 */
public interface UmsMemberService {
    /**
     * 生成验证码
     *
     * @param telephone
     * @return
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机是否匹配
     *
     * @param telephone
     * @param authCode
     * @return
     */
    CommonResult verifyAuthCode(String telephone, String authCode);
}
