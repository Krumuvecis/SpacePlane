package vehicles.engines.propellants;

//for ion engine
public class PotassiumPropellant extends Propellant {
    private static final double MOLAR_MASS = 39.0 / 1000; // approximate

    //
    public PotassiumPropellant() {
        super(MOLAR_MASS);
    }
}