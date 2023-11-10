package christmas.config;

public enum Month2023 {
    JANUARY(1,31),
    FEBRUARY(1,28),
    MARCH(1,31),
    APRIL(1,30),
    MAY(1,31),
    JUNE(1,30),
    JULY(1,31),
    AUGUST(1,31),
    SEPTEMBER(1,30),
    OCTOBER(1,31),
    NOVEMBER(1,30),
    DECEMBER(1,31);

    private final int minDays;
    private final int maxDays;

    Month2023(int minDays, int maxDays) {
        this.minDays = minDays;
        this.maxDays = maxDays;
    }

    public int getMinDays() {
        return minDays;
    }

    public int getMaxDays() {
        return maxDays;
    }

    public static boolean isDayInRange(int order, int day) {
        Month2023 month = Month2023.values()[order - 1];
        return month.getMinDays() <= day && day <= month.getMaxDays();
    }
}
