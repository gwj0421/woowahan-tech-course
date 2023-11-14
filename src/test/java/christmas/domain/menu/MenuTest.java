package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuTest {
    @DisplayName("입력된 문자열로 된 메뉴가 없는 메뉴일 경우, IllegalArgumentException 예외 발생")
    @ValueSource(strings = {"피자", "햄버거", "민트초코"})
    @ParameterizedTest
    void Should_ThrowException_When_GivenMenuNameIsNotExists(String menu) {
        assertThatThrownBy(() -> Menu.getMenuByMenuName(menu))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력된 문자열로 된 메뉴가 존재하는 메뉴일 경우, Menu 리턴")
    @EnumSource(names = {"MUSHROOM_SOUP", "T_BONE_STEAK", "RED_WINE"})
    @ParameterizedTest
    void Should_ReturnMenu_When_GivenMenuNameIsExists(Menu menu) {
        assertThat(Menu.getMenuByMenuName(menu.getName())).isEqualTo(menu);
    }
}