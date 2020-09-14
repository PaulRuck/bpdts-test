package uk.me.ruck.bpdtstest.service;

import uk.me.ruck.bpdtstest.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsersInLondon();
    List<User> getUsersNearLondon();
}
