package com.service.impl;

import org.springframework.stereotype.Service;

import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import com.dao.SuppliesDao;
import com.entity.SuppliesEntity;
import com.service.SuppliesService;
import com.entity.view.SuppliesView;

/**
 * 运动信息 服务实现类
 */
@Service("suppliesService")
@Transactional
public class SuppliesServiceImpl extends ServiceImpl<SuppliesDao, SuppliesEntity> implements SuppliesService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        Page<SuppliesView> page =new Query<SuppliesView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
