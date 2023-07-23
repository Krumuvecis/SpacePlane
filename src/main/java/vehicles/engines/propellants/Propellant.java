package vehicles.engines.propellants;

//
public class Propellant {
    private static final double GAS_CONSTANT = 8.31;

    private final double molarMass;

    //
    public Propellant(double molarMass) {
        this.molarMass = molarMass;
    }

    // ro = M * p / (R * T)
    public double getDensity(double temperature, double pressure) {
        return molarMass * pressure / (GAS_CONSTANT * temperature);
    }
}