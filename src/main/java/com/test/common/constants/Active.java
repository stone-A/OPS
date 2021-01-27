package com.test.common.constants;

/**
 * 激活状态
 *
 * @author youmoo
 * @since 15/9/25 11:16
 */
public enum Active implements KeyNamed<Integer> {
    ON(1, "有效"),
    OFF(0, "无效");

    public final int key;
    public final String name;

    Active(int key, String name) {
        this.key = key;
        this.name = name;
    }


    @Override
    public Integer getKey() {
        return this.key;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
