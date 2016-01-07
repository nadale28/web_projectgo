package com.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.NoticeBoardDAO;
import com.model.NoticeBoardDTO;

public class AddCommand implements CommandController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		NoticeBoardDAO dao = new  NoticeBoardDAO();
		NoticeBoardDAO dao2 = new  NoticeBoardDAO();
		HttpSession session=request.getSession();
		int chk= Integer.parseInt(request.getParameter("chk"));
		//chk가 0이면 봉사활동이  신청되어짐
		if(chk==0){
		
		dao.updateReg_people(Integer.parseInt(request.getParameter("add_num")),chk);
		dao2.insert_regId((String)session.getAttribute("memId"),
				Integer.parseInt(request.getParameter("add_num")));
		}
		//chk가 0이 아니면 봉사활동이 취소되어짐
		else{
			dao.updateReg_people(Integer.parseInt(request.getParameter("add_num")),chk);
			dao2.delete_regId((String)session.getAttribute("memId"),
					Integer.parseInt(request.getParameter("add_num")));
		}
		
	}

}
