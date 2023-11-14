package christmas.domain.week;

import christmas.domain.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WeekDiscountRangeTest {

    @DisplayName("주어진 날짜가 해당 이벤트의 요일 기간에 적합할 경우 True 리턴")
    @Test
    void Should_ReturnTrue_When_DateIsInWeekRange() {
        // given
        Date date = new Date(12, 1, Week.FRIDAY);

        // when
        boolean result = WeekDiscountRange.WEEK_END.canEventWeekRangeApply(date);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("주어진 날짜가 해당 이벤트의 요일 기간을 벗어난 경우 False 리턴")
    @Test
    void Should_ReturnTrue_When_DateIsNotInWeekRange() {
        // given
        Date date = new Date(12, 1, Week.MONDAY);

        // when
        boolean result = WeekDiscountRange.WEEK_END.canEventWeekRangeApply(date);

        // then
        assertThat(result).isFalse();
    }
}