package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public BoardDAO(){
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(
			"jdbc:oracle:thin:@localhost:1521","superJ","0000");
		}catch(Exception e){
		}
	}

		
		
		
		//게시글 조회
		public ReplyPageInfo select(int curPage, int curBlock){
			ReplyPageInfo page = new ReplyPageInfo();
			page.setTotalList(countN());//전체게시글수
			page.setTotalPage();//전체페이지수
			page.setTotalBlock();//전체블럭수
			page.setCurPage(curPage);//현재페이지번호
			page.setCurBlock(curBlock);//현재블럭번호
			page.setBeginPage();
			page.setEndPage();
			
			//현재페이지에서 보여질 시작게시글과 끝게시글
			int begin = (curPage-1)*page.pageList+1;
			int end = curPage*page.pageList;
			
			ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
			
			try{
			String sql
			="		select * from 	"
			+ "		(select rownum no, b.* from "
			+ "		    (select * from reply order by rdate desc) b "
			+ "		 order by no)"
			+ "		where no between ? and ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, begin);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			while(rs.next()){
				BoardDTO b = new BoardDTO();
				b.setContent( rs.getString("content"));
				b.setId( rs.getString("id"));
				b.setDate( rs.getString("rdate"));
				
				list.add(b);
			}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				disconnect();
			}
			page.setList(list);
			return page;
		}
		
		public int countN(){
			int cnt=0;
			try{
				String sql="select count(*) cnt from reply";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				rs.next();
				cnt = rs.getInt("cnt");
				rs.close();	
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
			}
			return cnt;
		}
		
		public boolean insert(BoardDTO b){
			boolean success = true;
			try{
			String sql="insert into reply "
					+ "			(content, id, rdate)"
					+ "			values (?,?,to_char(sysdate,'yy/mm/dd hh24:mi:ss') )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getContent());
			pstmt.setString(2, b.getId());
			pstmt.executeUpdate();
			}catch(Exception e){
				System.out.println(e.getMessage());
				success = false;
			}finally{
				disconnect();
			}
			return success; 
		}

		private void disconnect(){
			try{
				if( rs != null ) rs.close();
				if( pstmt != null ) pstmt.close();
				if( conn != null ) conn.close();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
}




/*public ArrayList<BoardDTO> selectAll(PageInfo page){
ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
try{
String sql
="select * from reply where no between ? and ? order by rdate desc";
int start = (page.getPageList()*(page.getCurPage()-1))+1;
int end = page.getPageList()*page.getCurPage();
pstmt = conn.prepareStatement(sql);
pstmt.setInt(1, start);
pstmt.setInt(2, end);
rs = pstmt.executeQuery();
while(rs.next()){
	BoardDTO b = new BoardDTO();
	//b.setNo( rs.getInt("no") );
	//b.setTitle( rs.getString("title"));
	b.setContent( rs.getString("content"));
	b.setId( rs.getString("id"));
	b.setDate( rs.getString("rdate"));
	list.add(b);
}

}catch(Exception e){
	System.out.println(e.getMessage());
	System.out.println("db select에러");
}finally{
	disconnect();
}
return list;
}*/
