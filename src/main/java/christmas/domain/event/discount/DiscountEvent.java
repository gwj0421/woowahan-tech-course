package christmas.domain.event.discount;

import christmas.domain.calender.Date;
import christmas.domain.event.EventType;

import java.util.Map;

public interface DiscountEvent {
    void apply(final Date visitDay, final Map<EventType, Integer> benefitDetail);

    default boolean canApply(final Date visitDay, final EventType eventType) {
        return eventType.isEventApply(visitDay);
    }
}
