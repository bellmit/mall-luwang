package com.mall.luwang.service;

import com.mall.luwang.nosql.mongodb.document.MemberReadHistory;

import java.util.List;

/**
 * 会员浏览记录管理service
 *
 * @author luWang
 * @date 2021/4/14 - 10:41
 * @day_of_week: 星期三
 */
public interface MemberReadHistoryService {
    /**
     * 生成浏览记录
     *
     * @param memberReadHistory
     * @return
     */
    int create(MemberReadHistory memberReadHistory);

    /**
     * 批量删除浏览记录
     *
     * @param ids
     * @return
     */
    int delete(List<String> ids);

    /**
     * 获取用户浏览历史记录
     *
     * @param memberId
     * @return
     */
    List<MemberReadHistory> list(Long memberId);
}
