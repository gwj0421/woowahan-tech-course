package christmas.domain.event;

import christmas.domain.calender.Date;
import christmas.domain.calender.EventCalender;
import christmas.domain.menu.OrderedMenu;
import christmas.domain.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PreviewDiscountMachineTest {
    @DisplayName("총 주문 금액이 이벤트 최소 적용 금액을 채우지 못한 경우 이벤트 적용 생략")
    @Test
    void Should_NotApplyEvent_When_OrderAmountIsUnderMinimum() {
        // given
        Map<String, Integer> order = new HashMap<>();
        order.put("타파스", 1);
        order.put("제로콜라", 1);

        // when
        PreviewDiscountMachine prev = new PreviewDiscountMachine(new EventCalender(12, Set.of()), new OrderedMenu(order), 12);
        PreviewResult prevResult = prev.getPreviewResult();

        // then
        assertThat(prevResult.getBenefitResult()).isEmpty();
        assertThat(prevResult.getGiveawayResult()).isEmpty();

    }

    @DisplayName("총 주문 금액이 이벤트 최소 금액 이상일 경우 적용할 수 있는 이벤트 적용")
    @Test
    void Should_ApplyEvent_When_OrderAmountIsOverMinimum() {
        // given
        Map<String, Integer> order = new HashMap<>();
        order.put("티본스테이크", 1);
        order.put("바비큐립", 1);
        order.put("초코케이크", 2);
        order.put("제로콜라", 1);

        // when
        PreviewDiscountMachine prev = new PreviewDiscountMachine(new EventCalender(12, Set.of(new Date(12, 3))), new OrderedMenu(order), 3);
        PreviewResult prevResult = prev.getPreviewResult();

        // then
        assertThat(prevResult.getBenefitResult()).hasSize(4).
                containsEntry(EventType.CHRISTMAS_D_DAY_DISCOUNT_EVENT, 1200).
                containsEntry(EventType.WEEKDAY_DISCOUNT_EVENT, 4046).
                containsEntry(EventType.SPECIAL_DISCOUNT_EVENT, 1000).
                containsEntry(EventType.GIVEAWAY_EVENT, 25000);
        assertThat(prevResult.getGiveawayResult()).hasSize(1).containsEntry(Menu.CHAMPAGNE, 1);
    }
}