package com.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.BoardDAO;
import com.model.BoardDTO;

public class ReplySelectAllCommand implements CommandController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = new BoardDAO();
		//ArrayList<BoardDTO> list = dao.selectAll();
		//request.setAttribute("reply_list", list);
	}

}
