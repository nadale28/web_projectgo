package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaymentCommand implements CommandController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		request.getParameter("support");
		request.getParameter("name");
		request.getParameter("jumin1");
		request.getParameter("jumin2");
		request.getParameter("phone1");
		request.getParameter("phone2");
		request.getParameter("phone3");
	}

}
