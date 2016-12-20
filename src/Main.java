/**
 * Created by gilshe on 12/19/16.
 */
public class Main {
    public static void main(String[] args){

        String imageName = "kitten-orig.jpg";
        ImageHandler imageHandler = new ImageHandler();

        BilateralFiltering bf = new BilateralFiltering(
                Double.valueOf(args[0]), Double.valueOf(args[1]), Integer.valueOf(args[2]));


        Image originalImage = new Image(imageName);
        imageHandler.display(originalImage.getName(), originalImage);
        Image filteredImage = bf.filter(originalImage);
        imageHandler.display(filteredImage.getName(), filteredImage);
    }
}
