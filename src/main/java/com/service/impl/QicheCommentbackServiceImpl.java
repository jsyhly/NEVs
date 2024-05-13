package com.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.QicheCommentbackDao;
import com.entity.QicheCommentbackEntity;
import com.entity.view.QicheCommentbackView;
import com.service.QicheCommentbackService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


/**
 * 汽车评价 服务实现类
 */
@Service("qicheCommentbackService")
@Transactional
public class QicheCommentbackServiceImpl extends ServiceImpl<QicheCommentbackDao, QicheCommentbackEntity> implements QicheCommentbackService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<QicheCommentbackView> page =new Query<QicheCommentbackView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}

