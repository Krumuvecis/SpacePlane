package main.graphics;

import vehicleGraphics.VehiclePanel;
import vehicleGraphics.VehiclePanelData;
import vehicles.Vehicle;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private static final int[] SIZE_CORRECTION = new int[] {17, 40};
    private static final Point LOCATION = new Point(700, 100);
    private static final String WINDOW_TITLE = "Vehicle viewer";
    private static final Color BACKGROUND = Color.BLACK;

    public Window(Vehicle vehicle) {
        super();
        setWindowConfig();
        setContents(vehicle);
        setResizable(false);
        setVisible(true);
    }

    private void setWindowConfig() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(LOCATION);

        Dimension optimalSize = VehiclePanelData.getOptimalSize();
        setSize(new Dimension(
                optimalSize.width + SIZE_CORRECTION[0],
                optimalSize.height + SIZE_CORRECTION[1]));

        setTitle(WINDOW_TITLE);
    }

    private void setContents(Vehicle vehicle) {
        setBackground(BACKGROUND);
        add(getVehiclePanel(vehicle));
    }

    private JPanel getVehiclePanel(Vehicle vehicle) {
        return new VehiclePanel(new VehiclePanelData(vehicle));
    }
}