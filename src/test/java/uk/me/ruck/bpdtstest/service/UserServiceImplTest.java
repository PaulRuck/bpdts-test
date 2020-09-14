package uk.me.ruck.bpdtstest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.me.ruck.bpdtstest.client.BptdsUsers;
import uk.me.ruck.bpdtstest.mock.UserMock;
import uk.me.ruck.bpdtstest.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private BptdsUsers userClient;

    private UserService userService;

    @BeforeEach
    void beforeTests(){
        userService = new UserServiceImpl(userClient);
    }

    @Test
    void getUsersInLondon_userByCityIsCalledOnce() {
        userService.getUsersInLondon();
        verify(userClient, times(1)).usersByCity("London");
    }

    @Test
    void getUsersNearLondon_getAllUsersWithNoneBeingNearLondon() {
        when(userClient.allUsers()).thenReturn(UserMock.getUsers(20));
        List<User> users = userService.getUsersNearLondon();

        verify(userClient, times(1)).allUsers();
        assertEquals(0, users.size());
    }

    @Test
    void getUsersNearLondon_getAllUsersWithTwoBeingNearLondon() {
        when(userClient.allUsers()).thenReturn(UserMock.getUsersTwoNearLondon());
        List<User> users = userService.getUsersNearLondon();

        verify(userClient, times(1)).allUsers();
        assertEquals(2, users.size());
    }
}