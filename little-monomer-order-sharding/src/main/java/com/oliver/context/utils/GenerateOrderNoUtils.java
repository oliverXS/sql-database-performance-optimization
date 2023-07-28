package com.oliver.context.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author xiaorui
 * @Description
 * Order encoding generator, generating 32-digit encoding
 * 13-digit timestamp + 4-digit random number + 3-digit user id
 */
public class GenerateOrderNoUtils {
    private static final int[] RANGE_4 = {10, 2, 11, 3, 0, 1, 9, 7, 12, 6, 4, 8, 5};
    private static final int[] RANGE_5 = {4, 3, 13, 15, 7, 8, 6, 2, 1, 10, 5, 12, 0, 11, 14, 9};
    private static final int[] RANGE_6 = {2, 7, 10, 9, 16, 3, 6, 8, 0, 4, 1, 12, 11, 13, 18, 5, 15, 17, 14};
    private static final int[] RANGE_7 = {18, 0, 2, 22, 8, 3, 1, 14, 17, 12, 4, 19, 11, 9, 13, 5, 6, 15, 10, 16, 20, 7, 21};
    private static final int[] RANGE_8 = {11, 8, 4, 0, 16, 14, 22, 7, 3, 5, 13, 18, 24, 25, 23, 10, 1, 12, 6, 21, 17, 2, 15, 9, 19, 20};
    private static final int[] RANGE_9 = {24, 23, 27, 3, 9, 16, 25, 13, 28, 12, 0, 4, 10, 18, 11, 2, 17, 1, 21, 26, 5, 15, 7, 20, 22, 14, 19, 6, 8};
    private static final int[] RANGE_10 = {32, 3, 1, 28, 21, 18, 30, 7, 12, 22, 20, 13, 16, 15, 6, 17, 9, 25, 11, 8, 4, 27, 14, 31, 5, 23, 24, 29, 0, 10, 19, 26, 2};
    private static final int[] RANGE_11 = {9, 13, 2, 29, 11, 32, 14, 33, 24, 8, 27, 4, 22, 20, 5, 0, 21, 25, 17, 28, 34, 6, 23, 26, 30, 3, 7, 19, 16, 15, 12, 31, 1, 35, 10, 18};
    private static final int[] RANGE_12 = {31, 4, 16, 33, 35, 29, 17, 37, 12, 28, 32, 22, 7, 10, 14, 26, 0, 9, 8, 3, 20, 2, 13, 5, 36, 27, 23, 15, 19, 34, 38, 11, 24, 25, 30, 21, 18, 6, 1};

    private static final int[][] RANGE_ARRAY = {RANGE_4, RANGE_5, RANGE_6, RANGE_7, RANGE_8, RANGE_9, RANGE_10, RANGE_11, RANGE_12};

    public static long genNo(long id, int length) {
        long maxValue = (long) Math.pow(10, length) - 1;
        int superScript = (int) (Math.log(maxValue) / Math.log(2));

        long r = 0;

        long sign = (long) Math.pow(2, superScript);

        id |= sign;

        int[] mapbit = RANGE_ARRAY[length - 4];

        for (int x = 0; x < superScript; x++) {
            long v = (id >> x) & 0x1;
            r |= (v << mapbit[x]);
        }

        r += maxValue - Math.pow(2, superScript) + 1;

        return r;
    }

    private static String getOrderIdKey(String userId) {
        return "10" + getDateTimeKey() + getAutoNoKey() + toCode(userId);
    }

    /**
     * @return
     */
    private static String getAutoNoKey() {
        long random = getRandom(6);
        return String.valueOf(genNo(random, 5));
    }

    /**
     * Encrypt and append a random number to form a fixed-length code based on the given user ID.
     */
    private static String toCode(String userId) {
        String substring = userId.substring(userId.length() - 3);
        long random = getRandom(3);
        return random + substring;
    }

    /**
     * Generate a timestamp in the format "yyMMddHHmmss".
     */
    private static String getDateTime() {
        DateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        return sdf.format(new Date());
    }

    /**
     * Generate a timestamp in the format "yyMMdd".
     */
    private static String getDateTimeKey() {
        return DateFormatUtils.format(new Date(), "yyMMdd");
    }

    /**
     * Generate a fixed-length random code.
     *
     * @param n Length
     */
    private static long getRandom(long n) {
        long min = 1, max = 9;
        for (int i = 1; i < n; i++) {
            min *= 10;
            max *= 10;
        }
        long rangeLong = (((long) (new Random().nextDouble() * (max - min)))) + min;
        return rangeLong;
    }

    /**
     * Generate a code without a category header based on the given user ID.
     *
     * @param userId
     */
    private static synchronized String getCode(String userId) {
        return getDateTime() + toCode(userId);
    }

    /**
     * Generate an order number code (calling method) based on the unique user ID from the website to prevent duplication.
     *
     * @param userId The unique ID of the user on the website to prevent duplication.
     */
    public static String getOrderNo(Long userId) {
        return getOrderIdKey(String.valueOf(userId));
    }
}
