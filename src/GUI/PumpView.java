package GUI;

import java.awt.*;
import javax.swing.*;

public class PumpView extends JPanel {
    private JLabel label;
    private boolean busy = false;

    public PumpView(int id) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        label = new JLabel("Pump " + id + " (Free)", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
        setBackground(Color.GREEN);
    }

    public void setBusy(String carName) {
        busy = true;
        SwingUtilities.invokeLater(() -> {
            label.setText("Busy with " + carName);
            setBackground(Color.RED);
        });
    }

    public void setFree() {
        busy = false;
        SwingUtilities.invokeLater(() -> {
            label.setText("Free");
            setBackground(Color.GREEN);
        });
    }
}
