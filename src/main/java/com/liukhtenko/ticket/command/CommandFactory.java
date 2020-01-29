package com.liukhtenko.ticket.command;


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
        Action command;
    }

    public static Action defineFrom(String cmd) {
        Command command;
        try {
            command = Command.valueOf(cmd);
        } catch (IllegalArgumentException e) {
            return Command.ERROR.command; // FIXME: 29.01.2020
        }
        switch (command) {
            case LOGIN:
                return Command.LOGIN.command;
            case LOGOUT:
                return Command.LOGOUT.command;
            case SIGNUP:
                return Command.SIGNUP.command;
            case PROFILE:
                return Command.PROFILE.command;
            case VIEWEVENT:
                return Command.VIEWEVENT.command;
            case CREATEEVENT:
                return Command.CREATEEVENT.command;
            default:
                return Command.ERROR.command;
        }
    }
}
