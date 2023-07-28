package com.oliver.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author xiaorui
 */
@Data
@TableName("order_item_detail")
public class OrderOperateLog {
    /**
     *Primary Key
     */
    private Long id;

    /**
     *Order ID
     */
    private String orderNo;

    /**
     *Operation type, 1: Create order, 2: Manually cancel order, 3: Automatically cancel order, 4: Pay for order, 5: Manually confirm receipt, 6: Automatically confirm receipt, 7: Product shipment, 8: Apply for return, 9: Return review failed, 10: Return review passed, 11: Send return products, 12: Confirm receipt of return, 13: Return has been warehoused, 14: Refund completed',
     */
    private Byte operateType;

    /**
     *Operation content
     */
    private String operateContent;

    /**
     *Creation time
     */
    private Date gmtCreate;

    /**
     *Update time
     */
    private Date gmtModified;

}
