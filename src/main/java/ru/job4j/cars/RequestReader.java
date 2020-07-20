package ru.job4j.cars;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * RequestReader.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class RequestReader implements DataReader {

    @Override
    public String read(HttpServletRequest req) throws IOException {
        BufferedReader json = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String str;
        while ((str = json.readLine()) != null) {
            sb.append(str);
        }
        return sb.toString();
    }
}