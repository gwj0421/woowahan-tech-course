package christmas.view;

public enum ErrorMessage {
    SPACE_OR_EMPTY_MESSAGE("[ERROR] 공백 혹은 빈 문자가 입력되었습니다. 다시 입력해 주세요."),
    NULL_MESSAGE("[ERROR] Null이 입력되었습니다. 다시 입력해 주세요."),
    DATE_ERROR_MESSAGE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    MENU_ERROR_MESSAGE("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    DEVELOP_ERROR_MESSAGE("[ERROR] 코드 에러. 잘못된 파라미터를 입력받았습니다.");
    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
