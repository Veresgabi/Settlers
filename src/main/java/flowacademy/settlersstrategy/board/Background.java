package flowacademy.settlersstrategy.board;


import flowacademy.settlersstrategy.Cell;

public class Background extends Cell {

    private String sign;

    public Background() {
        this.sign = ". ";
    }

    public Background(int positionI, int positionJ, String color, String sign) {
        super(positionI, positionJ, color);
        this.sign = sign;
    }

    public Background(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return sign;
    }

}
