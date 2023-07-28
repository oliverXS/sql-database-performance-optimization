package com.oliver.service.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oliver.bean.requet.order.UserOrderInfoRequestDTO;
import com.oliver.domain.query.OrderInfoQuery;
import com.oliver.domain.vo.OrderInfoVO;
import com.oliver.domain.vo.OrderItemDetailVO;

import java.util.List;

/**
 * @author xiaorui
 */
public interface UserOrderInfoService {
    /**
     * Submit user order.
     *
     * @param userOrderInfoRequestDTO The data transfer object containing user order information.
     */
    void generateOrder(UserOrderInfoRequestDTO userOrderInfoRequestDTO);

    /**
     * Get a list of orders based on the query criteria.
     *
     * @param orderInfoQuery The query object to filter order information.
     * @return A page containing the list of order information.
     */
    Page<OrderInfoVO> queryOrderInfoList(OrderInfoQuery orderInfoQuery);

    /**
     * Get order item details based on the order number.
     *
     * @param orderNo The order number to retrieve order item details.
     * @return A list of OrderItemDetailVO containing order item information.
     */
    List<OrderItemDetailVO> getOrderItemDetail(String orderNo);

    /**
     * Cancel the order placed by the user.
     *
     * @param orderNo The order number of the order to be canceled.
     * @param status  The status code indicating the cancellation status.
     */
    void updateOrderStatus(String orderNo, Integer status);

    /**
     * Get a list of orders based on the user ID.
     *
     * @param page   The page object for pagination.
     * @param userId The unique identifier of the user.
     * @return A page containing the list of order information.
     */
    Page<OrderInfoVO> queryHistoryOrderList(Page page, Long userId);

    /**
     * Automatically cancel unpaid orders.
     */
    void autoCancelOrder();

    /**
     * Cancel an order based on Redis data.
     *
     * @param orderNo The order number of the order to be canceled.
     */
    void redisCancelOrder(String orderNo);
}
