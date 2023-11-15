package christmas.domain.event.giveaway;

import christmas.domain.event.EventType;
import christmas.domain.menu.Menu;
import christmas.domain.menu.OrderedMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GiveawayByTotalPurchaseAmountEventTest {
    private static Map<String, Integer> order;
    private static Map<EventType, Integer> benefitDetail;
    private static GiveawayEvent giveawayEvent;

    @BeforeEach
    void setUp() {
        order = new LinkedHashMap<>(Map.of("타파스", 3));
        benefitDetail = new LinkedHashMap<>();
        giveawayEvent = new GiveawayByTotalPurchaseAmountEvent();
    }

    @DisplayName("주문한 메뉴들의 할인 전 총 가격이 증정 이벤트의 최소 금액들을 다 못채웠을 경우 증정 이벤트 적용하지 않고 빈 Map 리턴 및 혜택 내역 Map에 추가하지 않음")
    @Test
    void Should_DontApplyGiveawayEvent_When_TotalPriceIsLessThanMinimumPrice() {
        // when
        Map<Menu, Integer> giveaway = giveawayEvent.apply(new OrderedMenu(order), benefitDetail);

        // then
        assertThat(giveaway).isEmpty();
        assertThat(benefitDetail).isEmpty();
    }

    @DisplayName("주문한 메뉴들의 할인 전 총 가격이 증정 이벤트의 최소 금액을 채웠을 경우 증정 상품 Map 리턴 및 혜택 내역 Map 에 추가함")
    @CsvSource(value = {"티본스테이크,3", "바비큐립,3", "해산물파스타,5"})
    @ParameterizedTest
    void Should_ApplyGiveawayEvent_When_TotalPriceIsMoreThanMinimumPrice(final String menuName, final int cnt) {
        // given
        order.put(menuName, cnt);

        // when
        Map<Menu, Integer> giveaway = giveawayEvent.apply(new OrderedMenu(order), benefitDetail);

        // then
        assertThat(giveaway).containsEntry(Menu.CHAMPAGNE, 1);
        assertThat(benefitDetail).containsEntry(EventType.GIVEAWAY_EVENT, Menu.CHAMPAGNE.getPrice());
    }
}