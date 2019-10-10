package flowacademy.settlersstrategy.town;

import flowacademy.settlersstrategy.Player;

public class Blacksmith extends Building {

    private static int price = 300;
    private static String name2 = "Fegyverkovács műhely";
    private static String description = "A Kaszárnya és a Kiképző hely építésének egyik feltétele.";

    public Blacksmith(int positionI, int positionJ, String color, int id) {
        super(positionI, positionJ, color, id);
        super.setSign("F ");
        super.setName("Fegyverkovács műhely");
        super.setFrontName("Blacksmith");
    }

    public static int getPrice() {
        return price;
    }

    public static void setPrice() {
        Blacksmith.price = 300;
    }

    public static String getName2() {
        return name2;
    }

    public static void setName2() {
        Blacksmith.name2 = "Fegyverkovács műhely";
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription() {
        Blacksmith.description = "A Kaszárnya és a Kiképző hely építésének egyik feltétele.";
    }

    public static int setId(Town town) {
        int counter = 0;
        for (int i = 0; i < town.buildings.size(); i++) {
            if (town.buildings.get(i) instanceof Blacksmith) {
                counter++;
            }
        }
        return counter + 1;
    }

    @Override
    public String toString() {
        return getSign();
    }
}
