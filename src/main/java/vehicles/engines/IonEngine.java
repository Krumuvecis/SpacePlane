package vehicles.engines;

import vehicles.engines.propellants.Propellant;
import vehicles.engines.propellants.SodiumPropellant;

//
public class IonEngine extends Engine {
    private static final double
            EXHAUST_VELOCITY = 20000,       // arbitrary
            EXHAUST_TEMPERATURE = 5000,    // arbitrary
            EXHAUST_PRESSURE = 0.1 * 10000, // arbitrary
            HARDCODED_LENGTH = 2,           // arbitrary
            HARDCODED_MASS = 10000;         // arbitrary
    private static final Propellant PROPELLANT = new SodiumPropellant();

    //
    protected static IonEngine newIonEngine(double thrust) {
        Exhaust exhaust = new Exhaust(
                thrust, EXHAUST_VELOCITY,
                PROPELLANT.getDensity(EXHAUST_TEMPERATURE, EXHAUST_PRESSURE));
        return new IonEngine(HARDCODED_MASS, HARDCODED_LENGTH, exhaust.getDiameter());
    }

    //
    private IonEngine(double mass, double length, double diameter) {
        super(mass, length, diameter);
    }

    //
    @Override
    public double getExhaustVelocity() {
        return EXHAUST_VELOCITY;
    }
}