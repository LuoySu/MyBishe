package com.service.impl;

import org.springframework.stereotype.Service;

import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import com.dao.SuppliesCollectionDao;
import com.entity.SuppliesCollectionEntity;
import com.service.SuppliesCollectionService;
import com.entity.view.SuppliesCollectionView;

/**
 * 运动收藏 服务实现类
 */
@Service("suppliesCollectionService")
@Transactional
public class SuppliesCollectionServiceImpl extends ServiceImpl<SuppliesCollectionDao, SuppliesCollectionEntity> implements SuppliesCollectionService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        Page<SuppliesCollectionView> page =new Query<SuppliesCollectionView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
