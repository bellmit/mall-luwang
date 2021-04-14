package com.mall.luwang.service.impl;

import cn.hutool.json.JSONUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.mall.luwang.dto.OssCallbackParam;
import com.mall.luwang.dto.OssCallbackResult;
import com.mall.luwang.dto.OssPolicyResult;
import com.mall.luwang.service.OssService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author luWang
 * @date 2021/4/14 - 14:42
 * @day_of_week: 星期三
 */
@Service
public class OssServiceImpl implements OssService {
    private static Logger LOGGER = LoggerFactory.getLogger(OssServiceImpl.class);
    @Value("${aliyun.oss.policy.expire}")
    private int ALIYUN_OSS_EXPIRE;
    @Value("${aliyun.oss.maxSize}")
    private int ALIYUN_OSS_MAX_SIZE;
    @Value("${aliyun.oss.callback}")
    private String ALIYUN_OSS_CALLBACK;
    @Value("${aliyun.oss.bucketName}")
    private String ALIYUN_OSS_BUCKET_NAME;
    @Value("${aliyun.oss.endpoint}")
    private String ALIYUN_OSS_ENDPOINT;
    @Value("${aliyun.oss.dir.prefix}")
    private String ALIYUN_OSS_DIR_PREFIX;

    @Autowired
    private OSSClient ossClient;

    @Override
    public OssPolicyResult policyResult() {
        OssPolicyResult result = new OssPolicyResult();
        //存储目录
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dir = ALIYUN_OSS_DIR_PREFIX + dateFormat.format(new Date());
        //签名有效期
        long expireEndTime = System.currentTimeMillis() + ALIYUN_OSS_EXPIRE * 1000;
        Date expriation = new Date(expireEndTime);
        //文件大小
        long maxSize = ALIYUN_OSS_MAX_SIZE * 1024 * 1024;
        //回调
        OssCallbackParam callbackParam = new OssCallbackParam();
        callbackParam.setCallbackUrl(ALIYUN_OSS_CALLBACK);
        callbackParam.setCallbackBody("filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
        callbackParam.setCallbackBodyType("application/x-www-form-urlencoded");
        //提交节点
        String action = "htp://" + ALIYUN_OSS_BUCKET_NAME + "." + ALIYUN_OSS_ENDPOINT;
        try {
            PolicyConditions policyConditions = new PolicyConditions();
            policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, maxSize);
            policyConditions.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
            String postPolicy = ossClient.generatePostPolicy(expriation, policyConditions);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String policy = BinaryUtil.toBase64String(binaryData);
            String signature = ossClient.calculatePostSignature(postPolicy);
            String callbackData = BinaryUtil.toBase64String(JSONUtil.parse(callbackParam).toString().getBytes("utf-8"));
            //返回结果
            result.setAccessKeyId(ossClient.getCredentialsProvider().getCredentials().getAccessKeyId());
            result.setPolicy(policy);
            result.setSignature(signature);
            result.setDir(dir);
            result.setCallback(callbackData);
            result.setHost(action);
        } catch (Exception e) {
            LOGGER.error("签名生成失败", e);
        }
        return result;
    }

    @Override
    public OssCallbackResult callbackResult(HttpServletRequest request) {
        OssCallbackResult result = new OssCallbackResult();
        String filename = request.getParameter("filename");
        filename = "http://".concat(ALIYUN_OSS_BUCKET_NAME).concat(".").concat(ALIYUN_OSS_ENDPOINT).concat("/").concat(filename);
        result.setFilename(filename);
        result.setSize(request.getParameter("size"));
        result.setMimeType(request.getParameter("mimeType"));
        result.setWidth(request.getParameter("width"));
        result.setHeight(request.getParameter("height"));
        return result;
    }
}
