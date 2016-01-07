package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SponsorDAO {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public SponsorDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521", "superJ", "0000");
		} catch (Exception e) {

		}
	}
	

	// ¿˙¿Â
	public boolean insert(SponsorDTO info) {
		try {
			String sql = "insert into sponsor "
					+ "( tel_01, tel_02, tel_03, name, gen, mail,amount,jumin1,jumin2) "
					+ "values (?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, info.getTel_01());
			pstmt.setString(2, info.getTel_02());
			pstmt.setString(3, info.getTel_03());
			pstmt.setString(4, info.getName());
			pstmt.setString(5, info.getGen());
			pstmt.setString(6, info.getMail());
			pstmt.setString(7, info.getAmount());
			pstmt.setString(8,info.getJumin1());
			pstmt.setString(9,info.getJumin2());
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
