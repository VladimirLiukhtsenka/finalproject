package com.liukhtenko.ticket.customtimetag;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
public class InfoTimeTag extends TagSupport { // FIXME: 30.01.2020 upgrade
    @Override
    public int doStartTag() throws JspException {
        GregorianCalendar gc = new GregorianCalendar(new Locale("ru","RU")); // FIXME: 04.02.2020 
        String time = "<hr/><h2 align=\"center\">Time :  " + gc.getTime() + " </h2><hr/>";
       // String locale = "Locale : <b> " + Locale.getDefault() + " </b><hr/> ";
        try {
            JspWriter out = pageContext.getOut();
            out.write(time /*+ locale*/);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
