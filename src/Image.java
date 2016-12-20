import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by gilshe on 12/20/16.
 */
public class Image extends Component {
    private static final String JPG = ".jpg";
    private BufferedImage image;
    private String name;

    public Image(String name, int width, int height){
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.name = name + JPG;
    }

    public Image(String iname){
        iname = ImageHandler.imagesDir() + iname + JPG;
        Path ipath = Paths.get(iname);
        File ifile = ipath.toFile(); // assuming file exists
        this.name = ifile.getName();
        try {
            image = ImageIO.read(ifile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return image.getHeight();
    }

    public int getWidth() {
        return image.getWidth();
    }

    public void setColor(Pixel center, double color){
        Color c = new Color((int) color);
        image.setRGB(center.getCol(), center.getRow(), c.getRGB());
    }

    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }

    public Dimension getPreferredSize() {
        if (image == null) {
            return new Dimension(100,100);
        } else {
            return new Dimension(image.getWidth(null), image.getHeight(null));
        }
    }

    public int getColor(Pixel p) {
        Color c = new Color(image.getRGB(p.getCol(), p.getRow()));
        return c.getRGB();
    }

    public boolean inRange(int row, int col) {
        return  col < image.getWidth() && col >= 0 && row < image.getHeight() && row >= 0;
    }
}
