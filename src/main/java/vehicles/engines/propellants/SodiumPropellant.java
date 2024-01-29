package vehicles.engines.propellants;

//for ion engine
public class SodiumPropellant extends Propellant {
    private static final double MOLAR_MASS = 23.0 / 1000; // approximate

    //
    public SodiumPropellant() {
        super(MOLAR_MASS);
    }
}