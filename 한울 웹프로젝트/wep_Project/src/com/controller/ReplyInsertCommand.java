package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.BoardDAO;
import com.model.BoardDTO;

public class ReplyInsertCommand implements CommandController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDTO dto = new BoardDTO();
		
			//dto.setTitle(request.getParameter("title"));
			dto.setContent(request.getParameter("content"));
			dto.setId(request.getParameter("id"));
		
		
		BoardDAO dao = new BoardDAO();
		dao.insert(dto);
	}

}
