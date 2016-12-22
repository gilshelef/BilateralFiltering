/**
 * Created by gilshe on 12/19/16.
 */
public class Main {
    public static void main(String[] args){

        int sigmaD = Integer.valueOf(args[0]);
        int sigmaR = Integer.valueOf(args[1]);
        int window = Integer.valueOf(args[2]);
        String imageName = args[3];

        BilateralFiltering bf = new BilateralFiltering(sigmaD, sigmaR, window);

        Image originalImage = new Image(ImageHandler.imagesDir() + imageName);
        Image filteredImage = bf.filter(originalImage);
        ImageHandler.write(filteredImage);
    }
}
