package pl.klusek.michal.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.klusek.michal.dao.IVehicleDAO;
import pl.klusek.michal.model.Car;
import pl.klusek.michal.model.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class VehicleDAO implements IVehicleDAO {
    @Autowired
    SessionFactory sessionFactory;
    @Override
    public List<Vehicle> getFilteredVehicles(String pattern) {
        Session session = this.sessionFactory.openSession();
        Query<Vehicle> query = session.createQuery("FROM pl.klusek.michal.model.Vehicle WHERE brand LIKE :pattern OR model LIKE :pattern");
        query.setParameter("pattern", "%" + pattern + "%");
        List<Vehicle> vehicles = query.getResultList();
        return vehicles;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        Session session = this.sessionFactory.openSession();
        Query<Vehicle> query = session.createQuery("FROM pl.klusek.michal.model.Vehicle");
        List<Vehicle> vehicles = query.getResultList();
        session.close();
        return vehicles;
    }

 /*   @Override
    public Vehicle getVehicleByData(String brand, String model, String licensePlate) {
        Session session = this.sessionFactory.openSession();
        Query<Vehicle> query = session.createQuery("FROM pl.klusek.michal.model.Vehicle WHERE brand = :brand AND model = :model AND licensePlate = :licensePlate");
        query.setParameter("brand", brand);
        query.setParameter("model", model);
        query.setParameter("licensePlate", licensePlate);

        Vehicle vehicle = null;
        try{
            vehicle = query.getSingleResult();
        } catch (NoResultException e){

        }finally {
            session.close();
        }

        return vehicle;
    }*/

    @Override
    public void updateVehicle(Vehicle vehicle) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.update(vehicle);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(vehicle);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public Vehicle findVehicleByLicensePlate(String licensePlate) {
        Session session = this.sessionFactory.openSession();
        Query<Vehicle> query = session.createQuery("FROM pl.klusek.michal.model.Vehicle WHERE licensePlate = :licensePlate");
        query.setParameter("licensePlate", licensePlate);
        Vehicle vehicle = null;
        try{
            vehicle = query.getSingleResult();
        }catch (NoResultException e){

        }finally {
            session.close();
        }

        return  vehicle;
    }

    @Override
    public Vehicle getVehicleById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Vehicle> query = session.createQuery("FROM pl.klusek.michal.model.Vehicle WHERE vehicle_id = :id");
        query.setParameter("id", id);
        Vehicle vehicle = null;
        try{
            vehicle = query.getSingleResult();
        }catch (NoResultException e){

        }finally {
            session.close();
        }
        return vehicle;
    }

    @Override
    public List<Vehicle> getFilteredVehiclesByType(String vehicleType) {
        Session session = this.sessionFactory.openSession();
        List<Vehicle> vehicles = null;
        if(vehicleType.equals("car")){
            Query<Vehicle> query = session.createQuery("FROM pl.klusek.michal.model.Car");
            vehicles = query.getResultList();
            session.close();
            return vehicles;
        }else if(vehicleType.equals("motorcycle")){
            Query<Vehicle> query = session.createQuery("FROM pl.klusek.michal.model.Motorcycle");
            vehicles = query.getResultList();
            session.close();
            return vehicles;
        }else if(vehicleType.equals("bicycle")) {
            Query<Vehicle> query = session.createQuery("FROM pl.klusek.michal.model.Bike");
            vehicles = query.getResultList();
            session.close();
            return vehicles;
        }else if(vehicleType.equals("vehicles")){
            Query<Vehicle> query = session.createQuery("FROM pl.klusek.michal.model.Vehicle");
            vehicles = query.getResultList();
            session.close();
            return vehicles;
        }else{
            session.close();
            return vehicles;
        }
    }

    @Override
    public List<Vehicle> getFilteredVehiclesByTransmission(String transmission_type) {
        Session session = this.sessionFactory.openSession();
        List<Vehicle> vehicles = null;
        if(transmission_type.equals("automatic")){
            Query<Vehicle> vehicleQuery1 = session.createQuery("FROM pl.klusek.michal.model.Vehicle WHERE transmission = 'automatic'");
            vehicles = vehicleQuery1.getResultList();
            session.close();
            return vehicles;
        }else if(transmission_type.equals("manual")){
            Query<Vehicle> vehicleQuery = session.createQuery("FROM pl.klusek.michal.model.Vehicle WHERE transmission = 'manual'");
            vehicles = vehicleQuery.getResultList();
            session.close();
            return vehicles;
        }else{
            session.close();
            return vehicles;
        }
    }
}
