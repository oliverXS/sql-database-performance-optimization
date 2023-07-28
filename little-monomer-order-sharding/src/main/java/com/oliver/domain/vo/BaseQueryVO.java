package com.oliver.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaorui
 */
@Data
public class BaseQueryVO implements Serializable {
    /**
     * Logical deletion of identification
     */
    private Integer deleteFlag;
}
