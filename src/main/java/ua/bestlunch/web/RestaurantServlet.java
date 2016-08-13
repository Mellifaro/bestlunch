package ua.bestlunch.web;


import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Виктор on 13.08.2016.
 */
public class RestaurantServlet extends HttpServlet{
    private static final Logger LOG = getLogger(RestaurantServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("forward to restaurantList.jsp");
        req.getRequestDispatcher("/restaurantList.jsp").forward(req, resp);
    }
}
