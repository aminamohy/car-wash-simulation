package GUI;
import logic.ServiceStation;  
import javax.swing.*;
import java.awt.*;


import logic.ServiceStation; // 👈 عشان تستخدمي كلاس ServiceStation
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {

    private JTextField pumpsField;
    private JTextField queueField;
    private JTextField carsField;
    private JButton startButton;
    private JTextArea outputArea;

    public ControlPanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 248, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = new JLabel(" Service Station Simulator");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;


        gbc.gridy++;
        add(new JLabel("Number of Pumps (1-10):"), gbc);
        pumpsField = new JTextField(10);
        gbc.gridx = 1;
        add(pumpsField, gbc);


        gbc.gridy++; gbc.gridx = 0;
        add(new JLabel("Queue Size (1-10):"), gbc);
        queueField = new JTextField(10);
        gbc.gridx = 1;
        add(queueField, gbc);


        gbc.gridy++; gbc.gridx = 0;
        add(new JLabel("Number of Cars:"), gbc);
        carsField = new JTextField(10);
        gbc.gridx = 1;
        add(carsField, gbc);


        gbc.gridy++; gbc.gridx = 0; gbc.gridwidth = 2;
        startButton = new JButton("Start Simulation");
        add(startButton, gbc);

        
        gbc.gridy++;
        outputArea = new JTextArea(10, 25);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), gbc);


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSimulation();
            }
        });
    }

    private void startSimulation() {
        try {
            int pumps = Integer.parseInt(pumpsField.getText());
            int queue = Integer.parseInt(queueField.getText());
            int cars = Integer.parseInt(carsField.getText());


            ServiceStation station = new ServiceStation(pumps, queue);


            Thread simulationThread = new Thread(() -> {
                station.startSimulation(cars);
                SwingUtilities.invokeLater(() ->
                    outputArea.append(" Simulation Finished!\n")
                );
            });
            simulationThread.start();

            outputArea.append(" Simulation started...\n");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, " Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


