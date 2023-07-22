package vehicles.engines.chemical;

//
public class Nozzle {
    private static final double
            DIAMETER_THROAT_RATIO = 30.0, // arbitrary
            LENGTH_DIAMETER_RATIO = 1 / Math.tan(Math.toRadians(15)); // approximate

    private final double diameter, length;

    //
    protected Nozzle(double exhaustDiameter) {
        diameter = exhaustDiameter;
        length = diameter * LENGTH_DIAMETER_RATIO;
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