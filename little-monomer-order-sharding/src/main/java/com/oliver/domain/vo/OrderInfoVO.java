package com.oliver.domain.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author xiaorui
 */
@Data
public class OrderInfoVO extends BaseVO {
    /**
     * Order number
     */
    private String orderNo;

    /**
     * Order status: 10 - Pending payment, 20 - Pending acceptance, 30 - Order received, 40 - In delivery, 50 - Completed, 55 - Partial refund, 60 - Full refund, 70 - Order canceled
     */
    private Integer orderStatus;

    /**
     * Transaction time
     */
    private Date transTime;

    /**
     * Merchant ID
     */
    private Long merchantName;

    /**
     * Payment completion time
     */
    private Date rechargeTime;

    /**
     * Actual payment amount
     */
    private BigDecimal payAmount;

    /**
     * Delivery status: 0 - Awaiting delivery, 1 - Goods received, delivery in progress, 2 - Goods received, delivered
     */
    private Integer deliveryStatus;

    /**
     * Estimated delivery time
     */
    private Date deliveryExpectTime;

    /**
     * Order details list
     */
    private List<OrderItemDetailVO> orderItemDetails;

    /**
     * Order amount
     */
    private BigDecimal orderAmount;
}
