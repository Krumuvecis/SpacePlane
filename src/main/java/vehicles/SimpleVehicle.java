package vehicles;

import java.util.List;
import java.util.ArrayList;

//
public class SimpleVehicle extends Vehicle {
    private static final int PASSENGER_COUNT = 5;
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
            add(new Engines(TARGET_DIAMETER));
        }};
    }

    //
    private static class Cockpit extends VehiclePart {
        private static final String PART_NAME = "Cockpit";
        private static final double
                MINIMUM_LENGTH = 1.0,       // arbitrary
                MINIMUM_DIAMETER = 3.0,     // arbitrary
                VOLUME_PER_PASSENGER = 3.0, // arbitrary
                PART_DENSITY = 500;         // arbitrary

        //use this for creating new cockpit
        @SuppressWarnings("SameParameterValue")
        static Cockpit newCockpit(double targetDiameter, int passengerCount) {
            double
                    finalDiameter = calculateFinalDiameter(targetDiameter),
                    finalLength = calculateFinalLength(finalDiameter, passengerCount),
                    mass = calculateMass(finalDiameter, finalLength);
            return new Cockpit(mass, finalLength, finalDiameter);
        }

        private Cockpit(double mass, double length, double diameter) {
            super(PART_NAME, mass, length, diameter);
        }

        private static double calculateFinalDiameter(double targetDiameter) {
            return Math.max(targetDiameter, MINIMUM_DIAMETER);
        }

        private static double calculateFinalLength(double diameter, int passengerCount) {
            return Math.max(
                    getCylinderLength(passengerCount * VOLUME_PER_PASSENGER, diameter),
                    MINIMUM_LENGTH);
        }

        private static double calculateMass(double diameter, double length) {
            return PART_DENSITY * getCylinderVolume(diameter, length);
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

    //
    private static class Engines extends VehiclePart {
        //
        Engines(double diameter) {
            super("Engines", calculateMass(), 1, diameter);
        }

        private static double calculateMass() {
            return 10;
        }
    }

    //unused yet
    private abstract static class CylindricalPart extends VehiclePart {
        CylindricalPart(String name, double length, double diameter) {
            super(name, 0, length, diameter);
        }
    }

    private static double getCylinderCrossSection(double diameter) {
        return Math.PI * Math.pow(diameter / 2, 2);
    }

    private static double getCylinderLength(double volume, double diameter) {
        return volume / getCylinderCrossSection(diameter);
    }

    private static double getCylinderVolume(double diameter, double length) {
        return getCylinderCrossSection(diameter) * length;
    }
}