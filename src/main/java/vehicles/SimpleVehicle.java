package vehicles;

import java.util.List;
import java.util.ArrayList;

import vehicles.engines.EngineType;
import vehicles.engines.Engine;

//
public class SimpleVehicle extends Vehicle {
    private static final int PASSENGER_COUNT = 5;
    private static final EngineType ENGINE_TYPE = EngineType.CHEMICAL;
    private static final double TARGET_DIAMETER = 3.0;

    //
    public SimpleVehicle() {
        super();
    }

    //
    @Override
    List<VehiclePart> getInitialParts() {
        return new ArrayList<>() {{
            add(Cockpit.newCockpit(TARGET_DIAMETER, PASSENGER_COUNT));
            add(new Storage(TARGET_DIAMETER));
            add(new FuelTanks(TARGET_DIAMETER));
            add(Engine.newEngine(TARGET_DIAMETER, ENGINE_TYPE));
        }};
    }

    //unused yet
    private abstract static class CylindricalPart extends VehiclePart {
        CylindricalPart(String name, double length, double diameter) {
            super(name, 0, length, diameter);
        }
    }

    //
    private static class Storage extends VehiclePart {
        //
        Storage(double diameter) {
            super("Storage", calculateMass(), 5, diameter);
        }

        private static double calculateMass() {
            return 10;
        }
    }

    //
    private static class FuelTanks extends VehiclePart {
        //
        FuelTanks(double diameter) {
            super("Fuel Tanks", calculateMass(), 20, diameter);
        }

        private static double calculateMass() {
            return 10;
        }
    }
}