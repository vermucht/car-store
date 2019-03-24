package carstore.store;

import carstore.model.Image;
import carstore.model.User;
import carstore.model.car.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Car in-session storage.
 *
 * @author Aleksei Sapozhnikov (vermucht@gmail.com)
 * @version 0.1
 * @since 0.1
 */
public class NewCarStore extends AbstractStore {
    /**
     * Logger.
     */
    @SuppressWarnings("unused")
    private static final Logger LOG = LogManager.getLogger(NewCarStore.class);

    /**
     * Returns List of all cars stored.
     *
     * @param ses Current session.
     * @return List of cars stored.
     */
    @SuppressWarnings("unchecked")
    public List<Car> getAll(Session ses) {
        return this.doTransactionWithRollback(ses,
                session -> session.createQuery("from Car").list());
    }

    public Car createAndSave(Session ses, User user, Map<String, String> values, Set<Image> images) {
        return this.doTransactionWithCommit(ses, session -> {
            var car = new Car();
            car.setSeller(user);
            this.sstParameters(car, values);
            if (this.notEmpty(images)) {
                this.setImages(car, images);
            }
            session.save(car);
            return car;
        });
    }


    private boolean notEmpty(Set<Image> images) {
        return images.stream().anyMatch(img -> img.getData().length > 0);
    }

    private void setImages(Car car, Set<Image> images) {
        images.forEach(img -> img.setCar(car));
        car.setImages(images);
    }

    private void sstParameters(Car car, Map<String, String> values) {
        car.setMark(new Mark()
                .setManufacturer(values.get("mark_manufacturer"))
                .setModel(values.get("mark_model")));
        car.setAge(new Age()
                .setMileage(Long.parseLong(values.get("age_mileage")))
                .setManufactureYear(Integer.parseInt(values.get("age_manufactureYear")))
                .setNewness(values.get("age_newness")));
        car.setBody(new Body()
                .setColor(values.get("body_color"))
                .setType(values.get("body_type")));
        car.setChassis(new Chassis()
                .setTransmissionType(values.get("chassis_type")));
        car.setEngine(new Engine()
                .setEngineType(values.get("engine_type"))
                .setEngineVolume(Integer.parseInt(values.get("engine_volume"))));
        car.setPrice(Integer.parseInt(values.get("price")));
    }

}