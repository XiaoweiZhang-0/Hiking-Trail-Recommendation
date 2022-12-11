package com.hiking.servlet;

import com.hiking.dal.HikingHistoriesDao;
import com.hiking.model.HikingHistories;
import com.hiking.model.Users;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hikinghistorydelete")
public class HikingHistoryDelete extends HttpServlet {
  protected HikingHistoriesDao hikingHistoriesDao;

  @Override
  public void init() throws ServletException {
    this.hikingHistoriesDao = HikingHistoriesDao.getInstance();
  }

  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    //get hiking history id
    String histId = req.getParameter("hikingHistoryId");
    int id = Integer.parseInt(histId);
    try {
      HikingHistories history = hikingHistoriesDao.getHikingHistoryById(id);
      hikingHistoriesDao.delete(history);
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
    String userId = req.getParameter("userId");
    resp.sendRedirect("showhikinghistories?userId="+userId);
  }
}
