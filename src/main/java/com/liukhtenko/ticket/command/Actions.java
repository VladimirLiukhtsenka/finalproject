package com.liukhtenko.ticket.command;

import javax.servlet.http.HttpServletRequest;

public enum Actions { // FIXME: 24.01.2020 rename 
    LOGIN {{
        this.command = new CmdLogin();
    }},
    LOGOUT {{
        this.command = new CmdLogOut();
    }},
    SIGNUP {{
        this.command = new CmdSignUp();
    }},
    ERROR {{
        this.command = new CmdError();
    }};


    public Action command;

   public static Action defineFrom(HttpServletRequest request){  // FIXME: 26.01.2020 можно фабрикой
        String nameCommand = request.getParameter("command").toUpperCase();
        try {
            return Actions.valueOf(nameCommand).command;
        } catch (IllegalArgumentException e) {
            return Actions.ERROR.command;
        }
    }

}
