package com;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.view.QicheShijiaView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 汽车试驾预定 Dao 接口
 *
 * @author 
 */
public interface QicheShijiaDao extends BaseMapper<QicheShijiaEntity> {

   List<QicheShijiaView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
