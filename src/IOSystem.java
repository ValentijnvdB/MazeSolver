import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Handles In- and Output
 */

public class IOSystem {

    /**
     * read the image
     * @return image as BufferedImage
     * @throws IOException something goes wrong
     */
    public Maze readImage(String fileName) throws IOException {
        File input_file = new File("C:\\Users\\20191697\\IdeaProjects\\MazeSolver\\src\\Images\\" + fileName); //image file path
        BufferedImage image = ImageIO.read(input_file);
        int w = image.getWidth();
        int h = image.getHeight();
        //Convert colormodel
        BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        Maze maze = new Maze(newImage, w, h);

        System.out.println("Reading complete.");
        return maze;
    }

    /**
     * writes output image
     * @param maze Maze to write
     * @throws IOException something goes wrong
     */
    public void writeImage(Maze maze) throws IOException {
        File output_file = new File("C:\\Users\\20191697\\IdeaProjects\\MazeSolver\\src\\Images\\output.png");
        ImageIO.write(maze.getImage(), "png", output_file);
        System.out.println("Writing complete.");
    }

}
