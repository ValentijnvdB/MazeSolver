import javax.swing.*;

public class MainFrame {

    public MainFrame() {
        JFrame f = new JFrame("Maze Solver");
        f.setVisible(true);
        f.add(new MazePanel(null));
    }

    private void updateUI() {

    }
}
