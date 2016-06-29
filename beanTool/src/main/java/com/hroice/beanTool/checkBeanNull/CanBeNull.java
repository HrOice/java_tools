package com.hroice.beanTool.checkBeanNull;

import java.lang.annotation.*;

/**
 * DATE: 16/6/28 16:17 <br>
 * MAIL: lbw@terminus.io <br>
 * AUTHOR: macbook
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CanBeNull {
    String[] value() default {""};
}
