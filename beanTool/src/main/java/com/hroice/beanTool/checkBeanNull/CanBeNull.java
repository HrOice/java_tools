package com.hroice.beanTool.checkBeanNull;

import java.lang.annotation.*;

/**
 *
 * DATE: 16/6/28 16:17 <br>
 * MAIL: lbw@terminus.io <br>
 * AUTHOR: macbook
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CanBeNull {
    /**
     * on Class ,annotate the object's field. These field will be ignore when use CheckField.
     * @return
     */
    String[] value() default {""};
}
