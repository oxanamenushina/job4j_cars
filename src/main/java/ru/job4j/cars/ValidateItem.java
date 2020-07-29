package ru.job4j.cars;

import ru.job4j.cars.models.Item;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ValidateItem.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ValidateItem extends BaseValidate<Item> implements CarFilter {

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

    @Override
    public boolean delete(int id) {
        String fileName = getElem(id).getPhoto();
        boolean result = super.delete(id);
        if (result && fileName != null && getList().stream().noneMatch(it -> fileName.equals(it.getPhoto()))) {
            new File("images" + File.separator + fileName).delete();
        }
        return result;
    }

    @Override
    public List<Item> getItemsWithFilter(int ind, String value) {
        String val = this.getValues(value).get(ind);
        return DBStore.getInstance().getElementsWithFilter(ind, val);
    }

    private Map<Integer, String> getValues(String value) {
        Map<Integer, String> values = new HashMap<>();
        values.put(0, "Item");
        values.put(1, new SimpleDateFormat("yyyy-MM-dd").format(new GregorianCalendar().getTime()));
        values.put(2, "");
        values.put(3, value);
        return values;
    }
}