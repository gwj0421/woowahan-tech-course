package christmas.domain.week;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class WeekTest {
    @DisplayName("날짜와 해당 달의 첫째 날의 요일을 통해 해당 날짜의 요일 리턴")
    @CsvSource(value = {"2,SATURDAY,FRIDAY", "5,TUESDAY,FRIDAY", "15,FRIDAY,FRIDAY"})
    @ParameterizedTest
    void Should_ReturnWeek_When_GivenDateAndFirstDayWeekOfDay(int day, Week realWeekOfDay, Week FirstDayOfMonth) {
        // when
        Week expectedDay = Week.getWeekByDay(FirstDayOfMonth, day);

        // then
        assertThat(expectedDay).isEqualTo(realWeekOfDay);
    }
}