import java.util.List;

/**
 * Created by gilshe on 12/19/16.
 */
public class BilateralFiltering {
    private final double sigmaR;
    private final double sigmaD;
    private final int window;

    public BilateralFiltering(double sigmaD, double sigmaR, int window) {
        this.sigmaD = sigmaD;
        this.sigmaR = sigmaR;
        this.window = window;
    }


    public Image filter(Image image) {
        Image processedImage = new Image("Processed" + image.getName());
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                double clr = calcColor(x, y, image);
                processedImage.setColor(x, y, clr);
            }
        }
        return processedImage;
    }

    private double calcColor(int x, int y, Image image) {
        Pixel center = new Pixel(x, y);
        List<Pixel> omega = getPixelsInWindow(x,y);
        double normal = calcSum(center, omega, 1);
        double summation = calcSum(center, omega, image.getColor(x,y));
        return summation / normal;
    }

    private double calcSum(Pixel center, List<Pixel> omega, int val) {
        double sum = 0;
        for(Pixel p: omega){
            double geometric = geometric(center, p);
            double intensity = intensity(center, p);

            sum += (geometric * intensity * val);
        }
        return sum;
    }

    //TODO
    private double intensity(Pixel center, Pixel p) {
        return 0;
    }

    //TODO
    private double geometric(Pixel center, Pixel p) {
        return 0;
    }


    //TODO
    private List<Pixel> getPixelsInWindow(int xCenter, int yCenter) {
        return null;
    }
}
