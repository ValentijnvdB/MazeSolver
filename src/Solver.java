import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Swingworker that solves the maze in the background
 */

public class Solver extends SwingWorker<Maze, Maze> {

    private Maze maze;
    private int[] exit;
    private boolean found;
    private ArrayList<int[]> path;
    IOSystem ioSystem;

    /**
     * Constructor
     * @param maze the maze to solve
     * @param ioSystem the input output system, used to print the maze when solved
     */
    public Solver(Maze maze, IOSystem ioSystem) {
        this.maze = maze;
        this.exit = maze.getExit();
        this.path = new ArrayList<>();
        this.ioSystem = ioSystem;
    }

    @Override
    protected Maze doInBackground() throws Exception {
        System.out.println("doInBackground");
        int[] start = maze.getStart();
        int x = start[0];
        int y = start[1];
        setNewStatus(x, y, 2);
        found = findNext(x, y);
        return maze;
    }

    @Override
    protected void done() {
        super.done();
        if (found) {
            BufferedImage img = maze.getImage();
            int red = 255;
            int color = (new Color(red, 0, 0)).getRGB();
            for (int[] c: path) {
                img.setRGB(c[0], c[1], color);
                System.out.println(c[0] + ", " + c[1]);
            }
            try {
                ioSystem.writeImage(maze);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * recursive method that finds the route
     * @param x current x coordinate
     * @param y current y coordinate
     * @return whether a route has been found
     */
    private boolean findNext(int x, int y) {
        if (x == exit[0] && y == exit[1] && !isCancelled()) {
            return true;
        }

        // Check left
        if (isValid(x-1, y)) {
            System.out.println("Trying left");
            setNewStatus(x-1, y, 2);
            if (findNext(x-1, y)) {
                return true;
            }
            setNewStatus(x-1, y, 3);
        }

        // Check right
        if (isValid(x+1, y)) {
            System.out.println("Trying right");
            setNewStatus(x+1, y, 2);
            if (findNext(x+1, y)) {
                return true;
            }
            setNewStatus(x+1, y, 3);
        }

        // Check down
        if (isValid(x, y-1)) {
            System.out.println("Trying down");
            setNewStatus(x, y-1, 2);
            if (findNext(x, y-1)) {
                return true;
            }
            setNewStatus(x, y-1, 3);
        }

        // Check up
        if (isValid(x, y+1)) {
            System.out.println("Trying up");
            setNewStatus(x, y+1, 2);
            if (findNext(x, y+1)) {
                return true;
            }
            setNewStatus(x, y+1, 3);
        }

        return false;
    }

    /**
     * Checks whether square is inbound and walkable
     * @param x x coordinate
     * @param y y coordinate
     * @return whether square is inbound and walkable
     */
    private boolean isValid(int x, int y) {
        return maze.isInbound(x, y) && maze.isWalkable(x, y);
    }

    /**
     * Set new status for square (x, y)
     * @param x x coordinate
     * @param y y coordinate
     * @param s new status
     */
    private void setNewStatus(int x, int y, int s) {
        maze.setNewStatus(x, y, s);
        if ( s==2 ) {
            path.add( new int[]{x, y} );
            System.out.println("Added: (" + x + ", " + y + "), " + s);
        } else if ( s==3 ) {
            path.remove( path.size() - 1 );
            System.out.println("Removed: (" + x + ", " + y + "), " + s);
        }
    }

}
