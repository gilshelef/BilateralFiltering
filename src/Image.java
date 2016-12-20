import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by gilshe on 12/20/16.
 */
public class Image extends Component {
    private BufferedImage image;
    private String name;

    public Image(String name, boolean newImg) {
        String filePath = ImageHandler.imagesDir() + name;
        Path FROM = Paths.get(filePath);

        File file = null;

        if(newImg) {
            Path TO = Paths.get(filePath + "_processed.jpg"); //TODO
            if(!TO.toFile().exists()) {
                try {
                    Path res = Files.copy(FROM, TO);
                    file = res.toFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else file = TO.toFile();
        }

        else file = FROM.toFile();

        this.name = file.getName();

        try {
            image = ImageIO.read(file);
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
        int clr = (int) color;
        image.setRGB(center.getCol(), center.getRow(), clr);
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
