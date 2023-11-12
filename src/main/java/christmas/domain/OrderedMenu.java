package christmas.domain;

import christmas.domain.menu.Menu;
import christmas.validation.DomainValidation;

import java.util.Map;

public class OrderedMenu {
    private final Map<String, Integer> order;

    public OrderedMenu(Map<String, Integer> order) {
        validate(order);
        this.order = order;
    }

    private static void validate(Map<String, Integer> order) {
        DomainValidation.validateMenuExistsOrNot(order);
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (String menu : order.keySet()) {
            totalPrice += Menu.getPriceByMenuName(menu) * order.get(menu);
        }

        return totalPrice;
    }
}
