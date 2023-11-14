package christmas.domain.event.discount;

import christmas.domain.calender.Date;
import christmas.domain.event.EventType;
import christmas.domain.menu.OrderedMenu;
import christmas.domain.event.discount.DiscountEvent;
import christmas.domain.event.discount.WeekdayDiscountEvent;
import christmas.domain.week.Week;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class WeekdayDiscountEventTest {
    @DisplayName("방문한 날짜가 평일(일요일~목요일)이 아니고 디저트 메뉴가 아닌 경우, 혜택 내역 Map 에 추가하지 않음")
    @Test
    void Should_Fail_When_DateIsNotWeekdayAndNotDesert() {
        // given
        Map<EventType, Integer> discountResult = new LinkedHashMap<>();
        Map<String, Integer> order = new LinkedHashMap<>();
        order.put("타파스", 1);
        order.put("시저샐러드", 1);
        DiscountEvent event = new WeekdayDiscountEvent(new OrderedMenu(order));

        // when
        event.apply(new Date(12, 1, Week.FRIDAY), discountResult);

        // then
        assertThat(discountResult).isEmpty();
    }

    @DisplayName("방문한 날짜가 평일(일요일~목요일)이지만 디저트 메뉴가 아닐 경우, 혜택 내역 Map 에 추가하지 않음")
    @Test
    void Should_Fail_When_DateIsWeekdayAndNotDesert() {
        // given
        Map<EventType, Integer> discountResult = new LinkedHashMap<>();
        Map<String, Integer> order = new LinkedHashMap<>();
        order.put("타파스", 1);
        order.put("시저샐러드", 1);
        DiscountEvent event = new WeekdayDiscountEvent(new OrderedMenu(order));

        // when
        event.apply(new Date(12, 4, Week.MONDAY), discountResult);

        // then
        assertThat(discountResult).isEmpty();
    }

    @DisplayName("방문한 날짜가 평일(일요일~목요일)가 아니지만 디저트 메뉴일 경우, 혜택 내역 Map 에 추가하지 않음")
    @Test
    void Should_Fail_When_DateIsNotWeekdayAndDesert() {
        // given
        Map<EventType, Integer> discountResult = new LinkedHashMap<>();
        Map<String, Integer> order = new LinkedHashMap<>();
        order.put("초코케이크", 1);
        order.put("아이스크림", 1);
        DiscountEvent event = new WeekdayDiscountEvent(new OrderedMenu(order));

        // when
        event.apply(new Date(12, 1, Week.FRIDAY), discountResult);

        // then
        assertThat(discountResult).isEmpty();
    }

    @DisplayName("방문한 날짜가 평일(일요일~목요일)이면서 디저트 메뉴일 경우 혜택 내역 Map 에 추가함")
    @Test
    void Should_PutMap_When_DateIsWeekdayAndDesert() {
        // given
        Map<EventType, Integer> discountResult = new LinkedHashMap<>();
        Map<String, Integer> order = new LinkedHashMap<>();
        order.put("초코케이크", 1);
        order.put("아이스크림", 1);
        DiscountEvent event = new WeekdayDiscountEvent(new OrderedMenu(order));

        // when
        event.apply(new Date(12, 4, Week.MONDAY), discountResult);

        // then
        assertThat(discountResult).containsEntry(EventType.WEEKDAY_DISCOUNT_EVENT, 2023 * 2);
    }
}