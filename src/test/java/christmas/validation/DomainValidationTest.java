package christmas.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DomainValidationTest {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR]";

    @Nested
    class Describe_validateDateRange_Method {
        @DisplayName("주어진 범위를 벗어나는 날짜를 입력한 경우 예외 발생")
        @CsvSource(value = {"12,0", "12,32", "12,50"})
        @ParameterizedTest
        void Should_ThrowException_When_DayIsOutOfRange(int month, int day) {
            assertThatThrownBy(() -> DomainValidation.validateDateRange(month, day))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_MESSAGE_PREFIX);
        }

        @DisplayName("주어진 범위안의 날짜를 입력한 경우 정상 실행")
        @CsvSource(value = {"12,1", "12,15", "12,31"})
        @ParameterizedTest
        void Should_Success_When_DayIsInRange(int month, int day) {
            assertThatCode(() -> DomainValidation.validateDateRange(month, day)).doesNotThrowAnyException();
        }
    }

    @Nested
    class Describe_validateMenuExistsOrNot_Method {
        @DisplayName("주어진 메뉴에 없는 메뉴가 한개라도 입력될 경우 예외 발생")
        @Test
        void Should_ThrowException_When_MenuIsNotExist() {
            // given
            HashMap<String, Integer> order = new HashMap<>();
            order.put("양송이수프", 1);
            order.put("햄버거", 2);
            assertThatThrownBy(() -> DomainValidation.validateMenuExistsOrNot(order))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_MESSAGE_PREFIX);
        }

        @DisplayName("주어진 메뉴에 없는 메뉴가 한개라도 입력될 경우 예외 발생")
        @Test
        void Should_ThrowException_When_MenuIsExist() {
            // given
            HashMap<String, Integer> order = new HashMap<>();
            order.put("양송이수프", 1);
            order.put("타파스", 2);

            // when & then
            assertThatCode(() -> DomainValidation.validateMenuExistsOrNot(order)).doesNotThrowAnyException();
        }
    }
}