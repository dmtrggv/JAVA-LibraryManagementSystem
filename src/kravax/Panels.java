package kravax;

abstract class Panels {

    private static boolean panelExists = false;

    public static boolean isPanelExists() {
        return panelExists;
    }

    static void setPanelExist(boolean status) {
        panelExists = status;
    }

}
