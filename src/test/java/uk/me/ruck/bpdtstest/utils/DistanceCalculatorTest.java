package uk.me.ruck.bpdtstest.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DistanceCalculatorTest {

    @Test
    void distanceInMiles_sameLocationWillBeZero() {
        double distance = DistanceCalculator.distanceInMiles(1.0,2.0,1.0,2.0);
        assertEquals(0,distance);
    }

    @Test
    void distanceInMiles_betweenLondonAndManchester() {
        // Central London: 51.5074, -0.1278
        // Central Manchester: 53.4808, -2.2426
        double expectedDistance = 162.78; // miles
        double distance = DistanceCalculator.distanceInMiles(51.5074, -0.1278,53.4808, -2.2426);
        assertEquals(expectedDistance,distance,2);
    }
}