package christmas.validation;

import christmas.view.ErrorMessage;

import java.util.Map;

public class ViewValidation {
    private ViewValidation() {
    }

    public static void validateEmptyOrSpaceOrNull(String content) {
        validateNull(content);
        validateEmptyOrSpace(content);
    }

    private static void validateEmptyOrSpace(String content) {
        if (content.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.SPACE_OR_EMPTY_MESSAGE.getMessage());
        }
    }

    private static void validateNull(String content) {
        if (content == null) {
            throw new NullPointerException(ErrorMessage.NULL_MESSAGE.getMessage());
        }
    }

    public static void validateDuplicated(Map<String, Integer> order,String menu) {
        if (order.containsKey(menu)) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR_MESSAGE.getMessage());
        }
    }

    public static void validateNegativeOrZero(int num) {
        if (num < 1) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR_MESSAGE.getMessage());
        }
    }

    public static void validateOrderFormat(String orderPart) {
        if (orderPart.chars().filter(ch -> ch == '-').count() != 1 || orderPart.charAt(0) == '-' || orderPart.charAt(orderPart.length()-1) == '-') {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR_MESSAGE.getMessage());
        }
    }
}
