import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by gilshe on 12/19/16.
 */

class ImageHandler {
    private static final  String IMAGE_DIR = "images/";
    private static final  String FILTERED_IMAGE_DIR = "filtered-images/";


    public static void display(String title, Image image) {
        JFrame f = new JFrame(title);

        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        f.add(image);
        f.pack();
        f.setVisible(true);
    }


    public static String imagesDir() {
        return IMAGE_DIR;
    }

    public static void write(Image image) {
        try {
            BufferedImage bi = image.getBuffer();
            File outputFile = new File(FILTERED_IMAGE_DIR + image.getName());
            ImageIO.write(bi, "jpg", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



