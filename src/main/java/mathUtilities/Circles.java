package mathUtilities;

//
public class Circles {
    // C = 2 * pi * R
    public static double getCircumference(double radius) {
        return 2 * Math.PI * radius;
    }

    // R = C / 2 / pi
    public static double getRadiusFromCircumference(double circumference) {
        return circumference / 2 / Math.PI;
    }

    // S = pi * R ^ 2
    public static double getArea(double radius) {
        return Math.PI * Math.pow(radius, 2);
    }

    // R = sqrt(S / pi)
    public static double getRadiusFromArea(double area) {
        return Math.sqrt(area / Math.PI);
    }
}