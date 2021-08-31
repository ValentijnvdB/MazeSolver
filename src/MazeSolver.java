import javax.swing.*;
import java.awt.*;
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
    JFrame frame;
    MazePanel mp;

    /**
     * Main method
     * @param fileName the filename of the maze
     */
    private void run(String fileName) {
                frame = new JFrame("Maze Solver");
                frame.setPreferredSize(new Dimension(400, 300));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                mp = new MazePanel(null);
                frame.add(mp);
                frame.setVisible(true);

        try {
            ioSystem = new IOSystem();
            maze = ioSystem.readImage(fileName);
            mp.updateImage(maze.getImage());
            mp.repaint();
            solver = new Solver(maze, ioSystem, mp);
            solver.execute();
        }
        catch(IOException e) {
            System.out.println("Error: " + e);
        }
    }






    public static void main(String[] args) {
        String fileName = "braid2k.png";

        MazeSolver mazeSolver = new MazeSolver();
        mazeSolver.run(fileName);
    }

}
