package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JoinDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public JoinDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521", "superJ", "0000");
		} catch (Exception e) {

		}
	}

	// ¿˙¿Â
	public boolean insert(JoinDTO info) {
		try {
			String sql = "insert into join "
					+ "(dona, name, birth_01, birth_02, birth_03, mobile_01,mobile_02,mobile_03, front_email, mid_email, addr_01, addr_02,"
					+ "Card_com, Card_num_01, Card_num_02, Card_num_03, Card_num_04, Card_year_01, Card_year_02, Card_own,"
					+ "Bank_com, Bank_month, Bank_num, Bank_own, Bank_birth_01, Bank_birth_02, Bank_birth_03,"
					+ "Tax_num1,Tax_num2) " 
		+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, info.getDona());
			pstmt.setString(2, info.getName());
			pstmt.setInt(3, info.getBirth_01());
			pstmt.setInt(4, info.getBirth_02());
			pstmt.setInt(5, info.getBirth_03());
			pstmt.setString(6, info.getMobile_01());
			pstmt.setString(7, info.getMobile_02());
			pstmt.setString(8, info.getMobile_03());
			pstmt.setString(9, info.getFront_email());
			pstmt.setString(10, info.getMid_email());
			pstmt.setString(11, info.getAddr_01());
			pstmt.setString(12, info.getAddr_02());
	     	pstmt.setString(13, info.getCard_com());
			pstmt.setString(14, info.getCard_num_01());
			pstmt.setString(15, info.getCard_num_02());
			pstmt.setString(16, info.getCard_num_03());
			pstmt.setString(17, info.getCard_num_04());
			pstmt.setInt(18, info.getCard_year_01());
			pstmt.setInt(19, info.getCard_year_02());
			pstmt.setString(20, info.getCard_own());
			pstmt.setString(21, info.getBank_com());
			pstmt.setInt(22, info.getBank_month());
			pstmt.setString(23, info.getBank_num());
			pstmt.setString(24, info.getBank_own());
			pstmt.setInt(25, info.getBank_birth_01());
			pstmt.setInt(26, info.getBank_birth_02());
			pstmt.setInt(27, info.getBank_birth_03());
			pstmt.setInt(28, info.getTax_num1());
			pstmt.setInt(29, info.getTax_num2());
			
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			disconnect();
		}
		return true;
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
