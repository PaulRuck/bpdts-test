package uk.me.ruck.bpdtstest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import uk.me.ruck.bpdtstest.mock.UserMock;
import uk.me.ruck.bpdtstest.model.User;
import uk.me.ruck.bpdtstest.service.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserService userService;

    UserController userController;

    @BeforeEach
    void beforeEach() {
        userController = new UserController(userService);
    }

    @Test
    void getLondonUsers_returnsUserInAndNearLondon() {
        List<User> usersInLondon = UserMock.getUsers(20);
        List<User> usersNearLondon = UserMock.getTwoUsersNearLondon();
        int expectedSize = usersInLondon.size() + usersNearLondon.size();

        when(userService.getUsersInLondon()).thenReturn(usersInLondon);
        when(userService.getUsersNearLondon()).thenReturn(usersNearLondon);

        ResponseEntity<List<User>> response = userController.getLondonUsers();

        verify(userService, times(1)).getUsersInLondon();
        verify(userService, times(1)).getUsersNearLondon();

        assertEquals(expectedSize, response.getBody().size());
    }

    @Test
    void getLondonUsers_returnsUserInAndNearLondon_removingDuplicates() {
        List<User> usersInLondon = UserMock.getTwoUsersNearLondon();
        List<User> usersNearLondon = UserMock.getTwoUsersNearLondon();
        int expectedSize = usersNearLondon.size();

        when(userService.getUsersInLondon()).thenReturn(usersInLondon);
        when(userService.getUsersNearLondon()).thenReturn(usersNearLondon);

        ResponseEntity<List<User>> response = userController.getLondonUsers();

        verify(userService, times(1)).getUsersInLondon();
        verify(userService, times(1)).getUsersNearLondon();

        assertEquals(expectedSize, response.getBody().size());
    }
}