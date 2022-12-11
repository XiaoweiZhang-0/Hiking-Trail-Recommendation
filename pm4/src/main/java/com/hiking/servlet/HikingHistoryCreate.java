package com.hiking.servlet;

import com.hiking.dal.HikingHistoriesDao;
import com.hiking.dal.HikingTrailsDao;
import com.hiking.dal.UsersDao;
import com.hiking.model.HikingHistories;
import com.hiking.model.HikingTrails;
import com.hiking.model.Users;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hikinghistorycreate")
public class HikingHistoryCreate extends HttpServlet {

  protected HikingHistoriesDao hikingHistoriesDao;
  protected UsersDao usersDao;
  protected HikingTrailsDao hikingTrailsDao;

  @Override
  public void init() throws ServletException {
//    usersDao = UsersDao.getInstance();
    hikingHistoriesDao = HikingHistoriesDao.getInstance();
    usersDao = UsersDao.getInstance();
//    hikingHistoriesDao = HikingHistoriesDao.getInstance();
    hikingTrailsDao = HikingTrailsDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    String userId = req.getParameter("userId");
    //Just render the JSP.
    req.getRequestDispatcher("/HikingHistoryCreate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
      String userID = req.getParameter("userId");
      String hikingTrailId = req.getParameter("trailId");
      String travelTime = req.getParameter("travelTime");

//    Users.Gender genderObj = null;
      Users user = null;
      if(userID == null){
        throw new IOException("invalid userID");
      }
      else{
        try {
          user = usersDao.getUserById(Integer.parseInt(userID));
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }
      HikingTrails trail = null;
      if(hikingTrailId== null){
        throw new IOException("invalid trail id");
      }
      else{
        try{
          trail = hikingTrailsDao.getHikingTrailById(Integer.parseInt(hikingTrailId));
        }
        catch (SQLException e){
          throw new RuntimeException(e);
        }
      }
      if(travelTime==null){
        throw new IOException("invalid travel time");
      }
      if(!travelTime.matches("[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")){
      throw new IOException("Wrong new travel date formatting");
      }
      Date time = Date.valueOf(travelTime);
      HikingHistories hist = new HikingHistories(user, trail, time);
      try {
        hikingHistoriesDao.create(hist);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
      resp.sendRedirect("showhikinghistories?userId="+userID);
  }
}