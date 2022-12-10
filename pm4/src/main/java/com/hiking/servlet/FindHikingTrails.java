package com.hiking.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiking.dal.HikingTrailsDao;
import com.hiking.model.HikingTrails;


@WebServlet("/findhikingtrails")
public class FindHikingTrails extends HttpServlet {

	protected HikingTrailsDao hikingTrailsDao;
	
	@Override
	public void init() throws ServletException {
		hikingTrailsDao = HikingTrailsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<HikingTrails> hikingTrails = new ArrayList<HikingTrails>();
		String trailId = req.getParameter("trailId");
		int trailIdInt = 0;
		if (trailId != null) {
			trailIdInt = Integer.parseInt(trailId);
		}
		HikingTrails hikingTrail = null;
		
		try {
			hikingTrail = hikingTrailsDao.getHikingTrailById(trailIdInt);
			hikingTrails.add(hikingTrail);
		} catch (SQLException throwables) {
		      // todo
	    }
		
        req.setAttribute("hikingTrails", hikingTrails);
        req.getRequestDispatcher("/FindHikingTrails.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<HikingTrails> hikingTrails = new ArrayList<HikingTrails>();
		String trailId = req.getParameter("trailId");
		int trailIdInt = 0;
		if (trailId != null) {
			trailIdInt = Integer.parseInt(trailId);
		}
		HikingTrails hikingTrail = null;
		
		try {
			hikingTrail = hikingTrailsDao.getHikingTrailById(trailIdInt);
			hikingTrails.add(hikingTrail);
		} catch (SQLException throwables) {
		      // todo
	    }
		
        req.setAttribute("hikingTrails", hikingTrails);
        req.getRequestDispatcher("/FindHikingTrails.jsp").forward(req, resp);
		
	}
}
