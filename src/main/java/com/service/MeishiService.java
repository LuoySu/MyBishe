package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.MeishiEntity;
import com.utils.PageUtils;

import java.util.Map;

/**
 * 膳食信息 服务类
 */
public interface MeishiService extends IService<MeishiEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);

}
