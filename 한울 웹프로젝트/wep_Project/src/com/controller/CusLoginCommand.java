package com.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.CustomDAO;

public class CusLoginCommand implements CommandController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		CustomDAO dao = new CustomDAO();
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String idcheck = request.getParameter("saveid");
		

		
		

		if(idcheck != null && idcheck.equals("on"))
		{
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(60 * 60);
			response.addCookie(cookie);
		}
		else
		{
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(0);
		}
		
		int check = dao.userCheck(id, passwd);
		
		HttpSession session= request.getSession();
		
		if(check == 1){
			
			session.setAttribute("memId", id);
					
			//인환 수정 44번째줄 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			CustomDAO dao1 = new CustomDAO();
			String user_type = dao1.user_type(id);
			session.setAttribute("user_type", user_type);
			
		}
		

}
}
