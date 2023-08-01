package com.oliver.aspect;

import com.oliver.annotation.ReadOnly;
import com.oliver.context.config.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author xiaorui
 * Data source section
 */
@Slf4j
@Aspect
@Component
public class DynamicDataSourceAspect implements Ordered {

    /**
     * Before the service layer method obtains the datasource object, specify the current thread data source slave in the section.
     */
    @Before(value = "execution(* *(..)) && @annotation(readOnly)")
    public void before(JoinPoint point, ReadOnly readOnly) {
        log.info(point.getSignature().getName() + "Get from the Slave DB");
        DataSourceContextHolder.setDataSourceType(DataSourceContextHolder.SLAVE);
    }

    @After(value = "execution(* *(..)) && @annotation(readOnly)")
    public void restoreDataSource(JoinPoint point, ReadOnly readOnly) {
        log.info(point.getSignature().getName() + "Clear the data source");
        // Clear the data source after the method is executed
        DataSourceContextHolder.removeDataSourceType();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
