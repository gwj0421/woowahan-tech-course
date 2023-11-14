package christmas.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Month2023DetailTest {
    @DisplayName("주어진 달에 잘못된 날짜경우 False 리턴")
    @CsvSource(value = {"12,-1","12,0","12,50"})
    @ParameterizedTest
    void Should_ReturnTrue_When_DayIsInvalid(int monthOrder, int day) {
        Assertions.assertThat(Month2023Detail.isDayInRange(monthOrder,day)).isFalse();
    }

    @DisplayName("주어진 달에 날짜가 올바른 경우 True 리턴")
    @CsvSource(value = {"12,1","12,15","12,20"})
    @ParameterizedTest
    void Should_ReturnTrue_When_DayIsValid(int monthOrder, int day) {
        Assertions.assertThat(Month2023Detail.isDayInRange(monthOrder,day)).isTrue();
    }
}