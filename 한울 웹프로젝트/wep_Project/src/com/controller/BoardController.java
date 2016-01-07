package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();//board/list.bo
		String path = request.getContextPath();//board
		String cmd = uri.substring(path.length());// /list.bo
		
		String nextPage = null; //연결할 페이지명
		CommandController cc = null;
		boolean redirect = false;
		if(cmd.contains("/main.do")){
			if(request.getParameter("reply_content")!=null){
				cc = new ReplyInsertCommand();
				cc.execute(request, response);
			}
				cc= new ReplyPageListCommand();
				cc.execute(request, response);
			nextPage ="view/wep_main.jsp";
		}
		else if( cmd.contains("/write.do") ){
			//글쓰기 화면 요청
			nextPage = "view/write.jsp";
		}
		else if( cmd.contains("/insert.do") ){
			//게시글저장 요청
			cc = new InsertCommand();
			cc.execute(request, response);
			
			nextPage = "list.do";
			
		}
		else if( cmd.contains("/list.do") ){
			//게시글저장 요청
			/*cc = new ListCommand();
			cc.execute(request, response);
			
			nextPage = "view/list.jsp";*/
			cc= new PageListCommand();
			cc.execute(request, response);
			nextPage = "view/page_list.jsp";
		}
		else if( cmd.contains("/detail.do") 
				|| cmd.contains("/modify.do") ){
			//게시글에 대한 상세정보 요청
			//게시글정보 변경요청
			cc = new DetailCommand();
			cc.execute(request, response);
			
			nextPage =	cmd.contains("detail") 
				? "view/detail.jsp" : "view/modify.jsp";
		}
		else if( cmd.contains("/update.do")){
			//게시글정보 변경저장 요청			
			cc = new UpdateCommand();
			cc.execute(request, response);
			
			//nextPage = "list.bo";
			nextPage = "detail.do?num="+request.getParameter("num");
		}
		else if( cmd.contains("/delete.do")){
			//게시글 삭제요청
			cc = new DeleteCommand();
			cc.execute(request, response);
		
			nextPage = "list.do";
			redirect = true;
		}
		else if( cmd.contains("/add.do") ){
			//게시글저장 요청
			cc = new AddCommand();
			cc.execute(request, response);
		
			nextPage = "list.do";
			redirect=true;
		}
		else if( cmd.contains("/region2_search.do") ){
			//수정창에서 지역검색하기
			cc = new RegionCommand();
			cc.execute(request, response);
			nextPage = "view/region2_search.jsp";

		}
		else if(cmd.contains("/search.do")){
			//검색하기
			cc= new SearchCommand();
			cc.execute(request, response);
			nextPage="view/page_list.jsp";
			
			
		}
		/*회원가입 command*/
		else if (cmd.contains("/cus_inputForm.do")) {
			// 회원가입 요청페이지
			nextPage = "view/cus_inputForm.jsp";
			
		} else if (cmd.contains("/cus_insert.do")) {
			// 회원가입페이지
			cc = new CusInsertCommand();
			cc.execute(request, response);

			nextPage = "main.do";
			redirect = true;
		} else if (cmd.contains("/cus_loginform.do")) {
			// 로그인화면

			nextPage = "view/cus_loginForm.jsp";
			
		} else if (cmd.contains("/cus_login.do")) {
			// 로그인 완료 시 메인화면으로이동
			cc = new CusLoginCommand();
			cc.execute(request, response);

			nextPage = "main.do";
			redirect=true;
			
		}  else if (cmd.contains("/cus_logout.do")) {
			//로그아웃 후 메인페이지로
			cc = new CusLogoutCommand();
			cc.execute(request, response);
			nextPage = "main.do";
			redirect = true;
		}

		else if (cmd.contains("/cus_modify.do")) {
			// 게시글정보 변경저장 요청
			cc = new CusGetCommand();
			cc.execute(request, response);

			// nextPage = "list.bo";

			nextPage = "view/cus_modifyForm.jsp";
			// redirect = true;

		}

		else if (cmd.contains("/cus_searchIdForm.do")) {
			// 아이디 찾기 페이지 요청

			nextPage = "view/cus_searchIdForm.jsp";

		} else if (cmd.contains("/cus_searchId.do")) {
			// 아이디 찾기 요청
			cc = new CusSearchCommandId();
			cc.execute(request, response);

			nextPage = "view/cus_searchId.jsp";

		}

		else if (cmd.contains("/cus_searchPwForm.do")) {
			// 비번 찾기 페이지 요청

			nextPage = "view/cus_searchPwForm.jsp";

		} else if (cmd.contains("/cus_searchPw.do")) {
			// 비번 찾기 요청
			cc = new CusSearchCommandPw();
			cc.execute(request, response);

			nextPage = "view/cus_searchPw.jsp";

		}
		else if (cmd.contains("/cus_modifyForm.do")) {
			// 회원정보수정페이지로이동
			nextPage = "view/cus_modifyForm.jsp";

		}
		else if (cmd.contains("/cus_modifyPro.do")) {
			// 비번 찾기 요청
			cc = new CusModifyCommand();
			cc.execute(request, response);

			nextPage = "main.do";
		/*	redirect=ture;*/
		}
		else if (cmd.contains("/cus_confirmId.do")) {
			// 비번 찾기 요청
			cc = new CusConfirmldCommand();
			cc.execute(request, response);

			nextPage = "view/cus_confirmId.jsp";
		/*	redirect=ture;*/
		}
		/*else if(cmd.contains("/reply.do")){
				
			
		
				
				nextPage = "ih_reply.jsp" ;
				//redirect = true;
		}*/
		else if(cmd.contains("/repAjax.do")){
			
			if(request.getParameter("content")!=null){
				cc = new ReplyInsertCommand();
				cc.execute(request, response);
			}
				cc= new ReplyPageListCommand();
				cc.execute(request, response);
				
				nextPage =	"view/repAjax.jsp";
				//redirect = true;
		}else if (cmd.contains("/sponser.do")){
			cc= new SponCommand();
			cc.execute(request, response);
			nextPage="list.do";
			redirect=true;
		}
		else if(cmd.contains("/applicant.do")){
			cc = new ApplicantCommand();
			cc.execute(request, response);
			nextPage = "view/applicant_list.jsp";
		}
		else if(cmd.contains("/accept.do")){
			nextPage = "view/accept.html";
		}
		else if(cmd.contains("/cancle.do")){
			nextPage = "view/cancle.html";
		}
		if( redirect ){
			response.sendRedirect(nextPage);
		}else{
			RequestDispatcher rd 
				= request.getRequestDispatcher(nextPage);
			rd.forward(request, response);
		}
		
	}

}
