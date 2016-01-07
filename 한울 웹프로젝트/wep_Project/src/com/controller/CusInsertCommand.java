package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.CustomDAO;
import com.model.CustomDTO;
public class CusInsertCommand implements CommandController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//DTO�� �Էµ����� ���
		CustomDTO b = new CustomDTO();//���� �����ִ°�
		
		b.setName(request.getParameter("name"));
		b.setPasswd(request.getParameter("passwd"));
		b.setZipcode(request.getParameter("zipcode"));
		b.setAddress(request.getParameter("address"));
		b.setEmail(request.getParameter("email"));
		b.setId(request.getParameter("id"));
		b.setPhone1(request.getParameter("phone1"));
		b.setPhone2(request.getParameter("phone2"));
		b.setPhone3(request.getParameter("phone3"));
		b.setDetail_address(request.getParameter("detail_address"));
		b.setEmail2(request.getParameter("email2"));
		b.setUser_type(request.getParameter("user_type")); 
		//DAO����޼ҵ� ȣ��
		CustomDAO dao = new CustomDAO();
		try {
			dao.insertMember(b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
