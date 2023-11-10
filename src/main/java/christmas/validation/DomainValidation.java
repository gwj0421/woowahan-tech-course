package christmas.validation;

import christmas.config.Month2023;
import christmas.view.ErrorMessage;

public class DomainValidation {
    private DomainValidation() {
    }

    public static void validateDateRange(int month, int day) {
        if (!Month2023.isDayInRange(month,day)) {
            throw new IllegalArgumentException(ErrorMessage.DATE_ERROR_MESSAGE.getMessage());
        }
    }
}
