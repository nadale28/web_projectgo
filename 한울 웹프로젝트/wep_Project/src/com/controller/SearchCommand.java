package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.NoticeBoardDAO;
import com.model.PageInfo;

public class SearchCommand implements CommandController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		NoticeBoardDAO dao =new NoticeBoardDAO();
		PageInfo page=new PageInfo();
		HttpSession session= request.getSession();
		int curPage = 1;
		int curBlock = 1;
		if( request.getParameter("curPage")!=null)
			curPage = Integer.parseInt(request.getParameter("curPage"));
		if( request.getParameter("curBlock")!=null)
			curBlock = Integer.parseInt(request.getParameter("curBlock"));
		if(request.getParameter("search_name")!=null){
		 page= dao.Select(request.getParameter("search_name"),
				request.getParameter("search_value"),curPage,curBlock);
		 session.setAttribute("name", request.getParameter("search_name"));
			session.setAttribute("value", request.getParameter("search_value"));}
		else{
			 page= dao.Select((String)session.getAttribute("name"),
					(String)session.getAttribute("value"),curPage,curBlock);	
		}
		
		
		
		
		request.setAttribute("notice_bor_list", page.getList());
		request.setAttribute("paging", page);
		
		
				
	}

}
