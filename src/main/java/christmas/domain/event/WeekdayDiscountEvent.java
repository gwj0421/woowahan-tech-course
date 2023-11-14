package christmas.domain.event;

import christmas.domain.Date;
import christmas.domain.OrderedMenu;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuGroup;
import christmas.domain.week.WeekDiscountRange;

import java.util.Map;

public class WeekdayDiscountEvent implements DiscountEvent{
    private static final int DISCOUNT_PRICE = 2023;
    private static final MenuGroup TARGET_GROUP = MenuGroup.DESERT;
    private static final WeekDiscountRange WEEK_RANGE = WeekDiscountRange.WEEK_DAY;

    private final OrderedMenu orderedMenu;

    public WeekdayDiscountEvent(OrderedMenu orderedMenu) {
        this.orderedMenu = orderedMenu;
    }

    @Override
    public void apply(final Date visitDay, final Map<EventType, Integer> benefitDetail) {
        if (canApply(visitDay, EventType.WEEKDAY_DISCOUNT_EVENT) && WEEK_RANGE.canEventWeekRangeApply(visitDay)) {
            searchMenu(orderedMenu.getOrder(), benefitDetail);
        }
    }

    private void searchMenu(final Map<Menu,Integer> order, final Map<EventType,Integer> benefitDetail) {
        int total = 0;
        for (Map.Entry<Menu, Integer> menuEntry : order.entrySet()) {
            if (MenuGroup.getMenuGroupByMenu(menuEntry.getKey().getName()).equals(TARGET_GROUP)) {
                total += DISCOUNT_PRICE * menuEntry.getValue();
            }
        }
        if (total > 0) {
            benefitDetail.put(EventType.WEEKDAY_DISCOUNT_EVENT, total);
        }
    }
}
