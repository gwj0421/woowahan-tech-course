package christmas.config;

import christmas.domain.calender.Date;

import java.util.Set;

public class AppConstant {
    public static final int TARGET_MONTH = 12;
    public static final int MIN_ORDER_CNT = 1;
    public static final int MAX_ORDER_CNT = 20;
    public static final int MIN_EVENT_MONEY = 10000;
    public static final Set<Date> STAR_POS = Set.of(new Date(TARGET_MONTH, 3),
            new Date(TARGET_MONTH, 10), new Date(TARGET_MONTH, 17),
            new Date(TARGET_MONTH, 24), new Date(TARGET_MONTH, 25),
            new Date(TARGET_MONTH, 31));

    private AppConstant() {
    }
}
