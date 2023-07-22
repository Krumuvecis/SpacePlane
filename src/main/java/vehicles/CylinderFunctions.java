package vehicles;

//
public class CylinderFunctions {
    //
    public static double getCircleArea(double radius) {
        return Math.PI * Math.pow(radius, 2);
    }

    private static double getCrossSection(double diameter) {
        return getCircleArea(diameter / 2);
    }

    //
    public static double getCylinderVolume(double diameter, double length) {
        return getCrossSection(diameter) * length;
    }

    //
    public static double getCylinderLength(double volume, double diameter) {
        return volume / getCrossSection(diameter);
    }
}