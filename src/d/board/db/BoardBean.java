package d.board.db;

import java.sql.Date;

public class BoardBean {
	
		
		private int B_NUM;
		private String B_ID;
		private String B_TITLE;
		private String B_CONTENT;
		private String B_FILE;
		private int B_READCOUNT;
		private int B_GOOD;
		private Date B_DATE;
		
		
		public int getB_NUM() {
			return B_NUM;
		}
		public void setB_NUM(int b_NUM) {
			B_NUM = b_NUM;
		}
		public String getB_ID() {
			return B_ID;
		}
		public void setB_ID(String b_ID) {
			B_ID = b_ID;
		}
		public String getB_TITLE() {
			return B_TITLE;
		}
		public void setB_TITLE(String b_TITLE) {
			B_TITLE = b_TITLE;
		}
		public String getB_CONTENT() {
			return B_CONTENT;
		}
		public void setB_CONTENT(String b_CONTENT) {
			B_CONTENT = b_CONTENT;
		}
		public String getB_FILE() {
			return B_FILE;
		}
		public void setB_FILE(String b_FILE) {
			B_FILE = b_FILE;
		}
		public int getB_READCOUNT() {
			return B_READCOUNT;
		}
		public void setB_READCOUNT(int b_READCOUNT) {
			B_READCOUNT = b_READCOUNT;
		}
		public int getB_GOOD() {
			return B_GOOD;
		}
		public void setB_GOOD(int b_GOOD) {
			B_GOOD = b_GOOD;
		}
		public Date getB_DATE() {
			return B_DATE;
		}
		public void setB_DATE(Date b_DATE) {
			B_DATE = b_DATE;
		}
		
		
	

}
