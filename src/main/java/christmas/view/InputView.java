package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Date;
import christmas.util.ConvertUtil;
import christmas.validation.ViewValidation;

import static christmas.config.AppConstant.TARGET_MONTH;

public class InputView {
    private static final String GREETING_MESSAGE = "안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.\n";
    private static final String REQUEST_VISIT_DATE_MESSAGE = "%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)\n";

    private InputView() {
    }

    public static void helloCustomer() {
        System.out.printf(GREETING_MESSAGE, TARGET_MONTH.getNum());
    }

    public static Date inputVisitDate() {
        System.out.printf(REQUEST_VISIT_DATE_MESSAGE, TARGET_MONTH.getNum());
        return readDateWhileVerifying();
    }

    private static Date readDateWhileVerifying() {
        try {
            String inputContent = Console.readLine();
            ViewValidation.validateNull(inputContent);
            ViewValidation.validateEmptyOrSpace(inputContent);
            return new Date(TARGET_MONTH.getNum(), ConvertUtil.string2Int(inputContent));
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readDateWhileVerifying();
        }
    }
}
