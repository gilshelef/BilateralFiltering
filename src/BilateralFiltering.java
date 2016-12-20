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
        System.out.println("pixels: " + image.getHeight() * image.getWidth());
        int changed = 0;
        Image filteredImage = new Image(image.getName() + "_processed", image.getWidth(), image.getHeight());

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel center = new Pixel(x, y);
                double clr = calcColor(center, image);
                if(clr != image.getColor(center))
                    changed++;
                filteredImage.setColor(center, clr);
            }
        }

        System.out.println("changed: " + changed);
        return filteredImage;
    }

    private double calcColor(Pixel center, Image image) {
        List<Pixel> omega = getPixelsInWindow(center, image);
        double normal = calcSum(image, center, omega, false);
        double summation = calcSum(image, center, omega, true);
        return summation / normal;
    }

    private double calcSum(Image image, Pixel center, List<Pixel> omega, boolean withPixel) {
        double sum = 0;
        for(Pixel p: omega){
            double geometricDist = geometric(center, p);
            double intensityDist = intensity(image, center, p);
            if(withPixel)
                sum += (geometricDist * intensityDist * image.getColor(p));
            else sum += (geometricDist * intensityDist);
        }
        return sum;
    }

    // Intensity distance
    private double intensity(Image image, Pixel center, Pixel p) {
        double val = Math.abs(image.getColor(center) - image.getColor(p)) / sigmaR;
        val = Math.pow(val, 2);
        val *= -0.5;
        val = Math.exp(val);
        return val;

    }

    // Euclidean part
    private double geometric(Pixel center, Pixel p) {
        double val = center.distance(p) / sigmaD;
        val = Math.pow(val, 2);
        val *= -0.5;
        val = Math.exp(val);
        return val;
    }


    private List<Pixel> getPixelsInWindow(Pixel center, Image image) {
        List<Pixel> pixelsInWindow = new LinkedList<>();
        int rowMax = center.getRow() + (window / 2) + 1;
        int colMax = center.getCol() + (window / 2) + 1;

        for(int row = center.getRow() - (window / 2); row < rowMax; row++)
            for (int col = center.getCol() - (window / 2); col < colMax; col++) {
                if (image.inRange(row, col))
                    pixelsInWindow.add(new Pixel(col, row));
            }



        return pixelsInWindow;
    }
}
