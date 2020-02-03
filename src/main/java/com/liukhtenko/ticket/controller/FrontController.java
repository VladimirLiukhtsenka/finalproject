package com.liukhtenko.ticket.controller;

import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.CommandFactory;
import com.liukhtenko.ticket.validator.FormValidator;
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
    static Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s = req.getParameter("command");
        String nameCommand = s.toUpperCase();
        Command command = CommandFactory.defineFrom(nameCommand);
        String page = command.execute(req);
        if (page != null) {
            if (FormValidator.isPost(req)) {
                resp.sendRedirect(req.getContextPath() + page);
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(req, resp);
            }
        } else {
            page = PagePath.PAGE_ERROR;
            resp.sendRedirect(req.getContextPath() + page);
        }
    }
}
