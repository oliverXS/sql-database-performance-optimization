package com.oliver.domain.query;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaorui
 */
@Data
public class OrderInfoQuery implements Serializable {
    /**
     * Order number
     */
    private String orderNo;
    /**
     * Order status, 10 pending payment, 20 pending orders, 30 orders received, 40 delivery, 50 completed, 55 partial refunds, 60 full refunds, 70 cancellation orders
     */
    private Long orderStatus;
    /**
     *
     * Payment status, 1 to be paid, 2 successful payment
     */
    private Long payStatus;
    /**
     * User id
     */
    private Long userId;
    /**
     * Merchant id
     */
    private Long MerchantId;
    /**
     * Trading time start time
     */
    private Date startTransTime;
    /**
     * End time of transaction time
     */
    private Date endTransTime;
    /**
     * Current page
     */
    protected Integer current;
    /**
     * Number of articles
     */
    protected Integer size;
}
