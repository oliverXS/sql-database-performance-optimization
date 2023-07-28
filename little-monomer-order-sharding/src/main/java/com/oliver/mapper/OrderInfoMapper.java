package com.oliver.mapper;

import com.oliver.domain.entity.OrderInfo;
import com.oliver.domain.query.OrderInfoQuery;
import com.oliver.domain.vo.OrderInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * OrderInfo Mapper
 * @author xiaorui
 */
@Mapper
public interface OrderInfoMapper {
    /**
     * Insert Order
     * @param orderInfo
     * @return
     */
    int insertSelective(OrderInfo orderInfo);

    /**
     * Modify the order status order
     * @param orderNo
     * @param status
     */
    void updateOrderStatus(@Param("orderNo") String orderNo, @Param("status") Integer status);

    /**
     * Check the order list according to the conditions
     * @param page
     * @param orderInfoQuery
     * @return
     */
    Page<OrderInfoVO> queryOrderInfoList(Page page, @Param("record") OrderInfoQuery orderInfoQuery);

    /**
     * Search unpaid order
     * @return
     */
    List<OrderInfo> queryNoPayOrderList();

    /**
     * Check the order information according to the order number
     * @param orderNo
     * @return
     */
    OrderInfoVO getOrderInfoByNo(String orderNo);

    /**
     * Get the order number according to the id
     * @param orderId
     * @return
     */
    String getOrderNoById(Long orderId);
}
