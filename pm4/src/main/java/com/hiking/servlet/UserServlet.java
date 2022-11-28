package com.hiking.servlet;

import com.hiking.dal.UsersDao;
import com.hiking.model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.graalvm.compiler.options.OptionType.User;

/**
 * @author Yicheng Chen
 */

@WebServlet(value = "/user")
public class UserServlet extends HttpServlet {
    private UsersDao usersDao;

    @Override
    public void init() {
        System.out.println("init user get servlet");
        usersDao = UsersDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String userId = httpServletRequest.getParameter("userId");
        int userIdInt = 0;
        if (userId != null) {
            userIdInt = Integer.parseInt(userId);
        }
        Users user = null;

        try {
            user = usersDao.getUserById(userIdInt);
        } catch (SQLException throwables) {
            // todo
        }

        httpServletRequest.setAttribute("user", user);
        httpServletRequest.getRequestDispatcher("/index.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String firstName = httpServletRequest.getParameter("firstName");
        String lastName = httpServletRequest.getParameter("lastName");
        String password = httpServletRequest.getParameter("password");
        String gender = httpServletRequest.getParameter("gender");
        String age = httpServletRequest.getParameter("age");
        String weight = httpServletRequest.getParameter("weight");
        String height = httpServletRequest.getParameter("height");
        String hikingLevel = httpServletRequest.getParameter("hikingLevel");
        String address = httpServletRequest.getParameter("address");
        String phoneNumber = httpServletRequest.getParameter("phoneNumber");
        String email = httpServletRequest.getParameter("email");

        Users.Gender genderObj = null;
        if (gender != null) {
            genderObj = Users.Gender.valueOf(gender);
        }
        int ageInt = 0;
        if (age != null) {
            ageInt = Integer.parseInt(age);
        }
        int weightInt = 0;
        if (weight != null) {
            weightInt = Integer.parseInt(weight);
        }
        int heightInt = 0;
        if (height != null) {
            heightInt = Integer.parseInt(height);
        }
        Users.HikingLevel hikingLevelObj = null;
        if (hikingLevel != null) {
            hikingLevelObj = Users.HikingLevel.valueOf(hikingLevel);
        }

        Users user = new Users(firstName, lastName, password, genderObj, ageInt, weightInt, heightInt, hikingLevelObj, address, phoneNumber, email);
        try {
            user = usersDao.create(user);
            httpServletRequest.setAttribute("user", user);
            httpServletRequest.getRequestDispatcher("/index.jsp").forward(httpServletRequest, httpServletResponse);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String userId = httpServletRequest.getParameter("userId");
        String lastName = httpServletRequest.getParameter("lastName");
        int userIdInt = 0;
        if (userId != null) {
            userIdInt = Integer.parseInt(userId);
        }

        Users user = new Users(userIdInt);
        user.setLastName(lastName);
        try {
            usersDao.updateLastName(user, lastName);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String userId = httpServletRequest.getParameter("userId");
        int userIdInt = 0;
        if (userId != null) {
            userIdInt = Integer.parseInt(userId);
        }
        Users user = new Users(userIdInt);
        try {
            usersDao.delete(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
