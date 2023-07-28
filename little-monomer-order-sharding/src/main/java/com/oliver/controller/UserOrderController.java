package com.oliver.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oliver.bean.requet.order.UserOrderInfoRequestDTO;
import com.oliver.bean.web.DataResponse;
import com.oliver.bean.web.OperationResponse;
import com.oliver.bean.web.PageResponse;
import com.oliver.common.enums.OrderCode;
import com.oliver.domain.query.OrderInfoQuery;
import com.oliver.domain.vo.OrderInfoVO;
import com.oliver.domain.vo.OrderItemDetailVO;
import com.oliver.service.user.UserOrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiaorui
 */
@Slf4j
@RestController
@RequestMapping(value = "/user/order")
public class UserOrderController {
    @Autowired
    private UserOrderInfoService userOrderInfoService;

    /**
     * Create an order.
     *
     * @param userOrderInfoRequestDTO The UserOrderInfoRequestDTO containing the order information.
     * @return The OperationResponse indicating the result of the operation.
     */
    @PostMapping("/generateOrder")
    public OperationResponse generateOrder(@RequestBody UserOrderInfoRequestDTO userOrderInfoRequestDTO) {
        try {
            // Start timing
            long bTime = System.currentTimeMillis();
            userOrderInfoService.generateOrder(userOrderInfoRequestDTO);
            // End timing
            long eTime = System.currentTimeMillis();
            // Output
            log.info("Time taken to generate user order: " + (eTime - bTime));
            return OperationResponse.success(OrderCode.ADD_ORDER_SUCCESS.getDesc());
        } catch (Exception e) {
            log.info(e.getMessage());
            return OperationResponse.error(OrderCode.ADD_ORDER_ERROR.getDesc());
        }
    }

    /**
     * Queries the list of order information based on the provided {@link OrderInfoQuery} object.
     *
     * @param orderInfoQuery The query object containing parameters for the order information retrieval.
     * @return A {@link Page} containing a list of {@link OrderInfoVO} objects representing the order information.
     */
    @PostMapping("/queryOrderInfoList")
    public PageResponse queryOrderInfoList(@RequestBody OrderInfoQuery orderInfoQuery) {
        // Start timing
        long bTime = System.currentTimeMillis();
        try {
            Page<OrderInfoVO> orderInfoPage = userOrderInfoService.queryOrderInfoList(orderInfoQuery);
            // End timing
            long eTime = System.currentTimeMillis();
            // Output
            log.info("Time taken to query user order: " + (eTime - bTime));
            return PageResponse.success(orderInfoPage);
        } catch (Exception e) {
            log.info(e.getMessage());
            return  PageResponse.error(e.getMessage());
        }
    }

    @PostMapping("getOrderItemDetail")
    public DataResponse getOrderItemDetail(@RequestParam("orderNo") String orderNo) {
        try {
            // Start timing
            long bTime = System.currentTimeMillis();
            List<OrderItemDetailVO> orderItemDetailVOList = userOrderInfoService.getOrderItemDetail(orderNo);
            // End timing
            long eTime = System.currentTimeMillis();
            // Output
            log.info("Time taken to query user order detail: " + (eTime - bTime));
            return DataResponse.success(orderItemDetailVOList);
        } catch (Exception e) {
            return DataResponse.error(OrderCode.QUERY_ORDER_ERROR.getDesc());
        }
    }

    /**
     * Update order status - Cancel order, Confirm receipt.
     *
     * @param orderNo The order number.
     * @param status The status to update.
     * @return The OperationResponse indicating the result of the operation.
     */
    @PostMapping("/updateOrderStatus")
    public OperationResponse updateOrderStatus(@RequestParam("orderNo") String orderNo, @RequestParam("status") Integer status) {
        try {
            // Start timing
            long bTime = System.currentTimeMillis();
            userOrderInfoService.updateOrderStatus(orderNo, status);
            // End timing
            long eTime = System.currentTimeMillis();
            // Output
            log.info("Time taken to cancel user order: " + (eTime - bTime));
            return OperationResponse.success(OrderCode.DELETE_ORDER_SUCCESS.desc);
        } catch (Exception e) {
            log.info(e.getMessage());
            return OperationResponse.error(OrderCode.DELETE_ORDER_ERROR.getDesc());
        }
    }

}
