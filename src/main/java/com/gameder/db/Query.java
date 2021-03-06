package com.gameder.db;

import com.gameder.app.handlers.profiles.Profile;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.TreeSet;
import java.io.*;

@RestController
public class Query {
    private static TreeSet<Profile> tree = new TreeSet<Profile>();

    private static final String ENDPOINT = "/api/query";

    @CrossOrigin
    @RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
    public void get(HttpServletResponse response) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(tree);
            oos.close();
        } catch (IOException ex) {
            System.out.printf("'get' query failed: %s\n", ex.getLocalizedMessage());
        }
    }

    @CrossOrigin
    @RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
    public void post(HttpServletRequest request) {
        try {
            BufferedInputStream bis = new BufferedInputStream(request.getInputStream());
            ObjectInputStream ois = new ObjectInputStream(bis);
            Object obj = ois.readObject();
            ois.close();

            tree = (TreeSet) obj;
        } catch (Exception ex) {
            System.out.printf("'post' query failed: %s\n", ex.toString());
        }
    }
}
