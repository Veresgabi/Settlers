package flowacademy.settlersstrategy.town;

import flowacademy.settlersstrategy.Player;

public abstract class Building extends Town {

    private String sign;
    private int rooms;
    // private String statName;

    public Building(int positionI, int positionJ, String color, int id) {
        super(positionI, positionJ, color, id);
    }

    public Building(int positionI, int positionJ, String color) {
        super(positionI, positionJ, color);
    }

    public Building(int positionI, int positionJ, String color, int id, int rooms) {
        super(positionI, positionJ, color, id);
        this.rooms = rooms;
    }

    public static int buildingCounter(Town town, String name) {
        int counter = 0;
        for (int i = 0; i < town.getBuildings().size(); i++) {
            if (town.getBuildings().get(i).getName().equals(name)) {
                counter++;
            }
        }
        return counter;
    }


    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }
}
