package com.oliver.bean.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oliver.constant.ResponseCode;
import lombok.Data;

import java.util.List;

/**
 * @author xiaorui
 */
@Data
public class PageResponse extends BaseResponse{
    /**
     * Response value
     */
    private String code;

    /**
     * Query data
     */
    private List<?> data;

    /**
     * Total number of rows
     */
    private Long count;

    /**
     * Response message
     */
    private String message;

    /**
     * Returns a successful operation page response object.
     *
     * @param page The data page object.
     * @return A PageResponse representing a successful operation with pagination.
     * @auther sunfeng
     */
    public static PageResponse success(Page page) {
        PageResponse pageResponse = new PageResponse();
        pageResponse.setCode(ResponseCode.SUCCESS);
        pageResponse.setCount(page.getTotal());
        pageResponse.setData(page.getRecords());
        return pageResponse;
    }

    /**
     * Returns an error data query response object.
     *
     * @param message The message to be included in the response.
     * @return A PageResponse representing an error data query operation.
     * @auther sunfeng
     */
    public static PageResponse error(String message) {
        PageResponse dataResponse = new PageResponse();
        dataResponse.setCode(ResponseCode.ERROR);
        dataResponse.setMessage(message);
        return dataResponse;
    }

}
