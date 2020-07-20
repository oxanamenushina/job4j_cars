package ru.job4j.cars;

import ru.job4j.cars.models.Item;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * ValidateItem.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ValidateItem extends BaseValidate<Item> {

    /**
     * An instance of ValidateItem.
     */
    private static final ValidateItem VALIDATE = new ValidateItem();

    private ValidateItem() {
        super("Item");
        ValueFilling.getInstance().fillTables();
    }

    public static ValidateItem getInstance() {
        return VALIDATE;
    }

    @Override
    public boolean add(Item item) {
        boolean result = item != null;
        if (result) {
            item.setCreated(new SimpleDateFormat("yyyy-MM-dd").format(new GregorianCalendar().getTime()));
            super.add(item);
        }
        return result;
    }

    @Override
    public boolean update(Item item) {
        Item it = getElem(item.getId());
        boolean result = item != null && it != null;
        if (result) {
            if (item.getPhoto() == null || item.getPhoto().equals("")) {
                item.setPhoto(it.getPhoto());
            }
            super.update(item);
        }
        return result;
    }
}