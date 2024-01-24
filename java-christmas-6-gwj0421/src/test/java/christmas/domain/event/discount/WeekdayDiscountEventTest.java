package christmas.domain.event.discount;

import christmas.domain.calender.Date;
import christmas.domain.event.EventType;
import christmas.domain.menu.OrderedMenu;
import christmas.domain.week.Week;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class WeekdayDiscountEventTest {
    private static final OrderedMenu orderedMenu = new OrderedMenu(new LinkedHashMap<>(Map.of("타파스", 1, "시저샐러드", 1, "초코케이크", 1, "아이스크림", 1)));
    private static Map<EventType, Integer> discountResult;
    private static DiscountEvent event;

    @BeforeEach
    void setUp() {
        discountResult = new LinkedHashMap<>();
        event = new WeekdayDiscountEvent(orderedMenu);
    }

    @DisplayName("방문한 날짜가 이벤트 기간이 아닐 경우, 혜택 내역 Map 에 추가하지 않음")
    @CsvSource(value = {"1,1", "5,1", "9,1"})
    @ParameterizedTest
    void Should_DontApplyEvent_When_DateIsOutOfRange(final int month, final int day) {
        // when
        event.apply(new Date(month, day, Week.MONDAY), discountResult);

        // then
        assertThat(discountResult).isEmpty();
    }

    @DisplayName("방문한 날짜가 평일(일요일~목요일)가 아닐 경우 이벤트 적용하지 않고, 혜택 내역 Map 에 추가하지 않음")
    @EnumSource(names = {"FRIDAY", "SATURDAY"})
    @ParameterizedTest
    void Should_DontApplyEvent_When_DateIsNotWeekdayAndDesert(final Week week) {
        // when
        event.apply(new Date(12, 1, week), discountResult);

        // then
        assertThat(discountResult).isEmpty();
    }

    @DisplayName("방문한 날짜가 평일(일요일~목요일)일 경우 디저트 메뉴에만 이벤트 적용하여 혜택 내역 Map 에 추가함")
    @EnumSource(names = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "SUNDAY"})
    @ParameterizedTest
    void Should_ApplyEvent_When_DateIsWeekdayAndDesert(final Week week) {
        // when
        event.apply(new Date(12, 1, week), discountResult);

        // then
        assertThat(discountResult).containsEntry(EventType.WEEKDAY_DISCOUNT_EVENT, 2023 * 2);
    }
}