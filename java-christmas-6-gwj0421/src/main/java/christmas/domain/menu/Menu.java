package christmas.domain.menu;

import christmas.view.ErrorMessage;

import java.util.Arrays;

public enum Menu {
    // Appetizer
    MUSHROOM_SOUP("양송이수프", 6000),
    TAPAS("타파스", 5500),
    CAESAR_SALAD("시저샐러드", 8000),

    // Main Course
    T_BONE_STEAK("티본스테이크", 55000),
    BBQ_RIBS("바비큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000),

    // Dessert
    CHOCOLATE_CAKE("초코케이크", 15000),
    ICE_CREAM("아이스크림", 5000),

    // Beverage
    ZERO_COLA("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000);

    private final String name;
    private final int price;

    Menu(final String name, final int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public static Menu getMenuByMenuName(final String menuName) {
        return Arrays.stream(values())
                .filter(menu -> menu.getName().equals(menuName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.MENU_ERROR_MESSAGE.name()));
    }
}

