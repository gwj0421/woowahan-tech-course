package christmas.config;

public enum AppConstant {
    TARGET_MONTH(12);

    private final int num;

    AppConstant(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
