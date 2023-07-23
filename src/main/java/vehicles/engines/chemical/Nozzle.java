package vehicles.engines.chemical;

import mathUtilities.Circles;

//
public class Nozzle {
    private static final double
            GAS_CONSTANT = 8.31,
            DIAMETER_THROAT_RATIO = 30.0, // arbitrary
            LENGTH_DIAMETER_RATIO = 1 / Math.tan(Math.toRadians(15)); // approximate

    private final double
            diameter,
            length;

    //
    protected Nozzle(double thrust, double exhaustVelocity,
                     double propellantMolarMass, double exhaustTemperature, double exhaustPressure) {
        double
                propellantDensityAtExhaust = calculatePropellantDensityAtExhaust(
                        propellantMolarMass, exhaustTemperature, exhaustPressure),
                exhaustCrossSection = calculateExhaustCrossSection(
                        thrust, exhaustVelocity, propellantDensityAtExhaust);
        diameter = calculateExhaustDiameter(exhaustCrossSection);
        length = diameter * LENGTH_DIAMETER_RATIO;
        printParametersToConsole(propellantDensityAtExhaust, length, diameter);
    }

    // ro = p * M / R / T
    private double calculatePropellantDensityAtExhaust(double molarMass, double temperature, double pressure) {
        return pressure * molarMass / GAS_CONSTANT / temperature;
    }

    // S = F / (ro * ve ^ 2)
    private double calculateExhaustCrossSection(double thrust, double exhaustVelocity, double propellantDensity) {
        return thrust / (propellantDensity * Math.pow(exhaustVelocity, 2));
    }

    private double calculateExhaustDiameter(double crossSection) {
        return Circles.getRadiusFromArea(crossSection) * 2;
    }

    private static void printParametersToConsole(double propellantDensityAtExhaust,
                                                 double length, double diameter) {
        double
                propellantDensity_rounded = ((int) (propellantDensityAtExhaust * 1000)) / 1000.0,
                length_rounded = ((int) (length * 1000)) / 1000.0,
                diameter_rounded = ((int) (diameter * 1000)) / 1000.0;
        System.out.println("Propellant density at exhaust: " + propellantDensity_rounded + " kg/m3");
        System.out.println("Nozzle size: " + length_rounded + " x " + diameter_rounded);
    }

    //
    public double getDiameter() {
        return diameter;
    }

    //
    public double getLength() {
        return length;
    }

    //
    public double getThroatDiameter() {
        return diameter / DIAMETER_THROAT_RATIO;
    }
}