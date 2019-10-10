package flowacademy.settlersstrategy;

import flowacademy.settlersstrategy.army.Army;
import flowacademy.settlersstrategy.board.Background;
import flowacademy.settlersstrategy.board.Board;
import flowacademy.settlersstrategy.town.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    public List<Player> players = new ArrayList<>();
    int playerInd = 0;      // a Playereket tartalmazó tömb léptetésére szolgál a turn-öknél.


    public void mainMenu() {

        System.out.println("Az alábbi menüpontok közül választhatsz (pl.: a):");
        System.out.println("");
        System.out.println("a: Új játék indítása");
        // System.out.println("b: Játék betöltése");
        System.out.println("b: Kilépés a játékból");        // Demo esetében legyen b, amúgy c-re van állítva!!

        String noEnoughParam = "Nem adtál meg elegendő paramétert!";
        String tooMuchParams = "Túl sok paramétert adtál meg!";
        String invalidParams = "A megadott paraméterek nem megfelelőek.";

        Scanner scanner = new Scanner(System.in);

        String[] menuReader = scanner.nextLine().split("[ ]");


        if (menuReader.length == 0) {
            System.out.println(noEnoughParam);
            System.out.println("");
            mainMenu();
        }
        if (menuReader.length > 1) {
            System.out.println(tooMuchParams);
            System.out.println("");
            mainMenu();
        }
        if (menuReader.length == 1) {
            if (menuReader[0].equals("a")) {
                chooseNumPlayer();
            }
            if (menuReader[0].equals("c")) {
                System.out.println("Viszlát!");
                scanner.close();
            }
            if (!menuReader[0].equals("a") && !menuReader[0].equals("c")) {
                System.out.println(invalidParams);
                System.out.println("");
                mainMenu();
            }
        }
    }

    private void chooseNumPlayer() {
        System.out.println("Az alábbi menüpontok közül választhatsz (pl.: a):");
        System.out.println("");
        System.out.println("");
        System.out.println("Új játék indítása");
        System.out.println("");
        System.out.println("a: 2 játékossal");
        System.out.println("b: 3 játékossal");
        System.out.println("c: 4 játékossal");
        System.out.println("");
        System.out.println("d: Kilépés a játékból");

        String noEnoughParam = "Nem adtál meg elegendő paramétert!";
        String tooMuchParams = "Túl sok paramétert adtál meg!";
        String invalidParams = "A megadott paraméterek nem megfelelőek.";

        Scanner scanner = new Scanner(System.in);

        String[] menuReader = scanner.nextLine().split("[ ]");

        if (menuReader.length == 0) {
            System.out.println(noEnoughParam);
            System.out.println("");
            mainMenu();
        }
        if (menuReader.length > 1) {
            System.out.println(tooMuchParams);
            System.out.println("");
            mainMenu();
        }
        if (menuReader.length == 1) {
            if (menuReader[0].equals("a") || menuReader[0].equals("b") || menuReader[0].equals("c")) {
                generatePlayer(menuReader[0]);
            }
            if (menuReader[0].equals("d")) {
                System.out.println("Viszlát!");
                scanner.close();
            }
            if (!menuReader[0].equals("a") && !menuReader[0].equals("b") && !menuReader[0].equals("c") && !menuReader[0].equals("d")) {
                System.out.println(invalidParams);
                System.out.println("");
                chooseNumPlayer();
            }
        }
    }

    private void generatePlayer(String choosedMenu) {
        Board.fillBoardBg(Board.board);

        Player player1 = new Player("1. Játékos", "Kék");
        players.add(player1);

        Player player2 = new Player("2. Játékos", "Piros");
        players.add(player2);

        Player player3 = null;
        Player player4 = null;

        if (choosedMenu.equals("b")) {
            player3 = new Player("3. Játékos", "Zöld");
            players.add(player3);

        }
        if (choosedMenu.equals("c")) {
            player3 = new Player("3. Játékos", "Zöld");
            players.add(player3);

            player4 = new Player("4. Játékos", "Sárga");
            players.add(player4);
        }

        Player.showInsertableArea();
        Board.printBoard(Board.board);

        // player1 TownHall kijelölés
        Player.startGame1(player1);
        Player.showInsertableArea();
        printGame(player1);

        // player2 TownHall kijelölés
        Player.startGame1(player2);
        if (player3 == null && player4 == null) {
            Player.removeInsertableArea();
        }
        if (player3 != null) {
            Player.showInsertableArea();
            printGame(player2);
        }

        // player3 TownHall kijelölés
        if (player3 != null) {
            Player.startGame1(player3);
            if (player4 == null) {
                Player.removeInsertableArea();
            }
            if (player4 != null) {
                Player.showInsertableArea();
                printGame(player3);
            }
        }

        // player4 TownHall kijelölés
        if (player4 != null) {
            Player.startGame1(player4);
            Player.removeInsertableArea();
        }
        turning(players.get(playerInd));
    }


    public void turning(Player player) {
        player.setTurn(true);
        player.setGold();
        printGame(player);
        for (int i = 0; i < player.getTowns().size(); i++) {
            if (player.getTowns().get(i).getAllRooms() < player.getTowns().get(i).getPopulation()) {
                OverpopHandler(player, player.getTowns().get(i));
            }
        }

        System.out.println("");
        System.out.println("Az alábbi menüpontok közül választhatsz (pl.: a):");
        System.out.println("");
        System.out.println("");
        System.out.println("a: Városodba / városaidba történő belépés.");
        System.out.println("b: Hadsereg mozgatása (1 tevékenység pont).");
        System.out.println("c: Következő kör.");
        System.out.println("(d: Játék mentése.)");
        System.out.println("q: Kilépés a játékból");

        Scanner scanner = new Scanner(System.in);

        String[] turnReader = scanner.nextLine().split("[ ]");

        String noEnoughParam = "Nem adtál meg elegendő paramétert!";
        String tooMuchParams = "Túl sok paramétert adtál meg!";
        String invalidParams = "A megadott paraméterek nem megfelelőek.";

        if (turnReader.length == 0) {
            System.out.println(noEnoughParam);
            System.out.println("");
            turning(this.players.get(this.playerInd));
        }
        if (turnReader.length > 1) {
            System.out.println(tooMuchParams);
            System.out.println("");
            turning(this.players.get(this.playerInd));
        }
        if (turnReader.length == 1) {
            if (turnReader[0].equals("a")) {
                System.out.println("Válaszd ki a várost! (pl.: 1)");
                for (int i = 0; i < player.getTowns().size(); i++) {
                    System.out.println("");
                    System.out.println(i + 1 + ": " + player.getTowns().get(i).getName());
                }
                String[] townReader = scanner.nextLine().split("[ ]");

                if (townReader.length == 0) {
                    System.out.println(noEnoughParam);
                    System.out.println("");
                    turning(this.players.get(this.playerInd));
                }
                if (townReader.length > 1) {
                    System.out.println(tooMuchParams);
                    System.out.println("");
                    turning(this.players.get(this.playerInd));
                }
                if (townReader.length == 1 && townReader[0].matches("[0-9]")) {

                    if (Integer.parseInt(townReader[0]) - 1 < player.getTowns().size()) {

                        townMode(player.getTowns().get(Integer.parseInt(townReader[0]) - 1), player);
                    }
                    if (Integer.parseInt(townReader[0]) - 1 >= player.getTowns().size()) {

                        System.out.println(invalidParams);
                        System.out.println("");
                        turning(this.players.get(this.playerInd));
                    }
                }
                if (townReader.length == 1 && !townReader[0].matches("[0-9]")) {

                    System.out.println(invalidParams);
                    System.out.println("");
                    turning(this.players.get(this.playerInd));
                }
            }
            if (turnReader[0].equals("b")) {
                if (player.getArmies().size() == 0) {
                    System.out.println("Jelenleg nincs hadsereged.");
                    turning(player);
                } else if (player.getActionPoint() >= 1) {
                    armyMove(player, scanner);
                } else if (player.getActionPoint() < 1) {
                    boolean isArmyActionP = false;

                    for (int i = 0; i < player.getArmies().size(); i++) {
                        if (player.getArmies().get(i).getActionPoint() > 0) {
                            isArmyActionP = true;
                        }
                    }

                    if (!isArmyActionP) {
                        System.out.println("");
                        System.out.println("Nincs elég tevékenység pontod.");
                        System.out.println("");
                        turning(player);
                    } else {
                        armyMove(player, scanner);
                    }
                }
            }
            if (turnReader[0].equals("c")) {
                // player.setTurn(true);
                player.setTurnCount(player.getTurnCount() + 1);
                int bankCounter = 0;

                for (int i = 0; i < player.getArmies().size(); i++) {
                    player.getArmies().get(i).setSteps();               // Hadseregek lépéseinek beállítása.
                    player.getArmies().get(i).setActionPoint(0);           // Hadseregek tevékenység pontjainak beállítása.
                }

                for (int i = 0; i < player.getTowns().size(); i++) {


                    for (int j = 0; j < player.getTowns().get(i).getBuildings().size(); j++) {

                        if (player.getTowns().get(i).getBuildings().get(j) instanceof Bank) {
                            bankCounter++;
                        }
                    }

                    player.getTowns().get(i).setTax((int) Math.floor((double) player.getTowns().get(i).getPopulation() * 0.1));   // set town tax

                    // set town bankIncome
                    if (bankCounter > 0) {
                        player.getTowns().get(i).setBankIncome((int) Math.floor((double) player.getTowns().get(i).getTax() * (Math.pow(1.2, (double) bankCounter) - 1)));
                    }

                    player.getTowns().get(i).setGold(player.getTowns().get(i).getGold() + player.getTowns().get(i).getTax() + player.getTowns().get(i).getBankIncome());       // set town gold

                    double randomIncrease = (double) (int) (Math.random() * 15) / 100;
                    double randomDecrease = (double) (int) (Math.random() * 9) / 100;

                    player.getTowns().get(i).setPopulationIncrease((int) ((double) player.getTowns().get(i).getPopulation() * randomIncrease));    // set population increase
                    player.getTowns().get(i).setPopulationDecrease((int) ((double) player.getTowns().get(i).getPopulation() * randomDecrease));    // set population decrease

                    player.getTowns().get(i).setPopulation(player.getTowns().get(i).getPopulation() +                                               // set population
                            player.getTowns().get(i).getPopulationIncrease() - player.getTowns().get(i).getPopulationDecrease());

                }
                player.setActionPoint();
                System.out.println(bankCounter);

                if (this.playerInd < this.players.size() - 1) {         // A players tömb léptetése, illetve újraindítása
                    this.playerInd++;
                    turning(this.players.get(this.playerInd));
                }
                if (this.playerInd == this.players.size() - 1) {
                    this.playerInd = 0;
                    turning(this.players.get(this.playerInd));
                }
            }
            if (turnReader[0].equals("q")) {
                System.out.println("Viszlát!");
                scanner.close();
            }
            if (!turnReader[0].equals("a") && !turnReader[0].equals("c") && !turnReader[0].equals("q")) {
                System.out.println(invalidParams);
                System.out.println("");
                turning(this.players.get(this.playerInd));
            }
        }
    }


    public void armyMove(Player player, Scanner scanner) {

        String noEnoughParam = "Nem adtál meg elegendő paramétert!";
        String tooMuchParams = "Túl sok paramétert adtál meg!";
        String invalidParams = "A megadott paraméterek nem megfelelőek.";

        System.out.println("Válaszd ki a hadseregedet! (pl.: 1)");

        for (int i = 0; i < player.getArmies().size(); i++) {
            ;
            System.out.println("");
            System.out.println(i + 1 + ": " + player.getArmies().get(i).getColorSign());
        }
        String[] armyReader = scanner.nextLine().split("[ ]");

        if (armyReader.length == 0) {
            System.out.println(noEnoughParam);
            System.out.println("");
            turning(this.players.get(this.playerInd));
        }
        if (armyReader.length > 1) {
            System.out.println(tooMuchParams);
            System.out.println("");
            turning(this.players.get(this.playerInd));
        }
        if (armyReader.length == 1 && armyReader[0].matches("[0-9]")) {         // Ha csak egy adatot adott meg és az szám
            // Így 10 seregnél nem lehet több!!!

            // Ha hadseregként valid számot jelölt meg
            if (Integer.parseInt(armyReader[0]) - 1 < player.getArmies().size()) {

                if (player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).getActionPoint() > 0 || player.getActionPoint() > 0) {

                    int armyPositionI = player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).getPositionI();
                    int armyPositionJ = player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).getPositionJ();

                    int armyNewPosI = armyPositionI;
                    int armyNewPosJ;

                    boolean isBackgroundI = true;
                    boolean isBackgroundJ = true;

                    System.out.println("Add meg, hogy vertikális irányba mennyit szeretnél lépni (max +10-et, min -10-et lehet)!");

                    Scanner inVert = new Scanner(System.in);
                    int numI = inVert.nextInt();

                    if (Math.abs(numI) <= player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).getSteps()) {     // Van-e elég lépése

                        if (numI > 0) {
                            for (int i = armyPositionI + 1; i < Board.board.length && i <= armyPositionI + numI; i++) {
                                // így a pálya határán nem tud átmenni
                                if (!(Board.board[i][armyPositionJ] instanceof Background)) {
                                    isBackgroundI = false;
                                }
                                if (isBackgroundI == true) {
                                    armyNewPosI++;
                                }
                            }
                            System.out.println("beolvasott szám: " + numI);
                            System.out.println("isBackgroundI: " + isBackgroundI);
                            System.out.println("armyNewPosI= " + armyNewPosI);

                            if (!(Board.board[armyNewPosI][armyPositionJ] instanceof Background)) {
                                armyNewPosI--;
                            }
                            Board.board[armyNewPosI][armyPositionJ] = Board.board[armyPositionI][armyPositionJ];
                            Board.board[armyPositionI][armyPositionJ] = new Background(". ");

                            // A megváltozott adatokat beállítjuk az Army-nál
                            Board.board[armyNewPosI][armyPositionJ].setPositionI(armyNewPosI);      /// HIBALEHETŐSÉG -> LEHET, HOGY ITT IS AZ ARMY-S TÖMBÖT KÉNE MÓDOSÍTANI, MINT LENT

                            if (player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).getActionPoint() == 0) {
                                player.setActionPoint(player.getActionPoint() - 1);
                                player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).setActionPoint(1);
                            }

                            player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).setSteps(
                                    player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).getSteps() - Math.abs(armyPositionI - armyNewPosI));

                            if (armyNewPosI < Board.board.length - 1 && armyPositionJ < Board.board.length - 1 && armyPositionJ > 0) {

                                if ((Board.board[armyNewPosI + 1][armyPositionJ] instanceof Town.HorBorder && !(Board.board[armyNewPosI + 1][armyPositionJ].getColor().equals(player.getColor()))) ||
                                        (Board.board[armyNewPosI][armyPositionJ + 1] instanceof Town.VertBorder && !(Board.board[armyNewPosI][armyPositionJ + 1].getColor().equals(player.getColor()))) ||
                                        (Board.board[armyNewPosI][armyPositionJ - 1] instanceof Town.VertBorder && !(Board.board[armyNewPosI][armyPositionJ - 1].getColor().equals(player.getColor())))) {
                                    attack(player, player.getArmies().get(Integer.parseInt(armyReader[0]) - 1));
                                }
                            }
                        }
                        if (numI < 0) {
                            for (int i = armyPositionI - 1; i >= 0 && i >= armyPositionI + numI; i--) {

                                if (!(Board.board[i][armyPositionJ] instanceof Background)) {
                                    isBackgroundI = false;
                                }
                                if (isBackgroundI == true) {
                                    armyNewPosI--;
                                }
                            }

                            System.out.println("isBackgroundI: " + isBackgroundI);
                            System.out.println("armyNewPosI= " + armyNewPosI);

                            if (!(Board.board[armyNewPosI][armyPositionJ] instanceof Background)) {
                                armyNewPosI++;
                            }

                            Board.board[armyNewPosI][armyPositionJ] = Board.board[armyPositionI][armyPositionJ];
                            Board.board[armyPositionI][armyPositionJ] = new Background(". ");

                            // A megváltozott adatokat beállítjuk az Army-nál
                            Board.board[armyNewPosI][armyPositionJ].setPositionI(armyNewPosI);      /// HIBALEHETŐSÉG -> LEHET, HOGY ITT IS AZ ARMY-S TÖMBÖT KÉNE MÓDOSÍTANI, MINT LENT

                            if (player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).getActionPoint() == 0) {
                                player.setActionPoint(player.getActionPoint() - 1);
                                player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).setActionPoint(1);
                            }

                            player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).setSteps(
                                    player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).getSteps() - Math.abs(armyPositionI - armyNewPosI));

                            if (armyNewPosI > 0 && armyPositionJ < Board.board.length - 1 && armyPositionJ > 0) {

                                if ((Board.board[armyNewPosI - 1][armyPositionJ] instanceof Town.HorBorderDown && !(Board.board[armyNewPosI - 1][armyPositionJ].getColor().equals(player.getColor()))) ||
                                        (Board.board[armyNewPosI][armyPositionJ + 1] instanceof Town.VertBorder && !(Board.board[armyNewPosI][armyPositionJ + 1].getColor().equals(player.getColor()))) ||
                                        (Board.board[armyNewPosI][armyPositionJ - 1] instanceof Town.VertBorder && !(Board.board[armyNewPosI][armyPositionJ - 1].getColor().equals(player.getColor())))) {
                                    attack(player, player.getArmies().get(Integer.parseInt(armyReader[0]) - 1));
                                }
                            }

                        }

                        if (numI == 0) {
                        }

                    } else {
                        System.out.println("Ennyit nem tudsz lépni, mert összesen " + player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).getSteps() + " lépésed van ebben a körben.");

                        if (player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).getSteps() > 0) {
                            armyMove(player, scanner);
                        } else {
                            turning(player);
                        }
                    }

                    armyPositionI = player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).getPositionI();
                    armyPositionJ = player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).getPositionJ();

                    armyNewPosJ = armyPositionJ;

                    System.out.println("");
                    System.out.println("Frissített armyPositon:");
                    System.out.println("armyPositionI: " + armyPositionI);
                    System.out.println("armyPositionJ: " + armyPositionJ);
                    System.out.println("");

                    if (player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).getSteps() > 0) {

                        System.out.println("Add meg, hogy horizontális irányba mennyit szeretnél lépni (max +10-et, min -10-et lehet)!");

                        Scanner inHor = new Scanner(System.in);
                        int numJ = inHor.nextInt();

                        if (Math.abs(numJ) <= player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).getSteps()) {

                            if (numJ > 0) {
                                for (int j = armyPositionJ + 1; j < Board.board[armyPositionI].length && j <= armyPositionJ + numJ; j++) {

                                    if (!(Board.board[armyPositionI][j] instanceof Background)) {
                                        isBackgroundJ = false;
                                    }
                                    // így pálya határán nem tud átmenni
                                    if (isBackgroundJ == true) {
                                        armyNewPosJ++;
                                    }
                                }

                                if (!(Board.board[armyPositionI][armyNewPosJ] instanceof Background)) {
                                    armyNewPosJ--;
                                }

                                System.out.println("isBackgroundJ: " + isBackgroundJ);
                                System.out.println("armyNewPosJ: " + armyNewPosJ);

                                Board.board[armyPositionI][armyNewPosJ] = Board.board[armyPositionI][armyPositionJ];
                                Board.board[armyPositionI][armyPositionJ] = new Background(". ");

                                // A megváltozott adatokat beállítjuk az Army-nál
                                Board.board[armyPositionI][armyNewPosJ].setPositionJ(armyNewPosJ);

                                // Town.HorBorderDown, Town.HorBorder, Town.VertBorder

                                if (player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).getActionPoint() == 0) {
                                    player.setActionPoint(player.getActionPoint() - 1);
                                    player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).setActionPoint(1);
                                }

                                player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).setSteps(
                                        player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).getSteps() - Math.abs(armyPositionJ - armyNewPosJ));

                                System.out.println("army PositionJ: " + Board.board[armyPositionI][armyNewPosJ].getPositionJ());

                                if (armyNewPosJ < Board.board.length - 1 && armyPositionI < Board.board.length - 1 && armyPositionI > 0) {

                                    if ((Board.board[armyPositionI][armyNewPosJ + 1] instanceof Town.VertBorder && !(Board.board[armyPositionI][armyNewPosJ + 1].getColor().equals(player.getColor()))) ||
                                            (Board.board[armyPositionI + 1][armyNewPosJ] instanceof Town.HorBorder && !(Board.board[armyPositionI + 1][armyNewPosJ].getColor().equals(player.getColor()))) ||
                                            (Board.board[armyPositionI - 1][armyNewPosJ] instanceof Town.HorBorderDown && !(Board.board[armyPositionI - 1][armyNewPosJ].getColor().equals(player.getColor()))) ||
                                            // (Board.board[armyPositionI + 1][armyNewPosJ] instanceof Town.VertBorder && !(Board.board[armyPositionI + 1][armyNewPosJ].getColor().equals(player.getColor())) ) ||
                                            (Board.board[armyPositionI - 1][armyNewPosJ] instanceof Town.VertBorder && !(Board.board[armyPositionI - 1][armyNewPosJ].getColor().equals(player.getColor())))
                                    ) {
                                        attack(player, player.getArmies().get(Integer.parseInt(armyReader[0]) - 1));
                                    }
                                }

                                turning(player);

                            }
                            if (numJ < 0) {
                                for (int j = armyPositionJ - 1; j >= 0 && j >= armyPositionJ + numJ; j--) {
                                    // így pálya határán nem tud átmenni
                                    if (!(Board.board[armyPositionI][j] instanceof Background)) {
                                        isBackgroundJ = false;
                                    }
                                    // így pálya határán nem tud átmenni
                                    if (isBackgroundJ == true) {
                                        armyNewPosJ--;
                                    }
                                }

                                if (!(Board.board[armyPositionI][armyNewPosJ] instanceof Background)) {
                                    armyNewPosJ++;
                                }

                                System.out.println("isBackgroundJ: " + isBackgroundJ);
                                System.out.println("armyNewPosJ: " + armyNewPosJ);

                                Board.board[armyPositionI][armyNewPosJ] = Board.board[armyPositionI][armyPositionJ];
                                Board.board[armyPositionI][armyPositionJ] = new Background(". ");

                                // A megváltozott adatokat beállítjuk az Army-nál
                                Board.board[armyPositionI][armyNewPosJ].setPositionJ(armyNewPosJ);

                                if (player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).getActionPoint() == 0) {
                                    player.setActionPoint(player.getActionPoint() - 1);
                                    player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).setActionPoint(1);
                                }

                                player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).setSteps(
                                        player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).getSteps() - Math.abs(armyPositionJ - armyNewPosJ));

                                if (armyNewPosJ > 0 && armyPositionI < Board.board.length - 1 && armyPositionI > 0) {

                                    if ((Board.board[armyPositionI][armyNewPosJ - 1] instanceof Town.VertBorder && !(Board.board[armyPositionI][armyNewPosJ - 1].getColor().equals(player.getColor()))) ||
                                            (Board.board[armyPositionI + 1][armyNewPosJ] instanceof Town.HorBorder && !(Board.board[armyPositionI + 1][armyNewPosJ].getColor().equals(player.getColor()))) ||
                                            (Board.board[armyPositionI - 1][armyNewPosJ] instanceof Town.HorBorderDown && !(Board.board[armyPositionI - 1][armyNewPosJ].getColor().equals(player.getColor()))) ||
                                            // (Board.board[armyPositionI + 1][armyNewPosJ] instanceof Town.VertBorder && !(Board.board[armyPositionI + 1][armyNewPosJ].getColor().equals(player.getColor())) ) ||
                                            (Board.board[armyPositionI - 1][armyNewPosJ] instanceof Town.VertBorder && !(Board.board[armyPositionI - 1][armyNewPosJ].getColor().equals(player.getColor())))
                                    ) {
                                        attack(player, player.getArmies().get(Integer.parseInt(armyReader[0]) - 1));
                                    }
                                }

                                turning(player);

                            }

                            if (numJ == 0) {
                                turning(player);
                            }

                        } else {
                            System.out.println("Ennyit nem tudsz lépni, mert összesen " + player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).getSteps() + " lépésed van ebben a körben.");

                            if (player.getArmies().get(Integer.parseInt(armyReader[0]) - 1).getSteps() > 0) {
                                armyMove(player, scanner);
                            } else {
                                turning(player);
                            }
                        }
                        turning(player);
                    }
                    turning(player);
                } else {
                    System.out.println("Nincs elég tevékenységpontod ennek a hadseregnek a mozgatásához.");
                }
            } else {
                System.out.println(invalidParams);
                System.out.println("");
                turning(player);
            }
        } else if (armyReader.length == 1 && !(armyReader[0].matches("[0-9]"))) {
            System.out.println(invalidParams);
            System.out.println("");
            turning(player);
        }
    }

    private void attack(Player attPlayer, Army army) {
        Town defTown = null;
        Player defPlayer;
        int attackerThrow;
        int defenderThrow;
        boolean isTown = false;

        for (int i = army.getPositionI() + 1; i < Board.board.length && i < army.getPositionI() + 7; i++) {
            for (int j = army.getPositionJ() + 1; j < Board.board[i].length && j < army.getPositionJ() + 7; j++) {
                if (Board.board[i][j] instanceof Town && Board.board[i][j].getFrontName().equals("Town")) {
                    defTown = (Town) Board.board[i][j];
                    isTown = true;
                }
            }
        }
        for (int i = army.getPositionI() + 1; i < Board.board.length && i < army.getPositionI() + 7; i++) {
            for (int j = army.getPositionJ(); j > -1 && j > army.getPositionJ() - 7; j--) {
                if (Board.board[i][j] instanceof Town && Board.board[i][j].getFrontName().equals("Town")) {
                    defTown = (Town) Board.board[i][j];
                    isTown = true;
                }
            }
        }
        for (int i = army.getPositionI(); i > -1 && i > army.getPositionI() - 7; i--) {
            for (int j = army.getPositionJ() + 1; j < Board.board[i].length && j < army.getPositionJ() + 7; j++) {
                if (Board.board[i][j] instanceof Town && Board.board[i][j].getFrontName().equals("Town")) {
                    defTown = (Town) Board.board[i][j];
                    isTown = true;
                }
            }
        }
        for (int i = army.getPositionI(); i > -1 && i > army.getPositionI() - 7; i--) {
            for (int j = army.getPositionJ(); j > -1 && j > army.getPositionJ() - 7; j--) {
                if (Board.board[i][j] instanceof Town && Board.board[i][j].getFrontName().equals("Town")) {
                    defTown = (Town) Board.board[i][j];
                    isTown = true;
                }
            }
        }

        Board.printBoard(Board.board);

        System.out.println("isTown: " + isTown);
        System.out.println("Az ellenség városhatárához értél, így kirobbant a harc!!!");

        defPlayer = defTown.getPlayer();

        if (defTown.getSoldiers() == 0 && defTown.getUpgradedSoldiers() == 0) {      // Ha nincs egy katona sincs a védekező városban

            defPlayer.getTowns().remove(defTown);
            attPlayer.getTowns().add(defTown);
            defTown.setColor(attPlayer.getColor());

            for (int i = 0; i < 11; i++) {
                Board.board[defTown.getPositionI() - 5 + i][defTown.getPositionJ() - 5].setColor(attPlayer.getColor());
                Board.board[defTown.getPositionI() + 5][defTown.getPositionJ() - 5 + i].setColor(attPlayer.getColor());
                Board.board[defTown.getPositionI() - 5 + i][defTown.getPositionJ() + 5].setColor(attPlayer.getColor());
                Board.board[defTown.getPositionI() - 5][defTown.getPositionJ() - 5 + i].setColor(attPlayer.getColor());
            }

            System.out.println("Elfoglaltad a(z) " + defPlayer.getName() + " várát.");
            System.out.println();

            if (defPlayer.getTowns().size() == 0) {     // Ha ez város volt a megtámadott Player egyetlen városa

                for (int i = 0; i < defPlayer.getArmies().size(); i++) {        // Hadseregeit töröljük a board-ról
                    Board.board[defPlayer.getArmies().get(i).getPositionI()][defPlayer.getArmies().get(i).getPositionJ()] = new Background(". ");
                }

                players.remove(defPlayer);
                System.out.println("A(z) " + defPlayer.getName() + "t legyőzted, így az összes aranya a Tied lett!!");
                System.out.println("");
            }

            if (players.size() == 1) {
                System.out.println("Gratulálok, megnyerted a játékot!!");
                System.out.println("");
                mainMenu();
            }

            turning(attPlayer);
        }
        if (defTown.getSoldiers() > 0 || defTown.getUpgradedSoldiers() > 0) {

            while ((army.getSoldiers() !=  0 || army.getUpgradedSoldiers() != 0) && (defTown.getSoldiers() != 0 || defTown.getUpgradedSoldiers() != 0)) {

                attackerThrow = (int) (Math.random() * ((6 - 1) + 1)) + 1;
                defenderThrow = (int) (Math.random() * ((6 - 1) + 1)) + 1;

                System.out.println("A(z) " + attPlayer.getName() + " " + attackerThrow + " értéket dobott");
                System.out.println("A(z) " + defPlayer.getName() + " " + defenderThrow + " értéket dobott");
                System.out.println("");

                if (attackerThrow > defenderThrow && defTown.getSoldiers() < 20) {
                    defTown.setSoldiers(0);
                }

                if (attackerThrow > defenderThrow && defTown.getSoldiers() > 19) {

                    System.out.println("A város katonáinak száma: " + defTown.getSoldiers());
                    System.out.println("Ebből ennyien haltak meg: " + (int) Math.floor(defTown.getSoldiers() * (attackerThrow * 0.03 + ((double) army.getUpgradedSoldiers() / (army.getUpgradedSoldiers() + army.getSoldiers()) * attackerThrow * 0.03))));
                    System.out.println("Fogyási ráta:" + (attackerThrow * 0.03 + ((double) army.getUpgradedSoldiers() / (army.getUpgradedSoldiers() + army.getSoldiers()) * attackerThrow * 0.03)));

                    defTown.setSoldiers(defTown.getSoldiers() - (int) Math.floor(defTown.getSoldiers() * (attackerThrow * 0.03 + ((double) army.getUpgradedSoldiers() / (army.getUpgradedSoldiers() + army.getSoldiers()) * attackerThrow * 0.03))));
                    System.out.println("A városban " + defTown.getSoldiers() + " katona maradt.");
                    System.out.println("");

                }

                if (attackerThrow > defenderThrow && defTown.getUpgradedSoldiers() < 20) {
                    defTown.setUpgradedSoldiers(0);
                }
                if (attackerThrow > defenderThrow && defTown.getUpgradedSoldiers() > 19) {

                    System.out.println("A város képzett katonáinak száma: " + defTown.getUpgradedSoldiers());
                    System.out.println("Ebből ennyien haltak meg: " + (int) Math.floor(defTown.getUpgradedSoldiers() * (attackerThrow * 0.03 + ((double) army.getUpgradedSoldiers() / (army.getUpgradedSoldiers() + army.getSoldiers()) * attackerThrow * 0.03))));
                    System.out.println("Fogyási ráta:" + (attackerThrow * 0.03 + ((double) army.getUpgradedSoldiers() / (army.getUpgradedSoldiers() + army.getSoldiers()) * attackerThrow * 0.03)));
                    System.out.println("");

                    defTown.setUpgradedSoldiers(defTown.getUpgradedSoldiers() - (int) Math.floor(defTown.getUpgradedSoldiers() * (attackerThrow * 0.03 + ((double) army.getUpgradedSoldiers() / (army.getUpgradedSoldiers() + army.getSoldiers()) * attackerThrow * 0.03))));
                    System.out.println("A városban " + defTown.getUpgradedSoldiers() + " képzett katona maradt.");
                    System.out.println("");
                }

                if (attackerThrow < defenderThrow && army.getSoldiers() < 20) {
                    army.setSoldiers(0);
                }
                if (attackerThrow < defenderThrow && army.getSoldiers() > 19) {

                    System.out.println("A hadsereg katonáinak száma: " + army.getSoldiers());
                    System.out.println("Ebből ennyien haltak meg: " + (int) Math.floor(army.getSoldiers() * (defenderThrow * 0.05 + ((double) defTown.getUpgradedSoldiers() / (defTown.getUpgradedSoldiers() + defTown.getSoldiers()) * defenderThrow * 0.05))));
                    System.out.println("Fogyási ráta:" + (defenderThrow * 0.05 + ((double) defTown.getUpgradedSoldiers() / (defTown.getUpgradedSoldiers() + defTown.getSoldiers()) * defenderThrow * 0.05)));

                    army.setSoldiers(army.getSoldiers() - (int) Math.floor(army.getSoldiers() * (defenderThrow * 0.05 + ((double) defTown.getUpgradedSoldiers() / (defTown.getUpgradedSoldiers() + defTown.getSoldiers()) * defenderThrow * 0.05))));
                    System.out.println("A hadseregben " + army.getSoldiers() + " katona maradt.");
                    System.out.println("");

                }
                if (attackerThrow < defenderThrow && army.getUpgradedSoldiers() < 20) {
                    army.setUpgradedSoldiers(0);
                }
                if (attackerThrow < defenderThrow && army.getUpgradedSoldiers() > 19) {

                    System.out.println("A hadsereg képzett katonáinak száma: " + army.getUpgradedSoldiers());
                    System.out.println("Ebből ennyien haltak meg: " + (int) Math.floor(army.getUpgradedSoldiers() * (defenderThrow * 0.05 + ((double) defTown.getUpgradedSoldiers() / (defTown.getUpgradedSoldiers() + defTown.getSoldiers()) * defenderThrow * 0.05))));
                    System.out.println("Fogyási ráta:" + (defenderThrow * 0.05 + ((double) defTown.getUpgradedSoldiers() / (defTown.getUpgradedSoldiers() + defTown.getSoldiers()) * defenderThrow * 0.05)));

                    army.setUpgradedSoldiers(army.getUpgradedSoldiers() - (int) Math.floor(army.getUpgradedSoldiers() * (defenderThrow * 0.05 + ((double) defTown.getUpgradedSoldiers() / (defTown.getUpgradedSoldiers() + defTown.getSoldiers()) * defenderThrow * 0.05))));
                    System.out.println("A hadseregben " + army.getUpgradedSoldiers() + " képzett katona maradt.");
                    System.out.println("");
                }

            }
        }

        if (army.getSoldiers() == 0 && army.getUpgradedSoldiers() == 0) {        // Ha a védekező város nyert

            attPlayer.getArmies().remove(army);
            Board.board[army.getPositionI()][army.getPositionJ()] = new Background(". ");

            System.out.println("Nem sikerült a várost bevenni!!");

            turning(attPlayer);
        }
        if (defTown.getSoldiers() == 0 && defTown.getUpgradedSoldiers() == 0) {          // Ha a támadók nyertek

            defPlayer.getTowns().remove(defTown);
            attPlayer.getTowns().add(defTown);
            defTown.setColor(attPlayer.getColor());

            for (int i = 0; i < 11; i++) {
                Board.board[defTown.getPositionI() - 5 + i][defTown.getPositionJ() - 5].setColor(attPlayer.getColor());
                Board.board[defTown.getPositionI() + 5][defTown.getPositionJ() - 5 + i].setColor(attPlayer.getColor());
                Board.board[defTown.getPositionI() - 5 + i][defTown.getPositionJ() + 5].setColor(attPlayer.getColor());
                Board.board[defTown.getPositionI() - 5][defTown.getPositionJ() - 5 + i].setColor(attPlayer.getColor());
            }

            System.out.println("Elfoglaltad a(z) " + defPlayer.getName() + " várát.");
            System.out.println();

            if (defPlayer.getTowns().size() == 0) {     // Ha ez város volt a megtámadott Player egyetlen városa

                for (int i = 0; i < defPlayer.getArmies().size(); i++) {        // Hadseregeit töröljük a board-ról
                    Board.board[defPlayer.getArmies().get(i).getPositionI()][defPlayer.getArmies().get(i).getPositionJ()] = new Background(". ");
                }

                players.remove(defPlayer);
                System.out.println("A(z) " + defPlayer.getName() + "t legyőzted, így az összes aranya a Tied lett!!");
                System.out.println("");
            }

            if (players.size() == 1) {
                System.out.println("Gratulálok, megnyerted a játékot!!");
                System.out.println("");
                mainMenu();
            }

            turning(attPlayer);
        }
    }


    private void OverpopHandler(Player player, Town town) {
        System.out.println("A " + town.getName() + " lakosságának száma meghaladta a városodban lévő szálláshelyek számát. Ilyenkor köteles vagy Szállást építeni. " +
                "Amennyiben ezt nem tudod teljesíteni, 1000 ember elhagyja a városod.");

        System.out.println(town.getName());
        Cell[][] townBoard = new Cell[11][11];
        for (int i = town.getPositionI() - 5; i < town.getPositionI() + 6; i++) {
            for (int j = town.getPositionJ() - 5; j < town.getPositionJ() + 6; j++) {
                townBoard[i - (town.getPositionI() - 5)][j - (town.getPositionJ() - 5)] = Board.board[i][j];
            }
        }
        Board.printBoard(townBoard);

        boolean isNoElement = false;                // Megvizsgáljuk, hogy van-e szabad hely
        for (int i = 0; i < townBoard.length; i++) {
            for (int j = 0; j < townBoard[i].length; j++) {
                if (townBoard[i][j] instanceof Town.NoElement) {
                    isNoElement = true;
                }
            }
        }

        if (town.getGold() >= Accomodation.getPrice() && isNoElement) {         // Ha van elég aranyunk és van egy szabad hely a városban
            System.out.println("Add meg a koordinátákat, ahova építeni szeretnéd (1-től 9-ig)");
            Scanner scanner = new Scanner(System.in);
            String[] buildingPointReader = scanner.nextLine().split("[ ]");

            if (!validateBuilding(townBoard, buildingPointReader)) {
                OverpopHandler(player, town);
            }

            else {

                int i = Integer.parseInt(buildingPointReader[0]);
                int j = Integer.parseInt(buildingPointReader[1]);

                townBoard[i][j] = new Accomodation(town.getTownhall().getPositionI() + i - 5,
                        town.getTownhall().getPositionJ() + j - 5, player.getColor(), Accomodation.setId(town));

                Board.board[townBoard[i][j].getPositionI()][townBoard[i][j].getPositionJ()] = townBoard[i][j];
                town.buildings.add((Accomodation) townBoard[i][j]);

                town.setGold(town.getGold() - Accomodation.getPrice());
                town.setAllRooms();

                player.setActionPoint(player.getActionPoint() - 2);
                System.out.println("A(z) " + townBoard[i][j].getId() + ". " + Accomodation.getName2() + " felépült!!");

                turning(player);
            }

        }
        else {
            System.out.println("Nem lehetséges az építkezés, mert vagy a városnak nincs elég aranya, vagy már nincs szabad hely a városban. 1000 ember elhagyta a városodat.");
            town.setPopulationDecrease(1000);
            town.setPopulationIncrease(0);
            town.setPopulation(town.getPopulation() - town.getPopulationDecrease());
            turning(player);
        }

    }


    private void townMode(Town town, Player player) {
        player.setGold();
        town.armies.removeAll(town.armies);
        armySearcher(town);

        System.out.println(town.getName());
        Cell[][] townBoard = new Cell[11][11];
        for (int i = town.getPositionI() - 5; i < town.getPositionI() + 6; i++) {
            for (int j = town.getPositionJ() - 5; j < town.getPositionJ() + 6; j++) {
                townBoard[i - (town.getPositionI() - 5)][j - (town.getPositionJ() - 5)] = Board.board[i][j];
            }
        }
        Board.printBoard(townBoard);
        System.out.println();
        if (town.armies.size() > 0 && town.isTrainingG()) {
            for (int i = 0; i < town.armies.size(); i++) {
                town.armies.get(i).setUpgradedSoldiers(town.armies.get(i).getUpgradedSoldiers() + town.armies.get(i).getSoldiers());
                town.armies.get(i).setSoldiers(0);
            }
            System.out.println("Hadseregeid Alapszintű katonáinak továbbképzése megtörtént!!");
        }
        System.out.println();
        System.out.println("Az adóbevételed összege: " + town.getTax() + " arany.");
        System.out.println("A banktól származó bevételed összege: " + town.getBankIncome() + " arany.");

        System.out.println("Arany: " + town.getGold());
        System.out.println("Lakosság: " + town.getPopulation());
        System.out.println("Szálláshelyek: " +  town.getAllRooms());
        System.out.println("Alapszintű katonák száma: " + town.getSoldiers());
        System.out.println("Képzett katonák száma: " + town.getUpgradedSoldiers());
        System.out.println("Katonai szálláshelyek: " +  town.getAllSoldierRooms());
        System.out.println("Városhatárnál tartózkodó hadseregek száma: " +  town.armies.size());
        System.out.println("Épületek: ");
        System.out.println("\t" + Building.buildingCounter(town, Accomodation.getName2()) + " " + Accomodation.getName2());
        System.out.println("\t" + Building.buildingCounter(town, Bank.getName2()) + " " + Bank.getName2());
        System.out.println("\t" + Building.buildingCounter(town, Blacksmith.getName2()) + " " + Blacksmith.getName2());
        System.out.println("\t" + Building.buildingCounter(town, Barrack.getName2()) + " " + Barrack.getName2());
        System.out.println("\t" + Building.buildingCounter(town, TrainingGround.getName2()) + " " + TrainingGround.getName2());
        System.out.println("\t" + Building.buildingCounter(town, SoldierAccomodation.getName2()) + " " + SoldierAccomodation.getName2());
        System.out.println();
        System.out.println(player);
        System.out.println();
        System.out.println("Az alábbi lehetőségek közül választhatsz (pl. Szállás építése: 3):");
        System.out.println();
        if (town.armies.size() > 0) {
            System.out.println("0: hadsereg(ek) kezelése, akik a városhatárnál tartózkodnak");
        }
        System.out.println("1: 50 katona toborzása. (2 tevékenység pontba kerül. Legalább 50 civil lakos, 100 arany, 50 szabad katonaszállás és Kaszárnya szükséges hozzá.)");
        System.out.println("2: Hadsereg szervezése");
        System.out.println();
        System.out.println("Épületek építése (2 tevékenység pontba kerül):");
        System.out.println();

        System.out.println("3: " + Accomodation.getName2() + " építése (ára: " + Accomodation.getPrice() + " arany, " + Accomodation.getDescription() + ")");
        System.out.println("4: " + Bank.getName2() + " építése (ára: " + Bank.getPrice() + " arany, " + Bank.getDescription() + ")");

        if (town.isBlackshmith() == false) {
            System.out.println("5: " + Blacksmith.getName2() + " építése (ára: " + Blacksmith.getPrice() + " arany, " + Blacksmith.getDescription() + " Csak 1 ilyen épület építhető.)");
        }
        if (town.isBarrack() == false) {
            System.out.println("6: " + Barrack.getName2() + " építése (ára: " + Barrack.getPrice() + " arany, " + Barrack.getDescription() + " Csak 1 ilyen épület építhető.)");
        }
        if (town.isTrainingG() == false) {
            System.out.println("7: " + TrainingGround.getName2() + " építése (ára: " + TrainingGround.getPrice() + " arany, " + TrainingGround.getDescription() + " Csak 1 ilyen épület építhető.)");
        }
        System.out.println("8: " + SoldierAccomodation.getName2() + " építése (ára: "  + SoldierAccomodation.getPrice() + " arany, " + SoldierAccomodation.getDescription() + ")");

        System.out.println("9: Visszalépés a térképhez");
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        String[] townActionReader = scanner.nextLine().split("[ ]");

        String noEnoughParam = "Nem adtál meg elegendő paramétert!";
        String tooMuchParams = "Túl sok paramétert adtál meg!";
        String invalidParams = "A megadott paraméterek nem megfelelőek.";

        if (townActionReader.length == 0) {
            System.out.println(noEnoughParam);
            System.out.println("");
            townMode(town, player);
        }
        if (townActionReader.length > 1) {
            System.out.println(tooMuchParams);
            System.out.println("");
            townMode(town, player);
        }

        if (townActionReader.length == 1) {

            if (townActionReader[0].equals("0")) {
                if (town.armies.size() == 0) {
                    System.out.println(invalidParams);
                    townMode(town, player);
                } else {
                    armyHandler(town, player);
                }
            }

            if (townActionReader[0].equals("1")) {

                if (town.getGold() >= 100 && player.getActionPoint() >= 2 && town.getPopulation() >= 50
                        && (town.getAllSoldierRooms() - town.getSoldiers() - town.getUpgradedSoldiers()) >= 50 && town.isBarrack()) {

                    player.setActionPoint(player.getActionPoint() - 2);
                    town.setGold(town.getGold() - 100);
                    town.setPopulation(town.getPopulation() - 50);

                    if (town.isTrainingG()) {
                        town.setUpgradedSoldiers(town.getUpgradedSoldiers() + 50);
                        System.out.println("50 katona toborzása sikeresen megtörtént!! Továbbképzésük szintén sikeres!!");
                        townMode(town, player);
                    }
                    else {
                        town.setSoldiers(town.getSoldiers() + 50);
                        System.out.println("50 katona toborzása sikeresen megtörtént!!");
                        townMode(town, player);
                    }
                } else {
                    System.out.println("Nem lehetséges a katonák toborzása, mert nem adottak a szükséges feltételek.");
                    townMode(town, player);
                }
            }


            if (townActionReader[0].equals("2")) {
                if ((town.getSoldiers() > 0 || town.getUpgradedSoldiers() > 0) && town.armies.size() == 0 ) {
                    System.out.println("Add meg, hogy hány katonát szeretnél hadseregbe szervezni!");

                    String[] soldierNumReader = scanner.nextLine().split("[ ]");

                    if (!validateSoldComm(town, soldierNumReader)) {
                        townMode(town, player);
                    }
                    else {
                        int numOfSolds = Integer.parseInt(soldierNumReader[0]);

                        int indI = town.getPositionI();
                        int indJ;
                        if (town.getPositionJ() >= Board.board[indI].length / 2) {
                            indJ = town.getPositionJ() - 6;
                        }
                        else {
                            indJ = town.getPositionJ() + 6;
                        }
                        if (town.getSoldiers() > 0) {
                            Board.board[indI][indJ] = new Army(indI, indJ, player.getColor(), player.getArmies().size() + 1, numOfSolds, 0, Army.setColorSign(player));

                            town.setSoldiers(town.getSoldiers() - numOfSolds);
                            player.getArmies().add((Army) Board.board[indI][indJ]);

                            System.out.println("A hadsereged sikeresen megalakult.");
                            townMode(town, player);

                            }
                        else {
                            Board.board[indI][indJ] = new Army(indI, indJ, player.getColor(), player.getArmies().size() + 1, 0, numOfSolds, Army.setColorSign(player));

                            town.setUpgradedSoldiers(town.getUpgradedSoldiers() - numOfSolds);
                            // town.setArmyInTown(true);       // Majd ezt egy külön függvény fogja figyelni!!!
                            player.getArmies().add((Army) Board.board[indI][indJ]);

                            System.out.println("A hadsereged sikeresen megalakult.");
                            townMode(town, player);
                        }
                    }
                }
                else {
                    if (town.getSoldiers() == 0 && town.getUpgradedSoldiers() == 0) {
                        System.out.println("Nincs katona a városban!");
                        townMode(town, player);
                    }
                    else if (town.armies.size() > 0) {
                        System.out.println("Már tartózkodik hadsereg a városhatárnál, ilyenkor nincs lehetőséged újabb hadsereget szervezni.");
                        townMode(town, player);
                    }
                }
            }

            if (townActionReader[0].equals("3")) {
                if (town.getGold() >= Accomodation.getPrice() && player.getActionPoint() >= 2) {
                    System.out.println("Add meg a koordinátákat, ahova építeni szeretnéd (1-től 9-ig)");
                    String[] buildingPointReader = scanner.nextLine().split("[ ]");

                    if (!validateBuilding(townBoard, buildingPointReader)) {
                        townMode(town, player);
                    }

                    else {

                        int i = Integer.parseInt(buildingPointReader[0]);
                        int j = Integer.parseInt(buildingPointReader[1]);

                        townBoard[i][j] = new Accomodation(town.getTownhall().getPositionI() + i - 5,
                                        town.getTownhall().getPositionJ() + j - 5, player.getColor(), Accomodation.setId(town));

                        Board.board[townBoard[i][j].getPositionI()][townBoard[i][j].getPositionJ()] = townBoard[i][j];
                        town.buildings.add((Accomodation) townBoard[i][j]);

                        town.setGold(town.getGold() - Accomodation.getPrice());
                        town.setAllRooms();

                        player.setActionPoint(player.getActionPoint() - 2);
                        System.out.println("A(z) " + townBoard[i][j].getId() + ". " + Accomodation.getName2() + " felépült!!");

                        townMode(town, player);
                    }

                }
                else {
                    System.out.println("Nem lehetséges az építkezés, mert vagy nincs elég tevékenység pontod, vagy a városnak nincs elég aranya.");
                    townMode(town, player);
                }
            }

            if (townActionReader[0].equals("4")) {
                if (town.getGold() >= Bank.getPrice() && player.getActionPoint() >= 2) {
                    System.out.println("Add meg a koordinátákat, ahova építeni szeretnéd (1-től 9-ig)");
                    String[] buildingPointReader = scanner.nextLine().split("[ ]");

                    if (!validateBuilding(townBoard, buildingPointReader)) {
                        townMode(town, player);
                    }
                    else {

                        int i = Integer.parseInt(buildingPointReader[0]);
                        int j = Integer.parseInt(buildingPointReader[1]);

                        townBoard[i][j] = new Bank(town.getTownhall().getPositionI() + i - 5,
                                town.getTownhall().getPositionJ() + j - 5, player.getColor(), Bank.setId(town));

                        Board.board[townBoard[i][j].getPositionI()][townBoard[i][j].getPositionJ()] = townBoard[i][j];
                        town.buildings.add((Bank) townBoard[i][j]);

                        town.setGold(town.getGold() - Bank.getPrice());
                        player.setActionPoint(player.getActionPoint() - 2);
                        System.out.println("A(z) " + townBoard[i][j].getId() + ". " + Bank.getName2() + " felépült!!");

                        townMode(town, player);
                    }

                }
                else {
                    System.out.println("Nem lehetséges az építkezés, mert vagy nincs elég tevékenység pontod, vagy a városnak nincs elég aranya.");
                    townMode(town, player);
                }
            }

            if (townActionReader[0].equals("5")) {
                if (town.isBlackshmith() == true) {
                    System.out.println(invalidParams);
                    townMode(town, player);
                }
                 else if (town.getGold() >= Blacksmith.getPrice() && player.getActionPoint() >= 2) {
                    System.out.println("Add meg a koordinátákat, ahova építeni szeretnéd (1-től 9-ig)");
                    String[] buildingPointReader = scanner.nextLine().split("[ ]");

                    if (!validateBuilding(townBoard, buildingPointReader)) {
                        townMode(town, player);
                    }
                    else {

                        int i = Integer.parseInt(buildingPointReader[0]);
                        int j = Integer.parseInt(buildingPointReader[1]);

                        townBoard[i][j] = new Blacksmith(town.getTownhall().getPositionI() + i - 5,
                                town.getTownhall().getPositionJ() + j - 5, player.getColor(), Blacksmith.setId(town));

                        Board.board[townBoard[i][j].getPositionI()][townBoard[i][j].getPositionJ()] = townBoard[i][j];
                        town.buildings.add((Blacksmith) townBoard[i][j]);

                        town.setGold(town.getGold() - Blacksmith.getPrice());
                        player.setActionPoint(player.getActionPoint() - 2);
                        town.setBlackshmith(true);

                        System.out.println("A " + Blacksmith.getName2() + " felépült!!");
                        System.out.println("");

                        townMode(town, player);
                    }
                }
                else {
                    System.out.println("Nem lehetséges az építkezés, mert vagy nincs elég tevékenység pontod, vagy a városnak nincs elég aranya.");
                    townMode(town, player);
                }
            }

            if (townActionReader[0].equals("6")) {
                if (town.isBarrack() == true) {
                    System.out.println(invalidParams);
                    townMode(town, player);
                }
                else {
                    if ((!town.isBlackshmith())) {
                        System.out.println("Fegyverkovács műhellyel nem rendelkezel, így Kaszárnyát nincs lehetőséged építeni!");
                        System.out.println("");
                        townMode(town, player);
                    }
                    else if (town.getGold() >= Barrack.getPrice() && player.getActionPoint() >= 2) {
                        System.out.println("Add meg a koordinátákat, ahova építeni szeretnéd (1-től 9-ig)");
                        String[] buildingPointReader = scanner.nextLine().split("[ ]");

                        if (!validateBuilding(townBoard, buildingPointReader)) {
                            townMode(town, player);
                        }
                        else {

                            int i = Integer.parseInt(buildingPointReader[0]);
                            int j = Integer.parseInt(buildingPointReader[1]);

                            townBoard[i][j] = new Barrack(town.getTownhall().getPositionI() + i - 5,
                                    town.getTownhall().getPositionJ() + j - 5, player.getColor(), Barrack.setId(town));

                            Board.board[townBoard[i][j].getPositionI()][townBoard[i][j].getPositionJ()] = townBoard[i][j];
                            town.buildings.add((Barrack) townBoard[i][j]);

                            town.setGold(town.getGold() - Barrack.getPrice());
                            player.setActionPoint(player.getActionPoint() - 2);
                            town.setBarrack(true);

                            System.out.println("A " + Barrack.getName2() + " felépült!!");
                            System.out.println("");

                            townMode(town, player);
                        }
                    }
                    else {
                        System.out.println("Nem lehetséges az építkezés, mert vagy nincs elég tevékenység pontod, vagy a városnak nincs elég aranya.");
                        townMode(town, player);
                    }
                }
            }

            if (townActionReader[0].equals("7")) {
                if (town.isTrainingG() == true) {
                    System.out.println(invalidParams);
                    townMode(town, player);
                }
                else {
                    if (!town.isBlackshmith() || !town.isBarrack()) {
                        System.out.println("Fegyverkovács műhellyel és/vagy Kaszárnyával nem rendelkezel, így Kiképző helyet nincs lehetőséged építeni!");
                        System.out.println("");
                        townMode(town, player);
                    }
                    else if (town.getGold() >= TrainingGround.getPrice() && player.getActionPoint() >= 2) {
                        System.out.println("Add meg a koordinátákat, ahova építeni szeretnéd (1-től 9-ig)");
                        String[] buildingPointReader = scanner.nextLine().split("[ ]");

                        if (!validateBuilding(townBoard, buildingPointReader)) {
                            townMode(town, player);
                        }
                        else {

                            int i = Integer.parseInt(buildingPointReader[0]);
                            int j = Integer.parseInt(buildingPointReader[1]);

                            townBoard[i][j] = new TrainingGround(town.getTownhall().getPositionI() + i - 5,
                                    town.getTownhall().getPositionJ() + j - 5, player.getColor(), TrainingGround.setId(town));

                            Board.board[townBoard[i][j].getPositionI()][townBoard[i][j].getPositionJ()] = townBoard[i][j];
                            town.buildings.add((TrainingGround) townBoard[i][j]);

                            town.setGold(town.getGold() - TrainingGround.getPrice());

                            System.out.println("A " + TrainingGround.getName2() + " felépült!!");
                            if (town.getSoldiers() > 0) {
                                System.out.println("A városban lévő " + town.getSoldiers() + " katona továbbképzése megtörtént!!");
                            }
                            System.out.println("");
                            town.setUpgradedSoldiers(town.getUpgradedSoldiers() + town.getSoldiers());        // Katonák továbbképzése;
                            town.setSoldiers(0);

                            player.setActionPoint(player.getActionPoint() - 2);
                            town.setTrainingG(true);

                            if (town.armies.size() > 0 && town.isTrainingG()) {
                                for (int k = 0; k < town.armies.size(); k++) {
                                    town.armies.get(k).setUpgradedSoldiers(town.armies.get(k).getUpgradedSoldiers() + town.armies.get(k).getSoldiers());
                                    town.armies.get(k).setSoldiers(0);
                                }
                                System.out.println("Hadseregeid Alapszintű katonái továbbképzésben részesültek!!");
                            }

                            townMode(town, player);
                        }
                    }
                    else {
                        System.out.println("Nem lehetséges az építkezés, mert vagy nincs elég tevékenység pontod, vagy a városnak nincs elég aranya.");
                        townMode(town, player);
                    }
                }
            }

            if (townActionReader[0].equals("8")) {
                if (town.getGold() >= SoldierAccomodation.getPrice() && player.getActionPoint() >= 2) {
                    System.out.println("Add meg a koordinátákat, ahova építeni szeretnéd (1-től 9-ig)");
                    String[] buildingPointReader = scanner.nextLine().split("[ ]");

                    if (!validateBuilding(townBoard, buildingPointReader)) {
                        townMode(town, player);
                    }

                    else {

                        int i = Integer.parseInt(buildingPointReader[0]);
                        int j = Integer.parseInt(buildingPointReader[1]);

                        townBoard[i][j] = new SoldierAccomodation(town.getTownhall().getPositionI() + i - 5,
                                town.getTownhall().getPositionJ() + j - 5, player.getColor(), SoldierAccomodation.setId(town));

                        Board.board[townBoard[i][j].getPositionI()][townBoard[i][j].getPositionJ()] = townBoard[i][j];
                        town.buildings.add((SoldierAccomodation) townBoard[i][j]);

                        town.setGold(town.getGold() - SoldierAccomodation.getPrice());
                        town.setAllSoldierRooms();

                        player.setActionPoint(player.getActionPoint() - 2);
                        System.out.println("A(z) " + townBoard[i][j].getId() + ". " + SoldierAccomodation.getName2() + " felépült!!");

                        townMode(town, player);
                    }

                }
                else {
                    System.out.println("Nem lehetséges az építkezés, mert vagy nincs elég tevékenység pontod, vagy a városnak nincs elég aranya.");
                    townMode(town, player);
                }
            }

            if (townActionReader[0].equals("9")) {
                turning(player);
            }

            if (!townActionReader[0].equals("1") && !townActionReader[0].equals("2") && !townActionReader[0].equals("3") && !townActionReader[0].equals("4")
                    && !townActionReader[0].equals("5") && !townActionReader[0].equals("6") && !townActionReader[0].equals("7") && !townActionReader[0].equals("8")
                    && !townActionReader[0].equals("9")) {
                System.out.println(invalidParams);
                System.out.println("");
                townMode(town, player);
            }
        }
    }

    private void armyHandler(Town town, Player player) {

        String noEnoughParam = "Nem adtál meg elegendő paramétert!";
        String tooMuchParams = "Túl sok paramétert adtál meg!";
        String invalidParams = "A megadott paraméterek nem megfelelőek.";

        System.out.println("Válaszd ki a hadseregedet! (pl.: 1)");

        for (int i = 0; i < town.armies.size(); i++) {
            System.out.println("");
            System.out.println(i + 1 + ": " + town.armies.get(i).getColorSign());
        }

        Scanner scanner = new Scanner(System.in);
        String[] armyReader = scanner.nextLine().split("[ ]");

        if (armyReader.length == 0) {
            System.out.println(noEnoughParam);
            System.out.println("");
            townMode(town, player);
        }
        if (armyReader.length > 1) {
            System.out.println(tooMuchParams);
            System.out.println("");
            townMode(town, player);
        }
        if (armyReader.length == 1 && armyReader[0].matches("[0-9]")) {         // Ha csak egy adatot adott meg és az szám
            // Így 10 seregnél nem lehet több!!!

            // Ha hadseregként valid számot jelölt meg
            if (Integer.parseInt(armyReader[0]) - 1 < town.armies.size()) {

                int indexOfArmy = Integer.parseInt(armyReader[0]) - 1;

                if (town.armies.get(indexOfArmy).getSoldiers() > 0 || town.getSoldiers() > 0) {

                    System.out.println("");
                    System.out.println("Add meg, hogy mennyi Alapszintű katonát szeretnél a seregedhez hozzáadni, vagy a seregedből" +
                        " a városodba áthelyezni (a városba történő áthelyezés esetében negatív számot használj). A seregedben minimum" +
                        " 1 katonának maradnia kell!");
                    System.out.println("");
                    System.out.println("A városban lévő Alapszintű katonák száma: " + town.getSoldiers());
                    System.out.println("A " + town.armies.get(indexOfArmy).getColorSign() +
                        " számú hadseregedben lévő Alapszintű katonák száma: " + town.armies.get(indexOfArmy).getSoldiers());

                    Scanner soldsNum = new Scanner(System.in);
                    int solds = soldsNum.nextInt();

                // Ha negatív számot adott meg:
                    if (solds < 0) {

                        if ((town.armies.get(indexOfArmy).getUpgradedSoldiers() == 0
                            && town.armies.get(indexOfArmy).getSoldiers() <= Math.abs(solds)) ||
                            (town.armies.get(indexOfArmy).getUpgradedSoldiers() > 0
                                    && town.armies.get(indexOfArmy).getSoldiers() < Math.abs(solds)) ) {

                            System.out.println("Nincs a seregedben ehhez elég Alapszintű katona.");
                            System.out.println("");
                            armyHandler(town, player);
                        }
                        if ((town.armies.get(indexOfArmy).getUpgradedSoldiers() == 0
                                && town.armies.get(indexOfArmy).getSoldiers() > Math.abs(solds)) ||
                                (town.armies.get(indexOfArmy).getUpgradedSoldiers() > 0
                                && town.armies.get(indexOfArmy).getSoldiers() >= Math.abs(solds)) ) {

                            if (town.getAllSoldierRooms() < (town.getSoldiers() + town.getUpgradedSoldiers()) + Math.abs(solds)) {

                                System.out.println("Nincs a városban elég hely a katonák elszállásolásához.");
                                System.out.println("");
                                armyHandler(town, player);
                            }
                            if (town.getAllSoldierRooms() >= (town.getSoldiers() + town.getUpgradedSoldiers()) + Math.abs(solds)) {

                                town.armies.get(indexOfArmy).setSoldiers(town.armies.get(indexOfArmy).getSoldiers() - Math.abs(solds));
                                town.setSoldiers(town.getSoldiers() + Math.abs(solds));
                            }
                        }
                    }
                    if (solds >= 0) {

                        if (town.getSoldiers() < solds) {

                            System.out.println("Nincs a városban ehhez elég Alapszintű katona.");
                            System.out.println("");
                            armyHandler(town, player);
                        }

                        if (town.getSoldiers() >= solds) {

                            town.setSoldiers(town.getSoldiers() - solds);
                            town.armies.get(indexOfArmy).setSoldiers(town.armies.get(indexOfArmy).getSoldiers() + solds);
                        }
                    }
                }
                if (town.armies.get(indexOfArmy).getUpgradedSoldiers() > 0 || town.getUpgradedSoldiers() > 0) {

                    System.out.println("");
                    System.out.println("Add meg, hogy mennyi Képzett katonát szeretnél a seregedhez hozzáadni, vagy a seregedből" +
                            " a városodba áthelyezni (a városba történő áthelyezés esetében negatív számot használj). A seregedben minimum" +
                            " 1 katonának maradnia kell!");
                    System.out.println("");
                    System.out.println("A városban lévő Képzett katonák száma: " + town.getUpgradedSoldiers());
                    System.out.println("A " + town.armies.get(indexOfArmy).getColorSign() +
                            " számú hadseregedben lévő Képzett katonák száma: " + town.armies.get(indexOfArmy).getUpgradedSoldiers());

                    Scanner upgSoldsNum = new Scanner(System.in);
                    int upgSolds = upgSoldsNum.nextInt();

                    // Ha negatív számot adott meg:
                    if (upgSolds < 0) {

                        if ((town.armies.get(indexOfArmy).getSoldiers() == 0
                                && town.armies.get(indexOfArmy).getUpgradedSoldiers() <= Math.abs(upgSolds)) ||
                                (town.armies.get(indexOfArmy).getSoldiers() > 0
                                        && town.armies.get(indexOfArmy).getUpgradedSoldiers() < Math.abs(upgSolds))) {

                            System.out.println("Nincs a seregedben ehhez elég Képzett katona.");
                            System.out.println("");
                            armyHandler(town, player);
                        }

                        if ((town.armies.get(indexOfArmy).getSoldiers() == 0
                                && town.armies.get(indexOfArmy).getUpgradedSoldiers() > Math.abs(upgSolds)) ||
                                (town.armies.get(indexOfArmy).getSoldiers() > 0
                                        && town.armies.get(indexOfArmy).getUpgradedSoldiers() >= Math.abs(upgSolds))) {

                            if (town.getAllSoldierRooms() < (town.getSoldiers() + town.getUpgradedSoldiers()) + Math.abs(upgSolds)) {

                                System.out.println("Nincs a városban elég hely a katonák elszállásolásához.");
                                System.out.println("");
                                armyHandler(town, player);
                            }
                            if (town.getAllSoldierRooms() >= (town.getSoldiers() + town.getUpgradedSoldiers()) + Math.abs(upgSolds)) {

                                town.armies.get(indexOfArmy).setUpgradedSoldiers(town.armies.get(indexOfArmy).getUpgradedSoldiers() - Math.abs(upgSolds));
                                town.setUpgradedSoldiers(town.getUpgradedSoldiers() + Math.abs(upgSolds));
                                townMode(town, player);
                            }
                        }
                    }
                    if (upgSolds >= 0) {

                        if (town.getUpgradedSoldiers() < upgSolds) {

                            System.out.println("Nincs a városban ehhez elég Képzett katona.");
                            System.out.println("");
                            armyHandler(town, player);
                        }
                        if (town.getUpgradedSoldiers() >= upgSolds) {

                            town.setUpgradedSoldiers(town.getUpgradedSoldiers() - upgSolds);
                            town.armies.get(indexOfArmy).setUpgradedSoldiers(town.armies.get(indexOfArmy).getUpgradedSoldiers() + upgSolds);
                            townMode(town, player);
                        }
                    }
                }
                townMode(town, player);
            }
            else {
                System.out.println(invalidParams);
                System.out.println("");
                townMode(town, player);
            }
        }

    }

    public void armySearcher(Town town) {
        for (int i = town.getPositionI() + 1; i < Board.board.length && i < town.getPositionI() + 7; i++) {
            for (int j = town.getPositionJ() + 1; j < Board.board[i].length && j < town.getPositionJ() + 7; j++) {
                if (Board.board[i][j] instanceof Army && Board.board[i][j].getColor().equals(town.getColor())) {
                    town.armies.add( (Army) Board.board[i][j]);
                }
            }
        }
        for (int i = town.getPositionI() + 1; i < Board.board.length && i < town.getPositionI() + 7; i++) {
            for (int j = town.getPositionJ(); j > -1 && j > town.getPositionJ() - 7; j--) {
                if (Board.board[i][j] instanceof Army && Board.board[i][j].getColor().equals(town.getColor())) {
                    town.armies.add( (Army) Board.board[i][j]);
                }
            }
        }
        for (int i = town.getPositionI(); i > -1 && i > town.getPositionI() - 7; i--) {
            for (int j = town.getPositionJ() + 1; j < Board.board[i].length && j < town.getPositionJ() + 7; j++) {
                if (Board.board[i][j] instanceof Army && Board.board[i][j].getColor().equals(town.getColor())) {
                    town.armies.add( (Army) Board.board[i][j]);
                }
            }
        }
        for (int i = town.getPositionI(); i > -1 && i > town.getPositionI() - 7; i--) {
            for (int j = town.getPositionJ(); j > -1 && j > town.getPositionJ() - 7; j--) {
                if (Board.board[i][j] instanceof Army && Board.board[i][j].getColor().equals(town.getColor())) {
                    town.armies.add( (Army) Board.board[i][j]);
                }
            }
        }
    }



    private boolean validateSoldComm(Town town, String[] soldierNumReader) {

        String noEnoughParam = "Nem adtál meg elegendő paramétert!";
        String invalidParams = "A megadott paraméterek nem megfelelőek.";

        if (soldierNumReader.length == 0) {

            System.out.println(noEnoughParam);
            System.out.println("");
            return false;
        }
        else if (soldierNumReader[0].length() == 1) {        // Ha 1 jegyű adat került megadásra

            StringBuilder soldierNum = new StringBuilder(soldierNumReader[0]);

            String a = "" + soldierNum.charAt(0);

            if (a.matches("[0-9]") && Integer.parseInt(soldierNumReader[0]) > 0 &&
                    (Integer.parseInt(soldierNumReader[0]) <= town.getSoldiers() || Integer.parseInt(soldierNumReader[0]) <= town.getUpgradedSoldiers()) ) {
                return true;
            }
            else {
                System.out.println(invalidParams);
                return false;
            }
        }
        else if (soldierNumReader[0].length() == 2) {        // Ha 2 jegyű adat került megadásra

            StringBuilder soldierNum = new StringBuilder(soldierNumReader[0]);

            String a = "" + soldierNum.charAt(0);
            String b = "" + soldierNum.charAt(1);

            if (a.matches("[0-9]") && b.matches("[0-9]") && Integer.parseInt(soldierNumReader[0]) > 0 &&
                    (Integer.parseInt(soldierNumReader[0]) <= town.getSoldiers() || Integer.parseInt(soldierNumReader[0]) <= town.getUpgradedSoldiers()) ) {
                return true;
            }
            else {
                System.out.println(invalidParams);
                return false;
            }
        }
        else if (soldierNumReader[0].length() == 3) {        // Ha 3 jegyű adat került megadásra

            StringBuilder soldierNum = new StringBuilder(soldierNumReader[0]);

            String a = "" + soldierNum.charAt(0);
            String b = "" + soldierNum.charAt(1);
            String c = "" + soldierNum.charAt(2);

            if (a.matches("[0-9]") && b.matches("[0-9]") && c.matches("[0-9]") && Integer.parseInt(soldierNumReader[0]) > 0 &&
                    (Integer.parseInt(soldierNumReader[0]) <= town.getSoldiers() || Integer.parseInt(soldierNumReader[0]) <= town.getUpgradedSoldiers()) ) {
                return true;
            }
            else {
                System.out.println(invalidParams);
                return false;
            }
        }
        else if (soldierNumReader[0].length() == 4) {        // Ha 4 jegyű adat került megadásra

            StringBuilder soldierNum = new StringBuilder(soldierNumReader[0]);

            String a = "" + soldierNum.charAt(0);
            String b = "" + soldierNum.charAt(1);
            String c = "" + soldierNum.charAt(2);
            String d = "" + soldierNum.charAt(3);

            if (a.matches("[0-9]") && b.matches("[0-9]") && c.matches("[0-9]") && d.matches("[0-9]") && Integer.parseInt(soldierNumReader[0]) > 0 &&
                    (Integer.parseInt(soldierNumReader[0]) <= town.getSoldiers() || Integer.parseInt(soldierNumReader[0]) <= town.getUpgradedSoldiers()) ) {
                return true;
            }
            else {
                System.out.println(invalidParams);
                return false;
            }
        }
        return false;
    }



    public boolean validateBuilding(Cell[][] townBoard, String[] buildingPointReader) {

        String noEnoughParam = "Nem adtál meg elegendő paramétert!";
        String giveNumbers = "Kérlek, csak számokat adj meg!";
        String invalidParams = "A megadott paraméterek nem megfelelőek.";
        String thereIsBuild = "A megadott paraméterek nem megfelelőek, mert megjelölt helyen épület található.";

        if (buildingPointReader.length == 1 || buildingPointReader.length == 0) {
            System.out.println(noEnoughParam);
            System.out.println("");
            return false;
        }
        if (buildingPointReader.length > 1) {

            if (buildingPointReader[0].length() > 1 || buildingPointReader[1].length() > 1) {   // Ha az 1. vagy a 2. karakterlánc több jegyű
                System.out.println(invalidParams);
                System.out.println("");
                return false;
            }
            if ((buildingPointReader[0].length() == 1 && buildingPointReader[1].length() == 1)) {    // Ha az 1. és a 2. karakterlánc is 1 jegyű

                if (buildingPointReader[0].matches("[0-9]") && buildingPointReader[1].matches("[0-9]")) {

                    if (Integer.parseInt(buildingPointReader[0]) > 0 && Integer.parseInt(buildingPointReader[0]) < townBoard.length - 1 &&
                            Integer.parseInt(buildingPointReader[1]) > 0 && Integer.parseInt(buildingPointReader[1]) < townBoard[Integer.parseInt(buildingPointReader[0])].length - 1) {

                        if (!(townBoard[Integer.parseInt(buildingPointReader[0])][Integer.parseInt(buildingPointReader[1])] instanceof Town) &&
                                !(townBoard[Integer.parseInt(buildingPointReader[0])][Integer.parseInt(buildingPointReader[1])] instanceof Building)) {
                            return true;
                        }
                        else {
                            System.out.println(thereIsBuild);
                            System.out.println("");
                            return false;
                        }
                    }
                    else {
                        System.out.println(invalidParams);
                        System.out.println("");
                        return false;
                    }

                }
                else {
                    System.out.println(giveNumbers);
                    System.out.println("");
                    return false;
                }
            }
        }
        return false;
    }


    public void printGame(Player player) {
        Board.printBoard(Board.board);
        if (player.isTurn() == true) {
            System.out.println(player);
            System.out.println("");
            System.out.println("Városaid:");
            System.out.println("");
            for (int i = 0; i < player.getTowns().size(); i++) {
                System.out.println(i + 1 + ". Város: "  + player.getTowns().get(i).getName());
                System.out.println("Lakosság: " + player.getTowns().get(i).getPopulation());
                if (player.getTurnCount() > 1) {
                    System.out.println("Az előző héthez képest a lakosság " + player.getTowns().get(i).getPopulationIncrease() + " fővel növekedett és "
                            + player.getTowns().get(i).getPopulationDecrease() + " fővel csökkent, így a változás " +
                            (player.getTowns().get(i).getPopulationIncrease() - player.getTowns().get(i).getPopulationDecrease()) + " fő.");
                    System.out.println("Az adóbevételed összege: " + player.getTowns().get(i).getTax() + " arany.");
                    System.out.println("A banktól származó bevételed összege: " + player.getTowns().get(i).getBankIncome() + " arany.");
                }
                System.out.println("Alapszintű katonák száma: " + player.getTowns().get(i).getSoldiers());
                System.out.println("Képzett katonák száma: " + player.getTowns().get(i).getUpgradedSoldiers());
                System.out.println("Katonai szálláshelyek: " +  player.getTowns().get(i).getAllSoldierRooms());
                System.out.println("");
                /* System.out.println("Épületek:");
                for (int j = 0; j < player.getTowns().get(i).buildings.size(); j++) {
                    System.out.println("\t" + player.getTowns().get(i).buildings.get(j).getName());
                } */
            }
            System.out.println("");
            System.out.println("Hadseregeid:");
            System.out.println("");
            for (int i = 0; i < player.getArmies().size(); i++) {
                if (player.getArmies().size() > 0) {
                    System.out.println(player.getArmies().get(i).getColorSign());
                    System.out.println("Alapszintű katonák száma: " + player.getArmies().get(i).getSoldiers());
                    System.out.println("Képzett katonák száma: " + player.getArmies().get(i).getUpgradedSoldiers());
                    System.out.println("Felhasználható lépések száma: " + player.getArmies().get(i).getSteps());
                    System.out.println("Átvett tevékenységpont: " + player.getArmies().get(i).getActionPoint());
                    System.out.println("");
                }
            }
        }
    }



}
