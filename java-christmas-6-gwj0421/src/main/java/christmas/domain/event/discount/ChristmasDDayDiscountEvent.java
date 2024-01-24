package christmas.domain.event.discount;

import christmas.domain.calender.Date;
import christmas.domain.event.EventType;

import java.util.Map;

public class ChristmasDDayDiscountEvent implements DiscountEvent {
    private static final int DISCOUNT_PRICE = 1000;
    private static final int DISCOUNT_PER_DAY = 100;

    @Override
    public void apply(final Date visitDay, final Map<EventType, Integer> benefitDetail) {
        if (canApply(visitDay, EventType.CHRISTMAS_D_DAY_DISCOUNT_EVENT)) {
            benefitDetail.put(EventType.CHRISTMAS_D_DAY_DISCOUNT_EVENT,
                    DISCOUNT_PRICE + DISCOUNT_PER_DAY * (visitDay.getDay() - 1));
        }
    }
}
