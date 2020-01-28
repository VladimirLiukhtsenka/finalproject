package com.liukhtenko.ticket.controller;

import com.liukhtenko.ticket.command.Action;
import com.liukhtenko.ticket.command.FactoryCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FrontController", urlPatterns = {"/do"})
public class FrontController extends HttpServlet {
    static Logger logger = LogManager.getLogger(FrontController.class);

    private RequestDispatcher dispatcher(Action action) {
        return getServletContext().getRequestDispatcher(action.getJsp()); // FIXME: 28.01.2020 
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameCommand = req.getParameter("command").toUpperCase();
        Action action = FactoryCommand.defineFrom(nameCommand);
        action.execute(req);
        dispatcher(action).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameCommand = req.getParameter("command").toUpperCase();
        Action action = FactoryCommand.defineFrom(nameCommand);
        String nextAction = action.execute(req);
        if (nextAction != null) {
            resp.sendRedirect("do?command=" + nextAction);
        } else {
            dispatcher(action).forward(req, resp);
        }
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ServletContext servletContext = getServletContext();
//        RequestDispatcher disp = servletContext.getRequestDispatcher("/index.jsp");
//        disp.forward(req,resp);

    }
}