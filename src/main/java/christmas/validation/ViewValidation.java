package christmas.validation;

import christmas.view.ErrorMessage;

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
}
