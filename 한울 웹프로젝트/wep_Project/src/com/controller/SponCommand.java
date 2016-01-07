package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.SponsorDAO;
import com.model.SponsorDTO;

public class SponCommand implements CommandController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		SponsorDTO info = new SponsorDTO();
		info.setTel_01(request.getParameter("hp1"));
		info.setTel_02(request.getParameter("hp2"));
		info.setTel_03(request.getParameter("hp3"));
		info.setName(request.getParameter("user_nm"));
		info.setGen(request.getParameter("sex"));
		info.setMail(request.getParameter("email"));
		info.setAmount(request.getParameter("amount"));
		info.setJumin1(request.getParameter("jumin1"));
		info.setJumin2(request.getParameter("jumin2"));
		SponsorDAO dao = new SponsorDAO();
		dao.insert(info);
	}

}
