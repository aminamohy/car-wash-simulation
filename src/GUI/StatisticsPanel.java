package GUI;

import javax.swing.*;
import java.awt.*;

public class StatisticsPanel extends JPanel {
    private JTextArea logsArea;
    private JLabel carsServedLabel;
    private JLabel waitingCarsLabel;
    private JLabel elapsedTimeLabel;
    private JProgressBar progressBar;

    private int totalCars = 0;

    public StatisticsPanel() {
        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createTitledBorder("Statistics & Logs"));

        // top info panel
        JPanel infoPanel = new JPanel(new GridLayout(4, 1, 4, 4));
        carsServedLabel = new JLabel("Cars Served: 0");
        waitingCarsLabel = new JLabel("Waiting Cars: 0");
        elapsedTimeLabel = new JLabel("Elapsed Time: 0 s");
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        infoPanel.add(carsServedLabel);
        infoPanel.add(waitingCarsLabel);
        infoPanel.add(elapsedTimeLabel);
        infoPanel.add(progressBar);

        // logs area
        logsArea = new JTextArea(12, 25);
        logsArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(logsArea);

        add(infoPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    // ==== API to be called from logic ====
    public void logEvent(String event) {
        SwingUtilities.invokeLater(() -> {
            logsArea.append(event + "\n");
            logsArea.setCaretPosition(logsArea.getDocument().getLength());
        });
    }

    public void updateCarsServed(int count) {
        SwingUtilities.invokeLater(() -> carsServedLabel.setText("Cars Served: " + count));
    }

    public void updateWaitingCars(int count) {
        SwingUtilities.invokeLater(() -> waitingCarsLabel.setText("Waiting Cars: " + count));
    }

    public void updateElapsedTime(int seconds) {
        SwingUtilities.invokeLater(() -> elapsedTimeLabel.setText("Elapsed Time: " + seconds + " s"));
    }

    public void setTotalCars(int total) {
        this.totalCars = total;
    }

    public void updateProgressPercentage(int percent) {
        SwingUtilities.invokeLater(() -> progressBar.setValue(Math.max(0, Math.min(100, percent))));
    }

    public void showSummary(int totalCarsServed, int totalTimeSeconds) {
        logEvent("=== Simulation Ended: " + totalCarsServed + " cars served in " + totalTimeSeconds + " s ===");
        updateProgressPercentage(100);
    }
}
