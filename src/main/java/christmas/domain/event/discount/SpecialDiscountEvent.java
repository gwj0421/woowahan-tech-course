package christmas.domain.event.discount;

import christmas.domain.calender.Date;
import christmas.domain.calender.EventCalender;
import christmas.domain.event.EventType;

import java.util.Map;

public class SpecialDiscountEvent implements DiscountEvent {
    private static final int DISCOUNT_PRICE = 1000;
    private final EventCalender calendar;

    public SpecialDiscountEvent(EventCalender calendar) {
        this.calendar = calendar;
    }

    public EventCalender getCalendar() {
        return calendar;
    }

    @Override
    public void apply(final Date visitDay, final Map<EventType, Integer> benefitDetail) {
        if (canApply(visitDay, EventType.SPECIAL_DISCOUNT_EVENT) && getCalendar().checkStarDay(visitDay)) {
            benefitDetail.put(EventType.SPECIAL_DISCOUNT_EVENT, DISCOUNT_PRICE);
        }
    }
}
