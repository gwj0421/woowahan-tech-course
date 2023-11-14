package christmas.validation;

import christmas.config.Month2023Detail;
import christmas.domain.menu.MenuGroup;
import christmas.view.ErrorMessage;

import java.util.Map;

import static christmas.config.AppConstant.MAX_ORDER_CNT;
import static christmas.config.AppConstant.MIN_ORDER_CNT;

public class DomainValidation {
    private DomainValidation() {
    }

    public static void validateDateRange(final int month, final int day) {
        if (!Month2023Detail.isDayInRange(month, day)) {
            throw new IllegalArgumentException(ErrorMessage.DATE_ERROR_MESSAGE.getMessage());
        }
    }

    public static void validateMenuExistsOrNot(final Map<String, Integer> order) {
        for (String orderMenu : order.keySet()) {
            if (MenuGroup.getMenuGroupByMenu(orderMenu) == MenuGroup.EMPTY) {
                throw new IllegalArgumentException(ErrorMessage.MENU_ERROR_MESSAGE.getMessage());
            }
        }
    }

    public static void validateOnlyBeverage(final Map<String, Integer> order) {
        for (Map.Entry<String, Integer> orderEntry : order.entrySet()) {
            if (!MenuGroup.getMenuGroupByMenu(orderEntry.getKey()).equals(MenuGroup.BEVERAGE)) {
                return;
            }
        }

        throw new IllegalArgumentException(ErrorMessage.MENU_ERROR_MESSAGE.getMessage());
    }

    public static void validateMaxOrderCnt(final Map<String, Integer> order) {
        int totalCnt = 0;
        for (Integer cnt : order.values()) {
            totalCnt += cnt;
        }

        if (totalCnt < MIN_ORDER_CNT || MAX_ORDER_CNT < totalCnt) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR_MESSAGE.getMessage());
        }

    }

    public static void validateMonthRange(final int monthOrder) {
        if (monthOrder < 1 || monthOrder > 12) {
            throw new IllegalArgumentException(ErrorMessage.DEVELOP_ERROR_MESSAGE.getMessage());
        }
    }

    public static void validateMoneyIsNegative(final int actualPayAmount) {
        if (actualPayAmount < 0) {
            throw new IllegalArgumentException(ErrorMessage.DEVELOP_ERROR_MESSAGE.getMessage());
        }
    }


}
