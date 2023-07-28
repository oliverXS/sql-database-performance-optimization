package com.oliver.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaorui
 */
@Data
public class BaseVO implements Serializable {
    /**
     * primary key id
     */
    private Long id;
    /**
     * Creation time
     */
    private Date createTime;
    /**
     * Create a user
     */
    private Long createUserId;
    /**
     * Update time
     */
    private Date updateTime;
    /**
     * Update users
     */
    private Long updateUserId;
    /**
     * isDeleted
     */
    private Integer deleteFlag;
}
