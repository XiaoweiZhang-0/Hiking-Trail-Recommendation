package com.hiking.servlet;

import com.hiking.dal.HikingHistoriesDao;
import com.hiking.dal.HikingTrailsDao;
import com.hiking.model.HikingHistories;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/showhikinghistories")
public class ShowHikingHistories extends HttpServlet {
  protected HikingHistoriesDao hikingHistoriesDao;

  @Override
  public void init() throws ServletException{
    hikingHistoriesDao = HikingHistoriesDao.getInstance();
  }
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
//    Map<String, String> messages = new HashMap<String, String>();
//    req.setAttribute("messages", messages);
//
//    // Retrieve user and validate.
//    String userId = req.getParameter("userId");
    List<HikingHistories> hikingHistories = new ArrayList<>();
    String userId = req.getParameter("userId");
    int userIdInt = 0;
    if(userId != null){
      userIdInt = Integer.parseInt(userId);
    }
    try {
      hikingHistories = hikingHistoriesDao.getHikingHistoriesByUserId(userIdInt);
    }catch (SQLException throwables){

    }
//    System.out.println(hikingHistories);
    req.setAttribute("hikingHistories", hikingHistories);
    req.getRequestDispatcher("/ShowHikingHistories.jsp").forward(req, resp);
//      this.doPost(req, resp);
//
  }

//  @Override
//  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    List<HikingHistories> hikingHistories = new ArrayList<>();
//    String userId = request.getParameter("userId");
//    int userIdInt = 0;
//    if(userId != null){
//      userIdInt = Integer.parseInt(userId);
//    }
//    try {
//      hikingHistories = hikingHistoriesDao.getHikingHistoriesByUserId(userIdInt);
//    }catch (SQLException throwables){
//
//    }
//    request.setAttribute("hikingHistories", hikingHistories);
//    request.getRequestDispatcher("/ShowHikingHistories.jsp").forward(request, response);
//  }
}
