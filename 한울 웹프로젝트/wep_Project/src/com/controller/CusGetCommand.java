package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.CustomDAO;
import com.model.CustomDTO;
public class CusGetCommand implements CommandController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//ȸ�������ϱ� ���� ���� ������ �ҷ����� command
		CustomDAO dao = new CustomDAO();
		CustomDTO dto = new CustomDTO();
		
		String id = request.getParameter("id");
		dto = dao.getMember(id);
		request.setAttribute("memberinfo",dto);
	}

}
