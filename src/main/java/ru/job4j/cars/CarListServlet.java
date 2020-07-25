package ru.job4j.cars;

import com.google.gson.Gson;
import ru.job4j.cars.models.Item;
import ru.job4j.cars.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * CarListServlet.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class CarListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ind = Integer.parseInt(req.getParameter("filter"));
        String br = req.getParameter("brand");
        HttpSession session = req.getSession();
        Gson gson = new Gson();
        String login = (String) session.getAttribute("login");
        ListData ld = new ListData();
        ld.setUser(ValidateUser.getInstance().getUserByLogin(login));
        ld.setItems(ValidateItem.getInstance().getItemsWithFilter(ind, br));
        String data = gson.toJson(ld);
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        writer.append(data);
        writer.flush();
    }

    /**
     * ListData.
     */
    public class ListData {

        private List<Item> items = null;
        private User user = null;
        private Components components = new Components();

        public List<Item> getItems() {
            return items;
        }

        public User getUser() {
            return user;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Components getComponents() {
            return components;
        }

        public void setComponents(Components components) {
            this.components = components;
        }
    }
}