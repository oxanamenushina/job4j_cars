package ru.job4j.cars;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * SigninController.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class SigninController extends HttpServlet {

    /**
     * An instance of ValidateUser class.
     */
    private final Permission logic = ValidateUser.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/auth.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataReader reader = new RequestReader();
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() { }.getType();
        Map<String, String> map = gson.fromJson(reader.read(req), type);
        String login = map.get("login");
        String password = map.get("password");
        boolean result = this.logic.isPermit(login, password);
        if (result) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
        }
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append(result ? "success" : "failure");
        writer.flush();
    }
}