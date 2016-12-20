import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by gilshe on 12/20/16.
 */
public class Image extends Component {
    private BufferedImage image;
    private String name;

    public Image(String name) {
        this.name = name;

        try {
            image = ImageIO.read(new File(ImageHandler.imagesDir() + image));
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
    //TODO
    public void setColor(int x, int y, double color){

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

    public int getColor(int x, int y) {
        return 0;
    }


}
