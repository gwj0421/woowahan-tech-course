package christmas.domain.event.discount;

import christmas.domain.calender.Date;
import christmas.domain.calender.EventCalender;
import christmas.domain.event.EventType;
import christmas.domain.week.Week;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialDiscountEventTest {
    private static Map<EventType, Integer> discountResult;
    private static DiscountEvent event;

    @BeforeEach
    void setUp() {
        discountResult = new LinkedHashMap<>();
        event = new SpecialDiscountEvent(new EventCalender(12, Set.of(new Date(12, 1), new Date(12, 2))));
    }

    @DisplayName("방문한 날짜가 이벤트 기간이 아닐 경우, 혜택 내역 Map 에 추가하지 않음")
    @CsvSource(value = {"1,1", "5,1", "9,1"})
    @ParameterizedTest
    void Should_DontApplyEvent_When_DateIsOutOfRange(final int month, final int day) {
        // when
        event.apply(new Date(month, day, Week.FRIDAY), discountResult);

        // then
        assertThat(discountResult).isEmpty();
    }

    @DisplayName("방문한 날짜에 별이 없다면, 혜택 내역 Map 에 추가하지 않음")
    @CsvSource(value = {"12,5", "12,10"})
    @ParameterizedTest
    void Should_DontApplyEvent_When_DateIsNotStar(final int month, final int day) {
        // when
        event.apply(new Date(month, day), discountResult);

        // then
        assertThat(discountResult).isEmpty();
    }

    @DisplayName("방문한 날짜에 별이 있다면, 혜택 내역 Map 에 추가함")
    @CsvSource(value = {"12,1", "12,2"})
    @ParameterizedTest
    void Should_ApplyEvent_When_DateIsStar(final int month, final int day) {
        // when
        event.apply(new Date(month, day), discountResult);

        // then
        assertThat(discountResult).hasSize(1)
                .containsEntry(EventType.SPECIAL_DISCOUNT_EVENT, 1000);
    }
}