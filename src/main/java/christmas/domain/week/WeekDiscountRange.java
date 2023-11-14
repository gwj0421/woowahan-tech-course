package christmas.domain.week;

import christmas.domain.Date;

import java.util.Arrays;

public enum WeekDiscountRange {
    WEEK_DAY(new Week[]{Week.SUNDAY,Week.MONDAY,Week.TUESDAY,Week.WEDNESDAY,Week.THURSDAY}),
    WEEK_END(new Week[]{Week.FRIDAY,Week.SATURDAY});

    private final Week[] discountRange;

    WeekDiscountRange(final Week[] discountRange) {
        this.discountRange = discountRange;
    }

    public final boolean canEventWeekRangeApply(final Date visitDay) {
        return Arrays.stream(this.discountRange)
                .anyMatch(week -> week.equals(visitDay.getWeek()));
    }
}
