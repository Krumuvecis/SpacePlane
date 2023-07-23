package vehicles.engines.propellants;

//for chemical engine
public class CarbonDioxidePropellant extends Propellant {
    private static final double MOLAR_MASS = 44.0 / 1000; // approximate

    //
    public CarbonDioxidePropellant() {
        super(MOLAR_MASS);
    }
}