package ru.job4j.cars;

import ru.job4j.cars.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * AddUserServlet.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class AddUserServlet extends HttpServlet {

    /**
     * An instance of ValidateUser.
     */
    private final Validate<User> logic = ValidateUser.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/reguser.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        DataReader reader = new RequestReader();
//        Gson gson = new Gson();
//        Type type = new TypeToken<Map<String, String>>() { }.getType();
//        Map<String, String> map = gson.fromJson(reader.read(req), type);
//        String id = map.get("id");
//        User user = new User();
//        user.setId(id != null ? Integer.parseInt(id) : 0);
//        user.setName(map.get("name"));
//        user.setLogin(map.get("login"));
//        user.setPassword(map.get("password"));
//        user.setEmail(map.get("email"));
//        user.setPhone(map.get("phone"));
        boolean result = this.logic.add(new UserData().getUser(req));
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append(result ? "success" : "failure");
        writer.flush();
    }
}