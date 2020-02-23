package com.liukhtenko.ticket.controller;

import com.liukhtenko.ticket.command.*;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "FrontController", urlPatterns = {"/do"})
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
        String reqParameter = req.getParameter(FormParameterName.FORM_PARAM_COMMAND);
        String nameCommand = reqParameter.toUpperCase();
        Command command = CommandProvider.defineFrom(nameCommand);
        String page = command.execute(req);
        if (page != null) {
            HttpSession session = req.getSession();
//            String typeMethod = (String) session.getAttribute(FormParameterName.TYPE_METHOD);
            String typeMethod = (String) req.getAttribute(FormParameterName.TYPE_METHOD);
            if (typeMethod.equals(FormParameterName.GET)) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(req, resp);
            }else {
                session.setAttribute(FormParameterName.REDIRECT_SECURE, FormParameterName.POST);
                String redirectPath = CommandHelper.findRedirectCommand(nameCommand);
//                session.setAttribute("redirectPath",redirectPath);
                resp.sendRedirect(req.getContextPath() + redirectPath);
               // resp.sendRedirect(req.getContextPath() + page);
            }

//            if (FormValidator.isPost(req) && req.getAttribute(PageMessage.MESSAGE_ERROR) == null // FIXME: 22.02.2020
//                    && req.getAttribute(PageMessage.MESSAGE) == null) {
//                HttpSession session = req.getSession();
//                session.setAttribute(FormParameterName.REDIRECT_SECURE, "true"); // FIXME: 23.02.2020
//                resp.sendRedirect(req.getContextPath() + page);
//            } else {
//               RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
//
//                dispatcher.forward(req, resp);
//            }
        } else {
            HttpSession session = req.getSession();
            page = PagePath.PAGE_ERROR; // FIXME: 23.02.2020 
            session = req.getSession();
            session.setAttribute(FormParameterName.REDIRECT_SECURE, "true");
            resp.sendRedirect(req.getContextPath() + page);
        }
    }

    @Override
    public void destroy() {
        CustomConnectionPool.INSTANCE.destroyPool();
    }
}
