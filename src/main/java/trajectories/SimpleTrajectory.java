package trajectories;

import java.util.ArrayList;
import java.util.List;

public class SimpleTrajectory {
    /*static final Velocity ATMOSPHERIC_CRUISE_SPEED = new Velocity(500); // m/s

    List<FlightPhase> phases;

    SimpleTrajectory() {
        phases = new ArrayList<>();
        addPhases();
    }

    void addPhases() {
        phases.add(new LiftoffPhase());
        phases.add(new AscentPhase());
        phases.add(new BallisticPhase());
        phases.add(new OrbitalPhase());
        phases.add(new ManeuverPhase());
    }

    static class FlightPhaseResults {
        double
                propellantMass, // kg
                energy; // J

        FlightPhaseResults(double propellantMass, int exhaustVelocity) {
            this.propellantMass = propellantMass;
            this.energy = propellantMass * Math.pow(exhaustVelocity, 2) / 2;
        }
    }

    static abstract class FlightPhase {
        int deltaV;

        FlightPhase(int deltaV) {
            this.deltaV = deltaV;
        }

        abstract FlightPhaseResults calculate();
    }

    static class LiftoffPhase extends FlightPhase {
        static final int STATIONARY_VELOCITY = 200; // m/s
        LiftoffPhase(int targetVelocity) {
            super(targetVelocity - STATIONARY_VELOCITY);
        }

        @Override
        FlightPhaseResults calculate() {
            return new FlightPhaseResults(100, 100);
        }
    }

    static class AscentPhase extends FlightPhase {
        AscentPhase() {
            //
        }

        @Override
        FlightPhaseResults calculate() {
            return new FlightPhaseResults(100, 100);
        }
    }

    static class BallisticPhase extends FlightPhase {
        BallisticPhase() {
            //
        }

        @Override
        FlightPhaseResults calculate() {
            return new FlightPhaseResults(100, 100);
        }
    }

    static class OrbitalPhase extends FlightPhase {
        OrbitalPhase() {
            //
        }

        @Override
        FlightPhaseResults calculate() {
            return new FlightPhaseResults(100, 100);
        }
    }

    static class ManeuverPhase extends FlightPhase {
        ManeuverPhase() {
            //
        }

        @Override
        FlightPhaseResults calculate() {
            return new FlightPhaseResults(100, 100);
        }
    }*/
}