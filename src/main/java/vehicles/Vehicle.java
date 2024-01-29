package vehicles;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import vehicles.engines.Engine;

//
public abstract class Vehicle {
    private final List<VehiclePart> parts;
    private final Map<VehiclePart, VehiclePartLocationInfo> partLocationMap;
    private final double exhaustVelocity;

    //
    public Vehicle() {
        parts = getInitialParts();
        partLocationMap = new HashMap<>();
        setPartLocationInfo();

        try {
            Engine engine = getEngine();
            exhaustVelocity = engine.getExhaustVelocity();
        } catch (NoEngineFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //must be ordered for now
    abstract List<VehiclePart> getInitialParts();

    public List<VehiclePart> getParts() {
        return parts;
    }

    private void setPartLocationInfo() {
        double x = getLength() / 2; // {0, 0} at amidship
        for (VehiclePart part : parts) {
            double partLength = part.getLength();
            partLocationMap.put(part, new VehiclePartLocationInfo(new double[] {
                    x - partLength / 2,
                    0}
            ));
            x -= partLength;
        }
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

    private Engine getEngine() throws NoEngineFoundException {
        for (VehiclePart part : parts) {
            if (part instanceof Engine) {
                return (Engine) part;
            }
        }
        throw new NoEngineFoundException();
    }

    private static class NoEngineFoundException extends Exception {
        NoEngineFoundException() {
            super("No engine found!");
        }
    }

    public double getExhaustVelocity() {
        return exhaustVelocity;
    }
}