package flowacademy.settlersstrategy.town;

import flowacademy.settlersstrategy.Player;

public class TrainingGround extends Building {

    private static int price = 400;
    private static String name2 = "Kiképző hely";
    private static String description = "A katonák támadóerejét megduplázza. Előfeltétele a Fegyverkovács műhely és a Kaszárnya megléte.";

    public TrainingGround(int positionI, int positionJ, String color, int id) {
        super(positionI, positionJ, color, id);
        super.setSign("KH");
        super.setName("Kiképző hely");
        super.setFrontName("Training");
    }

    public static int getPrice() {
        return price;
    }

    public static void setPrice() {
        TrainingGround.price = 400;
    }

    public static String getName2() {
        return name2;
    }

    public static void setName2() {
        TrainingGround.name2 = "Kiképző hely";
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription() {
        TrainingGround.description = "A katonák erejéhez hozzáadja az eredeti erőértéküket. Előfeltétele a Fegyverkovács műhely és a Kaszárnya megléte.";
    }

    public static int setId(Town town) {
        int counter = 0;
        for (int i = 0; i < town.buildings.size(); i++) {
            if (town.buildings.get(i) instanceof TrainingGround) {
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
