package christmas.domain.event;

import christmas.domain.calender.Date;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class EventTypeTest {
    @DisplayName("주어진 날짜에 해당 이벤트 기간이 아니면 False 리턴")
    @EnumSource(names = {"CHRISTMAS_D_DAY_DISCOUNT_EVENT","WEEKDAY_DISCOUNT_EVENT","WEEKEND_DISCOUNT_EVENT","SPECIAL_DISCOUNT_EVENT","GIVEAWAY_EVENT"})
    @ParameterizedTest
    void Should_ReturnFalse_When_DateIsInvalid(final EventType eventType) {
        // given
        Date date = new Date(10, 1);

        // when
        boolean result = eventType.isEventApply(date);

        // then
        Assertions.assertThat(result).isFalse();
    }

    @DisplayName("주어진 날짜에 해당 이벤트 기간이면 True 리턴")
    @EnumSource(names = {"CHRISTMAS_D_DAY_DISCOUNT_EVENT","WEEKDAY_DISCOUNT_EVENT","WEEKEND_DISCOUNT_EVENT","SPECIAL_DISCOUNT_EVENT","GIVEAWAY_EVENT"})
    @ParameterizedTest
    void Should_ReturnTrue_When_DateIsValid(final EventType eventType) {
        // given
        Date date = new Date(12, 1);

        // when
        boolean result = eventType.isEventApply(date);

        // then
        Assertions.assertThat(result).isTrue();
    }
}