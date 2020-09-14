package uk.me.ruck.bpdtstest.client;

import uk.me.ruck.bpdtstest.model.User;
import java.util.List;

public interface BptdsUsers {
    List<User> allUsers();
    List<User> usersByCity(String cityName);
}
