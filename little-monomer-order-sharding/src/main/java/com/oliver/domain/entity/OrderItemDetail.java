package com.oliver.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author xiaorui
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("order_item_detail")
public class OrderItemDetail extends BaseEntity {
    /**
     * Order number
     */
    private String orderNo;

    /**
     * Product id
     */
    private Long productId;

    /**
     * Product category id
     */
    private Long categoryId;

    /**
     * Goods number
     */
    private BigDecimal goodsNum;

    /**
     * Good price
     */
    private BigDecimal goodsPrice;

    /**
     * Goods Amount
     */
    private BigDecimal goodsAmount;

    /**
     * Discount Amount
     */
    private BigDecimal discountAmount;

    /**
     * Discount id
     */
    private Long discountId;
}
