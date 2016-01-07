package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.NoticeBoardDAO;
import com.model.NoticeBoardDTO;

public class InsertCommand implements CommandController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		NoticeBoardDTO nb = new NoticeBoardDTO();
		nb.setRegion_Total(request.getParameter("region1")+request.getParameter("region2"));
		nb.setTitle(request.getParameter("title"));
		nb.setContent(request.getParameter("content"));
		
		//surround below line with try catch block as below code throws checked exception
		Date start_day;
		Date end_day;
		try {
			String startDateStr = request.getParameter("start_Day");
			System.out.println();
			String endDateStr=request.getParameter("end_Day");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 start_day = sdf.parse(startDateStr);
			 end_day=sdf.parse(endDateStr);
			 nb.setStart_Day( new java.sql.Date(start_day.getTime ()));
			 nb.setEnd_Day( new java.sql.Date(end_day.getTime ()));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("먼가이상한거보오");
		}
		
		nb.setRec_People(Integer.parseInt(request.getParameter("rec_People")));
		System.out.println(request.getParameter("rec_People"));
		nb.setAuthor(request.getParameter("author"));
		HttpSession session=request.getSession();
		nb.setId((String)session.getAttribute("memId"));
		NoticeBoardDAO dao= new NoticeBoardDAO();
		dao.insert(nb);
		
	}

}
