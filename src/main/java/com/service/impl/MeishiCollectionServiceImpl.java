package com.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.MeishiCollectionDao;
import com.entity.MeishiCollectionEntity;
import com.entity.view.MeishiCollectionView;
import com.service.MeishiCollectionService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 膳食信息收藏 服务实现类
 */
@Service("meishiCollectionService")
@Transactional
public class MeishiCollectionServiceImpl extends ServiceImpl<MeishiCollectionDao, MeishiCollectionEntity> implements MeishiCollectionService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        Page<MeishiCollectionView> page =new Query<MeishiCollectionView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
