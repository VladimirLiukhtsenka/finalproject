package com.liukhtenko.ticket.command;

public enum Action {
    LOGIN {{
        this.command = new CommandLogin();
    }},
    LOGOUT {{
        this.command = new CommandLogOut();
    }},
    SIGN_UP {{
        this.command = new CommandSignUp();
    }},
    ERROR {{
        this.command = new CommandError();
    }};

    public String jsp = "/error.jsp";
    public ActionCommand command;

    public ActionCommand getActionCommand() {
        return command;
    }
}
