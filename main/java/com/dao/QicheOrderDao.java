package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.QicheOrderEntity;
import com.entity.view.QicheOrderView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 汽车订单 Dao 接口
 *
 * @author 
 */
public interface QicheOrderDao extends BaseMapper<QicheOrderEntity> {

   List<QicheOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
