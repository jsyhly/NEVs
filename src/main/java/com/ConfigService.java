
package com;

import com.baomidou.mybatisplus.service.IService;
import com.entity.ConfigEntity;
import com.utils.PageUtils;

import java.util.Map;


/**
 * 系统用户
 * @author
 * @date
 */
public interface ConfigService extends IService<ConfigEntity> {
	PageUtils queryPage(Map<String, Object> params);
}
