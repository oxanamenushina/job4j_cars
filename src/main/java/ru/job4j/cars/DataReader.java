package ru.job4j.cars;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * DataReader.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface DataReader {

    /**
     * The method reads json data from the request.
     * @param req request.
     * @return json data.
     */
    String read(HttpServletRequest req) throws IOException;
}