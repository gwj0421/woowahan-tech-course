package christmas.domain.menu;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MenuTest {
    @DisplayName("메뉴 입력 시, 가격 리턴")
    @CsvSource(value = {"양송이수프,6000", "타파스,5500"})
    @ParameterizedTest
    void Should_ReturnPrice_When_EnterMenu(String menuName, int price) {
        Assertions.assertThat(Menu.getPriceByMenuName(menuName)).isEqualTo(price);
    }
}