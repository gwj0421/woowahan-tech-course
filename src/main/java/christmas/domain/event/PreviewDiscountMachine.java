package christmas.domain.event;

import christmas.domain.calender.Date;
import christmas.domain.calender.EventCalender;
import christmas.domain.menu.OrderedMenu;
import christmas.domain.event.discount.*;
import christmas.domain.event.giveaway.GiveawayByTotalPurchaseAmountEvent;
import christmas.domain.event.giveaway.GiveawayEvent;
import christmas.domain.menu.Menu;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static christmas.config.AppConstant.MIN_EVENT_MONEY;

public class PreviewDiscountMachine {
    private final EventCalender eventCalender;
    private final OrderedMenu orderedMenu;
    private final Date visitDay;
    private final PreviewResult previewResult;

    public PreviewDiscountMachine(final EventCalender eventCalender, final OrderedMenu orderedMenu, final int day) {
        this.eventCalender = eventCalender;
        this.orderedMenu = orderedMenu;
        this.visitDay = eventCalender.getCalender().get(day - 1);
        this.previewResult = makePreviewResult();
    }

    public EventCalender getEventCalender() {
        return eventCalender;
    }

    public OrderedMenu getOrderedMenu() {
        return orderedMenu;
    }

    public Date getVisitDay() {
        return visitDay;
    }

    public PreviewResult getPreviewResult() {
        return previewResult;
    }

    private PreviewResult makePreviewResult() {
        Map<EventType, Integer> emptyBenefitResult = new LinkedHashMap<>();
        int actualPayAmount = getOrderedMenu().getTotalPrice();

        if (getOrderedMenu().getTotalPrice() >= MIN_EVENT_MONEY) {
            applyDiscountEvent(emptyBenefitResult);
            for (Map.Entry<EventType, Integer> benefitEntry : emptyBenefitResult.entrySet()) {
                actualPayAmount -= benefitEntry.getValue();
            }
            Map<Menu, Integer> giveaway = applyGiveawayEvent(emptyBenefitResult);

            return new PreviewResult(emptyBenefitResult,giveaway,actualPayAmount);
        }
        return new PreviewResult(Collections.emptyMap(),Collections.emptyMap(),actualPayAmount);
    }

    private void applyDiscountEvent(final Map<EventType, Integer> benefitDetail) {
        DiscountEvent christmasDDayDiscountEvent = new ChristmasDDayDiscountEvent();
        DiscountEvent specialDiscountEvent = new SpecialDiscountEvent(getEventCalender());
        DiscountEvent weekendDiscountEvent = new WeekendDiscountEvent(getOrderedMenu());
        DiscountEvent weekdayDiscountEvent = new WeekdayDiscountEvent(getOrderedMenu());

        christmasDDayDiscountEvent.apply(getVisitDay(), benefitDetail);
        specialDiscountEvent.apply(getVisitDay(), benefitDetail);
        weekendDiscountEvent.apply(getVisitDay(), benefitDetail);
        weekdayDiscountEvent.apply(getVisitDay(), benefitDetail);
    }

    private Map<Menu, Integer> applyGiveawayEvent(final Map<EventType, Integer> benefitDetail) {
        GiveawayEvent<Integer> giveawayByTotalPurchaseAmountEvent = new GiveawayByTotalPurchaseAmountEvent();
        return giveawayByTotalPurchaseAmountEvent.apply(getOrderedMenu(), benefitDetail);
    }
}
