package christmas.config;

import christmas.domain.week.Week;

public enum Month2023Detail {
    JANUARY(1,31,Week.SUNDAY),
    FEBRUARY(1,28,Week.WEDNESDAY),
    MARCH(1,31,Week.WEDNESDAY),
    APRIL(1,30,Week.SATURDAY),
    MAY(1,31,Week.MONDAY),
    JUNE(1,30,Week.THURSDAY),
    JULY(1,31,Week.SATURDAY),
    AUGUST(1,31,Week.TUESDAY),
    SEPTEMBER(1,30,Week.FRIDAY),
    OCTOBER(1,31,Week.SUNDAY),
    NOVEMBER(1,30,Week.WEDNESDAY),
    DECEMBER(1,31,Week.FRIDAY);

    private final int minDays;
    private final int maxDays;
    private final Week monthStartDay;

    Month2023Detail(final int minDays,final  int maxDays,final  Week monthStartDay) {
        this.minDays = minDays;
        this.maxDays = maxDays;
        this.monthStartDay = monthStartDay;
    }

    public int getMinDays() {
        return minDays;
    }

    public int getMaxDays() {
        return maxDays;
    }

    public Week getMonthStartDayOfWeek() {
        return monthStartDay;
    }

    public static boolean isDayInRange(int monthOrder, int day) {
        Month2023Detail month = Month2023Detail.values()[monthOrder - 1];
        return month.getMinDays() <= day && day <= month.getMaxDays();
    }
}
