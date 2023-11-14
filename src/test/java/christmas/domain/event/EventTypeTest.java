package christmas.domain.event;

import christmas.domain.calender.Date;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventTypeTest {
    @DisplayName("주어진 날짜에 해당 이벤트 기간이 아니면 False 리턴")
    @Test
    void Should_ReturnFalse_When_DateIsInvalid() {
        // given
        Date date = new Date(12, 26);

        // when
        boolean result = EventType.CHRISTMAS_D_DAY_DISCOUNT_EVENT.isEventApply(date);

        // then
        Assertions.assertThat(result).isFalse();
    }

    @DisplayName("주어진 날짜에 해당 이벤트 기간이면 True 리턴")
    @Test
    void Should_ReturnTrue_When_DateIsValid() {
        // given
        Date date = new Date(12, 25);

        // when
        boolean result = EventType.CHRISTMAS_D_DAY_DISCOUNT_EVENT.isEventApply(date);

        // then
        Assertions.assertThat(result).isTrue();
    }
}