package com.liukhtenko.ticket.command.admincommand;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.entity.TicketOffice;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.TicketOfficeService;
import com.liukhtenko.ticket.validator.FormRegexValidator;
import com.liukhtenko.ticket.validator.FormValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The class that create ticket office.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class CreateTicketOfficeCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    /**
     * @param request from browser
     * @return String page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = PagePath.PAGE_CREATE_TICKET_OFFICE;
        request.setAttribute(FormParameterName.TYPE_METHOD, FormParameterName.GET);
        if (!FormValidator.isPost(request)
                || (FormValidator.isPost(request) && request.getParameter(FormParameterName.FORM_PARAM_ADD_TICKET_OFFICE) != null)) {
            return page;
        } else {
            TicketOfficeService service = new TicketOfficeService();
            TicketOffice ticketOffice = new TicketOffice();
            try {
                String address = request.getParameter(ColumnName.ADDRESS);
                if (FormValidator.isValidString(address, FormRegexValidator.EVENT_ADDRESS)) {
                    ticketOffice.setAddress(address);
                }
                String operatingMode = request.getParameter(ColumnName.OPERATING_MODE);
                if (FormValidator.isValidString(operatingMode, FormRegexValidator.OPERATING_MODE)) {
                    ticketOffice.setOperatingMode(operatingMode);
                }
                String phone = request.getParameter(ColumnName.PHONE);
                if (FormValidator.isValidString(phone, FormRegexValidator.PHONE)) {
                    ticketOffice.setPhone(phone);
                }
                if (service.createTicketOffice(ticketOffice)) {
                    request.setAttribute(FormParameterName.TYPE_METHOD, FormParameterName.POST);
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error in CreateEventCommand", e);
                request.setAttribute(FormParameterName.FORM_PARAM_WRONG_INPUT_DATA, FormParameterName.FORM_PARAM_WRONG_INPUT_DATA);
            }
        }
        return page;
    }
}

