package vehicles.engines;

import vehicles.VehiclePart;
import vehicles.engines.chemical.ChemicalEngine;

//
public abstract class Engine extends VehiclePart {
    private static final String PART_NAME = "Engine";

    //use this for creating new engines
    @SuppressWarnings({"SameParameterValue", "UnnecessaryDefault"})
    public static Engine newEngine(double targetDiameter, EngineType type) {
        try {
            return switch (type) {
                case CHEMICAL -> ChemicalEngine.newChemicalEngine(targetDiameter);
                case ION -> new IonEngine(targetDiameter);
                default -> throw new UnsupportedEngineTypeException();
            };
        } catch (UnsupportedEngineTypeException e) {
            throw new RuntimeException(e);
        }
    }

    //
    protected Engine(double mass, double length, double diameter) {
        super(PART_NAME, mass, length, diameter);
    }

    public abstract double getExhaustVelocity();
}