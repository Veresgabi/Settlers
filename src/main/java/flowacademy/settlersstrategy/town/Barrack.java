package flowacademy.settlersstrategy.town;

import flowacademy.settlersstrategy.Player;

public class Barrack extends Building {

    private static int price = 200;
    private static String name2 = "Kaszárnya";
    private static String description = "Segítségével lehet katonákat toborozni. Előfeltétele, hogy a város rendelkezzen Fegyverkovács műhellyel.";

    public Barrack(int positionI, int positionJ, String color, int id) {
        super(positionI, positionJ, color, id);
        super.setSign("KA");
        super.setName("Kaszárnya");
        super.setFrontName("Barrack");
    }


    public static String getName2() {
        return name2;
    }

    public static void setName2() {
        Barrack.name2 = "Kaszárnya";
    }

    public static int getPrice() {
        return price;
    }

    public static void setPrice() {
        Barrack.price = 200;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        Barrack.description = "Segítségével lehet katonákat toborozni. Előfeltétele, hogy a város rendelkezzen Fegyverkovács műhellyel.";
    }

    public static int setId(Town town) {
        int counter = 0;
        for (int i = 0; i < town.buildings.size(); i++) {
            if (town.buildings.get(i) instanceof Barrack) {
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
