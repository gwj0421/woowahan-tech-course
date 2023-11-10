package christmas.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static christmas.util.ConvertUtil.string2Int;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConvertUtilTest {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR]";

    @DisplayName("정수로 이뤄지지 않은 문자열 입력시 예외 발생")
    @ValueSource(strings = {"a", "1a","a1"})
    @ParameterizedTest
    void Should_ThrowException_When_StringCanNotConvertInt(String input) {
        assertThatThrownBy(() -> string2Int(input))
                .isInstanceOf(NumberFormatException.class)
                .hasMessageContaining(ERROR_MESSAGE_PREFIX);
    }

    @DisplayName("정수로 이뤄진 문자열 입력시 전환된 정수 리턴")
    @CsvSource(value = {"1,1","23,23","123,123"})
    @ParameterizedTest
    void Should_Success_When_NotSpaceOrEmpty(String input,int expected) {
        assertThat(string2Int(input)).isEqualTo(expected);
    }
}