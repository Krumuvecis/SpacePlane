package main.graphics;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

import vehicles.Vehicle;
import vehicleGraphics.VehiclePanelData;
import vehicleGraphics.VehiclePanel;

//
public class Window extends JFrame {
    private static final int[] SIZE_CORRECTION = new int[] {17, 40};
    private static final Point LOCATION = new Point(700, 100);
    private static final String WINDOW_TITLE = "Vehicle viewer";
    private static final Color BACKGROUND = Color.BLACK;

    //
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