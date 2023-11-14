package christmas.view;

import christmas.domain.calender.Date;
import christmas.domain.event.EventType;
import christmas.domain.menu.Menu;

import java.util.Map;

import static christmas.config.AppConstant.TARGET_MONTH;

public class OutputView {
    private static final String PREVIEW_EVENT_BENEFITS_FORMAT_MESSAGE = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String SHOW_ORDER_MENU_MESSAGE = "\n<주문 메뉴>";
    private static final String SHOW_TOTAL_PRICE_BEFORE_DISCOUNT_MESSAGE = "\n<할인 전 총주문 금액>";
    private static final String SHOW_GIVEAWAY_MESSAGE = "\n<증정 메뉴>";
    public static final String SHOW_DISCOUNT_DETAILS_MESSAGE = "\n<혜택 내역>";
    public static final String SHOW_TOTAL_BENEFIT_AMOUNT_MESSAGE = "\n<총혜택 금액>";
    public static final String SHOW_EXPECTED_PAYMENT_AMOUNT_MESSAGE = "\n<할인 후 예상 결제 금액>";
    public static final String SHOW_EVENT_BADGE_MESSAGE = "\n<%d월 이벤트 배지>\n";
    private static final String ORDER_MENU_FORMAT = "%s %d개\n";
    private static final String GIVEAWAY_FORMAT = "%s %d개\n";
    public static final String DISCOUNT_DETAILS_FORMAT = "%s: -%s\n";
    public static final String TOTAL_BENEFIT_AMOUNT_FORMAT = "-%s\n";
    public static final String WON_FORMAT = "%,d원";
    public static final String EMPTY_MESSAGE = "없음";

    private OutputView() {
    }

    public static final void showPreviewEventBenefits(final Date date) {
        System.out.printf(PREVIEW_EVENT_BENEFITS_FORMAT_MESSAGE, date.getMonth(), date.getDay());
    }

    public static final void showOrderMenu(final Map<Menu, Integer> orderMenu) {
        System.out.println(SHOW_ORDER_MENU_MESSAGE);
        for (Map.Entry<Menu, Integer> order : orderMenu.entrySet()) {
            System.out.printf(ORDER_MENU_FORMAT, order.getKey().getName(), order.getValue());
        }
    }

    public static final void showTotalPriceBeforeDiscount(final int totalPriceBeforeDiscount) {
        System.out.println(SHOW_TOTAL_PRICE_BEFORE_DISCOUNT_MESSAGE);
        System.out.println(String.format(WON_FORMAT, totalPriceBeforeDiscount));
    }

    public static final void showGiveawayMenu(final Map<Menu, Integer> giveaway) {
        System.out.println(SHOW_GIVEAWAY_MESSAGE);
        if (!giveaway.isEmpty()) {
            for (Map.Entry<Menu, Integer> giveawayEntry : giveaway.entrySet()) {
                System.out.printf(GIVEAWAY_FORMAT, giveawayEntry.getKey().getName(), giveawayEntry.getValue());
            }
            return;
        }
        System.out.println(EMPTY_MESSAGE);
    }

    public static final void showDiscountDetails(final Map<EventType, Integer> totalBenefitDetail) {
        System.out.println(SHOW_DISCOUNT_DETAILS_MESSAGE);
        if (!totalBenefitDetail.isEmpty()) {
            for (Map.Entry<EventType, Integer> benefitEntry : totalBenefitDetail.entrySet()) {
                System.out.printf(DISCOUNT_DETAILS_FORMAT, benefitEntry.getKey().getDesc(), String.format(WON_FORMAT, benefitEntry.getValue()));
            }
            return;
        }
        System.out.println(EMPTY_MESSAGE);
    }

    public static final void showTotalBenefitAmount(final int totalBenefitAmount) {
        System.out.println(SHOW_TOTAL_BENEFIT_AMOUNT_MESSAGE);
        System.out.printf(TOTAL_BENEFIT_AMOUNT_FORMAT, String.format(WON_FORMAT, totalBenefitAmount));
    }

    public static final void showActualPaymentAmount(final int paymentAmount) {
        System.out.println(SHOW_EXPECTED_PAYMENT_AMOUNT_MESSAGE);
        System.out.println(String.format(WON_FORMAT, paymentAmount));
    }

    public static final void showBadge(final String badgeName) {
        System.out.printf(SHOW_EVENT_BADGE_MESSAGE, TARGET_MONTH);
        System.out.println(badgeName);
    }
}
