package vehicles.engines;

//
public class IonEngine extends Engine {
    private static final double
            EXHAUST_VELOCITY = 20000, // arbitrary
            HARDCODED_DIAMETER = 3,   // arbitrary
            HARDCODED_LENGTH = 1,     // arbitrary
            HARDCODED_MASS = 1000;    // arbitrary

    //
    protected IonEngine() {
        super(calculateMass(), calculateLength(), calculateDiameter());
    }

    private static double calculateDiameter() {
        return HARDCODED_DIAMETER;
    }

    private static double calculateLength() {
        return HARDCODED_LENGTH;
    }

    private static double calculateMass() {
        return HARDCODED_MASS;
    }

    //
    @Override
    public double getExhaustVelocity() {
        return EXHAUST_VELOCITY;
    }
}