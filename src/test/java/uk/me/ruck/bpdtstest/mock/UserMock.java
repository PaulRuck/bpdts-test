package uk.me.ruck.bpdtstest.mock;

import uk.me.ruck.bpdtstest.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserMock {
    public static List<User> getUsers(int count){
        List<User> users = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            User user = User.builder()
                    .id(i+1)
                    .firstName("Firstname_" + (i+1))
                    .lastName("Lastname_" + (i+1))
                    .latitude(i%180)
                    .longitude(i%180)
                    .build();
            users.add(user);
        }

        return users;
    }

    public static List<User> getUsersTwoNearLondon(){
        int recordCount = 12;

        return Stream.concat(getTwoUsersNearLondon().stream(),getUsers(recordCount).stream())
                    .collect(Collectors.toList());
    }

    public static List<User> getTwoUsersNearLondon(){
        List<User> users = new ArrayList<>();

        users.add(User.builder()
                    .id(1)
                    .firstName("Firstname_nearLondon_1")
                    .lastName("Lastname_1")
                    .latitude(51.6074)
                    .longitude(-0.2278)
                    .build());

        users.add(User.builder()
                    .id(2)
                    .firstName("Firstname_nearLondon_2")
                    .lastName("Lastname_2")
                    .latitude(51.7074)
                    .longitude(-0.3278)
                    .build());

        return users;
    }
}
