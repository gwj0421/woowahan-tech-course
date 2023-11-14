package christmas.controller;

import christmas.domain.badge.Badge;
import christmas.domain.calender.Date;
import christmas.domain.calender.EventCalender;
import christmas.domain.event.PreviewDiscountMachine;
import christmas.domain.event.PreviewResult;
import christmas.domain.menu.OrderedMenu;
import christmas.view.InputView;
import christmas.view.OutputView;

import static christmas.config.AppConstant.STAR_POS;
import static christmas.config.AppConstant.TARGET_MONTH;

public class EventPlannerController {
    private final Date visitDay;
    private final OrderedMenu orderedMenu;

    public EventPlannerController() {
        InputView.helloCustomer();

        this.visitDay = inputDay();
        this.orderedMenu = inputOrder();
    }

    public final void run() {
        PreviewDiscountMachine previewMachine = makePreviewMachine(visitDay, orderedMenu);

        showPreview(previewMachine);
    }

    private Date inputDay() {
        return InputView.inputVisitDate();
    }

    private OrderedMenu inputOrder() {
        return InputView.inputOrderMenuAndCnt();
    }

    private PreviewDiscountMachine makePreviewMachine(final Date visitDay, final OrderedMenu orderedMenu) {
        return new PreviewDiscountMachine(new EventCalender(TARGET_MONTH, STAR_POS), orderedMenu, visitDay.getDay());
    }

    private void showPreview(final PreviewDiscountMachine previewMachine) {
        showOrderAndPriceBeforeApplyEvent(previewMachine);

        showAppliedEventBenefits(previewMachine.getPreviewResult());

        OutputView.showBadge(Badge.giveBadge(previewMachine.getPreviewResult().getTotalBenefitAmount()).getDesc());
    }

    private void showOrderAndPriceBeforeApplyEvent(final PreviewDiscountMachine previewMachine) {
        OutputView.showPreviewEventBenefits(previewMachine.getVisitDay());
        OutputView.showOrderMenu(previewMachine.getOrderedMenu().getOrder());
        OutputView.showTotalPriceBeforeDiscount(previewMachine.getOrderedMenu().getTotalPrice());
    }

    private void showAppliedEventBenefits(final PreviewResult previewResult) {
        OutputView.showGiveawayMenu(previewResult.getGiveawayResult());
        OutputView.showDiscountDetails(previewResult.getBenefitResult());
        OutputView.showTotalBenefitAmount(previewResult.getTotalBenefitAmount());
        OutputView.showActualPaymentAmount(previewResult.getActualPayAmount());
    }
}
