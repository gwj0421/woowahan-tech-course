package christmas.domain.week;

public enum Week {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(0);

    private final int weekOrder;

    Week(final int weekOrder) {
        this.weekOrder = weekOrder;
    }

    public int getWeekOrder() {
        return weekOrder;
    }

    public static Week getWeekByDay(final Week monthStartDay, final int day) {
        return values()[(day + monthStartDay.getWeekOrder() - 2) % 7];
    }
}
