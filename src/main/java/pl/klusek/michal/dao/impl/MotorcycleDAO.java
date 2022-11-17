package pl.klusek.michal.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.klusek.michal.dao.IMotorcycleDAO;
import pl.klusek.michal.model.Motorcycle;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class MotorcycleDAO implements IMotorcycleDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Motorcycle> getAllMotorcycles() {
        Session session = this.sessionFactory.openSession();
        Query<Motorcycle> query = session.createQuery("FROM pl.klusek.michal.model.Motorcycle");
        List<Motorcycle> motorcycles = query.getResultList();
        session.close();

        return motorcycles;
    }

    @Override
    public void addMotorcycle(Motorcycle motorcycle) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(motorcycle);
            transaction.commit();
        } catch(Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public Motorcycle FindMotorcycleByLicensePlate(String plate) {
        Session session = this.sessionFactory.openSession();
        Query<Motorcycle> query = session.createQuery("FROM pl.klusek.michal.model.Motorcycle WHERE plate = :plate");
        query.setParameter("plate", plate);
        Motorcycle motorcycle = null;
        try{
            motorcycle = query.getSingleResult();
        } catch (NoResultException e){

        } finally {
            session.close();
        }

        return motorcycle;
    }

    @Override
    public void updateMotorcycle(Motorcycle motorcycle) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(motorcycle);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public Motorcycle getMotorcycleById(int motorcycleId) {
        Session session = this.sessionFactory.openSession();
        Query<Motorcycle> query = session.createQuery("FROM pl.klusek.michal.model.Motorcycle WHERE id = :id");
        query.setParameter("id", motorcycleId);

       Motorcycle motorcycle = null;
       try{
           motorcycle = query.getSingleResult();
       }catch (NoResultException e){

       }finally {
           session.close();
       }

       return motorcycle;
    }
}
