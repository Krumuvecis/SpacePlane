package vehicles.engines;

import mathUtilities.Circles;

//
public class Exhaust {
    private final double
            crossSection,
            diameter,
            massFlowRate;

    //
    public Exhaust(double thrust, double velocity, double propellantDensity) {
        crossSection = thrust / (propellantDensity * Math.pow(velocity, 2));
        diameter = Circles.getRadiusFromArea(crossSection) * 2;
        massFlowRate = velocity * propellantDensity * crossSection;
    }

    //
    public double getCrossSection() {
        return crossSection;
    }

    //
    public double getDiameter() {
        return diameter;
    }

    //
    public double getMassFlowRate() {
        return massFlowRate;
    }
}