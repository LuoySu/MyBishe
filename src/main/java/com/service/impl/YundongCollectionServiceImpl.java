package com.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.YundongCollectionDao;
import com.entity.YundongCollectionEntity;
import com.entity.view.YundongCollectionView;
import com.service.YundongCollectionService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 运动教程收藏 服务实现类
 */
@Service("yundongCollectionService")
@Transactional
public class YundongCollectionServiceImpl extends ServiceImpl<YundongCollectionDao, YundongCollectionEntity> implements YundongCollectionService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        Page<YundongCollectionView> page =new Query<YundongCollectionView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
