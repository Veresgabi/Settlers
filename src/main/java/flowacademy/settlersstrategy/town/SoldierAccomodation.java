package flowacademy.settlersstrategy.town;

public class SoldierAccomodation extends Building {

    private static int price = 150;
    private static String name2 = "Katonaszállás";
    private static String description = "100 katonát lehet benne elszállásolni.";

    public SoldierAccomodation(int positionI, int positionJ, String color, int id) {
        super(positionI, positionJ, color, id);
        super.setSign("K");
        super.setName("Katonaszállás");
        super.setRooms(100);
    }

    public static int setId(Town town) {
        int counter = 0;
        for (int i = 0; i < town.buildings.size(); i++) {
            if (town.buildings.get(i) instanceof SoldierAccomodation) {
                counter++;
            }
        }
        return counter + 1;
    }

    public static int getPrice() {
        return price;
    }

    public static void setPrice() {
        SoldierAccomodation.price = 150;
    }

    public static String getName2() {
        return name2;
    }

    public static void setName2() {
        SoldierAccomodation.name2 = "Katonaszállás";
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription() {
        SoldierAccomodation.description = "100 katonát lehet benne elszállásolni.";
    }

    @Override
    public String toString() {
        return getSign() + getId();
    }
}
