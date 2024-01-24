package christmas.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static christmas.util.ConvertUtil.convertString2Int;
import static christmas.util.ConvertUtil.convertString2Map;
import static org.assertj.core.api.Assertions.*;

class ConvertUtilTest {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR]";

    @Nested
    class Describe_convertString2Int_Method {
        @DisplayName("정수로 이뤄지지 않은 문자열 입력시 예외 발생")
        @ValueSource(strings = {"a", "1a", "a1"})
        @ParameterizedTest
        void Should_ThrowException_When_StringCanNotConvertInt(String input) {
            assertThatThrownBy(() -> convertString2Int(input))
                    .isInstanceOf(NumberFormatException.class)
                    .hasMessageContaining(ERROR_MESSAGE_PREFIX);
        }

        @DisplayName("정수로 이뤄진 문자열 입력시 전환된 정수 리턴")
        @CsvSource(value = {"1,1", "23,23", "123,123"})
        @ParameterizedTest
        void Should_Success_When_NotSpaceOrEmpty(String input, int expected) {
            assertThat(convertString2Int(input)).isEqualTo(expected);
        }
    }

    @Nested
    class Describe_convertString2Map_Method {
        @DisplayName("메뉴와 개수가 제대로 형식을 갖추지 않았을 경우 예외 발생")
        @ValueSource(strings = {"시저샐러드-1-1,아이스크림-1", "아이스크림-1,티본스테이크--1", "아이스크림-1-", "아이스크림", "1"})
        @ParameterizedTest
        void Should_ThrowException_When_OrderFormatMismatch(String input) {
            assertThatThrownBy(() -> convertString2Map(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_MESSAGE_PREFIX);
        }

        @DisplayName("메뉴 혹은 개수가 공백일 경우 예외 발생")
        @ValueSource(strings = {" - ", "티본스테이크- ", " -1"})
        @ParameterizedTest
        void Should_ThrowException_When_MenuOrCntIsEmptyOrSpaceOrNull(String input) {
            assertThatThrownBy(() -> convertString2Map(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_MESSAGE_PREFIX);
        }

        @DisplayName("입력된 메뉴가 중복일 경우 예외 발생")
        @ValueSource(strings = {"시저샐러드-1,시저샐러드-1", "티본스테이크-2,시저샐러드-1,티본스테이크-2", "아이스크림-3,티본스테이크-2,시저샐러드-1,티본스테이크-2"})
        @ParameterizedTest
        void Should_ThrowException_When_MenusAreDuplicated(String input) {
            assertThatThrownBy(() -> convertString2Map(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_MESSAGE_PREFIX);
        }

        @DisplayName("입력된 메뉴의 개수가 양수가 아닐 경우 예외 발생")
        @ValueSource(strings = {"시저샐러드-1,시저샐러드-0", "티본스테이크-0,시저샐러드-1", "아이스크림-0"})
        @ParameterizedTest
        void Should_ThrowException_When_CntIsNotPositive(String input) {
            assertThatThrownBy(() -> convertString2Map(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_MESSAGE_PREFIX);
        }

        @DisplayName("메뉴와 개수가 형식에 맞을 경우 정상 동작")
        @ValueSource(strings = {"시저샐러드-1", "티본스테이크-2", "아이스크림-3"})
        @ParameterizedTest
        void Should_Success_When_ValidOrderFormat(String input) {
            assertThatCode(() -> convertString2Map(input)).doesNotThrowAnyException();
        }
    }
}