import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Maze object
 *
 * Holds all information of the maze
 */

public class Maze {

    private BufferedImage mazeImage;
    private int width;
    private int height;

    // coordinate of exit and start
    private int[] exit;
    private int[] start;

    // status of each square
    // 0 - not walkable,
    // 1 - walkable, not checked,
    // 2 - walkable, currently checking / correct path,
    // 3 - walkable, checked and not correct
    private int[][] mazeMatrix;

    /**
     * Constructor
     * @param m image of maze
     * @param w width of image
     * @param h height of maze
     */
    public Maze(BufferedImage m, int w, int h) {
        mazeImage = m;
        this.width = w;
        this.height = h;
        exit = new int[]{-1, -1};
        start = new int[]{-1, -1};
        mazeMatrix = new int[w][h]; //0 wall, 1 isWalkable, 2 trying and 3 failed

        this.analyzeImage();
    }

    /**
     * Analyzes if the image is a valid maze
     *
     * @throws IllegalArgumentException if the maze is not valid
     */
    private void analyzeImage() throws IllegalArgumentException {

        for (int w = 0; w < this.width; w++) {
            for (int h = 0; h < this.height; h++) {
                analyzePixel(w, h);
            }
        }

        if (exit[0] == -1 || exit[1] == -1) {
            throw new IllegalArgumentException("No exit was found!");
        }
    }

    /**
     * Analyzes the color of the pixels and adds right value to matrix
     *
     * @param x x coordinate
     * @param y y coordinate
     * @throws IllegalArgumentException if multiple exit or exit on wrong location
     */
    private void analyzePixel(int x, int y) throws IllegalArgumentException {
        if (!isInbound(x, y)) {
            throw new IllegalArgumentException("x or y coordinate out of bound");
        }
        Color c = new Color(mazeImage.getRGB(x, y));

        boolean isWalkable = !(c.getRed() == 0 && c.getGreen() == 0 && c.getBlue() == 0);

        if (isWalkable) {
            mazeMatrix[x][y] = 1;
        } else {
            mazeMatrix[x][y] = 0;
        }

        // Coordinate is an edge so we check for exits
        if (x == 0 || x == this.width-1 || y == 0 || y == this.height-1) {
            if (isWalkable) { //Square is walkable
                if (y == this.height - 1 && (x != 0 && x != this.height-1)) { //square on bottom

                    if (exit[0] == -1 || exit[1] == -1) { //no exit found yet
                        exit[0] = x;
                        exit[1] = y;
                    } else { //already found an exit
                        throw new IllegalArgumentException("Multiple Exits Found!");
                    }

                } else if (y == 0 && (x != 0 && x != this.height-1)) { //!(x == 1 && y == 0)) { // Square at top
                    if (start[0] == -1 || start[1] == -1) { //no starts found yet
                        start[0] = x;
                        start[1] = y;
                    } else { //already found an exit
                        throw new IllegalArgumentException("Multiple Starts Found!");
                    }
                }

            }
        }
    }

    /**
     * returns whether coordinate is inbound
     * @param x current x coordinate
     * @param y current y coordinate
     * @return whether coordinate is inbound
     */
    public boolean isInbound(int x, int y) {
        return !(x < 0 || x >= width || y < 0 || y >= height);
    }

    /**
     * returns whether square is walkable
     * @param x current x coordinate
     * @param y current y coordinate
     * @return whether square is walkable
     */
    public boolean isWalkable(int x, int y) {
        return mazeMatrix[x][y] == 1;
    }

    /**
     * Set a new status of a square
     * @param x current x coordinate
     * @param y current y coordinate
     * @param s the new status
     */
    public void setNewStatus(int x, int y, int s) {
        mazeMatrix[x][y] = s;
        int color;
        if (s == 2) {
            color = Color.RED.getRGB();
            mazeImage.setRGB(x, y, color);
        } else if (s == 3) {
            color = Color.WHITE.getRGB();
            mazeImage.setRGB(x, y, color);
        }

    }

    /**
     * get the image of the maze
     * @return the image of the maze
     */
    public BufferedImage getImage() {
        return mazeImage;
    }

    /**
     * set a new image
     * @param img the new image
     */
    public void setImage(BufferedImage img) {
        mazeImage = img;
    }

    /**
     * get the exit coordinate
     * @return exit coordinate
     */
    public int[] getExit() {
        return exit;
    }

    /**
     * get the start coordinate
     * @return start coordinate
     */
    public int[] getStart() {
        return start;
    }
}
