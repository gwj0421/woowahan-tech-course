package christmas.domain.event;

import christmas.domain.event.EventType;
import christmas.domain.event.PreviewResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

class PreviewResultTest {
    @DisplayName("할인 이벤트 내역이 주어졌을 때 전체 이벤트 내역 총합을 리턴")
    @Test
    void testGetTotalBenefitAmount() {
        // given
        Map<EventType, Integer> benefitResult = new LinkedHashMap<>();
        benefitResult.put(EventType.WEEKDAY_DISCOUNT_EVENT, 5000);
        benefitResult.put(EventType.WEEKEND_DISCOUNT_EVENT, 6000);

        // when
        PreviewResult previewResult = new PreviewResult(benefitResult, Collections.emptyMap(), 15000);

        // then
        Assertions.assertThat(previewResult.getTotalBenefitAmount()).isEqualTo(11000);
    }
}