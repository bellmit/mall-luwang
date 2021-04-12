package com.mall.luwang.service;

import com.mall.luwang.mbg.model.PmsBrand;

import java.util.List;

/**
 * @author luWang
 * @date 2021/4/12 - 14:12
 * @day_of_week: 星期一
 */
public interface PmsBrandService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
