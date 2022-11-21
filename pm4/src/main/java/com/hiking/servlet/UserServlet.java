package com.hiking.servlet;

import com.hiking.model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yicheng Chen
 */

@WebServlet(value = "/user")
public class UserServlet extends HttpServlet {
//    private UsersDao usersDao;

    @Override
    public void init(){
        System.out.println("init user get servlet");
    }

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        httpServletRequest.setAttribute("messages", messages);

        List<Users> usersList = new ArrayList<>();

        Users users = new Users(1);
        usersList.add(users);

        httpServletRequest.setAttribute("users", usersList);
        httpServletRequest.getRequestDispatcher("/index.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

    }

    @Override
    public void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

    }

    @Override
    public void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

    }
}
