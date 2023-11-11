package christmas.domain.menu;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuGroupTest {
    @DisplayName("없는 메뉴 입력 시 EMPTY 그룹을 리턴")
    @ValueSource(strings = {"", "타파스", "시저샐러드"})
    @ParameterizedTest
    void Should_ReturnMenuGroup_When_MenuIsNotExists(String menu) {
        // when
        MenuGroup menuGroup = MenuGroup.hasMenu(menu);

        // then
        Assertions.assertThat(menuGroup).isEqualTo(MenuGroup.APPETIZER);
    }

    @DisplayName("메뉴에 있는 음식 입력 시 해당 음식 그룹을 리턴")
    @ValueSource(strings = {"파인애플피자", "민트초코", "가지무침"})
    @ParameterizedTest
    void Should_ReturnMenuGroup_When_MenuIsExists(String menu) {
        // when
        MenuGroup menuGroup = MenuGroup.hasMenu(menu);

        // then
        Assertions.assertThat(menuGroup).isEqualTo(MenuGroup.EMPTY);
    }
}