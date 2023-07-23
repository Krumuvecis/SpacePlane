package vehicles.engines;

import vehicles.VehiclePart;
import vehicles.engines.chemical.ChemicalEngine;

//
public abstract class Engine extends VehiclePart {
    private static final String PART_NAME = "Engine";

    //use this for creating new engines
    @SuppressWarnings({"SameParameterValue", "UnnecessaryDefault"})
    public static Engine newEngine(EngineType type, double thrust) {
        System.out.println("Creating new " + type + " engine.");
        System.out.println("Thrust: " + (thrust / 1000) + " kN");
        try {
            return switch (type) {
                case CHEMICAL -> ChemicalEngine.newChemicalEngine(thrust);
                case ION -> new IonEngine();
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

    //
    public abstract double getExhaustVelocity();
}