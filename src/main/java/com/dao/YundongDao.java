package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.YundongEntity;
import com.entity.view.YundongView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 运动教程 Dao 接口
 *
 * @author
 */
public interface YundongDao extends BaseMapper<YundongEntity> {

   List<YundongView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
