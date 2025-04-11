package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.MeishiCollectionEntity;
import com.entity.view.MeishiCollectionView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 膳食信息收藏 Dao 接口
 *
 * @author
 */
public interface MeishiCollectionDao extends BaseMapper<MeishiCollectionEntity> {

   List<MeishiCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
