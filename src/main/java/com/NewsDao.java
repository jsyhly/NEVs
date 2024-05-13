package com;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.NewsEntity;
import com.entity.view.NewsView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 公告信息 Dao 接口
 *
 * @author 
 */
public interface NewsDao extends BaseMapper<NewsEntity> {

   List<NewsView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
