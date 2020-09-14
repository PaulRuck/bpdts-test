package uk.me.ruck.bpdtstest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.me.ruck.bpdtstest.client.BptdsUsers;
import uk.me.ruck.bpdtstest.model.User;
import uk.me.ruck.bpdtstest.utils.DistanceCalculator;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    public static final double LONDON_LAT = 51.5074;
    public static final double LONDON_LON = -0.1278;
    public static final double NEAR_LONDON_MILES = 60;

    private BptdsUsers usersClient;

    public UserServiceImpl(BptdsUsers usersClient) {
        this.usersClient = usersClient;
    }

    @Override
    public List<User> getUsersInLondon() {
        List<User> users = usersClient.usersByCity("London");
        log.info("Users found in London: {}", users.size());
        return users;
    }

    @Override
    public List<User> getUsersNearLondon() {
        List<User> users = usersClient.allUsers();

        List<User> usersNearLondon =
                users.stream().filter(user -> isNearLondon(user.getLatitude(),user.getLongitude())).collect(Collectors.toList());

        log.info("Users within {} miles of London: {}", NEAR_LONDON_MILES, usersNearLondon.size());

        return usersNearLondon;
    }

    private boolean isNearLondon(double lat, double lon){
        double distance = DistanceCalculator.distanceInMiles(LONDON_LAT, LONDON_LON, lat, lon);
        return distance < NEAR_LONDON_MILES;
    }
}
