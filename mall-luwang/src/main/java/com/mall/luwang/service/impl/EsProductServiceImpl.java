package com.mall.luwang.service.impl;

import com.mall.luwang.dao.EsProductDao;
import com.mall.luwang.nosql.elasticsearch.document.EsProduct;
import com.mall.luwang.nosql.elasticsearch.repository.EsProductRepository;
import com.mall.luwang.service.EsProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 商品搜索管理Service实现类
 *
 * @author luWang
 * @date 2021/4/13 - 20:00
 * @day_of_week: 星期二
 */
@Service
public class EsProductServiceImpl implements EsProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsProductServiceImpl.class);
    @Autowired
    private EsProductDao esProductDao;
    @Autowired
    private EsProductRepository productRepository;

    @Override
    public int importAll() {
        List<EsProduct> esProductList = esProductDao.getAllEsProductList(null);
        Iterable<EsProduct> esProductIterable = productRepository.saveAll(esProductList);
        Iterator<EsProduct> iterator = esProductIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result ++;
            iterator.next();
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public EsProduct create(Long id) {
        EsProduct product = null;
        List<EsProduct> esProductList = esProductDao.getAllEsProductList(id);
        if(!CollectionUtils.isEmpty(esProductList)){
            EsProduct esProduct = esProductList.get(0);
            product = productRepository.save(esProduct);
        }
        return product;
    }

    @Override
    public void delete(List<Long> ids) {
        if(!CollectionUtils.isEmpty(ids)){
            List<EsProduct> esProductList = new ArrayList<>();
            for (Long id : ids) {
                EsProduct esProduct = new EsProduct();
                esProduct.setId(id);
                esProductList.add(esProduct);
            }
            productRepository.deleteAll(esProductList);
        }
    }

    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return productRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
    }
}
