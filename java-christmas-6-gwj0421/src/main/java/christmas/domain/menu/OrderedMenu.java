package christmas.domain.menu;

import christmas.domain.menu.Menu;
import christmas.validation.DomainValidation;

import java.util.Map;

import static christmas.util.ConvertUtil.convertKeyString2Menu;

public class OrderedMenu {
    private final Map<Menu, Integer> order;

    public OrderedMenu(final Map<String, Integer> order) {
        validate(order);
        this.order = convertKeyString2Menu(order);
    }

    public Map<Menu, Integer> getOrder() {
        return order;
    }

    public final int getTotalPrice() {
        int total = 0;
        for (Map.Entry<Menu, Integer> orderEntry : getOrder().entrySet()) {
            total += orderEntry.getKey().getPrice() * orderEntry.getValue();
        }
        return total;
    }

    private static void validate(final Map<String, Integer> order) {
        DomainValidation.validateMenuExistsOrNot(order);
        DomainValidation.validateOnlyBeverage(order);
        DomainValidation.validateMaxOrderCnt(order);
    }
}
