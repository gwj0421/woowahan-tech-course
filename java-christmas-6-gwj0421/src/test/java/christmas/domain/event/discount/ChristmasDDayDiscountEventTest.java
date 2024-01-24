package christmas.domain.event.discount;

import christmas.domain.calender.Date;
import christmas.domain.event.EventType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasDDayDiscountEventTest {
    private static Map<EventType, Integer> discountResult;
    private static DiscountEvent event;

    @BeforeEach
    void setUp() {
        // given
        discountResult = new LinkedHashMap<>();
        event = new ChristmasDDayDiscountEvent();
    }

    @DisplayName("방문한 날짜가 크리스마스 디데이 이벤트 기간이 아니라면, 이벤트 적용하지 않고 혜택 내역 Map 에 추가하지 않음 ")
    @CsvSource(value = {"12,28", "12,26", "12,29", "10,1"})
    @ParameterizedTest
    void Should_DontApplyEvent_When_DateISOutOfChristmasEventRange(final int month, final int day) {
        // when
        event.apply(new Date(month, day), discountResult);

        // then
        assertThat(discountResult).isEmpty();
    }

    @DisplayName("방문한 날짜가 크리스마스 디데이 이벤트 기간이라면, 이벤트 적용하고 혜택 내역 Map 에 추가함")
    @CsvSource(value = {"12,1,1000", "12,15,2400", "12,25,3400"})
    @ParameterizedTest
    void Should_ApplyEvent_When_DateISInChristmasEventRange(final int month, final int day, final int expectedDiscount) {
        // when
        event.apply(new Date(month, day), discountResult);

        // then
        assertThat(discountResult).hasSize(1)
                .containsEntry(EventType.CHRISTMAS_D_DAY_DISCOUNT_EVENT, expectedDiscount);
    }
}