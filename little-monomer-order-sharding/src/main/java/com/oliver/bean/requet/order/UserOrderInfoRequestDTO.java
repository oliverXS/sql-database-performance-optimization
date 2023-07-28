package com.oliver.bean.requet.order;

import com.oliver.domain.entity.OrderInfo;
import com.oliver.domain.entity.OrderItemDetail;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiaorui
 */
@Data
public class UserOrderInfoRequestDTO implements Serializable {
    /**
     * Order information.
     */
    private OrderInfo orderInfo;

    /**
     * Order details.
     */
    private List<OrderItemDetail> orderItemDetailList;

}
