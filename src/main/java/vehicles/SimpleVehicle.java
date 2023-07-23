package vehicles;

import java.util.List;
import java.util.ArrayList;

import vehicles.engines.EngineType;
import vehicles.engines.Engine;

//
public class SimpleVehicle extends Vehicle {
    private static final int PASSENGER_COUNT = 5;
    private static final EngineType ENGINE_TYPE = EngineType.CHEMICAL;
    private static final double
            ENGINE_THRUST_KN = 1000, // in kN; arbitrary
            TARGET_DIAMETER = 3.0;

    //
    public SimpleVehicle() {
        super();
    }

    //
    @Override
    List<VehiclePart> getInitialParts() {
        double thrust_SI = ENGINE_THRUST_KN * 1000;
        return new ArrayList<>() {{
            add(Cockpit.newCockpit(TARGET_DIAMETER, PASSENGER_COUNT));
            add(new Storage(TARGET_DIAMETER));
            add(new FuelTanks(TARGET_DIAMETER));
            add(Engine.newEngine(ENGINE_TYPE, thrust_SI));
        }};
    }

    //
    private static class Storage extends VehiclePart {
        private static final String PART_NAME = "Storage";
        private static final double
                LENGTH = 5,
                MASS = 10;

        //
        Storage(double diameter) {
            super(PART_NAME, calculateMass(), LENGTH, diameter);
        }

        private static double calculateMass() {
            return MASS;
        }
    }

    //
    private static class FuelTanks extends VehiclePart {
        private static final String PART_NAME = "Fuel tanks";
        private static final double
                LENGTH = 20,
                MASS = 10;

        //
        FuelTanks(double diameter) {
            super(PART_NAME, calculateMass(), LENGTH, diameter);
        }

        private static double calculateMass() {
            return MASS;
        }
    }
}