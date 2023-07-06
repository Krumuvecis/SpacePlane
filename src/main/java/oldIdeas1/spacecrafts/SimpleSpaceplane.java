package oldIdeas1.spacecrafts;

import java.util.ArrayList;
import java.util.List;

public class SimpleSpaceplane {
    List<Engine> engines;

    int dryMass; // kg

    SimpleSpaceplane() {
        engines = new ArrayList<>() {{
            add(new IonEngine());
            add(new AirBreathingRocketEngine());
        }};
    }

    /* Phases of flight:
     * 1. liftoff, initial acceleration on runway.
     * 2. ascent through atmosphere, reaching ballistic height
     * 3. ballistic sub-orbital trajectory
     * 4. orbital insertion
     * 5. maneuvers
     */


    int startingSpeed; // m/s, stationary on ground
    int ballisticMaximumHeight; // m, above sea level
    double getInitialSpeed;
    int orbitalSpeed; // m/s

    static abstract class Engine {
        int exhaustVelocity; // m/s
    }

    static class AirBreathingRocketEngine extends Engine {
        //
    }

    static class IonEngine extends Engine {
        //
    }
}