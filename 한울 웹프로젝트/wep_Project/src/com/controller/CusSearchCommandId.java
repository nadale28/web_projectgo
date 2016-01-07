package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.CustomDAO;
import com.model.CustomDTO;
public class CusSearchCommandId implements CommandController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		CustomDAO dao = new CustomDAO();

		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String email2=request.getParameter("email2");
		String id = dao.searchId(name,email,email2);
		System.out.println("id:"+id);
		System.out.println("email:"+email);
		System.out.println("email2:"+email2);
		request.setAttribute("id", id);
	}

}
