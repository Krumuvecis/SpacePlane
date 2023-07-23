package vehicles.engines.chemical;

import vehicles.engines.Engine;
import vehicles.engines.propellants.Propellant;
import vehicles.engines.propellants.WaterPropellant;

//
public class ChemicalEngine extends Engine {
    private static final double
            EXHAUST_VELOCITY = 5000, // average, approximate
            EXHAUST_TEMPERATURE = 5000, // arbitrary
            EXHAUST_PRESSURE = 200000, // arbitrary
            HARDCODED_MASS = 5000;   // arbitrary
    private static final Propellant PROPELLANT = new WaterPropellant();

    private final CombustionChamber combustionChamber;
    private final Nozzle nozzle;

    //use this for creating new chemical engine
    public static ChemicalEngine newChemicalEngine(double thrust) {
        Nozzle nozzle = new Nozzle(
                PROPELLANT,
                thrust, EXHAUST_VELOCITY,
                EXHAUST_TEMPERATURE, EXHAUST_PRESSURE);
        double throatDiameter = nozzle.getThroatDiameter();
        CombustionChamber combustionChamber = new CombustionChamber(throatDiameter);

        double
                finalDiameter = Math.max(combustionChamber.getDiameter(), nozzle.getDiameter()),
                finalLength = combustionChamber.getDiameter() + nozzle.getLength();
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