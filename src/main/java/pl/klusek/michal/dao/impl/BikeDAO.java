package pl.klusek.michal.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.klusek.michal.dao.IBikeDAO;
import pl.klusek.michal.model.Bike;
import pl.klusek.michal.model.Car;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class BikeDAO implements IBikeDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Bike> getAllBikes() {
        Session session = this.sessionFactory.openSession();
        Query<Bike> query = session.createQuery("FROM pl.klusek.michal.model.Bike");
        List<Bike> bikes = query.getResultList();
        session.close();
        return bikes;
    }

    @Override
    public void addBike(Bike bike) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(bike);
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
    public void updateBike(Bike bike) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.update(bike);
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
    public Bike getBikeById(int bikeId) {
        Session session = this.sessionFactory.openSession();
        Query<Bike> query = session.createQuery("FROM pl.klusek.michal.model.Bike WHERE id = :id");
        query.setParameter("id", bikeId);

        Bike bike = null;
        try{
            bike = query.getSingleResult();
        } catch (NoResultException e){

        }finally {
            session.close();
        }
        return bike;
    }

    @Override
    public Bike FindBikeByLicensePlate(String plate) {
        Session session = this.sessionFactory.openSession();
        Query<Bike> query = session.createQuery("FROM pl.klusek.michal.model.Bike WHERE licensePlate = :plate");
        query.setParameter("plate", plate);
        Bike bike = null;
        try{
            bike = query.getSingleResult();
        } catch(NoResultException e){

        } finally {
            session.close();
        }
        return bike;
    }
}
