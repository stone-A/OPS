package com.test.common;

/**
 * 花呗分期
 *
 * @author youmoo
 * @since 16/2/25 09:42
 */
public enum HuaBeiInstallments {
    PERIOD_3_LEVEL_1(3, "0", "0"),
    PERIOD_3_LEVEL_2(3, "50", "1.15"),
    PERIOD_3_LEVEL_3(3, "100", "1.8"),

    PERIOD_6_LEVEL_1(6, "0", "0"),
    PERIOD_6_LEVEL_2(6, "50", "2.25"),
    PERIOD_6_LEVEL_3(6, "100", "4.5"),

    PERIOD_9_LEVEL_1(9, "0", "0"),
    PERIOD_9_LEVEL_2(9, "50", "3"),
    PERIOD_9_LEVEL_3(9, "100", "6"),

    PERIOD_12_LEVEL_1(12, "0", "0"),
    PERIOD_12_LEVEL_2(12, "50", "4"),
    PERIOD_12_LEVEL_3(12, "100", "8"),;

    HuaBeiInstallments(int period, String serviceChargeRatio, String rate) {
        this.period = period;
        this.serviceChargeRatio = serviceChargeRatio;
        this.rate = rate;
    }

    public HuaBeiInstallments getHuaBeiInstallments(int period, String serviceChargeRatio, String rate) {
        for (HuaBeiInstallments c : HuaBeiInstallments.values()){
            if(c.period==period&&c.serviceChargeRatio==serviceChargeRatio&&rate.equals(rate)) {
                return c;
            }
        }
        return null;
    }

    /**
     * 分期付款期数
     */
    final int period;
    /**
     * 费率(%)
     */
    final String rate;
    /**
     * 服务费比例(%)
     */
    final String serviceChargeRatio;
}
