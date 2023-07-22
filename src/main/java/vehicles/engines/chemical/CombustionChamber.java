package vehicles.engines.chemical;

//a spherical combustion chamber
public class CombustionChamber {
    private static final double DIAMETER_THROAT_RATIO = 2.0;

    private final double diameter;

    //
    protected CombustionChamber(double throatDiameter) {
        diameter = throatDiameter * DIAMETER_THROAT_RATIO;
    }

    //
    public double getDiameter() {
        return diameter;
    }
}