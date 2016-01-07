package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.NoticeBoardDAO;

public class DeleteCommand implements CommandController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		NoticeBoardDAO dao = new NoticeBoardDAO();
		dao.delete(Integer.parseInt(request.getParameter("num")));
	}

}
