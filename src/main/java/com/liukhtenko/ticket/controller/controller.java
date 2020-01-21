package com.liukhtenko.ticket.controller;

import com.liukhtenko.ticket.entity.TypeSeat;
import com.liukhtenko.ticket.service.SomeService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class controller extends HttpServlet {
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {
        SomeService someService = new SomeService();
        List<TypeSeat> list = someService.findAllTypeOfSeat();
        System.out.println(list);
        httpServletResponse.getWriter().print(list+"Hello from servlet Привет");
      // httpServletResponse.getWriter().print("Hello from servlet");
    }
}
