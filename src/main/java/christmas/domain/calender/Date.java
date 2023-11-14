package christmas.domain.calender;

import christmas.domain.week.Week;
import christmas.validation.DomainValidation;

public class Date {
    private final int month;
    private final int day;
    private Week week;

    public Date(final int month, final int day) {
        validate(month, day);
        this.month = month;
        this.day = day;
    }

    public Date(final int month, final int day, final Week week) {
        validate(month, day);
        this.month = month;
        this.day = day;
        this.week = week;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public Week getWeek() {
        return week;
    }

    private static void validate(final int month, final int day) {
        DomainValidation.validateDateRange(month, day);
    }
}
