package christmas.util;

import christmas.view.ErrorMessage;

import java.util.HashMap;
import java.util.Map;

import static christmas.validation.ViewValidation.*;

public class ConvertUtil {
    private ConvertUtil() {
    }

    public static int convertString2Int(String content) {
        try {
            return Integer.parseInt(content);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ErrorMessage.MISMATCH_NUMBER_FORMAT.getMessage());
        }
    }

    public static Map<String, Integer> convertString2Map(String contents) {
        try {
            Map<String, Integer> order = new HashMap<>();
            for (String content : contents.split(",")) {
                splitMenuAndCnt(content, order);
            }
            return order;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ErrorMessage.MISMATCH_NUMBER_FORMAT.getMessage());
        }
    }

    private static void splitMenuAndCnt(String content, Map<String, Integer> order) {
        validateOrderFormat(content);
        String[] menuAndCnt = content.split("-");
        String menu = menuAndCnt[0];
        int cnt = Integer.parseInt(menuAndCnt[1]);

        validateEmptyOrSpaceOrNull(menu);
        validateDuplicated(order, menu);
        validateNegativeOrZero(cnt);

        order.put(menu, cnt);
    }

    public static final Map<Menu, Integer> convertKeyString2Menu(final Map<String, Integer> order) {
        Map<Menu, Integer> result = new EnumMap<>(Menu.class);
        try {
            for (Map.Entry<String, Integer> orderEntry : order.entrySet()) {
                result.put(Menu.getMenuByMenuName(orderEntry.getKey()), orderEntry.getValue());
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR_MESSAGE.getMessage());
        }

        return result;
    }
}
