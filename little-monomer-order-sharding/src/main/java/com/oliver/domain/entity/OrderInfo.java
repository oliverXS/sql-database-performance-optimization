package com.oliver.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiaorui
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("order_info")
public class OrderInfo extends BaseEntity{
    /**
     * Order number
     */
    private String orderNo;

    /**
     * Order amount
     */
    private BigDecimal orderAmount;

    /**
     * Order Freight
     */
    private BigDecimal orderFreight;

    /**
     * Order status, 10 pending payment, 20 pending orders, 30 orders received, 40 delivery, 50 completed, 55 partial refunds, 60 full refunds, 70 cancellation orders
     */
    private Integer orderStatus;

    /**
     * Trading time
     */
    private Date transTime;

    /**
     * Payment status, 1 to be paid, 2 successful payment
     */
    private Integer payStatus;

    /**
     * Merchant Id
     */
    private Long merchantId;

    /**
     * User Id
     */
    private Long userId;

    /**
     * Payment completion time
     */
    private Date rechargeTime;

    /**
     * The actual amount paid
     */
    private BigDecimal payAmount;

    /**
     * Pay the preferential amount
     */
    private BigDecimal payDiscountAmount;

    /**
     * Delivery address ID
     */
    private Long addressId;

    /**
     * Order notes and messages
     */
    private String remark;

    /**
     * Delivery method 1 self-pickup 2 Delivery
     */
    private Integer deliveryType;

    /**
     * Delivery status, 0 to be received, 1 received and delivered, 2 received, delivered
     */
    private Integer deliveryStatus;

    /**
     * Estimated delivery time
     */
    private Date deliveryExpectTime;

    /**
     * Delivery time of delivery
     */
    private Date deliveryCompleteTime;

    /**
     * Delivery freight
     */
    private BigDecimal deliveryAmount;

    /**
     * Coupon id
     */
    private Long couponId;
}
