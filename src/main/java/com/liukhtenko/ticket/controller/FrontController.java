package com.liukhtenko.ticket.controller;

        import com.liukhtenko.ticket.command.*;
        import com.liukhtenko.ticket.pool.CustomConnectionPool;
        import com.liukhtenko.ticket.pool.StartWatcher;

        import javax.servlet.RequestDispatcher;
        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.io.IOException;

/**
 * The class that controls the operation of the application.
 * Makes a redirect or forward to the jsp page.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
@WebServlet(name = "FrontController", urlPatterns = {"/do"})
public class FrontController extends HttpServlet { // FIXME: 02.03.2020 

    @Override
    public void init() {
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
            String typeMethod = (String) req.getAttribute(FormParameterName.TYPE_METHOD);
            if (typeMethod.equals(FormParameterName.GET)) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(req, resp);
            } else {
                session.setAttribute(FormParameterName.REDIRECT_SECURE, FormParameterName.POST);
                String redirectPath = CommandHelper.findRedirectCommand(nameCommand);
                resp.sendRedirect(req.getContextPath() + redirectPath);
            }
        } else {
            page = PagePath.PAGE_ERROR;
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        StartWatcher.stop();
        CustomConnectionPool.INSTANCE.destroyPool();
    }
}
