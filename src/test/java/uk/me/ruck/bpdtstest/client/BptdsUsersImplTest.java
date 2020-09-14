package uk.me.ruck.bpdtstest.client;

import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.me.ruck.bpdtstest.mock.UserMock;
import uk.me.ruck.bpdtstest.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ConfigurationProperties(value = "bptds.users", ignoreUnknownFields = false)
@ExtendWith(MockitoExtension.class)
class BptdsUsersImplTest {

    @Setter
    private String apihost;

    @Autowired
    @Spy
    private BptdsUsersImpl client;

    @Test
    void allUsers() {
        int mockResultCount = 200;

        List<User> mockUsers = UserMock.getUsers(mockResultCount);
        ResponseEntity<User[]> mockResponse = new ResponseEntity(mockUsers.stream().toArray(User[]::new), HttpStatus.OK);
        doReturn(mockResponse).when(client).getUserListFromUrl(apihost + "users");

        List<User> users = client.allUsers();

        assertEquals(mockResultCount, users.size());
    }

    @Test
    void usersByCity() {
        String cityName = "London";
        int mockResultCount = 20;

        List<User> mockUsers = UserMock.getUsers(mockResultCount);
        ResponseEntity<User[]> mockResponse = new ResponseEntity(mockUsers.stream().toArray(User[]::new), HttpStatus.OK);
        doReturn(mockResponse).when(client).getUserListFromUrl(apihost + "city/" + cityName + "/users");

        List<User> users = client.usersByCity(cityName);

        assertEquals(mockResultCount, users.size());
    }
}