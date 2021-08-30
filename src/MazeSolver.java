import javax.swing.*;
import java.io.IOException;

/**
 * Solve a maze
 *
 * Black squares are walls, all other colors are walkable
 *
 * A valid maze is an image with Black pixels around the edge,
 * and exactly one entrance at the top and
 * exactly one exit at the bottom (not in the corners)
 */
public class MazeSolver {

    Maze maze;
    Solver solver;
    IOSystem ioSystem;

    /**
     * Main method
     * @param fileName the filename of the maze
     */
    private void run(String fileName) {
        JFrame frame = new JFrame("Maze Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        try {
            ioSystem = new IOSystem();
            maze = ioSystem.readImage(fileName);
            solver = new Solver(maze, ioSystem);
            solver.execute();
            System.out.println("Started execution");
        }
        catch(IOException e) {
            System.out.println("Error: " + e);
        }
    }


    public static void main(String[] args) {
        String fileName = "braid200.png";

        MazeSolver mazeSolver = new MazeSolver();
        mazeSolver.run(fileName);
    }

}
