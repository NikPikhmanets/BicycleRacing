package com.bicycleracing.analysis.utils;

public class MathUtil {

    private MathUtil() {
    }

    public static double hav(double x) {
        double sinHalf = Math.sin(x * 0.5D);
        return sinHalf * sinHalf;
    }

    public static double arcHav(double x) {
        return 2.0D * Math.asin(Math.sqrt(x));
    }

    public static double havDistance(double lat1, double lat2, double dLng) {
        return hav(lat1 - lat2) + hav(dLng) * Math.cos(lat1) * Math.cos(lat2);
    }
}
