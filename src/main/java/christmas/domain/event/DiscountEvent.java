package christmas.domain.event;

import christmas.domain.Date;

import java.util.Map;

public interface DiscountEvent {
    void apply(final Date visitDay, final Map<EventType, Integer> benefitDetail);

    default boolean canApply(final Date visitDay, final EventType eventType) {
        return eventType.isEventApply(visitDay);
    }
}
