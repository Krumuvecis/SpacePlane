package vehicles.engines.chemical;

import vehicles.engines.Engine;

//
public class ChemicalEngine extends Engine {
    private static final double
            EXHAUST_VELOCITY = 5000,
            EXHAUST_DIAMETER = 1,
            HARDCODED_MASS = 1000;

    private final CombustionChamber combustionChamber;
    private final Nozzle nozzle;

    //use this for creating new chemical engine
    public static ChemicalEngine newChemicalEngine(double targetDiameter) {
        Nozzle nozzle = new Nozzle(EXHAUST_DIAMETER);
        CombustionChamber combustionChamber = new CombustionChamber(nozzle.getThroatDiameter());
        double
                finalDiameter = Math.max(combustionChamber.getDiameter(), nozzle.getDiameter()),
                finalLength = combustionChamber.getDiameter() + nozzle.getLength(),
                finalMass = HARDCODED_MASS;

        return new ChemicalEngine(
                finalMass, finalLength, finalDiameter,
                combustionChamber, nozzle);
    }

    private ChemicalEngine(double mass, double length, double diameter,
                           CombustionChamber combustionChamber, Nozzle nozzle) {
        super(mass, length, diameter);
        this.combustionChamber = combustionChamber;
        this.nozzle = nozzle;
    }

    //
    @Override
    public double getExhaustVelocity() {
        return EXHAUST_VELOCITY;
    }

    //
    public CombustionChamber getCombustionChamber() {
        return combustionChamber;
    }

    //
    public Nozzle getNozzle() {
        return nozzle;
    }
}