package BoardExample;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import SQL.Board;

public class BoardDAO {
	public boolean writeBoard() throws Exception{
		Connection conn = null;
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("게시글 작성");
			try {
				//JDBC Driver 등록
				Class.forName("oracle.jdbc.OracleDriver");
				
				//연결하기
				conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521/xe",
						"SYSTEM",
						"oracle"
						);
				//매개변수화된 SQL 문 작성
				String sql = "" + 
						"INSERT INTO boards (bno, btitle, bcontent, bwriter, bdate, bfilename, bfiledata)" + 
						"VALUES (board_seq.NEXTVAL, ?, ?, ?, SYSDATE, ?, ?)";
			//PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"bno"});
			System.out.print("제목: ");
			String title = sc.nextLine();
			System.out.print("내용: ");
			String content = sc.nextLine();
			System.out.print("작성자: ");
			String writer = sc.nextLine();
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			pstmt.setString(4, "snow.jpg");
			pstmt.setBlob(5, new FileInputStream("c:\\Temp/snow.jpg"));
			
			//SQL 문 실행
			pstmt.executeUpdate();
			return true;
				
}catch(Exception e) {
			e.printStackTrace();
}finally {
			if(conn != null) {
				try {
					conn.close();
					}catch(SQLException e) {}
				}
			}
		}
		return false;
	}
	
	public boolean readBoard(int bno) {
		Connection conn = null;
		try {
			//JDBC Driver 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//연결하기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe",
					"SYSTEM",
					"oracle"
					);
			//매개변수화된 SQL 문 작성
			String sql = "" +
					"SELECT bno, btitle, bcontent, bwriter, bdate, bfilename, bfiledata "+
					"FROM boards "+
					"WHERE bno=?";
			//PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			// SQL 문 실행 후, ResultSet을 통해 데이터 읽기
						ResultSet rs = pstmt.executeQuery();
						while(rs.next()) {
							Board board = new Board();
							board.setBno(rs.getInt("bno"));
							board.setBtitle(rs.getString("btitle"));
							board.setBcontent(rs.getString("bcontent"));
							board.setBwriter(rs.getString("bwriter"));
							board.setBdate(rs.getDate("bdate"));
							board.setBfilename(rs.getString("bfilename"));
							if(rs.getBlob("bfiledata") != null) {
								board.setBfiledata(rs.getBlob("bfiledata"));
							}
							
							//파일로 저장
							Blob blob = board.getBfiledata();
							if(blob != null) {
								InputStream is = blob.getBinaryStream();
								OutputStream os = new FileOutputStream("C:/Temp/" + board.getBfilename());
								is.transferTo(os);
								os.flush();
								os.close();
								is.close();
							}
							System.out.println("------------------------------------------------------------------");
							System.out.println("제목: " + board.getBtitle());
							System.out.println("내용: " + board.getBcontent());
							System.out.println("작성자: " + board.getBwriter());
							System.out.println("작성날짜: " + board.getBdate());
							System.out.println("------------------------------------------------------------------");
						}
						rs.close();
						return true;
			
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		if(conn != null) {
			try {
				conn.close();
				}catch(SQLException e) {}
			}
		}
		return false;
	}
	
	public void clearBoard() {
		Connection conn = null;
		try {
			//JDBC Driver 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//연결하기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe",
					"SYSTEM",
					"oracle"
					);
			//매개변수화된 SQL 문 작성
			String sql = "" +
					"DELETE FROM boards";
			//PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// SQL 문 실행 후, ResultSet을 통해 데이터 읽기
			int rows = pstmt.executeUpdate();
			System.out.println("삭제된 게시글 수:" + rows);
			pstmt.close();
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		if(conn != null) {
			try {
				conn.close();
				}catch(SQLException e) {}
			}
		}
	}

	public void updatePost(int bno) {
		try (Scanner sc = new Scanner(System.in)) {
			Connection conn = null;
			try {
				//JDBC Driver 등록
				Class.forName("oracle.jdbc.OracleDriver");
				
				//연결하기
				conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521/xe",
						"SYSTEM",
						"oracle"
						);
				//매개변수화된 SQL문 작성
				String sql = new StringBuilder()
						.append("UPDATE boards Set ")
						.append("btitle=?, ")
						.append("bcontent=? ")
						.append("WHERE bno=?")
						.toString();
				
				// PreparedStatement 얻기 및 값 지정
				PreparedStatement pstmt = conn.prepareStatement(sql);
				System.out.print("수정할 제목: ");
				String titleinput = sc.nextLine();
				pstmt.setString(1, titleinput);
				System.out.print("수정할 내용: ");
				String contentinput = sc.nextLine();
				pstmt.setString(2, contentinput);
				pstmt.setInt(3, bno); // boards 테이블에 있는 게시물 번호 지정
				
				//SQL 문 실행
				pstmt.executeUpdate();
				System.out.println("성공적으로 글을 수정했습니다.");
				
				//PreparedStatement 닫기
				pstmt.close();
}catch(Exception e) {
			e.printStackTrace();
}finally {
			if(conn != null) {
				try {
					conn.close();
					}catch(SQLException e) {}
				}
			}
		}
	}
	
	public void deletePost(int bno) {
		Connection conn = null;
		try {
			// JDBC Driver 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//연결하기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe",
					"SYSTEM",
					"oracle"
					);
			// 매개변수화된 SQL 문 작성
			String sql = "DELETE FROM boards WHERE bno=?";
			
			//PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			// SQL문 실행
			pstmt.executeUpdate();
			System.out.println("성공적으로 글을 삭제했습니다.");
			
			//PreparedStatement 닫기
			pstmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {}
			}
		}
	}
}
