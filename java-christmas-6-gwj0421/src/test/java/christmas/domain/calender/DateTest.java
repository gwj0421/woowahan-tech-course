package christmas.domain.calender;

import christmas.domain.calender.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DateTest {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR]";

    @DisplayName("주어진 범위를 벗어나는 날짜 입력시 IllegalException 발생")
    @CsvSource(value = {"12,0","12,32","12,50"})
    @ParameterizedTest
    void Should_ThrowException_When_DayIsOutOfRange(int month,int day) {
        assertThatThrownBy(() -> new Date(month,day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE_PREFIX);
    }

    @DisplayName("주어진 범위안의 날짜 입력시 Date 객체 정상 생성")
    @CsvSource(value = {"12,1", "12,15", "12,31"})
    @ParameterizedTest
    void Should_CreateDateObject_When_DayIsInRange(int month, int day) {
        assertThatCode(() -> new Date(month, day)).doesNotThrowAnyException();
    }

}