package christmas.domain.event.discount;

import christmas.domain.calender.Date;
import christmas.domain.menu.OrderedMenu;
import christmas.domain.event.EventType;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuGroup;
import christmas.domain.week.WeekDiscountRange;

import java.util.Map;

public class WeekendDiscountEvent implements DiscountEvent{
    private static final int DISCOUNT_PRICE = 2023;
    private static final MenuGroup TARGET_GROUP = MenuGroup.MAIN;
    private static final WeekDiscountRange WEEK_RANGE = WeekDiscountRange.WEEK_END;
    private final OrderedMenu orderedMenu;

    public WeekendDiscountEvent(final OrderedMenu orderedMenu) {
        this.orderedMenu = orderedMenu;
    }

    @Override
    public void apply(final Date visitDay, final Map<EventType, Integer> benefitDetail) {
        if (canApply(visitDay,EventType.WEEKEND_DISCOUNT_EVENT) && WEEK_RANGE.canEventWeekRangeApply(visitDay)) {
            searchMenu(orderedMenu.getOrder(),benefitDetail);
        }
    }

    private void searchMenu(final Map<Menu,Integer> order, final Map<EventType,Integer> benefitDetail) {
        int total = 0;
        for (Map.Entry<Menu, Integer> orderEntry : order.entrySet()) {
            if (MenuGroup.getMenuGroupByMenu(orderEntry.getKey().getName()).equals(TARGET_GROUP)) {
                total += DISCOUNT_PRICE * orderEntry.getValue();
            }
        }
        if (total > 0) {
            benefitDetail.put(EventType.WEEKEND_DISCOUNT_EVENT, total);
        }
    }
}