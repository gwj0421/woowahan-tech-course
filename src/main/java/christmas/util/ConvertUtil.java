package christmas.util;

import christmas.view.ErrorMessage;

public class ConvertUtil {
    private ConvertUtil() {
    }

    public static int string2Int(String content) {
        try {
            return Integer.parseInt(content);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ErrorMessage.MISMATCH_NUMBER_FORMAT.getMessage());
        }
    }
}
