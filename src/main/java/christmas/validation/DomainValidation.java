package christmas.validation;

import christmas.config.Month2023;
import christmas.domain.menu.MenuGroup;
import christmas.view.ErrorMessage;

import java.util.Map;

public class DomainValidation {
    private DomainValidation() {
    }

    public static void validateDateRange(int month, int day) {
        if (!Month2023.isDayInRange(month,day)) {
            throw new IllegalArgumentException(ErrorMessage.DATE_ERROR_MESSAGE.getMessage());
        }
    }

    public static void validateMenuExistsOrNot(Map<String, Integer> order) {
        for (String orderMenu : order.keySet()) {
            if (MenuGroup.hasMenu(orderMenu) == MenuGroup.EMPTY) {
                throw new IllegalArgumentException(ErrorMessage.MENU_ERROR_MESSAGE.getMessage());
            }
        }
    }
}
