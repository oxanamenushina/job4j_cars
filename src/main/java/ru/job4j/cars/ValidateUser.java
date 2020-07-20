package ru.job4j.cars;

import ru.job4j.cars.models.User;

import java.util.List;

/**
 * ValidateUser.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ValidateUser extends BaseValidate<User> implements Permission {

    /**
     * An instance of ValidateUser.
     */
    private static final ValidateUser VALIDATE = new ValidateUser();

    private ValidateUser() {
        super("User");
    }

    public static ValidateUser getInstance() {
        return VALIDATE;
    }

    @Override
    public boolean add(User user) {
        List<User> users = getList();
        boolean result = user != null && users.stream()
                .noneMatch(u -> user.getLogin().equals(u.getLogin()) || user.getEmail().equals(u.getEmail()));
        if (result) {
            super.add(user);
        }
        return result;
    }

    @Override
    public boolean update(User user) {
        boolean result = user != null && getElem(user.getId()) != null;
        if (result) {
            super.update(user);
        }
        return result;
    }

    @Override
    public boolean isPermit(String login, String password) {
        return getList().stream().anyMatch(u -> login.equals(u.getLogin()) && password.equals(u.getPassword()));
    }

    @Override
    public User getUserByLogin(String login) {
        return login != null ? ValidateUser.getInstance().getList().stream().filter(u -> login.equals(u.getLogin()))
                .findFirst().get() : null;
    }
}