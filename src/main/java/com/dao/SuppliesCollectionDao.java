package com.dao;

import com.entity.SuppliesCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.SuppliesCollectionView;

/**
 * 运动收藏 Dao 接口
 *
 * @author
 */
public interface SuppliesCollectionDao extends BaseMapper<SuppliesCollectionEntity> {

   List<SuppliesCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
