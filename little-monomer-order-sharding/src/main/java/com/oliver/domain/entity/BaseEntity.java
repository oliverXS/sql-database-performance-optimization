package com.oliver.domain.entity;

import com.oliver.common.enums.DeleteFlag;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaorui
 */
@Data
public class BaseEntity implements Serializable {
    /**
     * primary key id
     */
    private Long id;
    /**
     * create time
     */
    private Date createTime;
    /**
     * create user
     */
    private Long createUser;
    /**
     * update time
     */
    private Date updateTime;
    /**
     * update user
     */
    private Long updateUser;
    /**
     * 0 is not deleted,1 is deleted
     */
    private Integer deleteFlag;

    /**
     * Initialization parameters
     */
    public void initEntity() {
        this.createTime = new Date();
        this.updateTime = new Date();
        this.deleteFlag = DeleteFlag.NOT_DELETE.getCode();
    }

    /**
     * Initialization - Specify operator id
     * @param operatorId
     */
    public void initEntity(Long operatorId) {
        this.createTime = new Date();
        this.createUser = operatorId;
        this.updateTime = new Date();
        this.updateUser = operatorId;
        this.deleteFlag = DeleteFlag.NOT_DELETE.getCode();
    }

    /**
     * Modify and initialize
     */
    public void updateEntity(){
        this.updateTime = new Date();
    }

    /**
     * Modify and initialize - Specify the operator
     * @param operatorId
     */
    public void updateEntity(Long operatorId){
        this.updateTime = new Date();
        this.updateUser = operatorId;
    }

    /**
     * Delete initialization
     */
    public void deleteEntity(){
        this.deleteFlag = DeleteFlag.DELETED.getCode();
    }

    /**
     * Delete Initialization - Specify the Operator
     * @param operatorId
     */
    public void deleteEntity(Long operatorId){
        this.deleteFlag = DeleteFlag.DELETED.getCode();
        this.updateUser = operatorId;
    }
}
