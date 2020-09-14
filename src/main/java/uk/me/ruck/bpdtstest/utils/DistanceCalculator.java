package uk.me.ruck.bpdtstest.utils;

public class DistanceCalculator {
    public static double distanceInMiles(double lat1, double lon1, double lat2, double lon2) {
        if (lat1 == lat2 && lon1 == lon2) {
            return 0;
        } else {
            double theta = lon1 - lon2;
            double distance = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            distance = Math.acos(distance);
            distance = Math.toDegrees(distance);
            return distance * 60 * 1.1515;
        }
    }
}
