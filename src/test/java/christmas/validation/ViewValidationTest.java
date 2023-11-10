package christmas.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static christmas.validation.ViewValidation.validateEmptyOrSpace;
import static christmas.validation.ViewValidation.validateNull;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ViewValidationTest {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR]";

    @DisplayName("공백이나 빈 문자열 입력한 경우 예외 발생")
    @ValueSource(strings = {"", " "})
    @ParameterizedTest
    void Should_ThrowException_When_SpaceOrEmpty(String input) {
        assertThatThrownBy(() -> validateEmptyOrSpace(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE_PREFIX);
    }

    @DisplayName("공백이나 빈 문자열이 아닐 경우 정상 진행")
    @ValueSource(strings = {"1", "12", "123"})
    @ParameterizedTest
    void Should_Success_When_NotSpaceOrEmpty(String input) {
        assertThatCode(() -> validateEmptyOrSpace(input)).doesNotThrowAnyException();
    }

    @DisplayName("null 입력한 경우 예외 발생")
    @Test
    void Should_ThrowException_When_Null() {
        // given
        String input = null;

        // when & then
        assertThatThrownBy(() -> validateNull(input))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(ERROR_MESSAGE_PREFIX);
    }

    @DisplayName("null이 아닌 값을 입력한 경우 정상 진행")
    @ValueSource(strings = {"2", "23", "234"})
    @ParameterizedTest
    void Should_Success_When_NotNull(String input) {
        assertThatCode(() -> validateEmptyOrSpace(input)).doesNotThrowAnyException();
    }
}