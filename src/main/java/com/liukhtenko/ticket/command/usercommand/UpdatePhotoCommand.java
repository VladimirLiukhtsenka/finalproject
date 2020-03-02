package com.liukhtenko.ticket.command.usercommand;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.CommandHelper;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.UserService;
import com.liukhtenko.ticket.validator.FormRegexValidator;
import com.liukhtenko.ticket.validator.FormValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

public class UpdatePhotoCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        User user = CommandHelper.findUserInSession(request);
        String page = PagePath.PAGE_UPDATE_USER;
        InputStream inputStream;
        try {
            Part photo = request.getPart(ColumnName.PHOTO);
            String type = photo.getContentType();
            if (photo.getSize() > 0 && FormValidator.isValidString(type, FormRegexValidator.PHOTO)) {
                inputStream = photo.getInputStream();
                UserService userService = new UserService();
                userService.updatePhoto(inputStream, user.getId());
                User userUpdate = userService.findUserById(user.getId());
                HttpSession session = request.getSession();
                session.setAttribute(FormParameterName.FORM_PARAM_USER, userUpdate);
            } else {
                request.setAttribute(FormParameterName.FORM_PARAM_ERROR_PHOTO, "wrong data");
            }
        } catch (ServiceException | IOException | ServletException e) {
            logger.log(Level.INFO, "Incorrect data" + e.toString());
            request.setAttribute(FormParameterName.FORM_PARAM_MESSAGE_ERROR, "Incorrect data");
        }
        return page;
    }
}
