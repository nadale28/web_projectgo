package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.CustomDAO;
import com.model.CustomDTO;
public class CusSearchCommandPw implements CommandController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		CustomDAO dao = new CustomDAO();

		
		String id = request.getParameter("id");
		String email = request.getParameter("email");
				String email2=request.getParameter("email2");
		String pw = dao.searchPw(id,email,email2);
		request.setAttribute("pw", pw);

}
}
