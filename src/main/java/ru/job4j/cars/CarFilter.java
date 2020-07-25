package ru.job4j.cars;

import ru.job4j.cars.models.Item;

import java.util.List;

/**
 * CarFilter.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface CarFilter {

    /**
     * The method returns a list of items according to the filter.
     * @param ind filter index.
     * @param value
     * @return a list of items with the specified name.
     */
    List<Item> getItemsWithFilter(int ind, String value);
}