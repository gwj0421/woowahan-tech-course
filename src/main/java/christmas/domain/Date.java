package christmas.domain;

import christmas.validation.DomainValidation;

public class Date {
    private final int day;

    public Date(int month, int day) {
        validate(month, day);
        this.day = day;
    }

    private static void validate(int month, int day) {
        DomainValidation.validateDateRange(month, day);
    }
}
