package d.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class BoardDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DataSource ds = null;

	public BoardDAO() {

		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");

		} catch (Exception e) {
			System.out.println("driver load error : " + e);
		}
	}

	public void disconnect() {

		try {
			if (con != null)
				con.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			System.out.println("disconnect error : " + e);
		}
	}

	public boolean boardUserCheck(BoardBean board) {

		String sql = "select B_ID from d_board where B_NUM=?";
		boolean bool = false;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board.getB_NUM());

			rs = pstmt.executeQuery();
			rs.next();

			if (board.getB_ID().equals(rs.getString(1))) {
				bool = true;
				return bool;
			}
		} catch (Exception e) {
			System.out.println("boardUserCheck error : " + e);
		} finally {
			disconnect();
		}

		return bool;
	}

	public BoardBean getDetailList(int num) {
		String sql = "select * from d_board where B_NUM = ?";
		BoardBean board = new BoardBean();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				board.setB_NUM(rs.getInt("B_NUM"));
				board.setB_ID(rs.getString("B_ID"));
				board.setB_TITLE(rs.getString("B_TITLE"));
				board.setB_CONTENT(rs.getString("B_CONTENT"));
				board.setB_FILE(rs.getString("B_FILE"));
				board.setB_READCOUNT(rs.getInt("B_READCOUNT"));
				board.setB_GOOD(rs.getInt("B_GOOD"));
				board.setB_DATE(rs.getDate("B_DATE"));

				return board;
			}
		} catch (Exception e) {
			System.out.println("getDetailList error : " + e);
		} finally {
			disconnect();
		}
		return null;
	}

	public boolean boardModify(BoardBean board) {

		boolean bool = false;
		String sql = "update d_board set B_TITLE = ?, B_CONTENT = ?, B_DATE = sysdate where B_NUM = ?";

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, board.getB_TITLE());
			pstmt.setString(2, board.getB_CONTENT());
			pstmt.setInt(3, board.getB_NUM());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				bool = true;
			}

		} catch (Exception e) {
			System.out.println("boardModify error : " + e);

		} finally {
			disconnect();
		}

		return bool;
	}

	public boolean boardInsert(BoardBean board) {

		System.out.println("boardinsert 메서드 들어옴");
		int num = 0;
		int result = 0;

		String sql1 = "select max(b_num) from d_board";

		String sql2 = "insert into d_board " + " values (?, ?, ?, ?, ?, ?, ?, sysdate)";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql1);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1) + 1;
			} else {
				num = 1;
			}

			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getB_ID());
			pstmt.setString(3, board.getB_TITLE());
			pstmt.setString(4, board.getB_CONTENT());
			pstmt.setString(5, board.getB_FILE());
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);

			result = pstmt.executeUpdate();

			if (result > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("boardInsert() error : " + e);
		} finally {
			disconnect();
		}
		return false;
	}

	public void setReadCount(int num) {
		String sql = " update d_board set b_readcount = b_readcount + 1 where b_num = ?";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("setReadCount error : " + e);
		} finally {
			disconnect();
		}
	}
	
	
	

	public List getNewList() {
		
		List list = new ArrayList();
		
		String sql = "select * from"
					+ " (select rownum rnum, b_num, b_id, b_title, b_content, "
					+ "b_file, b_readcount, b_good,"
					+ "b_date from (select * from d_board order by b_date desc)) "
					+ "where rnum>=? and rnum<=?";
		
		try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, 1);
				pstmt.setInt(2, 4);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BoardBean board = new BoardBean();
					board.setB_NUM(rs.getInt("B_NUM"));
					board.setB_ID(rs.getString("B_ID"));
					board.setB_TITLE(rs.getString("B_TITLE"));
					board.setB_CONTENT(rs.getString("B_CONTENT"));
					board.setB_FILE(rs.getString("B_FILE"));
					board.setB_READCOUNT(rs.getInt("B_READCOUNT"));
					board.setB_GOOD(rs.getInt("B_GOOD"));
					board.setB_DATE(rs.getDate("B_DATE"));
					
					list.add(board);
					
				}
				
		
				
				return list;
			
			
		}catch (Exception e) {
				System.out.println("getNewlist error: " + e);
		}
		finally {
			disconnect();
		}
					
		return null;

	
		
	
	
	
	}

	public int getListCount() {
		int x = 0;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from d_board");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println("getListCount() error : " + e);
		} finally {

			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

		}

		return x;
	}

	public List getBoardList(int page, int limit) {

		String board_list_sql = "select * from " +
				"(select rownum rnum, b_num, b_id, b_title, " +
				"b_content, b_file, "+
				"b_readcount, b_good, b_date from " + 
				"(select * from d_board order by " +
				"b_date desc))" +
				"where rnum>=? and rnum<=?";

		List list = new ArrayList();

		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardBean board = new BoardBean();
				board.setB_NUM(rs.getInt("B_NUM"));
				board.setB_ID(rs.getString("B_ID"));
				board.setB_TITLE(rs.getString("B_TITLE"));
				board.setB_CONTENT(rs.getString("B_CONTENT"));
				board.setB_FILE(rs.getString("B_FILE"));
				board.setB_READCOUNT(rs.getInt("B_READCOUNT"));
				board.setB_GOOD(rs.getInt("B_GOOD"));				
				board.setB_DATE(rs.getDate("B_DATE"));
				list.add(board);
			}

			return list;

		} catch (Exception e) {
			System.out.println("getBoardList() error : " + e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}

		return null;
	}

	public int getGoodCount(int num) {
		int result = 0;
		int good =0;

		String sql = "select b_good from d_board where b_num =?";
		String sql2 ="update d_board set b_good=? where b_num=?";				
		
		try {
			con  = ds.getConnection();
			pstmt =con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			rs.next();
			
			if (rs.getInt(1)==0) {
				good = 1;
			}else {
				good = rs.getInt(1)+1;
			}
			
			pstmt = con.prepareStatement(sql2);
				
				pstmt.setInt(1, good);
				pstmt.setInt(2, num);
				result = pstmt.executeUpdate();
				
				if (result!=0) {
					return result;
				}
	
		}catch(Exception e) {
			System.out.println("getGoodCount error : " + e);
		}
		finally {
			disconnect();
		}
		return result;
		
	}
	
	public boolean isBoardWriter(int num, String id) {
	       
        String sql = "select b_id from d_board where b_num = ? ";
        
        try {
           con = ds.getConnection();
           pstmt = con.prepareStatement(sql);
           pstmt.setInt(1, num);
           rs = pstmt.executeQuery();
           
           rs.next();
           
           if (id.equals(rs.getString("b_id"))) {
              return true;
           }
           
        } catch (Exception e) {
           System.out.println("isBoardWrite 실패 : " + e );
        } finally {
           disconnect();
        }
        return false;   
     }
	
	
	public boolean boardDelete(int num) {
	      
	      String sql = "delete from d_board where b_num = ?";
	        
	        int result = 0;
	        
	        try {
	           con = ds.getConnection();
	           pstmt = con.prepareStatement(sql);
	           pstmt.setInt(1, num);
	           result = pstmt.executeUpdate();
	           if(result == 0) return false;
	           
	           return true;
	        } catch(Exception e) {
	           System.out.println("boardDelete 삭제 실패 : " + e);
	        } finally {
	           disconnect();
	        }
	        return false;
	   }

	public List getMyList(int page, int limit, String id) {
	      
        List list = new ArrayList();
         
         String sql = "select * from " +
               "(select rownum rnum, b_num, b_id, b_title, " +
               "b_content, b_file, "+
               "b_readcount, b_good, b_date from " + 
               "(select * from d_board where b_id = ? )) " +
               "where rnum>=? and rnum<=?";
         
         int startpage = (page - 1) * 10 + 1;
        int endpage = startpage + limit - 1;
         
         try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setInt(2, startpage);
            pstmt.setInt(3, endpage);
            
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
               
              BoardBean board = new BoardBean(); 
               board.setB_NUM(rs.getInt("B_NUM"));
               board.setB_ID(rs.getString("B_ID"));
               board.setB_TITLE(rs.getString("B_TITLE"));
               board.setB_CONTENT(rs.getString("B_CONTENT"));
               board.setB_FILE(rs.getString("B_FILE"));
               board.setB_READCOUNT(rs.getInt("B_READCOUNT"));
               board.setB_GOOD(rs.getInt("B_GOOD"));
               board.setB_DATE(rs.getDate("B_DATE"));
               
               list.add(board);
            }
            return list;
            
         }catch(Exception e) {
            System.out.println("getMyList error : " + e);
         }
         finally {
            disconnect();
         }
         return null;
   }
	
	
	public List getGoodList() {
		
		List list = new ArrayList();
		String sql = "select * from "
					+ "(select rownum rnum, b_num, b_id, b_title, b_file, b_good from "
					+ "(select * from d_board order by b_good desc))"
					+ " where rnum>=? and rnum<=?";
		
		try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, 1);
				pstmt.setInt(2, 3);
				rs = pstmt.executeQuery();
				
				
				while(rs.next()) {
					BoardBean board = new BoardBean();
					board.setB_NUM(rs.getInt("B_NUM"));
					board.setB_ID(rs.getString("B_ID"));
					board.setB_TITLE(rs.getString("B_TITLE"));
					board.setB_FILE(rs.getString("B_FILE"));
					board.setB_GOOD(rs.getInt("B_GOOD"));
					
					list.add(board);
					
					System.out.println(board.getB_TITLE());
				}
				return list;
		}catch(Exception e) {
			System.out.println("getGoodList error : " + e);
		}
		finally {
			disconnect();
		}
		return null;
		
	}
	
	

}
