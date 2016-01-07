package com.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.NoticeBoardDAO;
import com.model.PageInfo;
import com.model.RegIdDTO;

public class PageListCommand implements CommandController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int curPage = 1;
		int curBlock = 1;
		if( request.getParameter("curPage")!=null)
			curPage = Integer.parseInt(request.getParameter("curPage"));
		if( request.getParameter("curBlock")!=null)
			curBlock = Integer.parseInt(request.getParameter("curBlock"));
		
		//DAO의 조회메소드
		NoticeBoardDAO dao = new NoticeBoardDAO();
		NoticeBoardDAO dao2 = new NoticeBoardDAO();
	
		HttpSession session= request.getSession();
		session.removeAttribute("name");
		session.removeAttribute("value");
		String id=(String)session.getAttribute("memId");
	
		PageInfo page = dao.select(curPage, curBlock);
		System.out.println(id);
		ArrayList<RegIdDTO> list=dao2.reg_id(id);
		
		//request객체에 목록저장
		request.setAttribute("regId", list);
		request.setAttribute("notice_bor_list", page.getList());
		request.setAttribute("paging", page);	
	}

}
