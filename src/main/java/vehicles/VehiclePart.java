package vehicles;

//
public class VehiclePart {
    private final String name;
    private final double
            mass,
            length,
            diameter;

    //
    VehiclePart(String name, int mass, int length, int diameter) {
        this.name = name;
        this.mass = mass;
        this.length = length;
        this.diameter = diameter;
    }

    //
    public String getName() {
        return name;
    }

    //
    public double getMass() {
        return mass;
    }

    //
    public double getLength() {
        return length;
    }

    //
    public double getDiameter() {
        return diameter;
    }
}