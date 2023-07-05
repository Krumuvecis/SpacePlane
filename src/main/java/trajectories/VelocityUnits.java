package trajectories;

import java.util.HashMap;
import java.util.Map;

public class VelocityUnits {
    public enum VelocityUnitType {
        MS,
        KMS,
        KMH,
        MACH_AT_SEA,
        PART_OF_C
    }

    public static final VelocityUnitType DEFAULT_UNIT = VelocityUnitType.MS;
    private static final int
            METERS_IN_KILOMETER = 1000,
            SECONDS_IN_HOUR = 3600,
            SPEED_OF_SOUND_AT_SEA_LEVEL = 330, // m/s
            SPEED_OF_LIGHT = 3 * (int) Math.pow(10, 8); // m/s

    //stores divisor for lower->higher conversion, or multiplicand for higher->lower conversion
    private static final Map<VelocityUnitType, Double> UNIT_CONVERSION_TABLE = new HashMap<>() {{
        put(VelocityUnitType.MS, (double) 1);
        put(VelocityUnitType.KMS, (double) METERS_IN_KILOMETER);
        put(VelocityUnitType.KMH, (double) (METERS_IN_KILOMETER / SECONDS_IN_HOUR));
        put(VelocityUnitType.MACH_AT_SEA, (double) SPEED_OF_SOUND_AT_SEA_LEVEL);
        put(VelocityUnitType.PART_OF_C, (double) SPEED_OF_LIGHT);
    }};

    public static double convert(double value, VelocityUnitType startingUnit, VelocityUnitType targetUnit) {
        return convertFromDefault(convertToDefault(value, startingUnit), targetUnit);
    }

    private static double convertToDefault(double value, VelocityUnitType unit) {
        if (UNIT_CONVERSION_TABLE.containsKey(unit)) {
            return value * UNIT_CONVERSION_TABLE.get(unit);
        } else {
            throw new UndefinedVelocityUnitException();
        }
    }

    private static double convertFromDefault(double value, VelocityUnitType unit) {
        if (UNIT_CONVERSION_TABLE.containsKey(unit)) {
            return value / UNIT_CONVERSION_TABLE.get(unit);
        } else {
            throw new UndefinedVelocityUnitException();
        }
    }

    private static class UndefinedVelocityUnitException extends RuntimeException {
        UndefinedVelocityUnitException() {
            super("Velocity unit not defined!");
        }
    }
}