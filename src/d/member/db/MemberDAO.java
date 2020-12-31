package d.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DataSource ds = null;
	
	
	public MemberDAO() {
		
		try {
		Context init = new InitialContext();
		ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		
		}catch(Exception e) {
			System.out.println("driver load error : " + e);
		}
	}
	

	public void disconnect() {

			try {
				if(con!=null) con.close();
				if(pstmt!=null) pstmt.close();
				if (rs!=null) rs.close();
			} catch (SQLException e) {
				System.out.println("disconnect error : " + e);
			}
	}


	public boolean memberInsert(MemberBean member) {
		
		String sql = "insert into d_member values(?,?,?,?,?,?)";
		boolean bool = false;
		
		try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, member.getM_ID());
		pstmt.setString(2, member.getM_PW());
		pstmt.setString(3, member.getM_NAME());
		pstmt.setInt(4, member.getM_AGE());
		pstmt.setString(5, member.getM_GENDER());
		pstmt.setString(6, member.getM_EMAIL());
		
		int n = pstmt.executeUpdate();
		
		if (n>0) {
			bool = true;
		}
		
		}catch(Exception e) {
			System.out.println("memberInsert error : " + e);
		}finally {
			disconnect();
		}
		
		return bool;
		
	}
	
	
	public int memberUserCheck(String id, String pw) {
		String sql = "select M_PW from d_member where M_ID=?";
		int result = 0;
		
		try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		rs.next();
		
		String tablePw = rs.getString(1);
		
		if (pw.equals(tablePw)) {
			result = 1;
		}else {
			result = -1;
		}
		
		}catch(Exception e) {
			System.out.println("memberUserCheck error : " + e);
		}finally {
			disconnect();
		}
		
		return result;
	}
	
	

}
