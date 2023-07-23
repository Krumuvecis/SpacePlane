package vehicles.engines.chemical;

import vehicles.engines.Engine;

//
public class ChemicalEngine extends Engine {
    private static final double
            EXHAUST_VELOCITY = 5000, // average, approximate
            PROPELLANT_MOLAR_MASS = 18 / 1000.0,
            EXHAUST_TEMPERATURE = 5000,
            EXHAUST_PRESSURE = 200000,
            HARDCODED_MASS = 5000;   // arbitrary

    private final CombustionChamber combustionChamber;
    private final Nozzle nozzle;

    //use this for creating new chemical engine
    public static ChemicalEngine newChemicalEngine(double thrust) {
        Nozzle nozzle = new Nozzle(
                thrust, EXHAUST_VELOCITY,
                PROPELLANT_MOLAR_MASS, EXHAUST_TEMPERATURE, EXHAUST_PRESSURE);
        double throatDiameter = nozzle.getThroatDiameter();
        System.out.println("Throat diameter: " + throatDiameter + " m");
        CombustionChamber combustionChamber = new CombustionChamber(throatDiameter);

        double
                finalDiameter = Math.max(combustionChamber.getDiameter(), nozzle.getDiameter()),
                finalLength = combustionChamber.getDiameter() + nozzle.getLength();

        System.out.println("Total engine size: " + finalLength + " x " + finalDiameter);

        return new ChemicalEngine(
                HARDCODED_MASS, finalLength, finalDiameter,
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