package com.liukhtenko.ticket.tag;

import com.liukhtenko.ticket.command.FormParameterName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Custom tag class.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class InfoTimeTag extends TagSupport {
    static Logger logger = LogManager.getLogger();

    @Override
    public int doStartTag() throws JspException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FormParameterName.SIMPLE_DATE_FORMAT);
        try {
            JspWriter out = pageContext.getOut();
            out.write(dateFormat.format(new Date()));
        } catch (IOException e) {
            logger.log(Level.ERROR, "Incorrect work InfoTimeTag", e);
            throw new JspException(e.getCause());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
