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
        SIGNUP{
            {
                this.command = new SignUpCommand();
            }
        },
        PROFILE{
            {
                this.command = new ProfileCommand();
            }
        },
        CREATEEVENT{
            {
                this.command = new CreateEventCommand();
            }
        },
        VIEWEVENT{    // FIXME: 02.02.2020 DELETE!!! or in INDEX page
            {
                this.command = new ViewEventCommand();
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
        ERROR{
            {
                this.command = new ErrorCommand();
            }
        };
        com.liukhtenko.ticket.command.Command command;
    }

    public static com.liukhtenko.ticket.command.Command defineFrom(String cmd) {
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
                return CommandFactory.Command.LOGOUT.command;
            case SIGNUP:
                return CommandFactory.Command.SIGNUP.command;
            case PROFILE:
                return CommandFactory.Command.PROFILE.command;
            case VIEWEVENT:
                return CommandFactory.Command.VIEWEVENT.command;
            case CREATEEVENT:
                return CommandFactory.Command.CREATEEVENT.command;
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
            default:
                return CommandFactory.Command.ERROR.command;
        }
    }
}
