import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Solve a maze
 *
 * Black squares are walls, all other colors are walkable
 *
 * A valid maze is an image with Black pixels around the edge, and entrance at (1, 0) and
 * exactly one exit at the bottom (not in the corners)
 */
public class MazeSolver {

    Maze maze;

    /**
     * main method
     */
    private void run(int w, int h) {
        try {
            maze = readImage(w, h);
            writeImage();
        }
        catch(IOException e) {
            System.out.println("Error: " + e);
        }
    }

    /**
     * read the image
     * @return image as BufferedImage
     * @throws IOException something goes wrong
     */
    private Maze readImage(int w, int h) throws IOException {
        File input_file = new File("C:\\Users\\20191697\\IdeaProjects\\MazeSolver\\src\\Images\\input.png"); //image file path
        BufferedImage image = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_ARGB);
        image = ImageIO.read(input_file);
        maze = new Maze(image, w, h);
        System.out.println("Reading complete.");
        return maze;
    }

    /**
     * writes output image
     * @throws IOException something goes wrong
     */
    private void writeImage() throws IOException {
        File output_file = new File("C:\\Users\\20191697\\IdeaProjects\\MazeSolver\\src\\Images\\output.png");
        ImageIO.write(maze.getImage(), "png", output_file);
        System.out.println("Writing complete.");
    }


    public static void main(String[] args) {
        int width = 20;
        int height = 20;

        MazeSolver mazeSolver = new MazeSolver();
        mazeSolver.run(width, height);
    }

}
