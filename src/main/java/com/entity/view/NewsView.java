package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.NewsEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


/**
 * 公告信息
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("news")
public class NewsView extends NewsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 公告类型的值
		*/
		private String newsValue;



	public NewsView() {

	}

	public NewsView(NewsEntity newsEntity) {
		try {
			BeanUtils.copyProperties(this, newsEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 公告类型的值
			*/
			public String getNewsValue() {
				return newsValue;
			}
			/**
			* 设置： 公告类型的值
			*/
			public void setNewsValue(String newsValue) {
				this.newsValue = newsValue;
			}













}