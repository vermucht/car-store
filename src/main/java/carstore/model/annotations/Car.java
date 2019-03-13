package carstore.model.annotations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.util.Objects;

/**
 * Car bean.
 *
 * @author Aleksei Sapozhnikov (vermucht@gmail.com)
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "car")
public class Car {
    /**
     * Logger.
     */
    @SuppressWarnings("unused")
    private static final Logger LOG = LogManager.getLogger(Car.class);

    /**
     * Unique id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int id;
    /**
     * Price
     */
    @Column(name = "price")
    private int price;
    /**
     * Mark info
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "mark_id")
    private Mark mark;
    /**
     * Body info
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "body_id")
    private Body body;
    /**
     * Age info
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "age_id")
    private Age age;
    /**
     * Engine info
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "engine_id")
    private Engine engine;
    /**
     * Chassis info
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "chassis_id")
    private Chassis chassis;

    /* * * * * * * * * * * * * * * * * *
     * equals(), hashCode(), toString()
     * * * * * * * * * * * * * * * * * */

    /**
     * Object.equals() method override.
     *
     * @param o Other object.
     * @return <tt>true</tt> if this and given objects are equal, <tt>false</tt> if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id
                && price == car.price
                && Objects.equals(mark, car.mark)
                && Objects.equals(body, car.body)
                && Objects.equals(age, car.age)
                && Objects.equals(engine, car.engine)
                && Objects.equals(chassis, car.chassis);
    }

    /**
     * Returns this object's hashcode.
     *
     * @return Object's hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, price, mark, body, age, engine, chassis);
    }

    /**
     * Returns current object state as String object.
     *
     * @return Current object state as String object.
     */
    @Override
    public String toString() {
        return String.format(
                "Car{id=%d, price=%d, mark=%s, body=%s, age=%s, engine=%s, chassis=%s}",
                this.id, this.price, this.mark, this.body, this.age, this.engine, this.chassis);
    }

    /* * * * * * * * * * * *
     * getters and setters
     * * * * * * * * * * * */

    /**
     * Returns id.
     *
     * @return Value of id field.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets id value.
     *
     * @param id Value to set.
     */
    public Car setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Returns price.
     *
     * @return Value of price field.
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Sets price value.
     *
     * @param price Value to set.
     */
    public Car setPrice(int price) {
        this.price = price;
        return this;
    }

    /**
     * Returns mark.
     *
     * @return Value of mark field.
     */
    public Mark getMark() {
        return this.mark;
    }

    /**
     * Sets mark value.
     *
     * @param mark Value to set.
     */
    public Car setMark(Mark mark) {
        this.mark = mark;
        return this;
    }

    /**
     * Returns body.
     *
     * @return Value of body field.
     */
    public Body getBody() {
        return this.body;
    }

    /**
     * Sets body value.
     *
     * @param body Value to set.
     */
    public Car setBody(Body body) {
        this.body = body;
        return this;
    }

    /**
     * Returns age.
     *
     * @return Value of age field.
     */
    public Age getAge() {
        return this.age;
    }

    /**
     * Sets age value.
     *
     * @param age Value to set.
     */
    public Car setAge(Age age) {
        this.age = age;
        return this;
    }

    /**
     * Returns engine.
     *
     * @return Value of engine field.
     */
    public Engine getEngine() {
        return this.engine;
    }

    /**
     * Sets engine value.
     *
     * @param engine Value to set.
     */
    public Car setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    /**
     * Returns chassis.
     *
     * @return Value of chassis field.
     */
    public Chassis getChassis() {
        return this.chassis;
    }

    /**
     * Sets chassis value.
     *
     * @param chassis Value to set.
     */
    public Car setChassis(Chassis chassis) {
        this.chassis = chassis;
        return this;
    }
}
