package pl.klusek.michal.services;

import pl.klusek.michal.model.User;

public interface IAuthenticationService {
    boolean authenticate(String login, String password);
    void registerUser(User user);
}
