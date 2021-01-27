package com.test.common.constants;

/**
 * 为枚举类提供一个公共接口
 *
 * @author youmoo
 * @since 15/9/29 16:29
 */
public interface KeyNamed<T> {

    /**
     * 枚举值
     */
    T getKey();

    /**
     * 枚举描述
     */
    String getName();
}
