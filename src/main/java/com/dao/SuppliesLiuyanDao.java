package com.dao;

import com.entity.SuppliesLiuyanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.SuppliesLiuyanView;

/**
 * 运动留言 Dao 接口
 *
 * @author
 */
public interface SuppliesLiuyanDao extends BaseMapper<SuppliesLiuyanEntity> {

   List<SuppliesLiuyanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
