package pl.klusek.michal.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.klusek.michal.dao.IVehicleDAO;
import pl.klusek.michal.model.Vehicle;

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

    @Override
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
    }
}
