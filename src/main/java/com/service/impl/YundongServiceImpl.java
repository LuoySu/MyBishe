package com.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.YundongDao;
import com.entity.YundongEntity;
import com.entity.view.YundongView;
import com.service.YundongService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 运动教程 服务实现类
 */
@Service("yundongService")
@Transactional
public class YundongServiceImpl extends ServiceImpl<YundongDao, YundongEntity> implements YundongService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        Page<YundongView> page =new Query<YundongView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
