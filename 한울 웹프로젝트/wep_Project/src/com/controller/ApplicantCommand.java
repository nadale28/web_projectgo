package com.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.CustomDTO;
import com.model.NoticeBoardDAO;

public class ApplicantCommand implements CommandController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		NoticeBoardDAO dao = new NoticeBoardDAO();
		int num = Integer.parseInt(request.getParameter("num"));
		ArrayList<CustomDTO> apl_list = dao.apl_list(num);
		request.setAttribute("apl_list", apl_list);
		
	}

}
