package GradeManagement;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import SQL.Board;

public class GradeDAO {
	public void getBoardList() throws Exception {
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
					"FROM boards";
			//PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// SQL 문 실행 후, ResultSet을 통해 데이터 읽기
						ResultSet rs = pstmt.executeQuery();
						while(rs.next()) {
//							Grade grade = new Grade();
//							board.setBno(rs.getInt("bno"));
//							board.setBtitle(rs.getString("btitle"));
//							board.setBcontent(rs.getString("bcontent"));
//							board.setBwriter(rs.getString("bwriter"));
//							board.setBdate(rs.getDate("bdate"));
//							board.setBfilename(rs.getString("bfilename"));
//							if(rs.getBlob("bfiledata") != null) {
//								board.setBfiledata(rs.getBlob("bfiledata"));
//							}
//							
//							gradelist.add(grade);
						}
						rs.close();
			
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
	
	public void grade_insert(String subject, String score, String studentName, String teacherID) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			Scanner sc = new Scanner(System.in);
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","SYSTEM","oracle");
			
			//매개변수화된 호출문 작성과 CallableStatement 얻기
			String sql = "{call GRADE_INSERT(?, ?, ?, ?)}";
			CallableStatement cstmt = conn.prepareCall(sql);

			//값 지정 및 리턴 타입 지정
			cstmt.setString(1, subject);
			cstmt.setInt(2, Integer.parseInt(score));
			cstmt.setString(3, studentName);
			cstmt.setString(4, teacherID);
			
			//프로시저 실행 및 리턴값 얻기
			cstmt.execute();
			System.out.println("성적입력을 성공했습니다.");
			// CallableStatement 닫기
			cstmt.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {}
			}
		}
	}
}
