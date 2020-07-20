package ru.job4j.cars;

import ru.job4j.cars.models.User;

/**
 * Permission.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Permission {

    /**
     * The method checks if an account exists
     * with the given login and password.
     * @param login login.
     * @param password password.
     * @return true - authorization was successful.
     */
    boolean isPermit(String login, String password);

    /**
     * The method returns the user
     * with the specified login.
     * @param login login.
     * @return user.
     */
    User getUserByLogin(String login);
}
