package christmas.domain.event;

import christmas.domain.Date;

public enum EventType {
    CHRISTMAS_D_DAY_DISCOUNT_EVENT("크리스마스 디데이 할인", new Date(12, 1), new Date(12, 25)),
    WEEKDAY_DISCOUNT_EVENT("평일 할인", new Date(12, 1), new Date(12, 31)),
    WEEKEND_DISCOUNT_EVENT("주말 할인", new Date(12, 1), new Date(12, 31)),
    SPECIAL_DISCOUNT_EVENT("특별 할인", new Date(12, 1), new Date(12, 31)),
    GIVEAWAY_EVENT("증정 이벤트", new Date(12, 1), new Date(12, 31));

    private final String desc;
    private final Date startDate;
    private final Date endDate;

    EventType(final String desc, final Date startDate, final Date endDate) {
        this.desc = desc;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getDesc() {
        return desc;
    }

    public final boolean isEventApply(final Date date) {
        return startDate.getMonth() <= date.getMonth() && date.getMonth() <= endDate.getMonth() &&
                startDate.getDay() <= date.getDay() && date.getDay() <= endDate.getDay();
    }
}
