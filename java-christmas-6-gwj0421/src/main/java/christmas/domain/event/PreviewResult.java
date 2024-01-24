package christmas.domain.event;

import christmas.domain.menu.Menu;

import java.util.Map;

public class PreviewResult {
    private final Map<EventType, Integer> benefitResult;
    private final Map<Menu,Integer> giveawayResult;
    private final int actualPayAmount;

    public PreviewResult(final Map<EventType, Integer> benefitResult, final Map<Menu, Integer> giveawayResult, final int actualPayAmount) {
        this.benefitResult = benefitResult;
        this.giveawayResult = giveawayResult;
        this.actualPayAmount = actualPayAmount;
    }

    public Map<EventType, Integer> getBenefitResult() {
        return benefitResult;
    }

    public Map<Menu, Integer> getGiveawayResult() {
        return giveawayResult;
    }

    public int getActualPayAmount() {
        return actualPayAmount;
    }

    public final int getTotalBenefitAmount() {
        int totalBenefitAmount = 0;
        for (Integer benefitAmount : getBenefitResult().values()) {
            totalBenefitAmount += benefitAmount;
        }
        return totalBenefitAmount;
    }
}
