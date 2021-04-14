package com.mall.luwang.controller;

import com.mall.luwang.common.CommonResult;
import com.mall.luwang.dto.OssCallbackResult;
import com.mall.luwang.dto.OssPolicyResult;
import com.mall.luwang.service.impl.OssServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author luWang
 * @date 2021/4/14 - 14:59
 * @day_of_week: 星期三
 */
@Api(tags = "OssController", description = "Oss管理")
@Controller
@RequestMapping("/aliyun/oss")
public class OssController {
    @Autowired
    private OssServiceImpl ossService;

    @ApiOperation(value = "oss上传签名生成")
    @RequestMapping(value = "/policy", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OssPolicyResult> policy() {
        OssPolicyResult result = ossService.policyResult();
        return CommonResult.success(result);
    }

    /**
     * TODO 上传需要前端提取文件再来测试
     * @param request
     * @return
     */
    @ApiOperation(value = "oss上传成功回调")
    @RequestMapping(value = "callback", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<OssCallbackResult> callback(HttpServletRequest request) {
        OssCallbackResult ossCallbackResult = ossService.callbackResult(request);
        return CommonResult.success(ossCallbackResult);
    }
}
