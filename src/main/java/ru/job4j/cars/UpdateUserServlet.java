package ru.job4j.cars;

import com.google.gson.Gson;
import ru.job4j.cars.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * UpdateUserServlet.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class UpdateUserServlet extends HttpServlet {

    /**
     * An instance of ValidateUser.
     */
    private final ValidateUser logic = ValidateUser.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Gson gson = new Gson();
        String login = (String) session.getAttribute("login");
        User user = ValidateUser.getInstance().getUserByLogin(login);
        String data = gson.toJson(user);
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        writer.append(data);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new UserData().getUser(req);
        boolean result = this.logic.update(user);
        if (result) {
            req.getSession().setAttribute("login", user.getLogin());
        }
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append(result ? "success" : "failure");
        writer.flush();
    }
}