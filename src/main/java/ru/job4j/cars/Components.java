package ru.job4j.cars;

import ru.job4j.cars.models.*;

import java.util.List;

/**
 * Components.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Components {

    private List<Model> models = DBStore.getInstance().getList("Model");
    private List<Engine> engines = DBStore.getInstance().getList("Engine");
    private List<Transmission> transmissions = DBStore.getInstance().getList("Transmission");
    private List<CarBody> carBodies = DBStore.getInstance().getList("CarBody");
    private List<Brand> brands = DBStore.getInstance().getList("Brand");
    private List<Category> categories = DBStore.getInstance().getList("Category");

    public List<Model> getModels() {
        return models;
    }

    public List<Engine> getEngines() {
        return engines;
    }

    public List<Transmission> getTransmissions() {
        return transmissions;
    }

    public List<CarBody> getCarBodies() {
        return carBodies;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public void setEngines(List<Engine> engines) {
        this.engines = engines;
    }

    public void setTransmissions(List<Transmission> transmissions) {
        this.transmissions = transmissions;
    }

    public void setCarBodies(List<CarBody> carBodies) {
        this.carBodies = carBodies;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}