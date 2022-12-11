package com.hiking.servlet;

import com.hiking.dal.HikingHistoriesDao;
import com.hiking.model.Users;
import java.io.IOException;
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
    int hikingHistoryId = 0;
    try{
      hikingHistoryId = Integer.parseInt(req.getParameter("hikingHistoryId"));
    }
    catch(Throwable e){

    }

    req.getRequestDispatcher("/HikingHistoryUpdate.jsp").forward(req, resp);
  }

//  public
}
