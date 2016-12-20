import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by gilshe on 12/19/16.
 */

class ImageHandler {
    private static final  String IMAGE_DIR = "images/";


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
}



