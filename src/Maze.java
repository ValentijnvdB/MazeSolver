import java.awt.*;
import java.awt.image.BufferedImage;

public class Maze {

    private BufferedImage mazeImage;
    int width;
    int height;
    int[] exit;
    Boolean[][] mazeMatrix;

    public Maze(BufferedImage m, int w, int h) {
        mazeImage = m;
        this.width = w;
        this.height = h;
        exit = new int[]{-1, -1};
        mazeMatrix = new Boolean[w][h];

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
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("x or y coordinate out of bound");
        }
        Color c = new Color(mazeImage.getRGB(x, y));

        boolean isWalkable = !(c.getRed() == 0 && c.getGreen() == 0 && c.getBlue() == 0);

        mazeMatrix[x][y] = isWalkable;

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

                } else if (!(x == 1 && y == 0)) { // Square not at bottom
                    throw new IllegalArgumentException("Illegal exit found: Exit must at the bottom of the maze");
                }

            }
        }
    }

    public BufferedImage getImage() {
        return mazeImage;
    }
}
