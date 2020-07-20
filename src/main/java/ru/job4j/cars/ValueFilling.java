package ru.job4j.cars;

import ru.job4j.cars.models.*;

/**
 * ValueFilling.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ValueFilling {

    /**
     * An instance of ValueFilling.
     */
    private static final ValueFilling VF = new ValueFilling();

    /**
     * An instance of DBStore.
     */
    private final Store store = DBStore.getInstance();

    private ValueFilling() {
    }

    public static ValueFilling getInstance() {
        return VF;
    }

    public void fillTables() {
        if (this.isEmpty()) {
            this.fillEngines();
            this.fillTtansmissions();
            this.fillCategories();
            this.fillBrands();
            this.fillCarBodies();
            this.fillModels();
        }
    }

    private void fillTtansmissions() {
        this.addElem(new Transmission(), "Manual gearbox");
        this.addElem(new Transmission(), "Automatic gearbox");
        this.addElem(new Transmission(), "Robotic gearbox");
    }

    private void fillEngines() {
        this.addElem(new Engine(), "Petrol engine");
        this.addElem(new Engine(), "Diesel engine");
        this.addElem(new Engine(), "Gas engine");
        this.addElem(new Engine(), "Electric motor");
    }

    private void fillCategories() {
        this.addElem(new Category(), "Passenger car");
//        this.addElem(new Category(), "Truck");
    }

    private void fillBrands() {
        this.addElem(new Brand(), "BMW");
        this.addElem(new Brand(), "Volkswagen");
        this.addElem(new Brand(), "Toyota");
        this.addElem(new Brand(), "Opel");
    }

    private void fillCarBodies() {
        this.addElem(new CarBody(), "Sedan");
        this.addElem(new CarBody(), "Hatchback");
        this.addElem(new CarBody(), "Universal");
        this.addElem(new CarBody(), "Coupe");
        this.addElem(new CarBody(), "Crossover");
        this.addElem(new CarBody(), "SUV");
        this.addElem(new CarBody(), "Pickup");
        this.addElem(new CarBody(), "Minivan");
        this.addElem(new CarBody(), "Minibus");
        this.addElem(new CarBody(), "Cabriolet");
    }

    private void addElem(Component elem, String name) {
        elem.setName(name);
        this.store.add(elem);
    }

    private void fillModels() {
        Brand volk = (Brand) this.store.getElementsByName("Volkswagen", "Brand", "brand_name").get(0);
        Brand toyota = (Brand) this.store.getElementsByName("Toyota", "Brand", "brand_name").get(0);
        Brand bmw = (Brand) this.store.getElementsByName("BMW", "Brand", "brand_name").get(0);
        Brand opel = (Brand) this.store.getElementsByName("Opel", "Brand", "brand_name").get(0);
        Category leg = (Category) this.store.getElementsByName("Passenger car", "Category", "category_name").get(0);
        this.addModel("Golf", volk, leg);
        this.addModel("Passat", volk, leg);
        this.addModel("Beetle", volk, leg);
        this.addModel("Camry", toyota, leg);
        this.addModel("Land Cruiser", toyota, leg);
        this.addModel("RAV4", toyota, leg);
        this.addModel("X3", bmw, leg);
        this.addModel("X5", bmw, leg);
        this.addModel("M6", bmw, leg);
        this.addModel("Astra", opel, leg);
        this.addModel("Mokka", opel, leg);
        this.addModel("Insignia", opel, leg);
        this.addModel("Touareg", volk, leg);
    }

    private void addModel(String name, Brand brand, Category category) {
        Model model1 = new Model();
        model1.setName(name);
        model1.setBrand(brand);
        model1.setCategory(category);
        this.store.add(model1);
    }

    private boolean isEmpty() {
        return this.store.getList("Engine").isEmpty() && this.store.getList("Transmission").isEmpty()
                && this.store.getList("CarBody").isEmpty() && this.store.getList("Category").isEmpty()
                && this.store.getList("Brand").isEmpty() && this.store.getList("Model").isEmpty();
    }
}