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
        System.out.println("Thrust: " + (((int) (thrust / 1000 / 10)) * 10.0) + " kN");

        try {
            Engine engine = switch (type) {
                case CHEMICAL -> ChemicalEngine.newChemicalEngine(thrust);
                case ION -> IonEngine.newIonEngine(thrust);
                default -> throw new UnsupportedEngineTypeException();
            };
            double
                    lengthRounded = ((int) (engine.getLength() * 1000)) / 1000.0,
                    diameterRounded = ((int) (engine.getDiameter() * 1000)) / 1000.0;
            System.out.println("Total engine size: " + lengthRounded + " m x " + diameterRounded + " m");
            return engine;
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