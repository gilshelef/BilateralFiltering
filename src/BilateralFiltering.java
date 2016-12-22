import java.awt.*;

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
        String imageName = image.getName().substring(0, image.getName().indexOf(".")) + "_processed";
        Image filteredImage = new Image(imageName, image.getWidth(), image.getHeight());
        for (int y = 0; y < image.getHeight(); y++) { // height
            for (int x = 0; x < image.getWidth(); x++) { // width
                Pixel center = new Pixel(x, y);
                Color clr = calcColor(center, image);
                filteredImage.setColor(center, clr.getRGB());
            }
        }
        return filteredImage;
    }

    private Color calcColor(Pixel center, Image image) {

        int rowMax = center.getRow() + (window / 2) + 1;
        int colMax = center.getCol() + (window / 2) + 1;
        float sum = 0, normal = 0;

        for(int row = center.getRow() - (window / 2); row < rowMax; row++) {
            for (int col = center.getCol() - (window / 2); col < colMax; col++) {
                if (!image.inRange(row, col))
                    continue;

                Pixel p = new Pixel(col, row);
                double imageDist = spaceDistance(center, p);
                double colorDist = colorDistance(image, center, p);
                double mul = imageDist * colorDist;
                normal += mul;
                sum += (mul * image.getColor(p).getRed());
            }
        }

        double rgb = sum / normal;
        int red = (int) rgb;
        return new Color(red, red, red);
    }

    private double spaceDistance(Pixel center, Pixel p) {
        double spaceDist = center.distance(p);
        return calc(spaceDist, sigmaD);
    }

    private double colorDistance(Image image, Pixel p1, Pixel p2) {
        Color color1 = image.getColor(p1);
        Color color2 = image.getColor(p2);

        double colorDist = color1.getRed() - color2.getRed();
        return calc(colorDist, sigmaR);
    }

    private double calc(double val, double sigma) {
        val /= sigma;
        val *= val; // x^2
        val *= -0.5;
        return Math.exp(val);
    }
//
//    private List<Pixel> getPixelsInWindow(Pixel center, Image image) {
//        List<Pixel> pixelsInWindow = new LinkedList<>();
//        int rowMax = center.getRow() + (window / 2) + 1;
//        int colMax = center.getCol() + (window / 2) + 1;
//
//        for(int row = center.getRow() - (window / 2); row < rowMax; row++) {
//            for (int col = center.getCol() - (window / 2); col < colMax; col++) {
//                if (image.inRange(row, col))
//                    pixelsInWindow.add(new Pixel(col, row));
//            }
//        }
//        return pixelsInWindow;
//    }


//    private double calcColor(Pixel center, Image image) {
//        List<Pixel> omega = getPixelsInWindow(center, image);
//        double normal = calcSum(image, center, omega, false);
//        double summation = calcSum(image, center, omega, true);
//        return summation / normal;
//    }
//
//    private double calcSum(Image image, Pixel center, List<Pixel> omega, boolean withPixel) {
//        double sum = 0;
//        for(Pixel p: omega){
//            double geometricDist = geometric(center, p);
//            double intensityDist = intensity(image, center, p);
//            if(withPixel)
//                sum += (geometricDist * intensityDist * image.getColor(p));
//            else sum += (geometricDist * intensityDist);
//        }
//        return sum;
//    }

    // Intensity distance
//    private double intensity(Image image, Pixel center, Pixel p) {
//        double val = Math.abs(image.getColor(center) - image.getColor(p)) / sigmaR;
//        val = Math.pow(val, 2);
//        val *= -0.5;
//        val = Math.exp(val);
//        return val;
//
//    }

//    // Euclidean part
//    private double geometric(Pixel center, Pixel p) {
//        double val = center.distance(p) / sigmaD;
//        val = Math.pow(val, 2);
//        val *= -0.5;
//        val = Math.exp(val);
//        return val;
//    }

}
