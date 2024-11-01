package GradeManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SubjectDAO {
	
	public void getSubjectList() throws Exception {
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
					"SELECT ID, SUBJECT "+
					"FROM subjectTable";
			//PreparedStatement 얻기 및 값 지정
			System.out.println("과목ID\t과목이름");
            System.out.println("-----------------------------------------------");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// SQL 문 실행 후, ResultSet을 통해 데이터 읽기
						ResultSet rs = pstmt.executeQuery();
						while(rs.next()) {
							String ID = rs.getString("ID");
							String SUBJECT = rs.getString("SUBJECT");
							// 학생별 요약 성적 출력
			                System.out.printf("%s\t%s\n", 
			                		ID, SUBJECT);

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
	
	public void insertSubject(String SUBID, String SUBNAME) throws Exception{
		Connection conn = null;
		try (Scanner sc = new Scanner(System.in)) {
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
						"INSERT INTO subjectTable (SUBID, SUBNAME)" + 
						"VALUES (?, ?)";
			//PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"bno"});
			pstmt.setString(1, SUBID);
			pstmt.setString(2, SUBNAME);
			
			//SQL 문 실행
			pstmt.executeUpdate();
			System.out.println("성공적으로 과목을 추가했습니다.");
				
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
	
	public void delete_subject(String subID) {
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
			String sql = "DELETE FROM subjectTable WHERE ID=?";
			
			//PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subID);
			
			// SQL문 실행
			pstmt.executeUpdate();
			System.out.println("성공적으로 과목을 삭제했습니다.");
			
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
	
	public void edit_teacherSubject(String teacherID, String subjectName) {
		Scanner sc = new Scanner(System.in);
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
						.append("UPDATE TEACHERTABLE Set ")
						.append("position = ? ")
						.append("WHERE id = ?")
						.toString();
			
			// PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, subjectName);
			pstmt.setString(2, teacherID);

			
			//SQL 문 실행
			pstmt.executeUpdate();
			System.out.println("성공적으로 과목을 수정했습니다.");
			
			//PreparedStatement 닫기
			pstmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		if(conn != null) {
			try {
				conn.close();
				}catch(SQLException e) {}
			}
		}
	}
}
