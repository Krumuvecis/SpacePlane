package vehicles.engines;

public class ChemicalEngine extends Engine {
    private static final double EXHAUST_VELOCITY = 5000;
    private static final double
            COMBUSTION_CHAMBER_DIAMETER = 0.5,
            NOZZLE_PROPORTIONS = 3, // length / diameter
            HARDCODED_MASS = 1000;
    private final double nozzleLength;

    //use this for creating new chemical engine
    protected static ChemicalEngine newChemicalEngine(double targetDiameter) {
        double
                finalDiameter = calculateFinalDiameter(targetDiameter),
                nozzleLength = finalDiameter * NOZZLE_PROPORTIONS,
                finalLength = COMBUSTION_CHAMBER_DIAMETER + nozzleLength,
                finalMass = calculateMass();
        return new ChemicalEngine(finalMass, finalLength, finalDiameter, nozzleLength);
    }

    private ChemicalEngine(double mass, double length, double diameter, double nozzleLength) {
        super(mass, length, diameter);
        this.nozzleLength = nozzleLength;
    }

    private static double calculateFinalDiameter(double targetDiameter) {
        return Math.max(COMBUSTION_CHAMBER_DIAMETER, targetDiameter);
    }

    private static double calculateMass() {
        return HARDCODED_MASS;
    }

    @Override
    public double getExhaustVelocity() {
        return EXHAUST_VELOCITY;
    }

    public double getCombustionChamberDiameter() {
        return COMBUSTION_CHAMBER_DIAMETER;
    }

    public double[] getNozzleSize() {
        return new double[] {nozzleLength, getDiameter()};
    }
}