package com.hiking.servlet;

import com.hiking.dal.HikingHistoriesDao;
import com.hiking.model.HikingHistories;
import com.hiking.model.Users;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hikinghistoryupdate")
public class HikingHistoryUpdate extends HttpServlet {
  protected HikingHistoriesDao hikingHistoriesDao;

  @Override
  public void init() throws ServletException {
    this.hikingHistoriesDao = HikingHistoriesDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    System.out.println(req.getParameter("userId"));
//    String userId = req.getParameter("userId");
//    req.setAttribute("userId", req.getParameter("userId"));
//    String history = req.getParameter("hikingHistoryId");

    req.getRequestDispatcher("/HikingHistoryUpdate.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    int hikingHistoryId = 0;
    hikingHistoryId = Integer.parseInt(req.getParameter("hikingHistoryId"));
    int trailId = Integer.parseInt(req.getParameter("trailId"));
    String newTravelDate = req.getParameter("date");
    if(!newTravelDate.matches("[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")){
      throw new IOException("Wrong new travel date formatting");
    }
    Date travelDate = Date.valueOf(req.getParameter("date"));

    try {
      HikingHistories hist = hikingHistoriesDao.getHikingHistoryById(hikingHistoryId);
      hikingHistoriesDao.updateHikingHistory(hist, trailId, travelDate);
    }
    catch(Throwable e){

    }
    String userId = req.getParameter("userId");
    req.setAttribute("userId", userId);
    resp.sendRedirect("showhikinghistories?userId="+userId);
//    req.getRequestDispatcher("showhikinghistories").forward(req, resp);
  }

//  public
}
