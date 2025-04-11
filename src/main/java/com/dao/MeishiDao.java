package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.MeishiEntity;
import com.entity.view.MeishiView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 膳食信息 Dao 接口
 *
 * @author
 */
public interface MeishiDao extends BaseMapper<MeishiEntity> {

   List<MeishiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
