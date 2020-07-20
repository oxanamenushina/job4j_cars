package ru.job4j.cars;

import java.util.List;

/**
 * Validate.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Validate<T> {

    /**
     * The method checks the input for the add element operation.
     * @param elem an element to add.
     * @return true - the element added to the storage.
     */
    boolean add(T elem);

    /**
     * The method checks the input
     * for the update element operation.
     * @param elem an element to update.
     * @return true - the element updated.
     */
    boolean update(T elem);

    /**
     * The method checks the input
     * for the delete element operation.
     * @param id an element to delete.
     * @return true - the element deleted.
     */
    boolean delete(int id);

    /**
     * The method returns an element with the specified id.
     * @param id element ID.
     * @return an element with the specified id.
     */
    T getElem(int id);

    /**
     * The method returns an element with the specified name.
     * @param name name.
     * @return an element with the specified name.
     */
    List<T> getByName(String name);

    /**
     * The method returns a list of elements.
     * @return a list of elements.
     */
    List<T> getList();
}