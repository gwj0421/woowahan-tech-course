package christmas.domain.badge;

import christmas.domain.badge.Badge;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BadgeTest {

    @DisplayName("총혜택 금액이 뱃지 별 최소 금액을 달성시 해당 뱃지 리턴")
    @CsvSource(value = {"0,NORMAL","4900,NORMAL","5000,STAR","5100,STAR","10000,TREE","11000,TREE","20000,SANTA","30000,SANTA"})
    @ParameterizedTest
    void Should_ReturnBadge_When_GivenTotalBenefitAmount(int totalBenefitAmount, Badge actualBadge) {
        Assertions.assertThat(Badge.giveBadge(totalBenefitAmount)).isEqualTo(actualBadge);
    }
}