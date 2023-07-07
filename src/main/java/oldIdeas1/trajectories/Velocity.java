package oldIdeas1.trajectories;

public class Velocity {
    private double value;

    //
    public Velocity(double value) {
        this(value, VelocityUnits.DEFAULT_UNIT);
    }

    //
    public Velocity(double value, VelocityUnits.VelocityUnitType unit) {
        this.value = 0;
        set(value, unit);
    }

    //
    public void set(double value, VelocityUnits.VelocityUnitType unit) {
        this.value = VelocityUnits.convert(value, unit, VelocityUnits.DEFAULT_UNIT);
    }

    //gets in default units
    public double get() {
        return this.get(VelocityUnits.DEFAULT_UNIT);
    }

    //
    public double get(VelocityUnits.VelocityUnitType unit) {
        return VelocityUnits.convert(value, VelocityUnits.DEFAULT_UNIT, unit);
    }
}