package vehicles.engines.propellants;

//
public class WaterPropellant extends Propellant {
    private static final double MOLAR_MASS = 18.0 / 1000; // approximate

    //
    public WaterPropellant() {
        super(MOLAR_MASS);
    }
}