package christmas.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;

import static christmas.validation.ViewValidation.*;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ViewValidationTest {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR]";

    @Nested
    class Describe_validateEmptyOrSpaceOrNull_Method {
        @DisplayName("공백이나 빈 문자열 입력한 경우 예외 발생")
        @ValueSource(strings = {"", " "})
        @ParameterizedTest
        void Should_ThrowException_When_SpaceOrEmpty(String input) {
            assertThatThrownBy(() -> validateEmptyOrSpaceOrNull(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_MESSAGE_PREFIX);
        }

        @DisplayName("공백이나 빈 문자열이 아닐 경우 정상 진행")
        @ValueSource(strings = {"1", "12", "123"})
        @ParameterizedTest
        void Should_Success_When_NotSpaceOrEmpty(String input) {
            assertThatCode(() -> validateEmptyOrSpaceOrNull(input)).doesNotThrowAnyException();
        }

        @DisplayName("null 입력한 경우 예외 발생")
        @Test
        void Should_ThrowException_When_Null() {
            // given
            String input = null;

            // when & then
            assertThatThrownBy(() -> validateEmptyOrSpaceOrNull(input))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining(ERROR_MESSAGE_PREFIX);
        }

        @DisplayName("null이 아닌 값을 입력한 경우 정상 진행")
        @ValueSource(strings = {"2", "23", "234"})
        @ParameterizedTest
        void Should_Success_When_NotNull(String input) {
            assertThatCode(() -> validateEmptyOrSpaceOrNull(input)).doesNotThrowAnyException();
        }
    }

    @Nested
    class Describe_validateDuplicated_Method {
        @DisplayName("입력할 Map에 이미 key가 존재할 경우 예외 발생")
        @ValueSource(strings = {"파스타", "pasta"})
        @ParameterizedTest
        void Should_ThrowException_When_DuplicatedKey(String input) {
            // given
            HashMap<String, Integer> order = new HashMap<>();
            order.put(input, 0);

            // when & then
            assertThatThrownBy(() -> validateDuplicated(order, input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_MESSAGE_PREFIX);
        }

        @DisplayName("입력할 Map에 없는 key를 입력할 경우 정상 진행")
        @Test
        void Should_Success_When_NotDuplicated() {
            // given
            String[] menus = new String[]{"pasta", "pizza", "hamburger"};
            HashMap<String, Integer> order = new HashMap<>();

            // when & then
            for (String menu : menus) {
                assertThatCode(() -> validateDuplicated(order, menu)).doesNotThrowAnyException();
                order.put(menu, 0);
            }
        }
    }

    @Nested
    class Describe_validateNegativeOrZero_Method {
        @DisplayName("음수 혹은 0 입력시 예외 발생")
        @ValueSource(ints = {-1, -10, 0})
        @ParameterizedTest
        void Should_ThrowException_When_NumberIsNegativeOrZero(int input) {
            assertThatThrownBy(() -> validateNegativeOrZero(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_MESSAGE_PREFIX);
        }

        @DisplayName("양수 입력시 정상 진행")
        @ValueSource(ints = {1, 10, 20})
        @ParameterizedTest
        void Should_Success_When_NumberIsPositive(int input) {
            assertThatCode(() -> validateNegativeOrZero(input)).doesNotThrowAnyException();
        }
    }

    @Nested
    class Describe_validateOrderFormat_Method {
        @DisplayName("주어진 주문 형태(음식1-개수1,음식2-개수2)가 아닐 경우 예외 발생")
        @ValueSource(strings = {"시저샐러드-1-1,티본스테이크-1", "크리스마스파스타-1, 제로콜라-3", "아이스크림--1", "아이스크림1", "아이스크림,1", "1-아이스크림,제로콜라-3"})
        @ParameterizedTest
        void Should_ThrowException_When_OrderFormatMismatch(String input) {
            assertThatThrownBy(() -> validateOrderFormat(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_MESSAGE_PREFIX);
        }

        @DisplayName("주어진 주문 형태(음식-개수)일 경우 정상 진행")
        @ValueSource(strings = {"해산물파스타-2", "아이스크림-1"})
        @ParameterizedTest
        void Should_Success_When_OrderFormatMatch(String input) {
            assertThatCode(() -> validateOrderFormat(input)).doesNotThrowAnyException();
        }
    }
}