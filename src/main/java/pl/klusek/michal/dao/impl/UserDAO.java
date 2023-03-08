package pl.klusek.michal.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.klusek.michal.dao.IUserDAO;
import pl.klusek.michal.model.User;

import javax.persistence.NoResultException;

@Repository
public class UserDAO implements IUserDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User getUserByLogin(String login) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.klusek.michal.model.User "
                + "WHERE login = :login");
        query.setParameter("login", login);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {

        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public void changeUserPassword(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }
    }
}
