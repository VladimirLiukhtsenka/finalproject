package com.liukhtenko.ticket.command;
import com.liukhtenko.ticket.command.impl.*;

public class CommandFactory {

    private enum Command {
        LOGIN{
            {
                this.command = new LoginCommand();
            }
        },
        LOGOUT{
            {
                this.command = new LogOutCommand();
            }
        },
        SIGN_UP{
            {
                this.command = new SignUpCommand();
            }
        },
        PROFILE{
            {
                this.command = new ProfileCommand();
            }
        },
        CREATE_EVENT{
            {
                this.command = new CreateEventCommand();
            }
        },
        VIEW_TICKET{
            {
                this.command = new ViewTicketCommand();
            }
        },
        VIEW_TICKET_OFFICE{
            {
                this.command = new ViewTicketOfficeCommand();
            }
        },
        VIEW_SPORT_EVENT{
            {
                this.command = new ViewSportEventCommand();
            }
        },
        VIEW_CONCERTS_EVENT{
            {
                this.command = new ViewConcertsEventCommand();
            }
        },
        VIEW_FESTIVALS_EVENT{
            {
                this.command = new ViewFestivalsEventCommand();
            }
        },
        VIEW_THEATER_EVENT{
            {
                this.command = new ViewTheaterEventCommand();
            }
        },
        VIEW_FOR_CHILDREN_EVENT{
            {
                this.command = new ViewForChildrenEventCommand();
            }
        },
        VIEW_FOR_MOVIE_EVENT{
            {
                this.command = new ViewMovieEventCommand();
            }
        },
        DELETE_EVENT{
            {
                this.command = new DeleteEventCommand();
            }
        },
        EDIT_EVENT{
            {
                this.command = new EditEventCommand();
            }
        },
        HOME{
            {
                this.command = new HomeCommand();
            }
        },
        ADMIN_PROFILE{
            {
                this.command = new AdminProfileCommand();
            }
        },
        CREATE_TICKET{
            {
                this.command = new CreateTicketCommand();
            }
        },
        EDIT_TICKET{
            {
                this.command = new EditTicketCommand();
            }
        },
        DELETE_TICKET{
            {
                this.command = new DeleteTicketCommand();
            }
        },
        ERROR{
            {
                this.command = new ErrorCommand();
            }
        };
        com.liukhtenko.ticket.command.Command command;
    }

    public static com.liukhtenko.ticket.command.Command defineFrom(String cmd) { // FIXME: 06.02.2020 
        Command command;
        try {
            command = CommandFactory.Command.valueOf(cmd);
        } catch (IllegalArgumentException e) {
            return CommandFactory.Command.ERROR.command; // FIXME: 29.01.2020
        }
        switch (command) {
            case LOGIN:
                return Command.LOGIN.command;
            case LOGOUT:
                return CommandFactory.Command.LOGOUT.command;  // FIXME: 04.02.2020 delete and jsp
            case SIGN_UP:
                return CommandFactory.Command.SIGN_UP.command;
            case PROFILE:
                return CommandFactory.Command.PROFILE.command;
            case VIEW_TICKET:
                return CommandFactory.Command.VIEW_TICKET.command; // FIXME: 04.02.2020 delete and jsp
            case CREATE_EVENT:
                return CommandFactory.Command.CREATE_EVENT.command;
            case  VIEW_CONCERTS_EVENT:
                return CommandFactory.Command. VIEW_CONCERTS_EVENT.command;
            case  VIEW_FESTIVALS_EVENT:
                return CommandFactory.Command. VIEW_FESTIVALS_EVENT.command;
            case  VIEW_SPORT_EVENT:
                return CommandFactory.Command. VIEW_SPORT_EVENT.command;
            case  VIEW_THEATER_EVENT:
                return CommandFactory.Command. VIEW_THEATER_EVENT.command;
            case  VIEW_FOR_CHILDREN_EVENT:
                return CommandFactory.Command. VIEW_FOR_CHILDREN_EVENT.command;
            case  VIEW_FOR_MOVIE_EVENT:
                return CommandFactory.Command. VIEW_FOR_MOVIE_EVENT.command;
            case  VIEW_TICKET_OFFICE:
                return CommandFactory.Command. VIEW_TICKET_OFFICE.command;
            case  DELETE_EVENT:
                return CommandFactory.Command. DELETE_EVENT.command;
            case  EDIT_EVENT:
                return CommandFactory.Command. EDIT_EVENT.command;
            case  HOME:
                return CommandFactory.Command. HOME.command;
            case  ADMIN_PROFILE:
                return CommandFactory.Command. ADMIN_PROFILE.command;
            case  CREATE_TICKET:
                return CommandFactory.Command. CREATE_TICKET.command;
            case  EDIT_TICKET:
                return CommandFactory.Command. EDIT_TICKET.command;
            case  DELETE_TICKET:
                return CommandFactory.Command. DELETE_TICKET.command;
            default:
                return CommandFactory.Command.ERROR.command;
        }
    }
}
