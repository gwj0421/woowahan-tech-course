package christmas.util;

import christmas.domain.event.EventType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

class MapUtilTest {
    @DisplayName("할인 혜택이 주어지면 할인 혜택의 총 합을 리턴")
    @Test
    void Should_ReturnTotal_When_GivenDiscountAmounts() {
        // given
        Map<EventType, Integer> discountAmounts = new EnumMap<>(EventType.class);
        discountAmounts.put(EventType.SPECIAL_DISCOUNT_EVENT, 10000);
        discountAmounts.put(EventType.WEEKDAY_DISCOUNT_EVENT, 5000);

        // when
        int totalDiscountAmount = MapUtil.calculateTotalBenefitAboutDiscount(discountAmounts);

        // then
        Assertions.assertThat(totalDiscountAmount).isEqualTo(15000);
    }
}