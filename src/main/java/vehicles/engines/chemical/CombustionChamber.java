package vehicles.engines.chemical;

//a spherical combustion chamber
public class CombustionChamber {
    private static final double DIAMETER_THROAT_RATIO = 2.0;

    private final double diameter;

    //
    protected CombustionChamber(double throatDiameter) {
        diameter = throatDiameter * DIAMETER_THROAT_RATIO;
        printParametersToConsole(diameter);
    }

    private static void printParametersToConsole(double diameter) {
        double
                diameter_mm = diameter * 1000,
                diameter_mm_rounded = ((int) (diameter_mm * 100)) / 100.0;
        System.out.println("Combustion chamber diameter: " + diameter_mm_rounded + " mm");
    }

    //
    public double getDiameter() {
        return diameter;
    }
}