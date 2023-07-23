package mathUtilities;

//
public class Cylinders {
    //
    public static double getVolume(double diameter, double length) {
        return getCrossSection(diameter) * length;
    }

    //
    public static double getLength(double volume, double diameter) {
        return volume / getCrossSection(diameter);
    }

    private static double getCrossSection(double diameter) {
        return Circles.getArea(diameter / 2);
    }
}