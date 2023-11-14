package christmas.domain.event.giveaway;

import christmas.domain.event.EventType;
import christmas.domain.menu.OrderedMenu;
import christmas.domain.event.giveaway.GiveawayByTotalPurchaseAmountEvent;
import christmas.domain.event.giveaway.GiveawayEvent;
import christmas.domain.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static christmas.config.AppConstant.MAX_ORDER_CNT;
import static org.assertj.core.api.Assertions.assertThat;

class GiveawayByTotalPurchaseAmountEventTest {
    @DisplayName("주문한 메뉴들의 할인 전 총 가격이 증정 이벤트의 최소 금액들을 다 못채웠을 경우 빈 Map 리턴 및 혜택 내역 Map에 추가하지 않음")
    @Test
    void Should_Fail_When_TotalPriceIsLessThanMinimumPrice() {
        // given
        Map<EventType, Integer> benefitDetail = new LinkedHashMap<>();
        Map<String, Integer> order = new LinkedHashMap<>();
        order.put("타파스", 1);
        order.put("아이스크림", 1);

        // when
        GiveawayEvent giveawayEvent = new GiveawayByTotalPurchaseAmountEvent();
        Map<Menu,Integer> giveaway = giveawayEvent.apply(new OrderedMenu(order), benefitDetail);

        // then
        assertThat(giveaway).isEmpty();
        assertThat(benefitDetail).isEmpty();
    }

    @DisplayName("주문한 메뉴들의 할인 전 총 가격이 증정 이벤트의 최소 금액을 채웠을 경우 증정 상품 Map 리턴 및 혜택 내역 Map 에 추가함")
    @Test
    void Should_ReturnGiveawayMapAndPutBenefitMap_When_TotalPriceIsMoreThanMinimumPrice() {
        // given
        Map<EventType, Integer> benefitDetail = new LinkedHashMap<>();
        Map<String, Integer> order = new LinkedHashMap<>();
        order.put("티본스테이크", MAX_ORDER_CNT);

        // when
        GiveawayEvent giveawayEvent = new GiveawayByTotalPurchaseAmountEvent();
        Map<Menu, Integer> giveaway = giveawayEvent.apply(new OrderedMenu(order), benefitDetail);

        // then
        assertThat(giveaway).containsEntry(Menu.CHAMPAGNE, 1);
        assertThat(benefitDetail).containsEntry(EventType.GIVEAWAY_EVENT,Menu.CHAMPAGNE.getPrice());
    }
}