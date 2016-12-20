import java.util.LinkedList;
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
        Image processedImage = new Image(image.getName(), true);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel center = new Pixel(x, y);
                double clr = calcColor(center, image);
                processedImage.setColor(center, clr);
            }
        }
        return processedImage;
    }

    private double calcColor(Pixel center, Image image) {
        List<Pixel> omega = getPixelsInWindow(center, image);
        double normal = calcSum(center, omega, 1, image);
        double summation = calcSum(center, omega, image.getColor(center), image);
        return summation / normal;
    }

    private double calcSum(Pixel center, List<Pixel> omega, int val, Image image) {
        double sum = 0;
        for(Pixel p: omega){
            double geometric = geometric(center, p);
            double intensity = intensity(image, center, p);

            sum += (geometric * intensity * val);
        }
        return sum;
    }

    private double intensity(Image image, Pixel center, Pixel p) {
        double x = -Math.pow(image.getColor(center) - image.getColor(p), 2) / (2 * Math.pow(sigmaR, 2));
        return Math.exp(x);
    }

    private double geometric(Pixel center, Pixel p) {
        double x = -Math.pow(center.distance(p), 2) / (2 * Math.pow(sigmaD, 2));
        return Math.exp(x);
    }


    private List<Pixel> getPixelsInWindow(Pixel center, Image image) {
        List<Pixel> pixelsInWindow = new LinkedList<>();
        int row = center.getRow() - (window / 2);

        for(; row < center.getRow() + (window / 2) + 1; row++) {
            for (int col = center.getCol() - (window / 2); col < center.getCol() + (window / 2) + 1; col++) {
                if(image.inRange(row, col))
                    pixelsInWindow.add(new Pixel(col, row));
            }
        }


        return pixelsInWindow;
    }
}
