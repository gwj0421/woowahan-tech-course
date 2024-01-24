package christmas.domain.menu;

import christmas.domain.menu.OrderedMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

class OrderedMenuTest {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR]";

    @DisplayName("없는 메뉴 입력 시 예외 발생")
    @Test
    void Should_ThrowException_When_MenuIsNotExists() {
        // given
        HashMap<String, Integer> order = new HashMap<>();
        order.put("시저샐러드", 1);
        order.put("양송이수프", 2);
        order.put("파인애플피자", 3);

        // when & then
        assertThatThrownBy(() -> new OrderedMenu(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE_PREFIX);
    }

    @DisplayName("음료 메뉴만 입력 시 예외 발생")
    @Test
    void Should_ThrowException_When_EnterOnlyBeverage() {
        // given
        HashMap<String, Integer> order = new HashMap<>();
        order.put("제로콜라", 1);
        order.put("레드와인", 2);

        // when & then
        assertThatThrownBy(() -> new OrderedMenu(order)).
                isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE_PREFIX);
    }

    @DisplayName("메뉴 최대 개수를 넘게 입력 시 예외 발생")
    @Test
    void Should_ThrowException_When_ExceedMaximumMenuCnt() {
        // given
        HashMap<String, Integer> order = new HashMap<>();
        order.put("시저샐러드", 15);
        order.put("양송이수프", 15);

        // when & then
        assertThatThrownBy(() -> new OrderedMenu(order)).
                isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE_PREFIX);
    }

    @DisplayName("존재하는 메뉴 입력 시 OrderMenu 객체 생성")
    @Test
    void Should_CreateOrderMenuObject_When_MenuIsExists() {
        // given
        HashMap<String, Integer> order = new HashMap<>();
        order.put("시저샐러드", 1);
        order.put("양송이수프", 2);

        // when & then
        assertThatCode(() -> new OrderedMenu(order)).doesNotThrowAnyException();
    }

    @DisplayName("메뉴와 개수 입력 시 총 금액 리턴")
    @Test
    void Should_ReturnTotalPrice_When_EnterMenuAndCnt() {
        // given
        HashMap<String, Integer> order = new HashMap<>();
        order.put("시저샐러드", 1);
        order.put("양송이수프", 2);

        // when
        OrderedMenu orderedMenu = new OrderedMenu(order);
        int expectedPrice = orderedMenu.getTotalPrice();

        // then
        assertThat(expectedPrice).isEqualTo(20000);
    }
}