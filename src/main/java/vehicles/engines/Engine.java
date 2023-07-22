package vehicles.engines;

import vehicles.VehiclePart;

//
public abstract class Engine extends VehiclePart {
    private static final String PART_NAME = "Engine";

    //use this for creating new engines
    @SuppressWarnings({"SameParameterValue", "UnnecessaryDefault"})
    public static Engine newEngine(double diameter, EngineType type) {
        try {
            return switch (type) {
                case CHEMICAL -> new ChemicalEngine(diameter);
                case ION -> new IonEngine(diameter);
                default -> throw new UnsupportedEngineTypeException();
            };
        } catch (UnsupportedEngineTypeException e) {
            throw new RuntimeException(e);
        }
    }

    //
    Engine(double diameter) {
        super(PART_NAME, calculateMass(), 1, diameter);
    }

    private static double calculateMass() {
        return 10;
    }

    public abstract double getExhaustVelocity();
}