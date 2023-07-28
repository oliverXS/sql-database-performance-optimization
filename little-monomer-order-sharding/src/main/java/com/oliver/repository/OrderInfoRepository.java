package com.oliver.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oliver.context.utils.GenerateOrderNoUtils;
import com.oliver.domain.entity.OrderInfo;
import com.oliver.domain.entity.OrderItemDetail;
import com.oliver.domain.query.OrderInfoQuery;
import com.oliver.domain.vo.OrderInfoVO;
import com.oliver.domain.vo.OrderItemDetailVO;
import com.oliver.mapper.OrderInfoMapper;
import com.oliver.mapper.OrderItemDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xiaorui
 */
@Repository
public class OrderInfoRepository {
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private OrderItemDetailMapper orderItemDetailMapper;

    /**
     * Query the user order list
     * @param page
     * @param userOrderInfoQuery
     * @return
     */
    public Page<OrderInfoVO> queryOrderInfoList(Page page, OrderInfoQuery userOrderInfoQuery) {
        // 查询该用户订单
        return orderInfoMapper.queryOrderInfoList(page, userOrderInfoQuery);
    }

    /**
     * Check the order details according to the order number.
     * @param orderNo
     * @return
     */
    public List<OrderItemDetailVO> getOrderItemDetail(String orderNo) {
        Set<String> orders = new HashSet<>();
        orders.add(orderNo);
        return orderItemDetailMapper.getOrderItemDetailList(orders);
    }

    /**
     * Insert order information
     * @param orderInfo
     */
    @Transactional(rollbackFor = Exception.class)
    public String generateOrderInfo(OrderInfo orderInfo, List<OrderItemDetail> orderItemDetailList){
        //生成订单号
        String orderCode = GenerateOrderNoUtils.getOrderNo(orderInfo.getUserId());
        orderInfo.setOrderNo(orderCode);
        orderInfoMapper.insertSelective(orderInfo);
        for (OrderItemDetail orderItemDetail : orderItemDetailList) {
            orderItemDetail.setOrderNo(orderCode);
            orderItemDetailMapper.insertSelective(orderItemDetail);
        }
        return orderInfoMapper.getOrderNoById(orderInfo.getId());
    }

    /**
     * Cancel the order
     * @param orderNo
     */
    public void updateOrderStatus(String orderNo, Integer status) {
        orderInfoMapper.updateOrderStatus(orderNo, status);
    }

    /**
     * Check the order list according to the merchant
     * @param page
     * @param orderInfoQuery
     * @return
     */
    public Page<OrderInfoVO> queryOrderInfoByMerchant(Page page, OrderInfoQuery orderInfoQuery){
        return orderInfoMapper.queryOrderInfoList(page, orderInfoQuery);
    }

    /**
     * Inquire about unpaid orders
     * @return
     */
    public List<OrderInfo> queryNoPayOrderList(){
        return orderInfoMapper.queryNoPayOrderList();
    }

    public OrderInfoVO getOrderInfoByNo(String orderNo){
        return orderInfoMapper.getOrderInfoByNo(orderNo);
    }
}
