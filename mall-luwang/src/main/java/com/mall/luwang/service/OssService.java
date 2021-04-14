package com.mall.luwang.service;

import com.mall.luwang.dto.OssCallbackResult;
import com.mall.luwang.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * oss上传管理service
 *
 * @author luWang
 * @date 2021/4/14 - 14:41
 * @day_of_week: 星期三
 */
public interface OssService {
    /**
     * oss上传策略生成
     *
     * @return
     */
    OssPolicyResult policyResult();

    /**
     * oss上传成功回调
     *
     * @param request
     * @return
     */
    OssCallbackResult callbackResult(HttpServletRequest request);
}
