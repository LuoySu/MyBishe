package com.dao;

import com.entity.SuppliesEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.SuppliesView;

/**
 * 运动信息 Dao 接口
 *
 * @author
 */
public interface SuppliesDao extends BaseMapper<SuppliesEntity> {

   List<SuppliesView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
