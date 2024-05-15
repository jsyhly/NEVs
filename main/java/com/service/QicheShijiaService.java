package com.service;

import com.baomidou.mybatisplus.service.IService;
<<<<<<< HEAD
import com.entity.QicheShijiaEntity;
import com.utils.PageUtils;

import java.util.Map;
=======
import com.utils.PageUtils;
import com.entity.QicheShijiaEntity;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
>>>>>>> 5e07c34f7e0a4103594c3fa1a32e75e4369a300c


/**
 * 汽车试驾预定 服务类
 */
public interface QicheShijiaService extends IService<QicheShijiaEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}