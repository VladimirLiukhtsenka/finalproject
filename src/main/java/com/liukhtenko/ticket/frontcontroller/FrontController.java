package com.liukhtenko.ticket.frontcontroller;

import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FrontController extends HttpServlet {
    static Logger logger =  LogManager.getLogger(FrontController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
              UserService userService = new UserService();
        List<User> list = userService.findAllUsers();
        System.out.println(list);
        logger.log(Level.DEBUG, "I" +
                "mpossible to establish Auto" +
                "Commit to value false!!!!!!!!!");
        //ServletContext servletContext = getServletContext();
        //RequestDispatcher disp = servletContext.getRequestDispatcher("/index.jsp");
        //disp.forward(req,resp);

    }
}
