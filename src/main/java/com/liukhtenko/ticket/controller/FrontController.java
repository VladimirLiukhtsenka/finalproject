package com.liukhtenko.ticket.controller;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.CommandProvider;
import com.liukhtenko.ticket.command.PageMessage;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.pool.CustomConnectionPool;
import com.liukhtenko.ticket.pool.StartWatcher;
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

@WebServlet(name = "FrontController", urlPatterns = {"/do","/pages/do"}) // FIXME: 19.02.2020  /pages/do при redirect добавляет
public class FrontController extends HttpServlet {
    static Logger logger = LogManager.getLogger();

    @Override
    public void init() throws ServletException {
        StartWatcher.start();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqParameter = req.getParameter("command");
        String nameCommand = reqParameter.toUpperCase();
        Command command = CommandProvider.defineFrom(nameCommand);
        String page = command.execute(req);
        if (page != null) {
            if (FormValidator.isPost(req) && req.getAttribute(PageMessage.MESSAGE_ERROR) == null
                    && req.getAttribute(PageMessage.MESSAGE) == null) {
                resp.sendRedirect(req.getContextPath().replace("/pages","") + page);
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(req, resp);
            }
        } else {
            page = PagePath.PAGE_ERROR;
            resp.sendRedirect(req.getContextPath() + page);
        }
    }

    @Override
    public void destroy() {
        CustomConnectionPool.INSTANCE.destroyPool();
    }
}
