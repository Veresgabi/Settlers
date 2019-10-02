package flowacademy.settlersstrategy.town;

import flowacademy.settlersstrategy.Cell;
import flowacademy.settlersstrategy.Player;
import flowacademy.settlersstrategy.army.Army;
import flowacademy.settlersstrategy.board.Board;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Town extends Cell {

    public List<Building> buildings = new ArrayList<>();
    public List<Army> armies = new ArrayList<>();
    private int gold;
    private int population;
    private Townhall townhall;
    private Player player;
    private String name;
    private int tax;
    private int bankIncome = 0;
    private int populationIncrease;
    private int populationDecrease;
    private boolean isBarrack = true;           // Teszt esetében legyen true, amúgy no initialization!!
    private boolean isBlackshmith = true;       // Teszt esetében legyen true, amúgy no initialization!!
    private boolean isTrainingG;
    private int allRooms = 0;
    private int allSoldierRooms = 150;           // Teszt esetében legyen 150, amúgy no initialization!!
    private int soldiers = 0;
    private int upgradedSoldiers = 0;
    private static int soldiersPrice = 100;
    private boolean isArmyInTown = false;
    // private char sign;

    public Town(int positionI, int positionJ, String color, Townhall townhall, String name, Player player) {
        super(positionI, positionJ, color);
        this.gold = 1000;
        this.population = (int) (Math.random()*(1000 - 500))+ 500;
        this.townhall = townhall;
        this.buildings.add(townhall);
        this.name = name;
        this.player = player;
    }

    public Town(int positionI, int positionJ, String color, String name) {
        super(positionI, positionJ, color);
        this.gold = 1000;
        this.population = (int) (Math.random()*(1000 - 500))+ 500;
        this.name = name;
    }

    public Town(int positionI, int positionJ, String color, int id) {
        super(positionI, positionJ, color, id);
        this.gold = 1000;
        this.population = (int) (Math.random()*(1000 - 500))+ 500;
        this.name = name;
    }


    public Town(int positionI, int positionJ, String color) {
        super(positionI, positionJ, color);
        this.gold = 1000;
        this.population = (int) (Math.random()*(1000 - 500))+ 500;
    }

    public boolean isArmyInTown() {
        return isArmyInTown;
    }

    public void setArmyInTown(boolean armyInTown) {
        isArmyInTown = armyInTown;
    }

    public class HorBorder extends Cell {

        private String horBord;
        private Town town;

        public HorBorder(int positionI, int positionJ, String color) {
            super(positionI, positionJ, color);
            this.horBord = "- ";
        }

        @Override
        public String toString() {
            return horBord;
        }
    }

    public class HorBorderDown extends Cell {

        private String horBordDown;

        public HorBorderDown(int positionI, int positionJ, String color) {
            super(positionI, positionJ, color);
            this.horBordDown = "_ ";
        }

        @Override
        public String toString() {
            return horBordDown;
        }
    }

    public class VertBorder extends Cell {

        private String verticBord;

        public VertBorder(int positionI, int positionJ, String color) {
            super(positionI, positionJ, color);
            this.verticBord = "| ";
        }

        @Override
        public String toString() {
            return verticBord;
        }
    }

    public class NoElement extends Cell {

        private String noElem;

        public NoElement(int positionI, int positionJ, String color) {
            super(positionI, positionJ, color);
            this.noElem = "  ";
        }

        @Override
        public String toString() {
            return noElem;
        }
    }

    public Cell[][] putTownElements(Cell[][] board, String color) {
        int indI = -1;          // Később ebből NullPointException lehet!!!!!!!!!!!!!!!!!!!
        int indJ = -1;          // Később ebből NullPointException lehet!!!!!!!!!!!!!!!!!!!
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] instanceof Town && board[i][j].getColor().equals(color)) {
                    indI = i;
                    indJ = j;
                }
            }
        }
        System.out.println(indI + ", " + indJ);
        for (int i = 0; i < 11; i++) {
            board[indI - 5 + i][indJ - 5] = new VertBorder(indI - 5 + i, indJ - 5, color);
            board[indI + 5][indJ - 5 + i] = new HorBorderDown(indI + 5, indJ - 5 + i, color);
            board[indI - 5 + i][indJ + 5] = new VertBorder(indI - 5 + i, indJ + 5, color);
            board[indI - 5][indJ - 5 + i] = new HorBorder(indI - 5, indJ - 5 + i, color);
        }
        for (int i = indI - 4; i < indI + 5; i++) {
            for (int j = indJ - 4; j < indJ + 5; j++) {
                if (board[i][j] instanceof Town == false) {
                    board[i][j] = new NoElement(i, j, color);
                }
            }
        }
        return board;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public int getAllRooms() {
        return allRooms;
    }

    public void setAllRooms() {
        int accomodCounter = 0;
        for (int i = 0; i < getBuildings().size(); i++) {
            if (getBuildings().get(i) instanceof Accomodation || getBuildings().get(i) instanceof Townhall) {
                accomodCounter += getBuildings().get(i).getRooms();
            }
        }
        this.allRooms = accomodCounter;
    }

    public int getAllSoldierRooms() {
        return allSoldierRooms;
    }

    public void setAllSoldierRooms() {
        int accomodCounter = 0;
        for (int i = 0; i < getBuildings().size(); i++) {
            if (getBuildings().get(i) instanceof SoldierAccomodation) {
                accomodCounter += getBuildings().get(i).getRooms();
            }
        }
        this.allSoldierRooms = accomodCounter;
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

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getBankIncome() {
        return bankIncome;
    }

    public void setBankIncome(int bankIncome) {
        this.bankIncome = bankIncome;
    }

    public int getPopulationIncrease() {
        return populationIncrease;
    }

    public void setPopulationIncrease(int populationIncrease) {
        this.populationIncrease = populationIncrease;
    }

    public int getPopulationDecrease() {
        return populationDecrease;
    }

    public void setPopulationDecrease(int populationDecrease) {
        this.populationDecrease = populationDecrease;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    /* public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    } */

    public Townhall getTownhall() {
        return townhall;
    }

    public void setTownhall(Townhall townhall) {
        this.townhall = townhall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBarrack() {
        return isBarrack;
    }

    public void setBarrack(boolean barrack) {
        isBarrack = barrack;
    }

    public boolean isBlackshmith() {
        return isBlackshmith;
    }

    public void setBlackshmith(boolean blackshmith) {
        isBlackshmith = blackshmith;
    }

    public boolean isTrainingG() {
        return isTrainingG;
    }

    public void setTrainingG(boolean trainingG) {
        isTrainingG = trainingG;
    }

    public static int getSoldiersPrice() {
        return soldiersPrice;
    }

    public static void setSoldiersPrice(int soldiersPrice) {
        Town.soldiersPrice = soldiersPrice;
    }

    @Override
    public String toString() {
        return "" + townhall.getColorSign();
    }

}
