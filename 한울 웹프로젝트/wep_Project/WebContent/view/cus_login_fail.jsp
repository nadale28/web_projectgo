<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.sql.*" %>
<%
String driverName = "oracle.jdbc.driver.OracleDriver";
String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
String dbId = "superJ";
String dbPass = "0000";
%>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	
	Connection conn=null;
	PreparedStatement pstmt = null;
	ResultSet rs =null;
	String sql = "";
	
	try{
		Class.forName(driverName);
		conn = DriverManager.getConnection(jdbcUrl,dbId,dbPass);
		
		sql = "select id from members where id=? and passwd=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, passwd);
		rs = pstmt.executeQuery();
		
		String login_fail = "";
		if(rs.next()){
			login_fail = rs.getString("id");
		}
		%>
		{
		"result" : "success",
		"id" : "<%=login_fail %>"
		}
		<%
	}catch(Exception e){
		%>
		{
		"result" : "failure",		
		}
		<%
	}finally{
		//자원 정리
	     if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
	     if(conn!=null)try{conn.close();}catch(SQLException e){}
	     if(rs!=null)try{rs.close();}catch(SQLException e){}
	}
%>