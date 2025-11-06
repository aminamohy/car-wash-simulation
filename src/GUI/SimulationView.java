package GUI;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.*;

public class SimulationView extends JPanel {
    private CopyOnWriteArrayList<PumpView> pumps;
    private DefaultListModel<String> queueModel;
    private JList<String> queueList;

    public SimulationView(int numPumps) {
        setLayout(new BorderLayout(10, 10));

        JPanel pumpsPanel = new JPanel(new GridLayout(1, numPumps, 10, 10));
        pumps = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= numPumps; i++) {
            PumpView pump = new PumpView(i);
            pumps.add(pump);
            pumpsPanel.add(pump);
        }

        queueModel = new DefaultListModel<>();
        queueList = new JList<>(queueModel);
        queueList.setBorder(BorderFactory.createTitledBorder("Waiting Queue"));

        add(pumpsPanel, BorderLayout.CENTER);
        add(new JScrollPane(queueList), BorderLayout.SOUTH);
    }

    public void addCarToQueue(String carName) {
        SwingUtilities.invokeLater(() -> {
        System.out.println("Queue add: " + carName);
        if (!queueModel.contains(carName)) queueModel.addElement(carName);
        });
    }

    public void removeCarFromQueue(String carName) {
        SwingUtilities.invokeLater(() -> {
        System.out.println("Queue remove: " + carName);
        queueModel.removeElement(carName);
        });
    }

    
    public int getQueueSize() {
        return queueModel.getSize();
    }

    public void setPumpBusy(int pumpId, String carName) {
        pumps.get(pumpId - 1).setBusy(carName);
    }

    
    public void setPumpFree(int pumpId) {
        pumps.get(pumpId - 1).setFree();
    }

    

}

