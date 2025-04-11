package com.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.MeishiDao;
import com.entity.MeishiEntity;
import com.entity.view.MeishiView;
import com.service.MeishiService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 膳食信息 服务实现类
 */
@Service("meishiService")
@Transactional
public class MeishiServiceImpl extends ServiceImpl<MeishiDao, MeishiEntity> implements MeishiService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        Page<MeishiView> page =new Query<MeishiView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
