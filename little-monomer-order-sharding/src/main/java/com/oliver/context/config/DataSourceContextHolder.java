package com.oliver.context.config;


import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaorui
 * Data soure contextd
 */
@Slf4j
public class DataSourceContextHolder {
    public static final String MASTER = "MASTER";
    public static final String SLAVE = "SLAVE";

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setDataSourceType(String dataSourceType) {
        if (dataSourceType == null) {
            log.error("Datasource is null");
            throw new NullPointerException();
        }
        log.info("Set datasource as：{}", dataSourceType);
        CONTEXT_HOLDER.set(dataSourceType);
    }

    /**
     * Default writing mode
     *
     * @return
     */
    public static String getDataSourceType() {
        return CONTEXT_HOLDER.get() == null ? MASTER : CONTEXT_HOLDER.get();
    }

    public static void removeDataSourceType() {
        CONTEXT_HOLDER.remove();
    }
}
