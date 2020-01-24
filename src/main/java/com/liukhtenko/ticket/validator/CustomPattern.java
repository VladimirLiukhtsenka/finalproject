package com.liukhtenko.ticket.validator;

public interface CustomPattern {
    String Login = "(a-zA-Z0-9)(8,)"; // FIXME: 24.01.2020
    String Email = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$"; // FIXME: 24.01.2020
    String Password = "(a-zA-Z0-9)(8,)"; // FIXME: 24.01.2020
}
