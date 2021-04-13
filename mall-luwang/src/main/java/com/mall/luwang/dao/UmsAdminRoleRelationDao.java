package com.mall.luwang.dao;

import com.mall.luwang.mbg.model.UmsPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户与角色管理自定义Dao
 *
 * @author luWang
 * @date 2021/4/13 - 14:18
 * @day_of_week: 星期二
 */
public interface UmsAdminRoleRelationDao {
    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
}
