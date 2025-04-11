package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.SuppliesLiuyanEntity;
import java.util.Map;

/**
 * 运动留言 服务类
 */
public interface SuppliesLiuyanService extends IService<SuppliesLiuyanEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);

}
