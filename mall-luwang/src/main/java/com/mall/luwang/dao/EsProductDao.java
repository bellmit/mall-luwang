package com.mall.luwang.dao;

import com.mall.luwang.nosql.elasticsearch.document.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 搜索系统中的商品管理自定义Dao
 *
 * @author luWang
 * @date 2021/4/13 - 20:03
 * @day_of_week: 星期二
 */
public interface EsProductDao {
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
