/*
 * Copyright (c) 2016. 杭州端点网络科技有限公司.  All rights reserved.
 */

package com.hroice.excel.enums;

import com.google.common.base.Objects;

/**
 * 文章类型枚举
 *
 * Author  : panxin
 * Date    : 5:23 PM 3/21/16
 */
public enum ArticleType {

    SHOPPING_GUIDE(1, "购物指南"),
    DELIVERY_INFO(2, "配送信息");

    private final int value;

    private final String desc;

    ArticleType(int number, String desc) {
        this.value = number;
        this.desc = desc;
    }

    public static ArticleType from(int value) {
        for (ArticleType type : ArticleType.values()) {
            if (Objects.equal(type.value, value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("article.type.undefined");
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return desc;
    }
}
