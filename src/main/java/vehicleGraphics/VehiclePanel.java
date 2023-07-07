package vehicleGraphics;

import java.awt.Graphics;
import javax.swing.JPanel;

//
public class VehiclePanel extends JPanel {
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
        borderPainter = new BorderPainter(panelData, VehiclePanelData.getOptimalSize());
        rotationTestPainter = new RotationTestPainter(this);
        vehiclePainter = new VehiclePainter(this, this.panelData);
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