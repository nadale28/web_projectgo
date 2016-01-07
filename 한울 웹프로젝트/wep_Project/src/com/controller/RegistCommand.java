package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.JoinDAO;
import com.model.JoinDTO;




public class RegistCommand implements CommandController {

	@Override
	public void execute(HttpServletRequest request, 
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		JoinDTO info = new JoinDTO();
		info.setDona(request.getParameter("support"));
		info.setName(request.getParameter("name_01"));
		info.setBirth_01(Integer.parseInt(request.getParameter("birth_1")));
		info.setBirth_02(Integer.parseInt(request.getParameter("birth_2")));
		info.setBirth_03(Integer.parseInt(request.getParameter("birth_3")));
		info.setMobile_01(request.getParameter("m_mobile1"));
		info.setMobile_02(request.getParameter("m_mobile2"));
		info.setMobile_03(request.getParameter("m_mobile3"));
		info.setFront_email((request.getParameter("mail1")));
		info.setMid_email((request.getParameter("mail2")));
		info.setAddr_01(request.getParameter("addr1")); 
		info.setAddr_02(request.getParameter("addr2"));
		
		info.setCard_com(request.getParameter("a_cardcompany"));
		info.setCard_num_01(request.getParameter("a_cardnumber_1"));
		info.setCard_num_02(request.getParameter("a_cardnumber_2"));
		info.setCard_num_02(request.getParameter("a_cardnumber_3"));
		info.setCard_num_02(request.getParameter("a_cardnumber_4"));

		info.setCard_year_01(Integer.parseInt(request.getParameter("a_cardyear")));
		info.setCard_year_02(Integer.parseInt(request.getParameter("a_cardmonth")));
		info.setCard_own(request.getParameter("a_cardname"));
		
		info.setBank_com(request.getParameter("a_bankcompany"));
		info.setBank_month(Integer.parseInt(request.getParameter("date")));
		info.setBank_num(request.getParameter("a_banknumber"));
		info.setBank_own(request.getParameter("card_own"));
		info.setBank_birth_01(Integer.parseInt(request.getParameter("a_birth_1")));
		info.setBank_birth_02(Integer.parseInt(request.getParameter("a_birth_2")));
		info.setBank_birth_03(Integer.parseInt(request.getParameter("a_birth_3")));
		
		info.setTax_num1(Integer.parseInt(request.getParameter("front_jumin")));
		info.setTax_num2(Integer.parseInt(request.getParameter("end_jumin2")));
		//info.setWirteday(request.getParameter("addr2"));
		
		JoinDAO dao = new JoinDAO();
		dao.insert(info);
		
		
		
	}
	
}
