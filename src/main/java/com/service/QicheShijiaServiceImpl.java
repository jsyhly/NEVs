package com.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.QicheShijiaDao;
import com.entity.QicheShijiaEntity;
import com.entity.view.QicheShijiaView;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


/**
 * 汽车试驾预定 服务实现类
 */
@Service("qicheShijiaService")
@Transactional
public class QicheShijiaServiceImpl extends ServiceImpl<QicheShijiaDao, QicheShijiaEntity> implements QicheShijiaService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<QicheShijiaView> page =new Query<QicheShijiaView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
