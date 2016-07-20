package com.hroice.excel.enums;

/**
 * 用户类型枚举
 *
 * BUYER / COMPANY_SELLER 归类为角色, 不属于用户类型.
 * 但角色会作为一个临时的UserType而存在, 在JiddLoginUser中会将NORMAL用户细化为当时的UserRole
 *
 * @author Effet
 */
public enum UserType {

    /**
     * 普通用户
     */
    NORMAL(2),
    /**
     * 超级管理员
     */
    ADMIN(1),
    /**
     * 后台运营
     */
    OPERATOR(3),
    /**
     * 站点拥有者
     */
    SITE_OWNER(4);

    private final int value;

    UserType(int value) {
        this.value = value;
    }

    public final int value() {
        return value;
    }
}
