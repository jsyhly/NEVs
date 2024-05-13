package com.entity.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 汽车订单
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("qiche_order")
public class QicheOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单号
     */

    @TableField(value = "qiche_order_uuid_number")
    private String qicheOrderUuidNumber;


    /**
     * 收货地址
     */

    @TableField(value = "address_id")
    private Integer addressId;


    /**
     * 汽车
     */

    @TableField(value = "qiche_id")
    private Integer qicheId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 购买数量
     */

    @TableField(value = "buy_number")
    private Integer buyNumber;


    /**
     * 实付价格
     */

    @TableField(value = "qiche_order_true_price")
    private Double qicheOrderTruePrice;


    /**
     * 订单类型
     */

    @TableField(value = "qiche_order_types")
    private Integer qicheOrderTypes;


    /**
     * 支付类型
     */

    @TableField(value = "qiche_order_payment_types")
    private Integer qicheOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
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
	 * 设置：订单号
	 */
    public String getQicheOrderUuidNumber() {
        return qicheOrderUuidNumber;
    }


    /**
	 * 获取：订单号
	 */

    public void setQicheOrderUuidNumber(String qicheOrderUuidNumber) {
        this.qicheOrderUuidNumber = qicheOrderUuidNumber;
    }
    /**
	 * 设置：收货地址
	 */
    public Integer getAddressId() {
        return addressId;
    }


    /**
	 * 获取：收货地址
	 */

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
    /**
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
	 * 设置：购买数量
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 获取：购买数量
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getQicheOrderTruePrice() {
        return qicheOrderTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setQicheOrderTruePrice(Double qicheOrderTruePrice) {
        this.qicheOrderTruePrice = qicheOrderTruePrice;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getQicheOrderTypes() {
        return qicheOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setQicheOrderTypes(Integer qicheOrderTypes) {
        this.qicheOrderTypes = qicheOrderTypes;
    }
    /**
	 * 设置：支付类型
	 */
    public Integer getQicheOrderPaymentTypes() {
        return qicheOrderPaymentTypes;
    }


    /**
	 * 获取：支付类型
	 */

    public void setQicheOrderPaymentTypes(Integer qicheOrderPaymentTypes) {
        this.qicheOrderPaymentTypes = qicheOrderPaymentTypes;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

