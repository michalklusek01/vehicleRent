package pl.klusek.michal.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klusek.michal.dao.IUserDAO;
import pl.klusek.michal.model.User;
import pl.klusek.michal.services.IAuthenticationService;
import pl.klusek.michal.session.SessionObject;

@Service
public class AuthenticationService implements IAuthenticationService {
    @Autowired
    IUserDAO userDAO;

    @Autowired
    SessionObject sessionObject;

    @Override
    public boolean authenticate(String login, String password) {
        User user = this.userDAO.getUserByLogin(login);

        if(user != null && user.getPassword().equals(DigestUtils.md5Hex(password))){
            sessionObject.setUser(user);
            return true;
        }
        return false;
    }

    @Override
    public void registerUser(User user) {
        user.setRole(User.Role.USER);
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        this.userDAO.addUser(user);
    }

    @Override
    public void updateUserPassword(User user) {
        this.userDAO.changeUserPassword(user);
    }

    @Override
    public User getUserByLogin(String login) {
        if(this.userDAO.getUserByLogin(login) == null){
            return null;
        }else{
            return this.userDAO.getUserByLogin(login);
        }
    }

    @Override
    public boolean checkUserData(User user) {
        User userFromDb = this.userDAO.getUserByLogin(user.getLogin());
        if(user.getName().equals(userFromDb.getName()) && user.getSurname().equals(userFromDb.getSurname())){
            return true;
        }
        return false;
    }
}
