package hu.tigra.jee.data;

import hu.tigra.jee.model.Car;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class CarRepository {

    @Inject
    private EntityManager em;

    List<Car> findAllOrderedByLicensePlateNumber() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Car> criteria = cb.createQuery(Car.class);
        Root<Car> car = criteria.from(Car.class);

        criteria.select(car).orderBy(cb.asc(car.get("licensePlateNumber")));

        // Type-safe criteria query, a new feature in JPA 2.0
        // Car_ egy generált osztály. Nem kell megírni kézzel
        // criteria.select(car).orderBy(cb.asc(car.get(Car_.licensePlateNumber)));

        return em.createQuery(criteria).getResultList();
    }
}
