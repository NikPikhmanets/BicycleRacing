package com.bicycleracing.analysis.utils;

public class DistanceUtils {

    private DistanceUtils() {
    }

    public static double computeDistanceBetween(LatLng from, LatLng to) {
        return computeAngleBetween(from, to) * 6371009.0D;
    }

    private static double computeAngleBetween(LatLng from, LatLng to) {
        return distanceRadians(
                Math.toRadians(from.getLatitude()),
                Math.toRadians(from.getLongitude()),
                Math.toRadians(to.getLatitude()),
                Math.toRadians(to.getLongitude())
        );
    }

    private static double distanceRadians(double lat1, double lng1, double lat2, double lng2) {
        return MathUtil.arcHav(MathUtil.havDistance(lat1, lat2, lng1 - lng2));
    }
}
