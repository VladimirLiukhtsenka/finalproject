package com.liukhtenko.ticket.customtimetag;
import com.liukhtenko.ticket.command.FormParameterName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
public class InfoTimeTag extends TagSupport {
    static Logger logger = LogManager.getLogger();
    @Override
    public int doStartTag() throws JspException {
        SimpleDateFormat frm = new SimpleDateFormat(FormParameterName.SIMPLE_DATE_FORMAT);
        try {
            JspWriter out = pageContext.getOut();
            out.write(frm.format(new Date()));
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
