package pl.klusek.michal.dao;

import pl.klusek.michal.model.User;

public interface IUserDAO {
    void addUser(User user);
    void changeUserPassword(User user);
    User getUserByLogin(String login);
}
