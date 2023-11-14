package christmas.domain.event;

import christmas.domain.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasDDayDiscountEventTest {
    @DisplayName("방문한 날짜가 크리스마스 디데이 이벤트 기간이 아니라면, 혜택 내역 Map 에 추가하지 않음 ")
    @Test
    void Should_Fail_When_DateISOutOfChristmasEventRange() {
        // given
        Map<EventType, Integer> discountResult = new LinkedHashMap<>();
        DiscountEvent event = new ChristmasDDayDiscountEvent();

        // when
        event.apply(new Date(12, 28), discountResult);

        // then
        assertThat(discountResult).isEmpty();
    }

    @DisplayName("방문한 날짜가 크리스마스 디데이 이벤트 기간이라면, 크리스마스 디데이 이벤트에 맞는 할인 가격을 혜택 내역 Map 에 추가함")
    @Test
    void Should_PutMap_When_DateISInChristmasEventRange() {
        // given
        Map<EventType, Integer> discountResult = new LinkedHashMap<>();
        DiscountEvent event = new ChristmasDDayDiscountEvent();

        // when
        event.apply(new Date(12, 25), discountResult);

        // then
        assertThat(discountResult).hasSize(1)
                .containsEntry(EventType.CHRISTMAS_D_DAY_DISCOUNT_EVENT,3400);
    }
}