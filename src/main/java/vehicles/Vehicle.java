package vehicles;

import java.util.List;

//
public abstract class Vehicle {
    private final List<VehiclePart> parts;

    //
    public Vehicle() {
        parts = getInitialParts();
    }

    //must be ordered for now
    abstract List<VehiclePart> getInitialParts();

    public List<VehiclePart> getParts() {
        return parts;
    }

    public int getMass() {
        int total = 0;
        for(VehiclePart part : parts) {
            total += part.mass;
        }
        return total;
    }

    public int getLength() {
        int total = 0;
        for(VehiclePart part : parts) {
            total += part.length;
        }
        return total;
    }
}