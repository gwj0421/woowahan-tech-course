package christmas.domain;

import christmas.config.Month2023Detail;
import christmas.domain.week.Week;
import christmas.validation.DomainValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EventCalender {
    private final List<Date> calender;
    private final Set<Date> starPos;

    public EventCalender(final int monthOrder, final Set<Date> starPos) {
        validate(monthOrder);
        this.calender = makeEmptyCalender(monthOrder);
        this.starPos = starPos;
    }

    public List<Date> getCalender() {
        return calender;
    }

    private static void validate(final int monthOrder) {
        DomainValidation.validateMonthRange(monthOrder);
    }

    private List<Date> makeEmptyCalender(final int monthOrder) {
        List<Date> emptyCalender = new ArrayList<>();
        Month2023Detail month = Month2023Detail.values()[monthOrder - 1];
        for (int i = month.getMinDays(); i < month.getMaxDays(); i++) {
            emptyCalender.add(new Date(monthOrder, i, Week.getWeekByDay(month.getMonthStartDayOfWeek(), i)));
        }
        return emptyCalender;
    }

    public final boolean checkStarDay(final Date visitDay) {
        for (Date star : this.starPos) {
            if (star.getMonth() == visitDay.getMonth() && star.getDay() == visitDay.getDay()) {
                return true;
            }
        }
        return false;
    }
}
