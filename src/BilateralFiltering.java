import java.awt.*;

/**
 * Created by gilshe on 12/19/16.
 */
public class BilateralFiltering {

    private enum Channel { RED, GREEN, BLUE }
    private final int sigmaR;
    private final int sigmaD;
    private final int window;

    public BilateralFiltering(int sigmaD, int sigmaR, int window) {
        this.sigmaD = sigmaD;
        this.sigmaR = sigmaR;
        this.window = window;
    }


    public Image filter(Image image) {

        String imageName = image.getName().substring(0, image.getName().indexOf(".")) + "_processed_" + sigmaD + "_" + sigmaR;
        Image filteredImage = new Image(imageName, image.getWidth(), image.getHeight());
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel center = new Pixel(x, y);

                int red = calcColor(center, image, Channel.RED);
                int blue = calcColor(center, image, Channel.BLUE);
                int green = calcColor(center, image, Channel.GREEN);

                assert (red == blue && blue == green);
                Color clr = new Color(red, blue, green);

                filteredImage.setColor(center, clr.getRGB());
            }
        }
        return filteredImage;
    }

    private int calcColor(Pixel center, Image image, Channel channel) {

        int rowMax = center.getRow() + (window / 2) + 1;
        int colMax = center.getCol() + (window / 2) + 1;
        float sum = 0, normal = 0;

        for(int row = center.getRow() - (window / 2); row < rowMax; row++) {
            for (int col = center.getCol() - (window / 2); col < colMax; col++) {
                if (!image.inRange(row, col))
                    continue;

                Pixel p = new Pixel(col, row);
                double imageDist = spaceDistance(center, p);
                double colorDist = colorDistance(image, center, p, channel);
                double mul = imageDist * colorDist;
                normal += mul;
                sum += (mul * getChannel(image.getColor(p), channel));
            }
        }

        double channelColor = sum / normal;
        return (int) channelColor;
    }

    private double spaceDistance(Pixel center, Pixel p) {
        double spaceDist = center.distance(p);
        return calc(spaceDist, sigmaD);
    }

    private double colorDistance(Image image, Pixel p1, Pixel p2, Channel channel) {
        Color color1 = image.getColor(p1);
        Color color2 = image.getColor(p2);

        double colorDist = getChannel(color1, channel) - getChannel(color2, channel);
        return calc(colorDist, sigmaR);
    }

    private double getChannel(Color color, Channel channel) {
        switch (channel){
            case RED:
                return color.getRed();
            case GREEN:
                return color.getGreen();
            case BLUE:
                return color.getBlue();
            default:
                throw new RuntimeException("No such color: " + channel.toString());
        }

    }

    private double calc(double val, double sigma) {
        val /= sigma;
        val *= val; // x^2
        val *= -0.5;
        return Math.exp(val);
    }

}
