package christmas.util;

import java.util.Map;

public class MapUtil {
    private MapUtil() {
    }

    public static final int calculateTotalBenefitAboutDiscount(final Map<?, Integer> discountResult) {
        int totalValue = 0;
        for (int totalDiscountAmount : discountResult.values()) {
            totalValue += totalDiscountAmount;
        }
        return totalValue;
    }
}
