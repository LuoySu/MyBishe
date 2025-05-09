package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.YundongEntity;
import com.utils.PageUtils;

import java.util.Map;

/**
 * 运动教程 服务类
 */
public interface YundongService extends IService<YundongEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);

}
