package com.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.QicheDao;
import com.entity.QicheEntity;
import com.entity.view.QicheView;
import com.service.QicheService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


/**
 * 汽车 服务实现类
 */
@Service("qicheService")
@Transactional
public class QicheServiceImpl extends ServiceImpl<QicheDao, QicheEntity> implements QicheService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<QicheView> page =new Query<QicheView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
