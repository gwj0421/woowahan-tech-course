package christmas.domain;

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
}
