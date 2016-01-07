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
		
		String nextPage = null; //������ ��������
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
			//�۾��� ȭ�� ��û
			nextPage = "view/write.jsp";
		}
		else if( cmd.contains("/insert.do") ){
			//�Խñ����� ��û
			cc = new InsertCommand();
			cc.execute(request, response);
			
			nextPage = "list.do";
			
		}
		else if( cmd.contains("/list.do") ){
			//�Խñ����� ��û
			/*cc = new ListCommand();
			cc.execute(request, response);
			
			nextPage = "view/list.jsp";*/
			cc= new PageListCommand();
			cc.execute(request, response);
			nextPage = "view/page_list.jsp";
		}
		else if( cmd.contains("/detail.do") 
				|| cmd.contains("/modify.do") ){
			//�Խñۿ� ���� ������ ��û
			//�Խñ����� �����û
			cc = new DetailCommand();
			cc.execute(request, response);
			
			nextPage =	cmd.contains("detail") 
				? "view/detail.jsp" : "view/modify.jsp";
		}
		else if( cmd.contains("/update.do")){
			//�Խñ����� �������� ��û			
			cc = new UpdateCommand();
			cc.execute(request, response);
			
			//nextPage = "list.bo";
			nextPage = "detail.do?num="+request.getParameter("num");
		}
		else if( cmd.contains("/delete.do")){
			//�Խñ� ������û
			cc = new DeleteCommand();
			cc.execute(request, response);
		
			nextPage = "list.do";
			redirect = true;
		}
		else if( cmd.contains("/add.do") ){
			//�Խñ����� ��û
			cc = new AddCommand();
			cc.execute(request, response);
		
			nextPage = "list.do";
			redirect=true;
		}
		else if( cmd.contains("/region2_search.do") ){
			//����â���� �����˻��ϱ�
			cc = new RegionCommand();
			cc.execute(request, response);
			nextPage = "view/region2_search.jsp";

		}
		else if(cmd.contains("/search.do")){
			//�˻��ϱ�
			cc= new SearchCommand();
			cc.execute(request, response);
			nextPage="view/page_list.jsp";
			
			
		}
		/*ȸ������ command*/
		else if (cmd.contains("/cus_inputForm.do")) {
			// ȸ������ ��û������
			nextPage = "view/cus_inputForm.jsp";
			
		} else if (cmd.contains("/cus_insert.do")) {
			// ȸ������������
			cc = new CusInsertCommand();
			cc.execute(request, response);

			nextPage = "main.do";
			redirect = true;
		} else if (cmd.contains("/cus_loginform.do")) {
			// �α���ȭ��

			nextPage = "view/cus_loginForm.jsp";
			
		} else if (cmd.contains("/cus_login.do")) {
			// �α��� �Ϸ� �� ����ȭ�������̵�
			cc = new CusLoginCommand();
			cc.execute(request, response);

			nextPage = "main.do";
			redirect=true;
			
		}  else if (cmd.contains("/cus_logout.do")) {
			//�α׾ƿ� �� ������������
			cc = new CusLogoutCommand();
			cc.execute(request, response);
			nextPage = "main.do";
			redirect = true;
		}

		else if (cmd.contains("/cus_modify.do")) {
			// �Խñ����� �������� ��û
			cc = new CusGetCommand();
			cc.execute(request, response);

			// nextPage = "list.bo";

			nextPage = "view/cus_modifyForm.jsp";
			// redirect = true;

		}

		else if (cmd.contains("/cus_searchIdForm.do")) {
			// ���̵� ã�� ������ ��û

			nextPage = "view/cus_searchIdForm.jsp";

		} else if (cmd.contains("/cus_searchId.do")) {
			// ���̵� ã�� ��û
			cc = new CusSearchCommandId();
			cc.execute(request, response);

			nextPage = "view/cus_searchId.jsp";

		}

		else if (cmd.contains("/cus_searchPwForm.do")) {
			// ��� ã�� ������ ��û

			nextPage = "view/cus_searchPwForm.jsp";

		} else if (cmd.contains("/cus_searchPw.do")) {
			// ��� ã�� ��û
			cc = new CusSearchCommandPw();
			cc.execute(request, response);

			nextPage = "view/cus_searchPw.jsp";

		}
		else if (cmd.contains("/cus_modifyForm.do")) {
			// ȸ�������������������̵�
			nextPage = "view/cus_modifyForm.jsp";

		}
		else if (cmd.contains("/cus_modifyPro.do")) {
			// ��� ã�� ��û
			cc = new CusModifyCommand();
			cc.execute(request, response);

			nextPage = "main.do";
		/*	redirect=ture;*/
		}
		else if (cmd.contains("/cus_confirmId.do")) {
			// ��� ã�� ��û
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
