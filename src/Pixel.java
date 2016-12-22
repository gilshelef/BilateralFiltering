/**
 * Created by gilshe on 12/20/16.
 */
public class Pixel {

    private int col;
    private int row;

    public Pixel(int width, int height){
        this.col = width;
        this.row = height;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public double distance(Pixel other) {
        double height = row - other.getRow();
        double width = col - other.getCol();
//        return height*height + width*width;
        return Math.sqrt(height*height + width*width);
    }

    public String toString(){
        return "[" + row + ", " + col + "]";
    }
}
