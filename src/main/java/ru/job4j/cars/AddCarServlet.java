package ru.job4j.cars;

import com.google.gson.Gson;
import org.apache.commons.fileupload.FileUploadException;
import ru.job4j.cars.models.Item;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * AddCarServlet.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class AddCarServlet extends HttpServlet {

    /**
     * An instance of ValidateItem.
     */
    private final Validate<Item> logic = ValidateItem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        writer.append(new Gson().toJson(new Components()));
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletConfig().getServletContext();
        try {
            RequestHandler rh = new RequestHandler(req, servletContext);
            Item item = rh.setParameters();
            boolean result = this.logic.add(item);
            if (result && item.getPhoto() != null && !item.getPhoto().equals("")) {
                rh.writeFile(item.getPhoto());
            }
            resp.setContentType("text/html");
            PrintWriter writer = resp.getWriter();
            writer.append(result ? "success" : "failure");
            writer.flush();
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
}