package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class MenuGroupTest {
    @DisplayName("제공된 메뉴가 존재하지 않을 경우, Menu.EMPTY 리턴")
    @ValueSource(strings = {"피자", "햄버거", "민트초코"})
    @ParameterizedTest
    void Should_ReturnEmptyGroup_When_GivenMenuNameIsNotExists(String menuName) {
        assertThat(MenuGroup.getMenuGroupByMenu(menuName)).isEqualTo(MenuGroup.EMPTY);
    }

    @DisplayName("제공된 메뉴가 존재할 경우, 해당 메뉴가 있는 MenuGroup 리턴")
    @ValueSource(strings = {"양송이수프","타파스","시저샐러드"})
    @ParameterizedTest
    void Should_ReturnNotEmptyGroup_When_GivenMenuNameIsExists(String menuName) {
        assertThat(MenuGroup.getMenuGroupByMenu(menuName)).isEqualTo(MenuGroup.APPETIZER);
    }
}