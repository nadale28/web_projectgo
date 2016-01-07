package com.superJ.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

   @Controller
   public class AdminController {
	
	@Autowired
	private AdminDAO dao;
	@Autowired
	private PageAdminInfo page;
	@Autowired
	private ReplyPageInfo rpage;
   	@RequestMapping("/admin_info")
   	public String form(Model model){
   		List<AdminDTO> list = dao.admin_select();
   		model.addAttribute("select_admin",list);
   		return "admin_info";
   	}
   	
   	@RequestMapping(value = "/insert", method=RequestMethod.POST)
   	public String join(AdminDTO AdminDTO){
   		dao.inserAdmin(AdminDTO);
   		return "forward:admin_info";
   	}
   	@RequestMapping("/member_management")
   	public String members_page(HttpServletRequest request, Model model){
   		int curPage = 1;
		int curBlock = 1;
		if( request.getParameter("curPage")!=null)
			curPage = Integer.parseInt(request.getParameter("curPage"));
		if( request.getParameter("curBlock")!=null)
			curBlock = Integer.parseInt(request.getParameter("curBlock"));
		page.setTotalList(dao.totalCount());//전체게시글수
		page.setTotalPage();//전체페이지수
		page.setTotalBlock();//전체블럭수
		page.setCurPage(curPage);//현재페이지번호
		page.setCurBlock(curBlock);//현재블럭번호
		page.setBeginPage();//현재페이지의 시작페이지번호
		page.setEndPage();//현재페이지의 끝페이지번호
		page.end
		= page.getTotalList()-(curPage-1)*page.pageList;
	    page.begin = page.end-(page.pageList-1);
	    
	    List<AdminDTO> info=dao.member_select(page);
	    
		model.addAttribute("memmanger", info);
		model.addAttribute("paging", page);
		
   		return "member_management";
   	}
  
   	@RequestMapping(value = "/admin_delete")
   	public String admin_delete(@RequestParam("id") String id){
   			dao.deleteAdmin(id);
   		return "redirect:admin_info";
   	}
   	@RequestMapping(value="/admin_reply_list")
   	public String admin_reply_list(HttpServletRequest request, Model model){
   		int curPage = 1;
		int curBlock = 1;
		if( request.getParameter("curPage")!=null)
			curPage = Integer.parseInt(request.getParameter("curPage"));
		if( request.getParameter("curBlock")!=null)
			curBlock = Integer.parseInt(request.getParameter("curBlock"));
		
		int s_cnt = dao.reply_cnt();
		rpage.setTotalList(s_cnt);//전체게시글수
		rpage.setTotalPage();//전체페이지수
		rpage.setTotalBlock();//전체블럭수
		rpage.setCurPage(curPage);//현재페이지번호
		rpage.setCurBlock(curBlock);//현재블럭번호
		rpage.setBeginPage();//현재페이지의 시작페이지번호
		rpage.setEndPage();//현재페이지의 끝페이지번호
		rpage.end
		= rpage.getTotalList()-(curPage-1)*rpage.pageList;
	    rpage.begin = rpage.end-(rpage.pageList-1);
	    
	    List<BoardDTO> list=dao.reply_list(rpage);
		
		model.addAttribute("board", list);
		model.addAttribute("pageInfo", rpage);
	    	
   		return "admin_reply_list";
   	}
   	
   	@RequestMapping(value="/admin_reply_delete")
   	public String admin_reply_delete(@RequestParam("rdate") String rdate){
   		dao.reply_delete(rdate);
   		return "redirect:admin_reply_list";
   	}
   	@RequestMapping(value="/loginForm")
   	public String loginForm(){
   		
   		return "redirect:admin_reply_list";
   	}
   
   

}
