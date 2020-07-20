package ru.job4j.cars;

import java.util.List;

/**
 * Store.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Store {

    /**
     * The method adds an element to the storage.
     * @param elem an element to add.
     */
    <T> void add(T elem);

    /**
     * The method updates an element.
     * @param elem an element to update.
     */
    <T> void update(T elem);

    /**
     * The method deletes an element.
     * @param elem an element to delete.
     */
    <T> void delete(T elem);

    /**
     * The method returns an element with the specified id.
     * @param id element ID.
     * @param className
     * @return an element with the specified id.
     */
    <T> T getElem(int id, String className);

    /**
     * The method returns a list of elements.
     * @param className
     * @return a list of elements.
     */
    <T> List<T> getList(String className);

    /**
     * The method returns a list of elements with the specified name.
     * @param name element name.
     * @param className
     * @param column
     * @return a list of elements with the specified name.
     */
    <T> List<T> getElementsByName(String name, String className, String column);
}