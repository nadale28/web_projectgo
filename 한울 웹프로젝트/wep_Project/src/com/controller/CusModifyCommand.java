package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.CustomDAO;
import com.model.CustomDTO;

public class CusModifyCommand implements CommandController {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		HttpSession session=req.getSession();
		String id = (String) session.getAttribute("memId");
		CustomDTO dto = new CustomDTO();
		dto.setId(id);
		dto.setPasswd(req.getParameter("passwd"));
		dto.setEmail(req.getParameter("email"));
		dto.setEmail2(req.getParameter("email2"));
		dto.setDetail_address(req.getParameter("detail_address"));
		dto.setZipcode(req.getParameter("zipcode"));
		dto.setAddress(req.getParameter("address"));
		CustomDAO dao=new CustomDAO();
		dao.updateMember(dto);
	}
	
}
