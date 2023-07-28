package com.oliver.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiaorui
 */
@Data
public class OrderItemDetailVO implements Serializable {
    /**
     * Order number
     */
    private String orderNo;

    /**
     * Order amount
     */
    private BigDecimal orderAmount;

    /**
     * Freight
     */
    private BigDecimal orderFreight;

    /**
     * Order status: 10 - Pending payment, 20 - Pending acceptance, 30 - Order received, 40 - In delivery, 50 - Completed, 55 - Partial refund, 60 - Full refund, 70 - Order canceled
     */
    private Integer orderStatus;

    /**
     * Transaction time
     */
    private Date transTime;

    /**
     * Payment status: 1 - Pending payment, 2 - Payment successful
     */
    private Integer payStatus;

    /**
     * Payment completion time
     */
    private Date rechargeTime;

    /**
     * Actual payment amount
     */
    private BigDecimal payAmount;

    /**
     * Payment discount amount
     */
    private BigDecimal payDiscountAmount;

    /**
     * Shipping address ID
     */
    private Long addressId;

    /**
     * Order remarks/message
     */
    private String remark;

    /**
     * Delivery method: 1 - Self-pickup, 2 - Delivery
     */
    private Integer deliveryType;

    /**
     * Delivery status: 0 - Awaiting delivery, 1 - Goods received, delivery in progress, 2 - Goods received, delivered
     */
    private Integer deliveryStatus;

    /**
     * Estimated delivery time
     */
    private Date deliveryExpectTime;

    /**
     * Delivery completion time
     */
    private Date deliveryCompleteTime;

    /**
     * Delivery cost
     */
    private BigDecimal deliveryAmount;

    /**
     * Coupon ID
     */
    private Long couponId;

    /**
     * Product ID
     */
    private Long productId;

    /**
     * Product category ID
     */
    private Long categoryId;

    /**
     * Quantity of products purchased
     */
    private BigDecimal goodsNum;

    /**
     * Unit price of the product
     */
    private BigDecimal goodsPrice;

    /**
     * Total price of the product(s)
     */
    private BigDecimal goodsAmount;

    /**
     * Product discount amount
     */
    private BigDecimal discountAmount;

    /**
     * Participating activity ID
     */
    private Long discountId;
}
