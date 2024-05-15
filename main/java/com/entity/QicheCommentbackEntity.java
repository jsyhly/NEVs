package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
<<<<<<< HEAD:main/java/com/entity/QicheCommentbackEntity.java
 *
 * 汽车评价
=======
 * 汽车试驾预定
>>>>>>> 5e07c34f7e0a4103594c3fa1a32e75e4369a300c:main/java/com/entity/QicheShijiaEntity.java
 *
 * @author 
 * @email
 */
<<<<<<< HEAD:main/java/com/entity/QicheCommentbackEntity.java
@TableName("qiche_commentback")
public class QicheCommentbackEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public QicheCommentbackEntity() {

	}

	public QicheCommentbackEntity(T t) {
=======
@TableName("qiche_shijia")
public class QicheShijiaEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public QicheShijiaEntity() {

	}

	public QicheShijiaEntity(T t) {
>>>>>>> 5e07c34f7e0a4103594c3fa1a32e75e4369a300c:main/java/com/entity/QicheShijiaEntity.java
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
<<<<<<< HEAD:main/java/com/entity/QicheCommentbackEntity.java
     * 汽车
     */
    @TableField(value = "qiche_id")

    private Integer qicheId;
=======
     * 编号
     */
    @TableField(value = "qiche_shijia_order_uuid_number")

    private String qicheShijiaOrderUuidNumber;
>>>>>>> 5e07c34f7e0a4103594c3fa1a32e75e4369a300c:main/java/com/entity/QicheShijiaEntity.java


    /**
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
<<<<<<< HEAD:main/java/com/entity/QicheCommentbackEntity.java
     * 评价内容
     */
    @TableField(value = "qiche_commentback_text")

    private String qicheCommentbackText;


    /**
     * 评价时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 回复内容
     */
    @TableField(value = "reply_text")

    private String replyText;


    /**
     * 回复时间
=======
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 预定时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "qiche_shijia_order_time")

    private Date qicheShijiaOrderTime;


    /**
     * 预定审核
>>>>>>> 5e07c34f7e0a4103594c3fa1a32e75e4369a300c:main/java/com/entity/QicheShijiaEntity.java
     */
    @TableField(value = "qiche_shijia_order_yesno_types")

    private Integer qicheShijiaOrderYesnoTypes;


    /**
     * 审核结果
     */
    @TableField(value = "qiche_shijia_order_yesno_text")

    private String qicheShijiaOrderYesnoText;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
<<<<<<< HEAD:main/java/com/entity/QicheCommentbackEntity.java
	 * 设置：汽车
	 */
    public Integer getQicheId() {
        return qicheId;
    }
    /**
	 * 获取：汽车
	 */

    public void setQicheId(Integer qicheId) {
        this.qicheId = qicheId;
    }
    /**
	 * 设置：用户
=======
	 * 设置：编号
>>>>>>> 5e07c34f7e0a4103594c3fa1a32e75e4369a300c:main/java/com/entity/QicheShijiaEntity.java
	 */
    public String getQicheShijiaOrderUuidNumber() {
        return qicheShijiaOrderUuidNumber;
    }
    /**
<<<<<<< HEAD:main/java/com/entity/QicheCommentbackEntity.java
	 * 获取：用户
=======
	 * 获取：编号
>>>>>>> 5e07c34f7e0a4103594c3fa1a32e75e4369a300c:main/java/com/entity/QicheShijiaEntity.java
	 */

    public void setQicheShijiaOrderUuidNumber(String qicheShijiaOrderUuidNumber) {
        this.qicheShijiaOrderUuidNumber = qicheShijiaOrderUuidNumber;
    }
    /**
	 * 设置：评价内容
	 */
    public String getQicheCommentbackText() {
        return qicheCommentbackText;
    }
    /**
	 * 获取：评价内容
	 */

    public void setQicheCommentbackText(String qicheCommentbackText) {
        this.qicheCommentbackText = qicheCommentbackText;
    }
    /**
<<<<<<< HEAD:main/java/com/entity/QicheCommentbackEntity.java
	 * 设置：评价时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 获取：评价时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：回复内容
	 */
    public String getReplyText() {
        return replyText;
    }
    /**
	 * 获取：回复内容
	 */

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }
    /**
	 * 设置：回复时间
=======
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：预定时间
	 */
    public Date getQicheShijiaOrderTime() {
        return qicheShijiaOrderTime;
    }
    /**
	 * 获取：预定时间
	 */

    public void setQicheShijiaOrderTime(Date qicheShijiaOrderTime) {
        this.qicheShijiaOrderTime = qicheShijiaOrderTime;
    }
    /**
	 * 设置：预定审核
>>>>>>> 5e07c34f7e0a4103594c3fa1a32e75e4369a300c:main/java/com/entity/QicheShijiaEntity.java
	 */
    public Integer getQicheShijiaOrderYesnoTypes() {
        return qicheShijiaOrderYesnoTypes;
    }
    /**
<<<<<<< HEAD:main/java/com/entity/QicheCommentbackEntity.java
	 * 获取：回复时间
=======
	 * 获取：预定审核
>>>>>>> 5e07c34f7e0a4103594c3fa1a32e75e4369a300c:main/java/com/entity/QicheShijiaEntity.java
	 */

    public void setQicheShijiaOrderYesnoTypes(Integer qicheShijiaOrderYesnoTypes) {
        this.qicheShijiaOrderYesnoTypes = qicheShijiaOrderYesnoTypes;
    }
    /**
	 * 设置：审核结果
	 */
    public String getQicheShijiaOrderYesnoText() {
        return qicheShijiaOrderYesnoText;
    }
    /**
	 * 获取：审核结果
	 */

    public void setQicheShijiaOrderYesnoText(String qicheShijiaOrderYesnoText) {
        this.qicheShijiaOrderYesnoText = qicheShijiaOrderYesnoText;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
<<<<<<< HEAD:main/java/com/entity/QicheCommentbackEntity.java
        return "QicheCommentback{" +
            "id=" + id +
            ", qicheId=" + qicheId +
            ", yonghuId=" + yonghuId +
            ", qicheCommentbackText=" + qicheCommentbackText +
            ", insertTime=" + insertTime +
            ", replyText=" + replyText +
            ", updateTime=" + updateTime +
=======
        return "QicheShijia{" +
            "id=" + id +
            ", qicheShijiaOrderUuidNumber=" + qicheShijiaOrderUuidNumber +
            ", qicheId=" + qicheId +
            ", yonghuId=" + yonghuId +
            ", qicheShijiaOrderTime=" + qicheShijiaOrderTime +
            ", qicheShijiaOrderYesnoTypes=" + qicheShijiaOrderYesnoTypes +
            ", qicheShijiaOrderYesnoText=" + qicheShijiaOrderYesnoText +
            ", insertTime=" + insertTime +
>>>>>>> 5e07c34f7e0a4103594c3fa1a32e75e4369a300c:main/java/com/entity/QicheShijiaEntity.java
            ", createTime=" + createTime +
        "}";
    }
}
