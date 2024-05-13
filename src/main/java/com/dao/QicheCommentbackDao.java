package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.QicheCommentbackEntity;
import com.entity.view.QicheCommentbackView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 汽车评价 Dao 接口
 *
 * @author 
 */
public interface QicheCommentbackDao extends BaseMapper<QicheCommentbackEntity> {

   List<QicheCommentbackView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

