package ru.job4j.cars;

import java.util.List;

/**
 * DBItemFilter.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface DBItemFilter {

    /**
     * The method returns a list of elements according to the filter.
     * @param ind filter index.
     * @param value
     * @return a list of elements with the specified name.
     */
    <K> List<K> getElementsWithFilter(int ind, String value);
}