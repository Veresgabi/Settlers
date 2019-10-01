package flowacademy.settlersstrategy.town;

public class Bank extends Building {

    private static int price = 700;
    private static String name2 = "Bank";
    private static String description = "Az egy körben (héten) befolyó arany mennyiséget 20%-kal megnöveli.";

    public Bank(int positionI, int positionJ, String color, int id) {
        super(positionI, positionJ, color, id);
        super.setSign("B");
        super.setName("Bank");
    }

    public static String getName2() {
        return name2;
    }

    public static void setName2() {
        Bank.name2 = "Bank";
    }

    public static int getPrice() {
        return price;
    }

    public static void setPrice() {
        Bank.price = 700;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription() {
        Bank.description = "Az egy körben (héten) befolyó arany mennyiséget 20%-kal megnöveli.";
    }

    public static int setId(Town town) {
        int counter = 0;
        for (int i = 0; i < town.buildings.size(); i++) {
            if (town.buildings.get(i) instanceof Bank) {
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


