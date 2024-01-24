package christmas.domain.event.giveaway;

import christmas.domain.menu.OrderedMenu;
import christmas.domain.event.EventType;
import christmas.domain.menu.Menu;

import java.util.Collections;
import java.util.Map;

public class GiveawayByTotalPurchaseAmountEvent extends GiveawayEvent<Integer> {
    public GiveawayByTotalPurchaseAmountEvent() {
        super(Map.of(120000, Map.of(Menu.CHAMPAGNE, 1)));
    }

    @Override
    public Map<Menu, Integer> apply(final OrderedMenu orderedMenu, final Map<EventType, Integer> benefitDetail) {
        int totalPurchaseAmount = orderedMenu.getTotalPrice();
        for (Map.Entry<Integer, Map<Menu, Integer>> informationEntry : this.getGiveawayInformation().entrySet()) {
            if (informationEntry.getKey() <= totalPurchaseAmount) {
                return getTotalGiveawayAmount(informationEntry.getValue(), benefitDetail);
            }
        }
        return Collections.emptyMap();
    }

    private Map<Menu, Integer> getTotalGiveawayAmount(final Map<Menu, Integer> receivableGiveaway, final Map<EventType, Integer> benefitDetail) {
        int totalGiveawayAmount = 0;
        for (Map.Entry<Menu, Integer> giveawayAndCnt : receivableGiveaway.entrySet()) {
            totalGiveawayAmount += giveawayAndCnt.getKey().getPrice() * giveawayAndCnt.getValue();
        }
        benefitDetail.put(EventType.GIVEAWAY_EVENT, totalGiveawayAmount);
        return receivableGiveaway;
    }
}
