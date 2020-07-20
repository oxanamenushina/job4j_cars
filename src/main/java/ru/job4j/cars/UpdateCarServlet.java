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
 * UpdateCarServlet.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class UpdateCarServlet extends HttpServlet {

    /**
     * An instance of DBStore.
     */
    private final ValidateItem logic = ValidateItem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String str = req.getParameter("id");
        Item it = logic.getElem(Integer.parseInt(str));
        DataForUpdate dfu = new DataForUpdate();
        dfu.setItem(it);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.append(gson.toJson(dfu));
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletConfig().getServletContext();
        try {
            RequestHandler rh = new RequestHandler(req, servletContext);
            Item item = rh.setParameters();
            boolean result = this.logic.update(item);
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

    /**
     * DataForUpdate.
     */
    public class DataForUpdate {

        private Item item;
        private Components components = new Components();

        public Item getItem() {
            return item;
        }

        public Components getComponents() {
            return components;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public void setComponents(Components components) {
            this.components = components;
        }
    }
}