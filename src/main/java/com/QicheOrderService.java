package com;

import com.baomidou.mybatisplus.service.IService;
import com.entity.QicheOrderEntity;
import com.utils.PageUtils;

import java.util.Map;


/**
 * 汽车订单 服务类
 */
public interface QicheOrderService extends IService<QicheOrderEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}