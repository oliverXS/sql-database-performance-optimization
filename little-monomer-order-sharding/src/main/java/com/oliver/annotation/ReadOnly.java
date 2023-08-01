package com.oliver.annotation;

import java.lang.annotation.*;

/**
 * @author xiaorui
 * Custom reading db annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Inherited
public @interface ReadOnly {
}
