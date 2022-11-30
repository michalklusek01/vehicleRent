package pl.klusek.michal.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.klusek.michal.dao.ICarDAO;
import pl.klusek.michal.model.Car;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class CarDAO implements ICarDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Car> getAllCars() {
        Session session = this.sessionFactory.openSession();
        Query<Car> query = session.createQuery("FROM pl.klusek.michal.model.Car");
        List<Car> cars = query.getResultList();
        session.close();
        return cars;
    }

    @Override
    public void addCar(Car car) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(car);
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
    public Car FindCarByLicensePlate(String plate) {
        Session session = this.sessionFactory.openSession();
        Query<Car> query = session.createQuery("FROM pl.klusek.michal.model.Car WHERE licensePlate = :plate");
        query.setParameter("plate", plate);
        Car car = null;
        try{
            car = query.getSingleResult();
        } catch(NoResultException e){

        } finally {
            session.close();
        }
        return car;
    }

    @Override
    public void updateCar(Car car) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.update(car);
            transaction.commit();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public Car getCarById(int carId) {
        Session session = this.sessionFactory.openSession();
        Query<Car> query = session.createQuery("FROM pl.klusek.michal.model.Car WHERE id = :id");
        query.setParameter("id", carId);
        Car car = null;
        try{
            car = query.getSingleResult();
        } catch (NoResultException e){

        } finally {
            session.close();
        }

        return car;
    }
}
