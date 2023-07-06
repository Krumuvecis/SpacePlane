package vehicleGraphics;

import javax.swing.*;
import java.awt.*;

public class VehiclePanel extends JPanel {
    private static final Color
            BACKGROUND = Color.BLACK,
            BORDER_COLOR = Color.RED;
    private static final int BORDER_OFFSET = 0;

    private final VehiclePanelData panelData;
    private final PainterInterface
            borderPainter,
            vehiclePainter;

    public VehiclePanel(VehiclePanelData panelData) {
        super();
        this.panelData = panelData;
        setBackground(BACKGROUND);
        borderPainter = new BorderPainter(BORDER_COLOR, BORDER_OFFSET);
        vehiclePainter = new VehiclePainter(panelData.getVehicle());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        borderPainter.paint(g);
        vehiclePainter.paint(g);
    }

    private static class BorderPainter implements PainterInterface {
        private final Color color;
        private final int offset;

        BorderPainter(Color color, int offset) {
            this.color = color;
            this.offset = offset;
        }

        @Override
        public void paint(Graphics g) {
            g.setColor(color);
            Dimension size = VehiclePanelData.getOptimalSize();
            int
                    x = size.width,
                    y = size.height;
            g.drawRect(offset, offset, Math.max(0, x - 2 * offset), Math.max(0, y - 2 * offset));
        }
    }
}