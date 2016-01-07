package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.NoticeBoardDAO;
import com.model.NoticeBoardDTO;

public class UpdateCommand implements CommandController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		NoticeBoardDTO nb= new NoticeBoardDTO();
		nb.setNum(Integer.parseInt(request.getParameter("num")));
		nb.setTitle(request.getParameter("title"));
		nb.setAuthor(request.getParameter("author"));
		nb.setRegion_Total(request.getParameter("region1")+request.getParameter("region2"));
		Date start_day;
		Date end_day;
		try {
			String startDateStr = request.getParameter("start_Day");
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
		nb.setContent(request.getParameter("content"));
		NoticeBoardDAO dao = new NoticeBoardDAO();
		dao.update(nb);
	}

}
