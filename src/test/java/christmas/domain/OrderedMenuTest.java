package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class OrderedMenuTest {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR]";

    @DisplayName("없는 메뉴 입력 시 예외 발생")
    @Test
    void Should_ThrowException_When_MenuIsNotExists() {
        // given
        HashMap<String, Integer> order = new HashMap<>();
        order.put("시저샐러드",1);
        order.put("양송이수프",2);
        order.put("파인애플피자", 3);

        // when & then
        Assertions.assertThatThrownBy(() -> new OrderedMenu(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE_PREFIX);
    }

    @DisplayName("존재하는 메뉴 입력 시 OrderMenu 객체 생성")
    @Test
    void Should_CreateOrderMenuObject_When_MenuIsExists() {
        // given
        HashMap<String, Integer> order = new HashMap<>();
        order.put("시저샐러드",1);
        order.put("양송이수프",2);

        // when & then
        Assertions.assertThatCode(() -> new OrderedMenu(order)).doesNotThrowAnyException();
    }

}