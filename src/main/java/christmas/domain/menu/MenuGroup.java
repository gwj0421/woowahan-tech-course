package christmas.domain.menu;

import java.util.Arrays;

public enum MenuGroup {
    APPETIZER(new Menu[]{Menu.MUSHROOM_SOUP,Menu.TAPAS,Menu.CAESAR_SALAD}),
    MAIN(new Menu[]{Menu.T_BONE_STEAK,Menu.BBQ_RIBS,Menu.SEAFOOD_PASTA,Menu.CHRISTMAS_PASTA}),
    DESERT(new Menu[]{Menu.CHOCOLATE_CAKE,Menu.ICE_CREAM}),
    BEVERAGE(new Menu[]{Menu.ZERO_COLA, Menu.RED_WINE, Menu.CHAMPAGNE}),
    EMPTY(new Menu[]{});
    private final  Menu[] menus;

    MenuGroup(Menu[] menus) {
        this.menus = menus;
    }

    public Menu[] getMenus() {
        return menus;
    }

    public static MenuGroup hasMenu(String searchTarget) {
        return Arrays.stream(values())
                .filter(group -> searchMenuInGroup(group,searchTarget))
                .findAny()
                .orElse(MenuGroup.EMPTY);
    }

    private static boolean searchMenuInGroup(MenuGroup group, String searchTarget) {
        return Arrays.stream(group.getMenus())
                .map(Menu::getName)
                .anyMatch(subMenuName -> subMenuName.equals(searchTarget));
    }
}
