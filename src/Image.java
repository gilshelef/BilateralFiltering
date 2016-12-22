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
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.name = name + JPG;
    }

    public Image(String iname){
        iname += JPG;
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

    public BufferedImage getBuffer() {
        return image;
    }

    public void setColor(Pixel p, int color){
        image.setRGB(p.getCol(), p.getRow(), color);
    }

    public Color getColor(Pixel p) {
        return new Color(image.getRGB(p.getCol(), p.getRow()));
    }

    public boolean inRange(int row, int col) {
        return col < image.getWidth() && col >= 0 && row < image.getHeight() && row >= 0;
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

}
