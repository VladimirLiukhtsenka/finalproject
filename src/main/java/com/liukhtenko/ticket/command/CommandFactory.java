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
        VIEWEVENT{
            {
                this.command = new ViewEventCommand();
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
            default:
                return CommandFactory.Command.ERROR.command;
        }
    }
}
