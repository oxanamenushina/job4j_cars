package ru.job4j.cars;

import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.cars.models.Item;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * RequestHandler.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class RequestHandler {

    /**
     * A list of FileItems.
     */
    private final List<FileItem> items;

    /**
     * User's login.
     */
    private final String login;

    public RequestHandler(HttpServletRequest req, ServletContext servletContext) throws FileUploadException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        this.items = upload.parseRequest(req);
        this.login = (String) req.getSession().getAttribute("login");
    }

    /**
     * The method sets parameters received
     * from the request to the item.
     * @return item.
     */
    public Item setParameters() {
        String fn = null;
        Item item = null;
        for (FileItem it : this.items) {
            String key = it.getFieldName();
            if ("file".equals(key)) {
                fn = it.getName().substring(it.getName().lastIndexOf(File.separator) + 1);
            } else {
                Gson gson = new Gson();
                item = gson.fromJson(it.getString(), Item.class);
            }
        }
        if (fn != null) {
            item.setPhoto(fn);
        }
        item.setOwner(ValidateUser.getInstance().getList()
                .stream().filter(u -> login.equals(u.getLogin())).findFirst().get());
        return item;
    }

    /**
     * The method writes the file received from the request.
     * @param fn a file name.
     */
    public void writeFile(String fn) {
        File folder = new File("images");
        if (!folder.exists()) {
            folder.mkdir();
        }
        String[] files = folder.list();
        if (fn != null && files != null && Arrays.stream(files).noneMatch(f -> f.equals(fn))) {
            File file = new File(folder + File.separator + fn);
            try (FileOutputStream out = new FileOutputStream(file)) {
                out.write(items.stream().filter(f -> !f.isFormField()).findFirst().get().getInputStream().readAllBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}