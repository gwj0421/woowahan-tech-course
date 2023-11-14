package christmas.domain.week;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WeekTest {
    @DisplayName("날짜와 해당 달의 첫째 날의 요일을 통해 해당 날짜의 요일 리턴")
    @Test
    void Should_ReturnWeek_When_GivenDateAndFirstDayWeekOfDay() {
        // given
        int day2 = 2;
        Week day1WeekOfDay = Week.SATURDAY;
        int day5 = 5;
        Week day5WeekOfDay = Week.TUESDAY;
        int day15 = 15;
        Week day15WeekOfDay = Week.FRIDAY;

        // when
        Week expectedDay2 = Week.getWeekByDay(Week.FRIDAY, day2);
        Week expectedDay5 = Week.getWeekByDay(Week.FRIDAY, day5);
        Week expectedDay15 = Week.getWeekByDay(Week.FRIDAY, day15);

        // then
        assertThat(expectedDay2).isEqualTo(day1WeekOfDay);
        assertThat(expectedDay5).isEqualTo(day5WeekOfDay);
        assertThat(expectedDay15).isEqualTo(day15WeekOfDay);
    }
}