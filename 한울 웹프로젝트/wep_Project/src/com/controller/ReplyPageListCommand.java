package com.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.BoardDAO;
import com.model.BoardDTO;
import com.model.ReplyPageInfo;

public class ReplyPageListCommand implements CommandController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ReplyPageInfo pageInfo = new ReplyPageInfo();
		BoardDAO dao = new BoardDAO();
		int curPage = 1;
		int curBlock = 1;
		if(request.getParameter("curPage")!=null){
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		if(request.getParameter("curBlock")!=null){
			curBlock = Integer.parseInt(request.getParameter("curBlock"));
		}
		
		pageInfo = dao.select(curPage, curBlock);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("board", pageInfo.getList());
		
	}

}
