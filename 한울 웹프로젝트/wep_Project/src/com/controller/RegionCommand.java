package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.model.NoticeBoardDAO;
import com.model.NoticeBoardDTO;

public class RegionCommand implements CommandController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		NoticeBoardDAO dao= new NoticeBoardDAO();
		ArrayList<NoticeBoardDTO> list= dao.select_region(request.getParameter("region1"));
		request.setAttribute("select_region", list);
	//http://pureainu.tistory.com/m/post/7 //
	}

		
		/*NoticeBoardDAO dao = new NoticeBoardDAO();
		System.out.println("command³¡"+request.getParameter("region1"));
		ArrayList<NoticeBoardDTO> list= dao.select_region(request.getParameter("region1"));
		JSONArray js = JSONArray.fromObject(list);
		JSONObject obj = new JSONObject();
		obj.put("VERION_LIST", js.toString());
		response.setContentType("application/x-json; charset=UTF-8");
		try {
			response.getWriter().print(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
}
