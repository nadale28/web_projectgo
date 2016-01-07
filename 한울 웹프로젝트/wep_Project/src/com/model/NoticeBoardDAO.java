package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NoticeBoardDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public NoticeBoardDAO(){
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(
			"jdbc:oracle:thin:@localhost:1521","superJ","0000");
		}catch(Exception e){
		}
	}
	
	
	public ArrayList<NoticeBoardDTO> select(){
		ArrayList<NoticeBoardDTO> list = new ArrayList<NoticeBoardDTO>();
		try{
		String sql
		="select rownum no, b.* from  "
		+ "(select n.*,r.city1,r.city2 from NOTICE_BOARD n, region_code r "
		+ "where region1||region2=region_total order by num) b  "
		+ "order by no desc";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()){
			NoticeBoardDTO b = new NoticeBoardDTO();
			b.setNo( rs.getInt("no") );
			b.setNum( rs.getInt("num") );
			b.setRegion_Total( rs.getString("region_total"));
			b.setTitle( rs.getString("title"));
			b.setContent( rs.getString("content"));
			b.setStart_Day(rs.getDate("start_day"));
			b.setEnd_Day(rs.getDate("end_day"));
			b.setReg_People(rs.getInt("reg_people"));
			b.setRec_People(rs.getInt("rec_people"));
			b.setAuthor(rs.getString("author"));
			b.setReadCnt(rs.getInt("readcnt"));
			b.setCity1(rs.getString("city1"));
			b.setCity2(rs.getString("city2"));
			
			list.add(b);
		}
		
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			disconnect();
		}
		return list;
	}

	
	public boolean insert(NoticeBoardDTO b){
		boolean success = true;
		
		try{
		String sql="insert into notice_board "
		+ "(num,title,content,start_day,end_day,rec_people,author,region_total,id) "
		+ "values (notice_board_seq.nextval,?,?,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, b.getTitle());
		pstmt.setString(2, b.getContent());
		pstmt.setDate(3, b.getStart_Day());
		pstmt.setDate(4,b.getEnd_Day());
		pstmt.setInt(5, b.getRec_People());
		pstmt.setString(6, b.getAuthor());
		pstmt.setString(7, b.getRegion_Total());
		pstmt.setString(8,b.getId());
		pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage());
			success = false;
		}finally{
			/*insert_regId();*/
			disconnect();
		}
		return success; 
	}
	public ArrayList<String> reg_ids(int num){
		ArrayList<String> id_list = new ArrayList<String>();
		try {

			pstmt = conn.prepareStatement("select id from reg_id where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()){
				String id = rs.getString("id");
				id_list.add(id);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//disconnect();
		}
		return id_list;
	}
	//신청한 사람들 id로 name, phone 정보 가져옴~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public ArrayList<CustomDTO> apl_list(int num){
		ArrayList<String> id_list = reg_ids(num);
		ArrayList<CustomDTO> list = new ArrayList<CustomDTO>();
		String sql = "select name, phone1, phone2, phone3  from members where id = ?";

		try {
			for(String id : id_list){
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				while(rs.next()){
					CustomDTO custom = new CustomDTO();
					custom.setName(rs.getString("name"));
					custom.setPhone1(rs.getString("phone1"));
					custom.setPhone2(rs.getString("phone2"));
					custom.setPhone3(rs.getString("phone3"));
					list.add(custom);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			disconnect();	
		}
		return list;
	}

	
	public boolean update(NoticeBoardDTO b){
		boolean success = true;
		try{
		String sql="update notice_board set title=?, author=?, "
				+ "region_total=?, start_day=?,end_day=?,rec_people=?,"
				+ "content=? where num=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, b.getTitle());
		pstmt.setString(2, b.getAuthor());
		pstmt.setString(3, b.getRegion_Total());
		pstmt.setDate(4, b.getStart_Day());
		pstmt.setDate(5, b.getEnd_Day());
		pstmt.setInt(6, b.getRec_People());
		pstmt.setString(7, b.getContent());
		pstmt.setInt(8, b.getNum());
		pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			success = false;
		}finally{
			disconnect();
		}
		return success;
	}
	
	public boolean delete(int num){
		boolean success = true;
		try{
		String sql = "delete from notice_board "
				+ "where num=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			success = false;
		}finally{
			disconnect();
		}
		
		return success;
	}
	public void updateReg_people(int num,int chk){
	System.out.println(chk);
	System.out.println(num);
		
	try {String sql=null;
			if(chk==0){
			 sql="update notice_board set reg_people=reg_people+1 "
					+ "where num=?";}
			else{
				sql="update notice_board set reg_people=reg_people-1 "
						+ "where num=?";
			}
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	}finally{
		disconnect();
	}	
		
	}
	public void insert_regId(String id,int num){
		try {
			
	String sql="insert into reg_id(id,num) "
			+"values(?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2,num);
			pstmt.executeUpdate();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	}finally{
		disconnect();
	}	
		
	}
	public void delete_regId(String id,int num){
		try {
			
	String sql="delete from reg_id "
			+"where id=? and num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2,num);
			pstmt.executeUpdate();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	}finally{
		disconnect();
	}	
		
	}
		
	
	
	public ArrayList<RegIdDTO> reg_id(String id){
		/*게시글에 있는 아이디와 memId와 같다면 틀리다는 처리*/

		ArrayList<RegIdDTO> list=new ArrayList<RegIdDTO>();
		try {

			pstmt = conn.prepareStatement("select num from reg_id where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()){
				RegIdDTO r= new RegIdDTO();
				r.setNum(rs.getInt("num"));
				list.add(r);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	

	
public PageInfo select(int curPage, int curBlock){
		
		PageInfo page = new PageInfo();
		page.setTotalList(totalCount());//전체게시글수
		page.setTotalPage();//전체페이지수
		page.setTotalBlock();//전체블럭수
		page.setCurPage(curPage);//현재페이지번호
		page.setCurBlock(curBlock);//현재블럭번호
		page.setBeginPage();//현재페이지의 시작페이지번호
		page.setEndPage();//현재페이지의 끝페이지번호
		
		//현재페이지에서 보여질 시작게시글과 끝게시글
//		int begin = (curPage-1)*page.pageList+1;//(현재페이지-1)*4+1
//		int end = curPage*page.pageList;//현재페이지*4
		int end 
			= page.getTotalList()-(curPage-1)*page.pageList;
		int begin = end-(page.pageList-1);
		
		ArrayList<NoticeBoardDTO> list = new ArrayList<NoticeBoardDTO>();
		
		try{
			String sql
			="select m.*,  case when m.end_day<sysdate then 1 "
							 + "when m.rec_people=reg_people then 1 "
							 + "else 0 end deadline from "+
			"(select rownum no, b.* from  "
			+ "(select n.*,r.city1,r.city2 from NOTICE_BOARD n, region_code r "
			+ "where region1||region2=region_total order by num) b  "
			+ "order by no desc) m "
			+ "where no between ? and ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, begin);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			//1번일경우 취소버튼, 0일경우 신청버튼
			while(rs.next()){
				NoticeBoardDTO b = new NoticeBoardDTO();
				b.setNo( rs.getInt("no") );
				b.setNum( rs.getInt("num") );
				b.setRegion_Total( rs.getString("region_total"));
				b.setTitle( rs.getString("title"));
				b.setContent( rs.getString("content"));
				b.setStart_Day(rs.getDate("start_day"));
				b.setEnd_Day(rs.getDate("end_day"));
				b.setReg_People(rs.getInt("reg_people"));
				b.setRec_People(rs.getInt("rec_people"));
				b.setAuthor(rs.getString("author"));
				b.setReadCnt(rs.getInt("readcnt"));
				b.setCity1(rs.getString("city1"));
				b.setCity2(rs.getString("city2"));
				b.setId(rs.getString("id"));
				b.setDeadline(rs.getInt("deadline")==0 ? "모집중" : "마감됨");

				list.add(b);
			}
		page.setList(list);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			disconnect();
		}
		return page;
	}

public int totalCount(){
	int cnt = 0;
	try{
	String sql="select count(*) cnt from notice_board";
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();
	rs.next();
		cnt = rs.getInt("cnt");
	rs.close();	
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
	return cnt;
}
public int totalCount_search(String name,String value){
	//검색페이지의 페이징처리
	int cnt = 0;
	try{
	String sql="select count(*) cnt from NOTICE_BOARD n, region_code r where (region1||region2=region_total )and "
			+"("+name+" like ?)";
	
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, "%"+value+"%");
	rs = pstmt.executeQuery();
	rs.next();
		cnt = rs.getInt("cnt");
				System.out.println(cnt);
	rs.close();	
	}catch(Exception e){
		System.out.println("totalCount_search:"+e.getMessage());
	}
	return cnt;
}
public PageInfo Select(String name, String value,int curPage, int curBlock){
	PageInfo page = new PageInfo();
	page.setTotalList(totalCount_search(name,value));//전체게시글수
	page.setTotalPage();//전체페이지수
	page.setTotalBlock();//전체블럭수
	page.setCurPage(curPage);//현재페이지번호
	page.setCurBlock(curBlock);//현재블럭번호
	page.setBeginPage();//현재페이지의 시작페이지번호
	page.setEndPage();//현재페이지의 끝페이지번호
	
	int end 
		= page.getTotalList()-(curPage-1)*page.pageList;
	int begin = end-(page.pageList-1);
	
	ArrayList<NoticeBoardDTO> list = new ArrayList<NoticeBoardDTO>();
	try{
		String sql
		="select m.*,  case when m.end_day<sysdate then 1" 
				+" when m.rec_people=reg_people then 1"
				+" else 0 end deadline from "
+"(select rownum no, b.* from  "
+"(select n.*,r.city1,r.city2 from NOTICE_BOARD n, region_code r "
+"where (region1||region2=region_total)and ("+name+" like ?) "
+"order by num) b  "
+"order by no desc) m"
+ " where no between ? and ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "%"+value+"%");
		pstmt.setInt(2, begin);
		pstmt.setInt(3, end);
		rs = pstmt.executeQuery();
		while(rs.next()){
			NoticeBoardDTO b = new NoticeBoardDTO();
			b.setNo( rs.getInt("no") );
			b.setNum( rs.getInt("num") );
			b.setRegion_Total( rs.getString("region_total"));
			b.setTitle( rs.getString("title"));
			b.setContent( rs.getString("content"));
			b.setStart_Day(rs.getDate("start_day"));
			b.setEnd_Day(rs.getDate("end_day"));
			b.setReg_People(rs.getInt("reg_people"));
			b.setRec_People(rs.getInt("rec_people"));
			b.setAuthor(rs.getString("author"));
			b.setReadCnt(rs.getInt("readcnt"));
			b.setCity1(rs.getString("city1"));
			b.setCity2(rs.getString("city2"));
			b.setDeadline(rs.getInt("deadline")==0 ? "모집중" : "마감됨");
			list.add(b);
	
		}
		page.setList(list);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			disconnect();
		}
		return page;
	}

private void updateReadCount(int num){
	try{
	String sql ="update notice_board set readcnt=readcnt+1 "
			+ "where num=?";
	pstmt = conn.prepareStatement(sql);
	pstmt.setInt(1, num);
	pstmt.executeUpdate();
	}catch(Exception e){
		System.out.println(e.getMessage());
	}finally{
		
	}
}

public NoticeBoardDTO select_detail(int num){
	//게시글 조회횟수 증가시키기
	updateReadCount(num);
	
	NoticeBoardDTO b = null;
	try{
		String sql
		="select m.*,  case when m.end_day<sysdate then 1 "
						 + "when m.rec_people=reg_people then 1 "
						 + "else 0 end deadline from "+
		"(select rownum no, b.* from  "
		+ "(select n.*,r.city1,r.city2 from NOTICE_BOARD n, region_code r "
		+ "where region1||region2=region_total and num=? order by num) b  "
		+ "order by no desc) m ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		rs = pstmt.executeQuery();
		if(rs.next()){
			 b = new NoticeBoardDTO();
			b.setNo( rs.getInt("no") );
			b.setNum( rs.getInt("num") );
			b.setRegion_Total( rs.getString("region_total"));
			b.setTitle( rs.getString("title"));
			b.setContent( rs.getString("content"));
			b.setStart_Day(rs.getDate("start_day"));
			b.setEnd_Day(rs.getDate("end_day"));
			b.setReg_People(rs.getInt("reg_people"));
			b.setRec_People(rs.getInt("rec_people"));
			b.setAuthor(rs.getString("author"));
			b.setReadCnt(rs.getInt("readcnt"));
			b.setCity1(rs.getString("city1"));
			b.setCity2(rs.getString("city2"));
			b.setId(rs.getString("id"));
			b.setDeadline(rs.getInt("deadline")==0 ? "모집중" : "마감됨");
		}
	
	}catch(Exception e){
		System.out.println(e.getMessage());
	}finally{
		disconnect();
	}
	return b;
}

public ArrayList<NoticeBoardDTO>select_region(String region1){
	ArrayList<NoticeBoardDTO> list=new ArrayList<NoticeBoardDTO>();
	System.out.println("select1"+region1);
	try {
		String sql="select city2,region2 from region_code where region1=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,region1);
		rs=pstmt.executeQuery();
		while(rs.next()){
			NoticeBoardDTO b =new NoticeBoardDTO();
			b.setCity1(rs.getString("city2"));
			System.out.println(rs.getString("city2"));
			b.setRegion2(rs.getString("region2"));
			System.out.println(rs.getString("region2"));
			list.add(b);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return list;
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
