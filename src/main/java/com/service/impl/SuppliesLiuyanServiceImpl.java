package com.service.impl;

import org.springframework.stereotype.Service;

import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import com.dao.SuppliesLiuyanDao;
import com.entity.SuppliesLiuyanEntity;
import com.service.SuppliesLiuyanService;
import com.entity.view.SuppliesLiuyanView;

/**
 * 运动留言 服务实现类
 */
@Service("suppliesLiuyanService")
@Transactional
public class SuppliesLiuyanServiceImpl extends ServiceImpl<SuppliesLiuyanDao, SuppliesLiuyanEntity> implements SuppliesLiuyanService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        Page<SuppliesLiuyanView> page =new Query<SuppliesLiuyanView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
