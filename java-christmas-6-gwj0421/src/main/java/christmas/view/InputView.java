package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.calender.Date;
import christmas.domain.menu.OrderedMenu;
import christmas.util.ConvertUtil;
import christmas.validation.ViewValidation;

import static christmas.config.AppConstant.TARGET_MONTH;

public class InputView {
    private static final String GREETING_MESSAGE = "안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.\n";
    private static final String REQUEST_VISIT_DATE_MESSAGE = "%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)\n";
    private static final String ORDER_MENU_AND_CNT_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private InputView() {
    }

    public static void helloCustomer() {
        System.out.printf(GREETING_MESSAGE, TARGET_MONTH);
    }

    public static Date inputVisitDate() {
        System.out.printf(REQUEST_VISIT_DATE_MESSAGE, TARGET_MONTH);
        return readDateWhileVerifying();
    }

    private static Date readDateWhileVerifying() {
        try {
            String inputContent = Console.readLine().trim();
            ViewValidation.validateEmptyOrSpaceOrNull(inputContent);
            return new Date(TARGET_MONTH, ConvertUtil.convertString2Int(inputContent));
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readDateWhileVerifying();
        }
    }

    public static OrderedMenu inputOrderMenuAndCnt() {
        System.out.println(ORDER_MENU_AND_CNT_MESSAGE);
        return readOrderWhileVerifying();
    }

    private static OrderedMenu readOrderWhileVerifying() {
        try {
            String inputContent = Console.readLine().trim();
            ViewValidation.validateEmptyOrSpaceOrNull(inputContent);
            return new OrderedMenu(ConvertUtil.convertString2Map(inputContent));
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readOrderWhileVerifying();
        }
    }
}
