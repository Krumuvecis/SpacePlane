package vehicleGraphics;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

//
public class VehiclePanel extends JPanel {
    private static final double VEHICLE_DRAW_ANGLE = 55;
    private final VehiclePanelData panelData;
    private final PainterInterface
            borderPainter,
            rotationTestPainter,
            vehiclePainter;

    //
    public VehiclePanel(VehiclePanelData panelData) {
        super();
        this.panelData = panelData;
        setBackground(panelData.getBackgroundColor());

        borderPainter = new BorderPainter(panelData);
        rotationTestPainter = new RotationTestPainter(this);
        vehiclePainter = new VehiclePainter(this, this.panelData, VEHICLE_DRAW_ANGLE);
    }

    //
    @Override
    public void update(Graphics g) {
        super.update(g);
        Dimension size = getSize();
        panelData.setPanelSize(size.width, size.height);
    }

    //
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        borderPainter.paint(g, null);
        rotationTestPainter.paint(g, new int[] {250, 150});
        vehiclePainter.paint(g, new int[] {0, 0});
    }
}