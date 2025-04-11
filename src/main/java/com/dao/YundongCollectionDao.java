package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.YundongCollectionEntity;
import com.entity.view.YundongCollectionView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 运动教程收藏 Dao 接口
 *
 * @author
 */
public interface YundongCollectionDao extends BaseMapper<YundongCollectionEntity> {

   List<YundongCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
