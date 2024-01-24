package christmas.validation;

import christmas.view.ErrorMessage;

import java.util.Map;

public class ViewValidation {
    private ViewValidation() {
    }

    public static void validateEmptyOrSpaceOrNull(final String content) {
        validateNull(content);
        validateEmptyOrSpace(content);
    }

    private static void validateEmptyOrSpace(final String content) {
        if (content.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.SPACE_OR_EMPTY_MESSAGE.getMessage());
        }
    }

    private static void validateNull(final String content) {
        if (content == null) {
            throw new NullPointerException(ErrorMessage.NULL_MESSAGE.getMessage());
        }
    }

    public static void validateDuplicated(final Map<String, Integer> order,String menu) {
        if (order.containsKey(menu)) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR_MESSAGE.getMessage());
        }
    }

    public static void validateNegativeOrZero(final int num) {
        if (num < 1) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR_MESSAGE.getMessage());
        }
    }

    public static void validateOrderFormat(final String orderPart) {
        if (orderPart.chars().filter(ch -> ch == '-').count() != 1 || orderPart.charAt(0) == '-' || orderPart.charAt(orderPart.length()-1) == '-') {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR_MESSAGE.getMessage());
        }
    }
}
