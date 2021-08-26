import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MazeSolver {

    int width;
    int height;
    BufferedImage image;

    /**
     * Default constructor using 400x400 image
     */
    public MazeSolver() {
        this(400, 400);
    }

    /**
     * Constructor using custom sized image
     *
     * @param width width of image
     * @param height height of image
     */
    public MazeSolver(int width, int height) {
        this.width = width;    //width of the image
        this.height = height;   //height of the image
        image = null;
    }

    /**
     * main method
     */
    private void run() {
        image = readImage();
        if (image != null) {
            writeImage();
        }
    }

    /**
     * read the image
     * @return image as BufferedImage
     */
    private BufferedImage readImage() {
        try {
            File input_file = new File("C:\\Users\\20191697\\IdeaProjects\\MazeSolver\\src\\Images\\input.jpg"); //image file path
            image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_ARGB);
            image = ImageIO.read(input_file);
            System.out.println("Reading complete.");
            return image;
        }
        catch(IOException e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    /**
     * writes output image
     */
    private void writeImage() {
        try {
            File output_file = new File("C:\\Users\\20191697\\IdeaProjects\\MazeSolver\\src\\Images\\output.jpg");
            ImageIO.write(image, "jpg", output_file);
            System.out.println("Writing complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }


    public static void main(String[] args) {
        MazeSolver mazeSolver = new MazeSolver();
        mazeSolver.run();
    }

// WRITE IMAGE



}
