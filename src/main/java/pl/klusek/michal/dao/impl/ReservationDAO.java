package pl.klusek.michal.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.klusek.michal.dao.IReservationDAO;
import pl.klusek.michal.model.Reservation;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class ReservationDAO implements IReservationDAO {
    //TODO
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addReservation(Reservation reservation) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            session.save(reservation);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.delete(reservation);
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
    public List<Reservation> getReservationsByUserId(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Reservation> query = session.createQuery("FROM pl.klusek.michal.model.Reservation WHERE user_id = :id");
        query.setParameter("id", id);

        List<Reservation> userReservations= query.getResultList();
        session.close();
        return userReservations;
    }

    @Override
    public List<Reservation> getAllReservations() {
        Session session = this.sessionFactory.openSession();
        Query<Reservation> query = session.createQuery("FROM pl.klusek.michal.model.Reservation");
        List<Reservation> reservations = null;

        try{
            reservations = query.getResultList();
        } catch (NoResultException e){

        }finally {
            session.close();
        }

        return reservations;
    }

    @Override
    public List<Reservation> getReservationsByVehicleId(int userId) {
        Session session = this.sessionFactory.openSession();
        Query<Reservation> query = session.createQuery("FROM pl.klusek.michal.model.Reservation WHERE vehicle_vehicle_id = :id");
        query.setParameter("id", userId);
        List<Reservation> reservations = null;

        try {
            reservations = query.getResultList();
        }catch (NoResultException e){

        }finally {
            session.close();
        }

        return reservations;
    }

    @Override
    public Reservation getReservationById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Reservation> query = session.createQuery("FROM pl.klusek.michal.model.Reservation WHERE id = :id");
        query.setParameter("id", id);
        Reservation reservation = null;
        try {
            reservation = query.getSingleResult();
        }catch (NoResultException e){

        }finally {
            session.close();
        }
        return reservation;
    }
}
