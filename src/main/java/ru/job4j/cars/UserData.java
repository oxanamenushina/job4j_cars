package ru.job4j.cars;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.job4j.cars.models.User;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * UserData.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class UserData {

    public User getUser(HttpServletRequest req) throws IOException {
        DataReader reader = new RequestReader();
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() { }.getType();
        Map<String, String> map = gson.fromJson(reader.read(req), type);
        String id = map.get("id");
        User user = new User();
        user.setId(id != null ? Integer.parseInt(id) : 0);
        user.setName(map.get("name"));
        user.setLogin(map.get("login"));
        user.setPassword(map.get("password"));
        user.setEmail(map.get("email"));
        user.setPhone(map.get("phone"));
        return user;
    }
}