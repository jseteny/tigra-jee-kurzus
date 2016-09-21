package hu.tigra.jee.data;

import hu.tigra.jee.model.Car;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
public class CarListProducer {

    @Inject
    private CarRepository carRepository;

    private List<Car> cars;

    // @Named provides access the return value via the EL variable name "cars" in the UI (e.g.
    // Facelets or JSP view)
    @Produces
    @Named
    public List<Car> getCars() {
        return cars;
    }

    @SuppressWarnings("UnusedParameters")
    public void onCarListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Car car) {
        retrieveAllCarsOrderedByName();
    }

    @PostConstruct
    public void retrieveAllCarsOrderedByName() {
        cars = carRepository.findAllOrderedByLicensePlateNumber();
    }
}
