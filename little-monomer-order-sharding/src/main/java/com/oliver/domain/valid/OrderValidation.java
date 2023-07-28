package com.oliver.domain.valid;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.oliver.domain.query.OrderInfoQuery;
import com.oliver.exception.BaseException;

/**
 * @author xiaorui
 */
public class OrderValidation {
    /**
     * Check and validate the order query parameters.
     * @param orderInfoQuery The query object to check.
     */
    public static void checkVerifyOrderQuery(OrderInfoQuery orderInfoQuery) {
        if (ObjectUtils.isEmpty(orderInfoQuery)) {
            throw new BaseException("Input parameter object cannot be empty.");
        }

        if (ObjectUtils.isEmpty(orderInfoQuery.getSize())) {
            throw new BaseException("The 'size' parameter cannot be empty.");
        }

        if (ObjectUtils.isEmpty(orderInfoQuery.getCurrent())) {
            throw new BaseException("The 'current' parameter cannot be empty.");
        }

        if (ObjectUtils.isEmpty(orderInfoQuery.getUserId())) {
            throw new BaseException("The 'userId' parameter cannot be empty.");
        }
    }

}
