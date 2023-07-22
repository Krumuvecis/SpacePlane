package vehicles.engines;

public class IonEngine extends Engine {
    private static final double EXHAUST_VELOCITY = 20000;

    protected IonEngine(double targetDiameter) {
        super(calculateMass(), 1, targetDiameter);
    }

    private static double calculateMass() {
        return 10;
    }

    @Override
    public double getExhaustVelocity() {
        return EXHAUST_VELOCITY;
    }
}