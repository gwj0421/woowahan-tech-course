package christmas.domain.event;

import christmas.domain.OrderedMenu;
import christmas.domain.menu.Menu;

import java.util.Map;

public abstract class GiveawayEvent<T> {
    private final Map<T, Map<Menu, Integer>> giveawayInformation;

    protected GiveawayEvent(Map<T, Map<Menu, Integer>> giveawayInformation) {
        this.giveawayInformation = giveawayInformation;
    }

    public Map<T, Map<Menu, Integer>> getGiveawayInformation() {
        return giveawayInformation;
    }

    public abstract Map<Menu,Integer> apply(final OrderedMenu orderedMenu, final Map<EventType, Integer> benefitDetail);
}
