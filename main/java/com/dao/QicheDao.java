package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.QicheEntity;
import com.entity.view.QicheView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 汽车 Dao 接口
 *
 * @author 
 */
public interface QicheDao extends BaseMapper<QicheEntity> {

   List<QicheView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
