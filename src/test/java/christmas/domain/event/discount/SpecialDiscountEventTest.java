package christmas.domain.event.discount;

import christmas.domain.calender.Date;
import christmas.domain.calender.EventCalender;
import christmas.domain.event.EventType;
import christmas.domain.event.discount.DiscountEvent;
import christmas.domain.event.discount.SpecialDiscountEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialDiscountEventTest {
    @DisplayName("방문한 날짜에 별이 없다면, 혜택 내역 Map 에 추가하지 않음")
    @Test
    void Should_Fail_When_DateIsNotStar() {
        // given
        Map<EventType, Integer> discountResult = new LinkedHashMap<>();
        Set<Date> starPos = Set.of(new Date(12, 1), new Date(12, 2));
        DiscountEvent event = new SpecialDiscountEvent(new EventCalender(12,starPos));

        // when
        event.apply(new Date(12, 2), discountResult);

        // then
        assertThat(discountResult).hasSize(1)
                .containsEntry(EventType.SPECIAL_DISCOUNT_EVENT, 1000);
    }

    @DisplayName("방문한 날짜에 별이 있다면, 혜택 내역 Map 에 추가함")
    @Test
    void Should_PutMap_When_DateIsStar() {
        // given
        Map<EventType, Integer> discountResult = new LinkedHashMap<>();
        Set<Date> starPos = Set.of(new Date(12, 1), new Date(12, 2));
        DiscountEvent event = new SpecialDiscountEvent(new EventCalender(12,starPos));

        // when
        event.apply(new Date(12, 3), discountResult);

        // then
        assertThat(discountResult).isEmpty();
    }
}