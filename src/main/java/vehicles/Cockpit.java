package vehicles;

import mathUtilities.Cylinders;

//
class Cockpit extends VehiclePart {
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
                Cylinders.getLength(passengerCount * VOLUME_PER_PASSENGER, diameter),
                MINIMUM_LENGTH);
    }

    private static double calculateMass(double diameter, double length) {
        return PART_DENSITY * Cylinders.getVolume(diameter, length);
    }
}