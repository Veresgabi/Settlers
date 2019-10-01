package flowacademy.settlersstrategy;

public abstract class Cell {
    private int positionI;
    private int positionJ;
    private String color;
    private int id;

    public Cell(int positionI, int positionJ, String color, int id) {
        this.positionI = positionI;
        this.positionJ = positionJ;
        this.color = color;
        this.id = id;
    }

    public Cell(int positionI, int positionJ, String color) {
        this.positionI = positionI;
        this.positionJ = positionJ;
        this.color = color;
    }

    public Cell() {
        this.positionI = 0;
        this.positionJ = 0;
        this.color = "none";
    }

    public static Cell[][] putCellToBoard(Cell[][] board, Cell element) {
        board[element.positionI][element.positionJ] = element;
        return board;
    }

    public int getPositionI() {
        return positionI;
    }

    public void setPositionI(int positionI) {
        this.positionI = positionI;
    }

    public int getPositionJ() {
        return positionJ;
    }

    public void setPositionJ(int positionJ) {
        this.positionJ = positionJ;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cell";
    }
}

