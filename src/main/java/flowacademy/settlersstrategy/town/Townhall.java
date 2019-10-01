package flowacademy.settlersstrategy.town;

import flowacademy.settlersstrategy.Player;

public class Townhall extends Building {

    private String colorSign;

    public Townhall(int positionI, int positionJ, String color) {
        super(positionI, positionJ, color);
        super.setSign("T ");
        super.setName("Városháza");
        super.setId(1);
        super.setRooms(2000);
        setColorSign();
    }

    public String getColorSign() {
        return colorSign;
    }

    public void setColorSign() {
        StringBuilder colorStr = new StringBuilder(super.getColor());
        this.colorSign = colorStr.charAt(0) + " ";
    }

    @Override
    public String toString() {
        return "\t" + this.getName() + "\n" + "\t" + "\t" + "Városházán lévő összes szálláshely: " + this.getRooms() + "\n" + "\t" + "\t" +
                "Városházán lévő szabad szálláshelyek: ";
    }

    public void setColorSign(String colorSign) {
        this.colorSign = colorSign;
    }


    // A túlcsordulást még le kell kezelni!!
    /* public void setFreeRooms(Player player) {
        for (int i = 0; i <  player.getTowns().size(); i++) {
            if (this.rooms - player.getTowns().get(i).getPopulation() >= 0) {
                this.freeRooms = this.rooms - player.getTowns().get(i).getPopulation();
            }
            if (this.rooms - player.getTowns().get(i).getPopulation() < 0) {
                this.freeRooms = 0;
            }
        }

    } */


}
