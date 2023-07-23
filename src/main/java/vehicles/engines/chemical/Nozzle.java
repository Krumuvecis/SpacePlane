package vehicles.engines.chemical;

import mathUtilities.Circles;
import vehicles.engines.propellants.Propellant;

//
public class Nozzle {
    private static final double
            DIAMETER_THROAT_RATIO = 30.0, // arbitrary
            LENGTH_DIAMETER_RATIO = 1 / Math.tan(Math.toRadians(15)); // approximate

    private final double
            diameter,
            length;

    //
    protected Nozzle(Propellant propellant,
                     double thrust, double exhaustVelocity,
                     double exhaustTemperature, double exhaustPressure) {
        double
                propellantDensityAtExhaust = propellant.getDensity(exhaustTemperature, exhaustPressure),
                exhaustCrossSection = calculateExhaustCrossSection(
                        thrust, exhaustVelocity, propellantDensityAtExhaust);
        diameter = calculateExhaustDiameter(exhaustCrossSection);
        length = diameter * LENGTH_DIAMETER_RATIO;

        printParametersToConsole(
                propellantDensityAtExhaust,
                length, diameter,
                getThroatDiameter());
    }

    // S = F / (ro * ve ^ 2)
    private double calculateExhaustCrossSection(double thrust, double exhaustVelocity, double propellantDensity) {
        return thrust / (propellantDensity * Math.pow(exhaustVelocity, 2));
    }

    private double calculateExhaustDiameter(double crossSection) {
        return Circles.getRadiusFromArea(crossSection) * 2;
    }

    private static void printParametersToConsole(double propellantDensityAtExhaust,
                                                 double length, double diameter,
                                                 double throatDiameter) {
        //propellant density
        double propellantDensity_rounded = ((int) (propellantDensityAtExhaust * 1000)) / 1000.0;
        System.out.println("Propellant density at exhaust: " + propellantDensity_rounded + " kg/m3");

        //length x diameter
        double
                length_rounded = ((int) (length * 1000)) / 1000.0,
                diameter_rounded = ((int) (diameter * 1000)) / 1000.0;
        System.out.println("Nozzle size: " + length_rounded + " m x " + diameter_rounded + " m");

        //throat diameter
        double
                throatDiameter_mm = throatDiameter * 1000,
                throatDiameter_mm_rounded = ((int) (throatDiameter_mm * 100)) / 100.0;
        System.out.println("Throat diameter: " + throatDiameter_mm_rounded + " mm");
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