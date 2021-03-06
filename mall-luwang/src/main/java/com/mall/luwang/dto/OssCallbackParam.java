package com.mall.luwang.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * oss上传成功后的回调参数
 *
 * @author luWang
 * @date 2021/4/14 - 14:40
 * @day_of_week: 星期三
 */
@Data
public class OssCallbackParam {
    @ApiModelProperty("请求的回调地址")
    private String callbackUrl;
    @ApiModelProperty("回调是传入request中的参数")
    private String callbackBody;
    @ApiModelProperty("回调时传入参数的格式，比如表单提交形式")
    private String callbackBodyType;
}
