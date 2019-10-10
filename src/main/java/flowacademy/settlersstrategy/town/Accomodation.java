package flowacademy.settlersstrategy.town;

import flowacademy.settlersstrategy.Player;

public class Accomodation extends Building {

    private static int price = 100;
    private static String description = "2000 embert lehet itt elszállásolni.";
    private static String name2 = "Szállás";

    public Accomodation(int positionI, int positionJ, String color, int id) {
        super(positionI, positionJ, color, id);
        super.setSign("S");
        super.setName("Szállás");
        super.setRooms(2000);
        super.setFrontName("Accomodation");
    }


    public static String getName2() {
        return name2;
    }

    public static void setName2() {
        Accomodation.name2 = "Szállás";
    }

    public static int getPrice() {
        return price;
    }

    public static void setPrice() {
        Accomodation.price = 100;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription() {
        Accomodation.description = "2000 embert lehet itt elszállásolni.";
    }


    public static int setId(Town town) {
        int counter = 0;
        for (int i = 0; i < town.buildings.size(); i++) {
            if (town.buildings.get(i) instanceof Accomodation) {
                counter++;
            }
        }
        return counter + 1;
    }



    @Override
    public String toString() {
        return getSign() + getId();
    }
}

