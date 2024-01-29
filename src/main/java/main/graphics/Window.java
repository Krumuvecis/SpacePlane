package main.graphics;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

import vehicleGraphics.VehiclePanelData;
import vehicleGraphics.VehiclePanel;
import vehicles.VehicleAdapter;

//
public class Window extends JFrame {
    private static final Dimension PANEL_SIZE = new Dimension(1000, 900);
    private static final int[] WINDOW_SIZE_CORRECTION = new int[] {17, 40};
    private static final Point LOCATION = new Point(600, 50);
    private static final String WINDOW_TITLE = "Vehicle viewer";
    private static final Color BACKGROUND = Color.BLACK;

    private final VehiclePanelData vehiclePanelData;

    //
    public Window(VehicleAdapter vehicleAdapter) {
        super();
        setWindowConfig();
        vehiclePanelData = new VehiclePanelData(vehicleAdapter, PANEL_SIZE);
        setContents();
        //setResizable(false);
        setVisible(true);
    }

    private void setWindowConfig() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(LOCATION);
        setSize(new Dimension(
                PANEL_SIZE.width + WINDOW_SIZE_CORRECTION[0],
                PANEL_SIZE.height + WINDOW_SIZE_CORRECTION[1]));

        setTitle(WINDOW_TITLE);
    }

    private void setContents() {
        setBackground(BACKGROUND);
        add(getVehiclePanel());
    }

    private JPanel getVehiclePanel() {
        return new VehiclePanel(vehiclePanelData);
    }
}