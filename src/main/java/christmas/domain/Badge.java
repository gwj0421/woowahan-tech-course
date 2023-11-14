package christmas.domain;

public enum Badge {
    SANTA("산타",20000),
    TREE("트리",10000),
    STAR("별",5000),
    NORMAL("없음",0);

    private final String desc;
    private final int condition;

    Badge(final String desc, final int condition) {
        this.desc = desc;
        this.condition = condition;
    }

    public String getDesc() {
        return desc;
    }

    public int getCondition() {
        return condition;
    }

    public static Badge giveBadge(final int totalBenefitAmount) {
        for (Badge badge : values()) {
            if (badge.getCondition() <= totalBenefitAmount) {
                return badge;
            }
        }
        return Badge.NORMAL;
    }
}
