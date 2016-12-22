/**
 * Created by gilshe on 12/19/16.
 */
public class Main {
    public static void main(String[] args){

        double sigmaD = 30;
        double sigmaR = 30;
        int window = 7;
//        String imageName = args[3];


        BilateralFiltering bf = new BilateralFiltering(sigmaD, sigmaR, window);

//        BilateralFiltering bf = new BilateralFiltering(
//                Double.valueOf(args[0]), Double.valueOf(args[1]), Integer.valueOf(args[2]));

        String imageName = "lena";

        Image originalImage = new Image(ImageHandler.imagesDir() + imageName);

        Image filteredImage = bf.filter(originalImage);
        ImageHandler.write(filteredImage);
        ImageHandler.display(filteredImage.getName(), filteredImage);
    }
}
