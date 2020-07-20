package ru.job4j.cars.models;

import javax.persistence.*;
import java.util.Objects;

/**
 * Car.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
@Entity
@Table (name = "cars")
public class Car {

    @Id
    @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "model_id", foreignKey = @ForeignKey(name = "MODEL_ID_FK"))
    private Model model;

    @Column (name = "car_year")
    private int year;

    @Column (name = "car_mileage")
    private int mileage;

    @Column (name = "car_colour", length = 50)
    private String colour;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_body_id", foreignKey = @ForeignKey(name = "CAR_BODY_ID_FK"))
    private CarBody carBody;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private Engine engine;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "transmission_id", foreignKey = @ForeignKey(name = "TRANSMISSION_ID_FK"))
    private Transmission transmission;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public CarBody getCarBody() {
        return carBody;
    }

    public void setCarBody(CarBody carBody) {
        this.carBody = carBody;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

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
                && year == car.year
                && mileage == car.mileage
                && Objects.equals(model, car.model)
                && Objects.equals(colour, car.colour)
                && Objects.equals(carBody, car.carBody)
                && Objects.equals(engine, car.engine)
                && Objects.equals(transmission, car.transmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, year, mileage, colour, carBody, engine, transmission);
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", model=" + model
                + ", year=" + year
                + ", mileage=" + mileage
                + ", colour='" + colour + '\''
                + ", carBody=" + carBody
                + ", engine=" + engine
                + ", transmission=" + transmission
                + '}';
    }
}