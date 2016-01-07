package com.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.NoticeBoardDAO;
import com.model.NoticeBoardDTO;
import com.model.RegIdDTO;

public class DetailCommand implements CommandController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	NoticeBoardDAO dao = new NoticeBoardDAO();
	NoticeBoardDAO dao2 = new NoticeBoardDAO();
	NoticeBoardDTO b = dao.select_detail(Integer.parseInt(request.getParameter("num")));
	HttpSession session=request.getSession();
	String id = (String)session.getAttribute("memId");
	ArrayList<RegIdDTO> list=dao2.reg_id(id);
	
	//request객체에 목록저장
	request.setAttribute("regId", list);
	
	request.setAttribute("notice_bor_detail", b);
	}

}
