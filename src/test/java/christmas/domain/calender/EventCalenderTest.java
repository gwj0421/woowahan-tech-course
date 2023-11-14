package christmas.domain.calender;

import christmas.domain.calender.Date;
import christmas.domain.calender.EventCalender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EventCalenderTest {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR]";
    @DisplayName("1~12월이 아닌 달을 입력할 경우 예외 발생")
    @ValueSource(ints = {0, 13, 14})
    @ParameterizedTest
    void Should_ThrowException_When_GivenInvalidMonth(int monthOrder) {
        assertThatThrownBy(() -> new EventCalender(monthOrder, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE_PREFIX);
    }

    @DisplayName("해당 날짜가 스타일 경우 True 리턴")
    @Test
    void Should_ReturnTrue_When_GivenStarDay() {
        // given
        EventCalender calender = new EventCalender(12, Set.of(new Date(12, 3), new Date(12, 10)));
        Date visitDay = new Date(12, 3);

        // when
        boolean result = calender.checkStarDay(visitDay);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("해당 날짜가 스타일 경우 False 리턴")
    @Test
    void Should_ReturnFalse_When_GivenNotStarDay() {
        // given
        EventCalender calender = new EventCalender(12, Set.of(new Date(12, 3), new Date(12, 10)));
        Date visitDay = new Date(12, 1);

        // when
        boolean result = calender.checkStarDay(visitDay);

        // then
        assertThat(result).isFalse();
    }

}