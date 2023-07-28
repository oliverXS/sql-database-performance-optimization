package com.oliver.service.user.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oliver.bean.requet.order.UserOrderInfoRequestDTO;
import com.oliver.common.util.RedisUtils;
import com.oliver.constant.OrderConstant;
import com.oliver.domain.entity.OrderInfo;
import com.oliver.domain.entity.OrderItemDetail;
import com.oliver.domain.query.OrderInfoQuery;
import com.oliver.domain.valid.OrderValidation;
import com.oliver.domain.vo.OrderInfoVO;
import com.oliver.domain.vo.OrderItemDetailVO;
import com.oliver.repository.OrderInfoRepository;
import com.oliver.service.user.UserOrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserOrderInfoServiceImpl implements UserOrderInfoService {
    @Autowired
    private OrderInfoRepository orderInfoRepository;
    @Autowired
    private RedisUtils redisUtils;

    private final static Long FINISH = 50L;

    /**
     * Generate and insert order info into Mysql and Redis
     * @param userOrderInfoRequestDTO The data transfer object containing user order information.
     */
    @Override
    public void generateOrder(UserOrderInfoRequestDTO userOrderInfoRequestDTO) {
        // Add order information
        OrderInfo orderInfo = userOrderInfoRequestDTO.getOrderInfo();
        // Add order details
        List<OrderItemDetail> orderItemDetailList = userOrderInfoRequestDTO.getOrderItemDetailList();
        // Get the order number
        String orderNo = orderInfoRepository.generateOrderInfo(orderInfo, orderItemDetailList);
        // Set Redis
        redisUtils.set(OrderConstant.OrderRedisKey.PREFIX_KEY + orderNo, orderNo, 60L, TimeUnit.SECONDS);
    }

    /**
     * Queries order information either from Redis or Mysql based on the provided {@link OrderInfoQuery} object.
     *
     * @param orderInfoQuery The query object containing parameters to filter order information.
     * @return A {@link Page} containing a list of {@link OrderInfoVO} objects representing the order information.
     */
    @Override
    public Page<OrderInfoVO> queryOrderInfoList(OrderInfoQuery orderInfoQuery) {
        // Validate the orderInfoQuery to ensure it meets the required criteria
        OrderValidation.checkVerifyOrderQuery(orderInfoQuery);

        // Create a new Page object to hold the results
        Page<OrderInfoVO> page = new Page<>();

        // Set the current page number and page size based on the query parameters
        page.setCurrent(orderInfoQuery.getCurrent());
        page.setSize(orderInfoQuery.getSize());

        // Query completed orders
        if (FINISH.equals(orderInfoQuery.getOrderStatus())) {
            // Assemble the Redis key based on the query parameters to uniquely identify the data
            // redis key = user_id + page_number + page_size
            String redisKey = orderInfoQuery.getUserId() + orderInfoQuery.getCurrent().toString() + orderInfoQuery.getSize().toString();

            // Check if the data is available in the Redis cache
            Object redisObject = redisUtils.get(redisKey);

            // If Redis cache is empty, query the data from the database
            if (Objects.isNull(redisObject)) {
                // Query the order information list from the database
                Page<OrderInfoVO> userOrderInfoVOPage = orderInfoRepository.queryOrderInfoList(page, orderInfoQuery);

                // Set the result into Redis cache with an expiration time of one hour (3600 seconds)
                redisUtils.set(redisKey, userOrderInfoVOPage, 3600L, TimeUnit.SECONDS);

                // Return the query result obtained from the database
                return userOrderInfoVOPage;
            }

            // If Redis cache contains the data, return it directly without querying the database
            log.info("Retrieved data from Redis, key: {}", redisKey);
            return (Page<OrderInfoVO>) redisObject;
        }

        // If the order status is not 'FINISH', query the order information list from the database
        return orderInfoRepository.queryOrderInfoList(page, orderInfoQuery);
    }

    @Override
    public List<OrderItemDetailVO> getOrderItemDetail(String orderNo) {
        return orderInfoRepository.getOrderItemDetail(orderNo);
    }

    @Override
    public void updateOrderStatus(String orderNo, Integer status) {
        orderInfoRepository.updateOrderStatus(orderNo, status);
    }

    @Override
    public Page<OrderInfoVO> queryHistoryOrderList(Page page, Long userId) {
        return null;
    }

    @Override
    public void autoCancelOrder() {
        List<OrderInfo> orderInfos = orderInfoRepository.queryNoPayOrderList();
        for (OrderInfo orderInfo : orderInfos) {
            orderInfoRepository.updateOrderStatus(orderInfo.getOrderNo(), OrderConstant.OrderBaseStatus.CANCEL_ORDER);
        }
    }

    @Override
    public void redisCancelOrder(String orderNo) {
        // Check if the order is still unpaid
        OrderInfoVO orderInfoByNo = orderInfoRepository.getOrderInfoByNo(orderNo);
        if (!Objects.isNull(orderInfoByNo)) {
            // Update the order status
            orderInfoRepository.updateOrderStatus(orderNo, OrderConstant.OrderBaseStatus.CANCEL_ORDER);
        }
    }

}
