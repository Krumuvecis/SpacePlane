package vehicles.engines;

public class ChemicalEngine extends Engine {
    private static final double EXHAUST_VELOCITY = 5000;

    protected ChemicalEngine(double diameter) {
        super(diameter);
    }

    @Override
    public double getExhaustVelocity() {
        return EXHAUST_VELOCITY;
    }
}