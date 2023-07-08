package vehicles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//
public abstract class Vehicle {
    private final List<VehiclePart> parts;
    private final Map<VehiclePart, VehiclePartInfo> partInfo;

    //
    public Vehicle() {
        parts = getInitialParts();
        partInfo = new HashMap<>() {{
            double x = getLength() / 2;

            for (VehiclePart part : parts) {
                put(part, new VehiclePartInfo(new double[]{x - part.getLength() / 2, 0}));
                x -= part.getLength();
            }
        }};
    }

    //must be ordered for now
    abstract List<VehiclePart> getInitialParts();

    public List<VehiclePart> getParts() {
        return parts;
    }

    public VehiclePartInfo getPartInfo(VehiclePart part) {
        return partInfo.get(part);
    }

    public double getMass() {
        double total = 0;
        for (VehiclePart part : parts) {
            total += part.getMass();
        }
        return total;
    }

    public double getLength() {
        double total = 0;
        for (VehiclePart part : parts) {
            total += part.getLength();
        }
        return total;
    }
}