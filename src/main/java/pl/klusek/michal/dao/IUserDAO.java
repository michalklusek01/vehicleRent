package pl.klusek.michal.dao;

import pl.klusek.michal.model.User;

public interface IUserDAO {
    User getUserByLogin(String login);
    void addUser(User user);
}
