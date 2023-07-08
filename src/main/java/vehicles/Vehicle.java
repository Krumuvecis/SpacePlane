package vehicles;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

//
public abstract class Vehicle {
    private final List<VehiclePart> parts;
    private final Map<VehiclePart, VehiclePartLocationInfo> partLocationMap;

    //
    public Vehicle() {
        parts = getInitialParts();
        partLocationMap = new HashMap<>() {{
            double x = getLength() / 2;
            for (VehiclePart part : parts) {
                put(
                        part,
                        new VehiclePartLocationInfo(
                                new double[]{x, 0}));
                x -= part.getLength();
            }
        }};
    }

    //must be ordered for now
    abstract List<VehiclePart> getInitialParts();

    public List<VehiclePart> getParts() {
        return parts;
    }

    public VehiclePartLocationInfo getPartInfo(VehiclePart part) {
        return partLocationMap.get(part);
    }

    //
    public double getMass() {
        double total = 0;
        for (VehiclePart part : parts) {
            total += part.getMass();
        }
        return total;
    }

    //
    public double getLength() {
        double total = 0;
        for (VehiclePart part : parts) {
            total += part.getLength();
        }
        return total;
    }
}