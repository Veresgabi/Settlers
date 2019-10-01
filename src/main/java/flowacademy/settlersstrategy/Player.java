package flowacademy.settlersstrategy;

import flowacademy.settlersstrategy.army.Army;
import flowacademy.settlersstrategy.board.Background;
import flowacademy.settlersstrategy.board.Board;
import flowacademy.settlersstrategy.town.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Player {

    private String name;
    private String color;
    private int gold;
    private int actionPoint;
    private boolean turn;
    private int turnCount = 1;

    private List<Town> towns = new ArrayList<>();
    private List<Army> armies = new ArrayList<>();

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
        this.actionPoint = 3;
        this.turn = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getGold() {
        return gold;
    }

    public void setGold() {
        int totalGold = 0;
        if (towns != null) {
            for (int i = 0; i < towns.size(); i++) {
                totalGold += towns.get(i).getGold();
            }
        }
        this.gold = totalGold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getActionPoint() {
        return actionPoint;
    }

    public void setActionPoint(int actionPoint) {
        this.actionPoint = actionPoint;
    }

    public void setActionPoint() {
        this.actionPoint = 3;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public List<Town> getTowns() {
        return towns;
    }

    public void setTowns(List<Town> towns) {
        this.towns = towns;
    }

    public List<Army> getArmies() {
        return armies;
    }

    public void setArmies(List<Army> armies) {
        this.armies = armies;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    @Override
    public String toString() {
        return turnCount + ". hét, " + name + ", Szín: " + color +
                ", Városok: " + towns.size() +
                ", Hadseregek: " + armies.size() +
                ", Összes arany: " + gold +
                ", Tevékenység pont: " + actionPoint;
    }

    public static Cell[][] startGame1(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(player.name + ": Jelöld ki a városod középpontját! A kordináták értékének 4-nél nagyobbnak és 95-nél kisebbeknek kell lenniük (pl.: 5 94) ");

        String[] townhallPointReader = scanner.nextLine().split("[ ]");

        if (validTownCommandForm(townhallPointReader) == false) {
            startGame1(player);
        }

        if (validTownCommandForm(townhallPointReader) == true) {

            if (validTownCommandValue(townhallPointReader) == true) {
                createTown(townhallPointReader, player);
            }
            else {
                startGame1(player);
            }
        }
        return Board.board;
    }


    public static boolean validTownCommandForm(String[] townhallPointReader) {

        StringBuilder townhallPoint1;  // A beolvasott szöveg 1. eleme
        StringBuilder townhallPoint2;    // A beolvasott szöveg 2. eleme

        String a1;
        String a2;
        String b1;
        String b2;

        String noEnoughParam = "Nem adtál meg elegendő paramétert!";
        String giveNumbers = "Kérlek, csak számokat adj meg!";
        String noAddLongChar = "Két karakternél hosszabb paramétert nem adhatsz meg!";

        if (townhallPointReader.length < 2) {
            System.out.println(noEnoughParam);
            return false;
        }
        if (townhallPointReader.length > 1) {
            townhallPoint1 = new StringBuilder(townhallPointReader[0]);   // A beolvasott szöveg 1. eleme
            townhallPoint2 = new StringBuilder(townhallPointReader[1]);    // A beolvasott szöveg 2. eleme

            if (townhallPoint1.length() == 1 && townhallPoint2.length() == 1) {   // Ha az 1. és a 2. karakterlánc is 1 jegyű
                a1 = "" + townhallPoint1.charAt(0);
                b1 = "" + townhallPoint2.charAt(0);

                if (a1.matches("[0-9]") && b1.matches("[0-9]")) {
                    return true;
                }
                if (!a1.matches("[0-9]") || !b1.matches("[0-9]")) {
                    System.out.println(giveNumbers);
                    return false;
                }
            }
            if (townhallPoint1.length() == 2 && townhallPoint2.length() == 2) {    // Ha az 1. és a 2. karakterlánc is 2 jegyű
                a1 = "" + townhallPoint1.charAt(0);
                a2 = "" + townhallPoint1.charAt(1);
                b1 = "" + townhallPoint2.charAt(0);
                b2 = "" + townhallPoint2.charAt(1);

                if (a1.matches("[0-9]") && a2.matches("[0-9]") && b1.matches("[0-9]") && b2.matches("[0-9]")) {
                    return true;
                }
                if (!a1.matches("[0-9]") || !a2.matches("[0-9]") || !b1.matches("[0-9]") || !b2.matches("[0-9]")) {
                    System.out.println(giveNumbers);
                    return false;
                }
            }
            if (townhallPoint1.length() == 2 && townhallPoint2.length() == 1) {     // Ha az 1. karakterlánc 2 jegyű és a 2. karakterlánc 1 jegyű
                a1 = "" + townhallPoint1.charAt(0);
                a2 = "" + townhallPoint1.charAt(1);
                b1 = "" + townhallPoint2.charAt(0);

                if (a1.matches("[0-9]") && a2.matches("[0-9]") && b1.matches("[0-9]")) {
                    return true;
                }
                if (!a1.matches("[0-9]") || !a2.matches("[0-9]") || !b1.matches("[0-9]")) {
                    System.out.println(giveNumbers);
                    return false;
                }
            }
            if (townhallPoint1.length() == 1 && townhallPoint2.length() == 2) {     // Ha az 1. karakterlánc 1 jegyű és a 2. karakterlánc 2 jegyű
                a1 = "" + townhallPoint1.charAt(0);
                b1 = "" + townhallPoint2.charAt(0);
                b2 = "" + townhallPoint2.charAt(1);

                if (a1.matches("[0-9]") && b1.matches("[0-9]") && b2.matches("[0-9]")) {
                    return true;

                }
                if (!a1.matches("[0-9]") || !b1.matches("[0-9]") || !b2.matches("[0-9]")) {
                    System.out.println(giveNumbers);
                    return false;
                }
            }

            if (townhallPoint1.length() > 2 || townhallPoint2.length() > 2) {    // Ha az 1. vagy a 2. karakterlánc 2-nél több jegyű
                System.out.println(noAddLongChar);
                return false;
            }
        }
        return false;
    }


    public static boolean validTownCommandValue(String[] townhallPointReader) {
        String noValidParams1 = "Nem megfelelő paramétereket adtál meg, mert a városhatár a pályán kívülre esne.";
        String noValidParams2 = "Nem megfelelő paramétereket adtál meg, mert az ellenséges városközpont és a Te városközpontod között nincs 30 egységnyi távolság.";
        boolean isThereOtherTown = false;

        int indI = Integer.parseInt(townhallPointReader[0]);
        int indJ = Integer.parseInt(townhallPointReader[1]);

        for (int i = indI + 1; i < Board.board.length && i < indI + 31; i++) {
            for (int j = indJ + 1; j < Board.board[i].length && j < indJ + 31; j++) {
                if (Board.board[i][j] instanceof Town) {
                    isThereOtherTown = true;
                } /* if (Board.board[i][j] instanceof Background) {
                    Board.board[i][j] = new Background('0');
                } */
            }
        }
        for (int i = indI + 1; i < Board.board.length && i < indI + 31; i++) {
            for (int j = indJ; j > -1 && j > indJ - 31; j--) {
                if (Board.board[i][j] instanceof Town) {
                    isThereOtherTown = true;
                } /* if (Board.board[i][j] instanceof Background) {
                    Board.board[i][j] = new Background('0');
                } */
            }
        }
        for (int i = indI; i > -1 && i > indI - 31; i--) {
            for (int j = indJ + 1; j < Board.board[i].length && j < indJ + 31; j++) {
                if (Board.board[i][j] instanceof Town) {
                    isThereOtherTown = true;
                } /* if (Board.board[i][j] instanceof Background) {
                    Board.board[i][j] = new Background('0');
                } */
            }
        }
        for (int i = indI; i > -1 && i > indI - 31; i--) {
            for (int j = indJ; j > -1 && j > indJ - 31; j--) {
                if (Board.board[i][j] instanceof Town) {
                    isThereOtherTown = true;
                } /* if (Board.board[i][j] instanceof Background) {
                    Board.board[i][j] = new Background('0');
                } */
            }
        }

        if (Integer.parseInt(townhallPointReader[0]) > 4 &&
                Integer.parseInt(townhallPointReader[0]) < Board.board.length - 5 &&
                Integer.parseInt(townhallPointReader[1]) > 4 &&
                Integer.parseInt(townhallPointReader[1]) < Board.board.length - 5 &&
                isThereOtherTown == false) {

            return true;
        }
        if (Integer.parseInt(townhallPointReader[0]) <= 4 ||
                Integer.parseInt(townhallPointReader[0]) >= Board.board.length - 5 ||
                Integer.parseInt(townhallPointReader[1]) <= 4 ||
                Integer.parseInt(townhallPointReader[1]) >= Board.board.length - 5) {
            System.out.println(noValidParams1);
            return false;
        }
        if (isThereOtherTown == true) {
            System.out.println(noValidParams2);
        }
        return false;
    }


    public static void showInsertableArea() {
        List<Integer> townParams = new ArrayList<>();
        int indI;
        int indJ;

        // A pálya szélső 5 karakteres négyzet alakú sávjának megjelölése
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < Board.board[i].length; j++) {
                if (Board.board[i][j] instanceof Background) {
                    Board.board[i][j] = new Background("* ");
                }
            }
        }
        for (int i = Board.board.length - 5; i < Board.board.length; i++) {
            for (int j = 0; j < Board.board[i].length; j++) {
                if (Board.board[i][j]instanceof Background) {
                    Board.board[i][j] = new Background("* ");
                }
            }
        }
        for (int i = 5; i < Board.board.length - 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (Board.board[i][j] instanceof Background) {
                    Board.board[i][j] = new Background("* ");
                }
            }
        }
        for (int i = 5; i < Board.board.length - 5; i++) {
            for (int j = Board.board[i].length - 5; j < Board.board[i].length; j++) {
                if (Board.board[i][j] instanceof Background) {
                    Board.board[i][j] = new Background("* ");
                }
            }
        }

        // A meglévő városközpontok alapján a nem választható területek megjelölése
        for (int i = 0; i < Board.board.length; i++) {
            for (int j = 0; j < Board.board[i].length; j++) {
                if (Board.board[i][j] instanceof Town) {
                    townParams.add(i);
                    townParams.add(j);
                }
            }
        }

        for (int k = 0; k < townParams.size(); k = k + 2) {
            indI = townParams.get(k);
            indJ = townParams.get(k + 1);
            for (int i = indI + 1; i < Board.board.length && i < indI + 31; i++) {
                for (int j = indJ + 1; j < Board.board[i].length && j < indJ + 31; j++) {
                    if (Board.board[i][j] instanceof Background) {
                        Board.board[i][j] = new Background("* ");
                    }
                }
            }
            for (int i = indI + 1; i < Board.board.length && i < indI + 31; i++) {
                for (int j = indJ; j > -1 && j > indJ - 31; j--) {
                    if (Board.board[i][j] instanceof Background) {
                        Board.board[i][j] = new Background("* ");
                    }
                }
            }
            for (int i = indI; i > -1 && i > indI - 31; i--) {
                for (int j = indJ + 1; j < Board.board[i].length && j < indJ + 31; j++) {
                    if (Board.board[i][j] instanceof Background) {
                        Board.board[i][j] = new Background("* ");
                    }
                }
            }
            for (int i = indI; i > -1 && i > indI - 31; i--) {
                for (int j = indJ; j > -1 && j > indJ - 31; j--) {
                    if (Board.board[i][j] instanceof Background) {
                        Board.board[i][j] = new Background("* ");
                    }
                }
            }
        }
    }

    public static void removeInsertableArea() {
        for (int i = 0; i < Board.board.length; i++) {
            for (int j = 0; j < Board.board[i].length; j++) {
                if (Board.board[i][j] instanceof Background) {
                    Board.board[i][j] = new Background(". ");
                }
            }
        }
    }


    public static void createTown(String[] townhallPointReader, Player player) {
        Town town = new Town(Integer.parseInt(townhallPointReader[0]), Integer.parseInt(townhallPointReader[1]), player.color,
                new Townhall(Integer.parseInt(townhallPointReader[0]), Integer.parseInt(townhallPointReader[1]), player.color), player.color + "város", player);
        player.towns.add(town);
        town.setAllRooms();
        // player.setGold();
        System.out.println(Integer.parseInt(townhallPointReader[0]) + " " + Integer.parseInt(townhallPointReader[1]));
        Cell.putCellToBoard(Board.board, town);
        town.putTownElements(Board.board, player.color);
    }

}
