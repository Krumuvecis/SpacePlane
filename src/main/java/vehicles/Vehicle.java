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

    public double getMass() {
        double total = 0;
        for(VehiclePart part : parts) {
            total += part.getMass();
        }
        return total;
    }

    public double getLength() {
        double total = 0;
        for(VehiclePart part : parts) {
            total += part.getLength();
        }
        return total;
    }
}