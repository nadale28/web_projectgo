package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CusLogoutCommand implements CommandController {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		HttpSession session=req.getSession();
		session.invalidate();
	}

}
