import GUI.*;
import java.awt.*;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Service Station Simulation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 600);
            frame.setLayout(new BorderLayout());

            // default for now
            ControlPanel control = new ControlPanel(frame);

            frame.add(control, BorderLayout.WEST);
            

            frame.setVisible(true);
        });
    }
}

