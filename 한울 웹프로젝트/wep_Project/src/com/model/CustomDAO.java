package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class CustomDAO

{
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	private static CustomDAO instance = new CustomDAO();

	public static CustomDAO getInstance() {
		return instance;
	}

	/*
	 * private LogonDBBean() {}
	 * 
	 * private Connection getConnection() throws Exception { String jdbcDriver =
	 * "jdbc:apache:commons:dbcp:/pool";
	 * 
	 * return DriverManager.getConnection(jdbcDriver); }
	 */

	public CustomDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521", "superJ", "0000");
		} catch (Exception e) {
		}
	}

	// 데이터를 입력
	public void insertMember(CustomDTO member) {

		try {
			String sql = "insert into members (id,passwd,name,email,zipcode,address"
					+ ",phone1,phone2,phone3,detail_address,email2,user_type)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getZipcode());
			pstmt.setString(6, member.getAddress());
			pstmt.setString(7, member.getPhone1());
			pstmt.setString(8, member.getPhone2());
			pstmt.setString(9, member.getPhone3());
			pstmt.setString(10,member.getDetail_address());
			pstmt.setString(11, member.getEmail2());
			pstmt.setString(12, member.getUser_type());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnect();
		}
	}
	public String user_type(String id){
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String user_type = null;
		try{
			String sql = "select user_type from members where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				user_type = rs.getString("user_type");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
		return user_type;
	}

	// 로그인 로직에서 사용할...
	public int userCheck(String id, String passwd) {

		String dbpasswd = "";
		int x = -1;

		try {

			pstmt = conn
					.prepareStatement("select passwd from MEMBERS where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dbpasswd = rs.getString("passwd");

				if (dbpasswd.equals(passwd))
					x = 1; // 인증 성공
				else
					x = 0; // 비밀번호 틀림
			} else
				x = -1; // 해당 아이디 없음
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnect();
		}
		return x;
	}

	// 회원가입시 ID를 체크할 때 호출
	public int confirmId(String id)  {
		// Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = -1;

		try {

			pstmt = conn
					.prepareStatement("select id from MEMBERS where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next())
				x = 1; // 해당 아이디 있음
			else
				x = -1; // 해당 아이디 없음
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnect();
		}
		return x;
	}

	private void disconnect() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// 아이디 찾기
	public String searchId(String name,String email,String email2) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sid=null;
		try {
			pstmt = conn
					.prepareStatement("select id from MEMBERS where name = ? "
							+ "and email =? and email2=?");
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, email2);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				sid=rs.getString("id");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnect();
			System.out.println(sid);
		}
		return sid;
	}

	// 비밀번호 찾기
	public String searchPw(String id, String email,String email2) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String spw = null;
		try {
			pstmt = conn
					.prepareStatement("select passwd from MEMBERS where id = ? "
							+ "and email=? and email2=?");
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			pstmt.setString(3, email2);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				spw=rs.getString("passwd");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnect();
		}
		return spw;
	}

	// 업데이트시 입력된 데이터를 보여줄 때 사용
	public CustomDTO getMember(String id){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomDTO member = null;

		try {

			pstmt = conn.prepareStatement("select * from MEMBERS where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new CustomDTO();
				member.setId(rs.getString("id"));
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setZipcode(rs.getString("zipcode"));
				member.setAddress(rs.getString("address"));
				member.setPhone1(rs.getString("phone1"));
				member.setPhone2(rs.getString("phone2"));
				member.setPhone3(rs.getString("phone3"));
				member.setDetail_address(rs.getString("detail_address"));
				member.setEmail2(rs.getString("email2"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
            disconnect();
           
            }
		 return member;
	}

	// 회원정보를 수정
	public void updateMember(CustomDTO member)  {
		PreparedStatement pstmt = null;

		try {

			pstmt = conn
					.prepareStatement("update MEMBERS set passwd = ?,  "
							+ "email = ? ,zipcode=?,address=?,detail_address=?,email2=? where id = ?");
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getZipcode());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getDetail_address());
			pstmt.setString(6, member.getEmail2());
			pstmt.setString(7, member.getId());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}