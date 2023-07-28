package com.oliver.constant;

/**
 * @author xiaorui
 */
public class OrderConstant {
    /**
     * Merchant shop status.
     */
    public interface OrderBaseStatus {
        /**
         * Pending payment
         */
        int OBLIGATION = 10;

        /**
         * Cancelled order
         */
        int CANCEL_ORDER = 70;
    }

    public interface OrderRedisKey {
        String PREFIX_KEY = "order:";
    }
}
