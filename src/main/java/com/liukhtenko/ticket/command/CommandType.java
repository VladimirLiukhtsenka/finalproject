package com.liukhtenko.ticket.command;

import com.liukhtenko.ticket.command.admincommand.*;
import com.liukhtenko.ticket.command.supportcommand.ChangeLocaleCommand;
import com.liukhtenko.ticket.command.supportcommand.ErrorCommand;
import com.liukhtenko.ticket.command.supportcommand.HomeCommand;
import com.liukhtenko.ticket.command.usercommand.*;
import com.liukhtenko.ticket.command.viewcommand.*;

public enum CommandType {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    SIGN_UP {
        {
            this.command = new SignUpCommand();
        }
    },
    PROFILE {
        {
            this.command = new ProfileCommand();
        }
    },
    CREATE_EVENT {
        {
            this.command = new CreateEventCommand();
        }
    },
    VIEW_TICKET {
        {
            this.command = new ViewTicketCommand();
        }
    },
    VIEW_TICKET_OFFICE {
        {
            this.command = new ViewTicketOfficeCommand();
        }
    },
    VIEW_SPORT_EVENT {
        {
            this.command = new ViewSportEventCommand();
        }
    },
    VIEW_CONCERTS_EVENT {
        {
            this.command = new ViewConcertsEventCommand();
        }
    },
    VIEW_FESTIVALS_EVENT {
        {
            this.command = new ViewFestivalsEventCommand();
        }
    },
    VIEW_THEATER_EVENT {
        {
            this.command = new ViewTheaterEventCommand();
        }
    },
    VIEW_FOR_CHILDREN_EVENT {
        {
            this.command = new ViewForChildrenEventCommand();
        }
    },
    VIEW_FOR_MOVIE_EVENT {
        {
            this.command = new ViewMovieEventCommand();
        }
    },
    DELETE_EVENT {
        {
            this.command = new DeleteEventCommand();
        }
    },
    EDIT_EVENT {
        {
            this.command = new EditEventCommand();
        }
    },
    HOME {
        {
            this.command = new HomeCommand();
        }
    },
    ADMIN_PROFILE {
        {
            this.command = new AdminProfileCommand();
        }
    },
    CREATE_TICKET {
        {
            this.command = new CreateTicketCommand();
        }
    },
    EDIT_TICKET {
        {
            this.command = new EditTicketCommand();
        }
    },
    DELETE_TICKET {
        {
            this.command = new DeleteTicketCommand();
        }
    },
    BUY_TICKET {
        {
            this.command = new BuyTicketCommand();
        }
    },
    UPDATE_USER {
        {
            this.command = new UpdateUserCommand();
        }
    },
    CHANGE_LOCALE {
        {
            this.command = new ChangeLocaleCommand();
        }
    },
    ERROR {
        {
            this.command = new ErrorCommand();
        }
    };
    public Command command;

    public Command getCommand() {
        return command;
    }
}
