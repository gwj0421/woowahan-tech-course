package christmas.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;

import static christmas.config.AppConstant.MAX_ORDER_CNT;
import static christmas.validation.DomainValidation.*;
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
            assertThatThrownBy(() -> validateDateRange(month, day))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_MESSAGE_PREFIX);
        }

        @DisplayName("주어진 범위안의 날짜를 입력한 경우 정상 통과")
        @CsvSource(value = {"12,1", "12,15", "12,31"})
        @ParameterizedTest
        void Should_Success_When_DayIsInRange(int month, int day) {
            assertThatCode(() -> validateDateRange(month, day)).doesNotThrowAnyException();
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
            assertThatThrownBy(() -> validateMenuExistsOrNot(order))
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
            assertThatCode(() -> validateMenuExistsOrNot(order)).doesNotThrowAnyException();
        }
    }

    @Nested
    class Describe_validateOnlyBeverage_Method {
        @DisplayName("주어진 메뉴가 음료 메뉴만 있으면 예외 발생")
        @Test
        void Should_ThrowException_When_GivenOnlyBeverageMenu() {
            // given
            HashMap<String, Integer> order = new HashMap<>();
            order.put("제로콜라", 1);
            order.put("레드와인", 2);

            // when & then
            assertThatThrownBy(() -> validateOnlyBeverage(order))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_MESSAGE_PREFIX);
        }

        @DisplayName("주어진 메뉴가 음료 메뉴만 있지 않는 경우 정상 통과")
        @Test
        void Should_Success_When_GivenNotOnlyBeverageMenu() {
            // given
            HashMap<String, Integer> order = new HashMap<>();
            order.put("제로콜라", 1);
            order.put("아이스크림", 2);

            // when & then
            assertThatCode(() -> validateOnlyBeverage(order)).doesNotThrowAnyException();
        }
    }

    @Nested
    class Describe_validateMaxOrderCnt_Method {
        @DisplayName("주어진 메뉴의 총 개수가 제한한 범위를 넘을 경우 예외 발생")
        @Test
        void Should_ThrowException_When_TotalMenuCntIsOutOfRange() {
            // given
            HashMap<String, Integer> order = new HashMap<>();
            order.put("제로콜라", MAX_ORDER_CNT);
            order.put("레드와인", 5);

            // when & then
            assertThatThrownBy(() -> validateMaxOrderCnt(order))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_MESSAGE_PREFIX);
        }

        @DisplayName("주어진 메뉴의 총 개수가 제한된 범위 안일 경우 정상 통과")
        @Test
        void Should_Success_When_TotalMenuCntIsRange() {
            // given
            HashMap<String, Integer> order = new HashMap<>();
            order.put("제로콜라", MAX_ORDER_CNT);

            // when & then
            assertThatCode(() -> validateMaxOrderCnt(order)).doesNotThrowAnyException();
        }
    }

    @Nested
    class Describe_validateMonthRange_Method {
        @DisplayName("1~12월이 아닌 달을 입력할 경우 예외 발생")
        @ValueSource(ints = {0, 13, 14})
        @ParameterizedTest
        void Should_ThrowException_When_GivenInvalidMonth(int monthOrder) {
            assertThatThrownBy(() -> validateMonthRange(monthOrder))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_MESSAGE_PREFIX);
        }

        @DisplayName("1~12월사이 달을 입력할 경우 정상 통과")
        @ValueSource(ints = {1,5,12})
        @ParameterizedTest
        void Should_Success_When_GivenValidMonth(int monthOrder) {
            assertThatCode(() -> validateMonthRange(monthOrder)).doesNotThrowAnyException();
        }
    }

    @Nested
    class Describe_validateMoneyIsNegative_Method {
        @DisplayName("금액이 음수일 경우 경우 예외 발생")
        @ValueSource(ints = {-1,-5,-10})
        @ParameterizedTest
        void Should_ThrowException_When_GivenNegativeMoney(int money) {
            assertThatThrownBy(() -> validateMoneyIsNegative(money))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_MESSAGE_PREFIX);
        }

        @DisplayName("금액이 0 이상일 경우 정상 통과")
        @ValueSource(ints = {0,5,10})
        @ParameterizedTest
        void Should_Success_When_GivenNotNegativeMoney(int money) {
            assertThatCode(() -> validateMoneyIsNegative(money)).doesNotThrowAnyException();
        }
    }

}