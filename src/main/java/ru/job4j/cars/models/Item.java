package ru.job4j.cars.models;

import javax.persistence.*;
import java.util.Objects;

/**
 * Item.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
@Entity
@Table (name = "items")
public class Item {

    @Id
    @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "item_name")
    private String name;

    @ManyToOne (optional = false, cascade = CascadeType.ALL)
    @JoinColumn (name = "user_id", foreignKey = @ForeignKey(name = "USER_ID_FK"))
    private User owner;

    @OneToOne (optional = false, cascade = CascadeType.ALL)
    @JoinColumn (name = "car_id", foreignKey = @ForeignKey(name = "CAR_ID_FK"))
    private Car car;

    @Column (name = "item_photo")
    private String photo;

    @Column (name = "item_for_sale")
    private boolean forSale;

    @Column (name = "item_price")
    private int price;

    @Column (name = "item_desc", length = 10000)
    private String desc;

    @Column (name = "item_created")
    private String created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isForSale() {
        return forSale;
    }

    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return id == item.id
                && forSale == item.forSale
                && price == item.price
                && Objects.equals(name, item.name)
                && Objects.equals(owner, item.owner)
                && Objects.equals(car, item.car)
                && Objects.equals(photo, item.photo)
                && Objects.equals(desc, item.desc)
                && Objects.equals(created, item.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, car, photo, forSale, price, desc, created);
    }

    @Override
    public String toString() {
        return "Item{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", owner=" + owner
                + ", car=" + car
                + ", photo='" + photo + '\''
                + ", forSale=" + forSale
                + ", price=" + price
                + ", desc='" + desc + '\''
                + ", created='" + created + '\''
                + '}';
    }
}