package flowacademy.settlersstrategy.board;

import flowacademy.settlersstrategy.Cell;
import flowacademy.settlersstrategy.army.Army;

public class Board {

    public static Cell[][] board = new Cell[100][100];

    public static Cell[][] fillBoardBg(Cell[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Background();
                // System.out.print(board[i][j]);
            }
        }
        return board;
    }

    /*public static Cell[][] putArmytoBoard(Cell[][] board) {
        board[99][10] = new Army(10, 10);
        return board;
    } */

   /*  public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    } */

    public static void printBoard(Cell[][] board) {
        int i = 0;
        while (i < board.length) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            i++;
            System.out.println();
        }
    }



    /* @Override
    public String toString() {
        return "Board";
    } */
}
