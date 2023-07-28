package com.oliver.mapper;

import com.oliver.domain.entity.OrderItemDetail;
import com.oliver.domain.vo.OrderItemDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author xiaorui
 */
@Mapper
public interface OrderItemDetailMapper {

    /**
     * Get the order details origin
     * @param orderNo
     * @return
     */
    List<OrderItemDetailVO> getOrderItemDetailListOrigin(@Param("orderNo") String orderNo);

    /**
     * Get the order details
     * @param orderNos
     * @return
     */
    List<OrderItemDetailVO> getOrderItemDetailList(@Param("orderNos") Set<String> orderNos);

    /**
     * Insert order details
     * @param record
     * @return
     */
    int insertSelective(OrderItemDetail record);

    /**
     * Modify the order details
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(OrderItemDetail record);

}
