import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MazePanel extends JPanel {

    private BufferedImage image;

    public MazePanel(BufferedImage img) {
        image = img;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            double ratioWidth = (double)getWidth() / image.getWidth();
            double ratioHeight = (double)getHeight() / image.getHeight();
            double ratio = ratioWidth;
            if (ratioHeight < ratioWidth) { // Make sure the whole maze is onscreen
                ratio = ratioHeight;
            }
            g.drawImage(image, 0, 0,
                    (int)Math.floor(image.getWidth() * ratio), (int)Math.floor(image.getHeight() * ratio),
                    this);
        }
    }



    public void updateImage(BufferedImage img) {
        image = img;
    }

}
