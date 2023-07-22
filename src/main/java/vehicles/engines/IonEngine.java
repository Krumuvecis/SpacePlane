package vehicles.engines;

public class IonEngine extends Engine {
    private static final double EXHAUST_VELOCITY = 20000;

    protected IonEngine(double diameter) {
        super(diameter);
    }

    @Override
    public double getExhaustVelocity() {
        return EXHAUST_VELOCITY;
    }
}