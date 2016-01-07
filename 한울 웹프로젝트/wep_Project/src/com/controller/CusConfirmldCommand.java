package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.CustomDAO;

public class CusConfirmldCommand implements CommandController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("id");
		CustomDAO dao = new CustomDAO();
			
			 int check= dao.confirmId(id);
		HttpSession session = request.getSession();
		session.setAttribute("check", check);
	}

}
