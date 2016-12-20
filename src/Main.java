/**
 * Created by gilshe on 12/19/16.
 */
public class Main {
    public static void main(String[] args){

        BilateralFiltering bf = new BilateralFiltering(
                Double.valueOf(args[0]), Double.valueOf(args[1]), Integer.valueOf(args[2]));

        String imageName = "child-orig";

        Image originalImage = new Image(imageName);
        ImageHandler.display(originalImage.getName(), originalImage);
        Image filteredImage = bf.filter(originalImage);
        ImageHandler.display(filteredImage.getName(), filteredImage);
    }
}
