import GUI.ControlPanel;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Service Station Simulation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 600);
            frame.setLocationRelativeTo(null);

            frame.add(new ControlPanel());
            frame.setVisible(true);
        });
    }
}
