package flowacademy.settlersstrategy.army;

import flowacademy.settlersstrategy.Cell;
import flowacademy.settlersstrategy.Game;
import flowacademy.settlersstrategy.Player;
import flowacademy.settlersstrategy.board.Background;
import flowacademy.settlersstrategy.board.Board;
import flowacademy.settlersstrategy.town.Blacksmith;
import flowacademy.settlersstrategy.town.Town;

import java.util.Scanner;

public class Army extends Cell {

    private int soldiers;
    private int upgradedSoldiers;
    private String colorSign;
    private int steps;
    private int actionPoint = 0;


    public Army(int PositionI, int PositionJ, String color, int id, int soldiers, int upgradedSoldiers, String colorSingn) {
        super(PositionI, PositionJ, color, id);
        this.soldiers = soldiers;
        this.upgradedSoldiers = upgradedSoldiers;
        this.colorSign = colorSingn;
        this.steps = 10;            // Teszt esetében legyen 60!!
    }

    public static String setColorSign(Player player) {
        StringBuilder colorStr = new StringBuilder(player.getColor());
        return colorStr.charAt(0) + "" + (player.getArmies().size() + 1) + "";
    }

    public String getColorSign() {
        return colorSign;
    }

    public int getSoldiers() {
        return soldiers;
    }

    public void setSoldiers(int soldiers) {
        this.soldiers = soldiers;
    }

    public int getUpgradedSoldiers() {
        return upgradedSoldiers;
    }

    public void setUpgradedSoldiers(int upgradedSoldiers) {
        this.upgradedSoldiers = upgradedSoldiers;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps() {
        this.steps = 10;
    }           // Teszt esetében legyen 60!!

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getActionPoint() {
        return actionPoint;
    }

    public void setActionPoint(int actionPoint) {
        this.actionPoint = actionPoint;
    }

    /* public static int setId(Player player) {
        int counter = 0;
        for (int i = 0; i < player.getArmies().size(); i++) {
            if (player.getArmies().get(i) instanceof Army) {
                counter++;
            }
        }
        return counter + 1;
    } */

    @Override
    public String toString() {
        return colorSign;
    }

}