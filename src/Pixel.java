/**
 * Created by gilshe on 12/20/16.
 */
public class Pixel {

    private int col;
    private int row;

    public Pixel(int x, int y){
        this.col = x;
        this.row = y;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public double distance(Pixel other) {
        double ycoord = Math.abs (row - other.getRow());
        double xcoord = Math.abs (col- other.getCol());
        return Math.sqrt(Math.pow(ycoord, 2) + Math.pow(xcoord, 2));
    }

    public String toString(){
        return "[" + row + ", " + col + "]";
    }
}
