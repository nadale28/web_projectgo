package com.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.NoticeBoardDAO;
import com.model.NoticeBoardDTO;

public class ListCommand implements CommandController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		NoticeBoardDAO dao = new  NoticeBoardDAO();
		/*dao.updateRec_people(num);*/
/*		dao.updateRec_people(num);*/
		ArrayList<NoticeBoardDTO> list= dao.select();
		request.setAttribute("notice_bor_list", list);
		

	}

}
