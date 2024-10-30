package GradeManagement;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
	
	public void getPersonalScores(String loginID) {
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
			String gradesSql = 
					"SELECT STUDENTNAME, SUBJECT, SCORE " +
					"FROM gradeTable " +
					"WHERE STUDENTID = ? " +
					"ORDER BY SUBJECT";
			String summarySql = 
					"SELECT STUDENTNAME, SUM(SCORE) AS TOTAL_SCORE, " +
					"AVG(SCORE) AS AVG_SCORE, " +
					"RANK() OVER (ORDER BY SUM(SCORE) DESC) AS RANK " +
					"FROM gradeTable " + 
					"WHERE STUDENTID = ? " +
					"GROUP BY STUDENTNAME";
			
			//PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmtG = conn.prepareStatement(gradesSql);
			PreparedStatement pstmtS = conn.prepareStatement(summarySql);
			
			pstmtG.setString(1, loginID);
			pstmtS.setString(1, loginID);
			
            System.out.println("\n성적 요약");
            System.out.println("----------------");
			ResultSet rsS = pstmtS.executeQuery();
			while(rsS.next()) {
				String studentName = rsS.getString("STUDENTNAME");
				int totalScore = rsS.getInt("TOTAL_SCORE");
				double avgScore = rsS.getDouble("AVG_SCORE");
				int rank = rsS.getInt("RANK");
				
				System.out.printf("학생명: %s\n총점: %d\n평균: %.2f\n등수: %d\n", 
                        studentName, totalScore, avgScore, rank);
			}
			System.out.println("과목\t점수");
            System.out.println("---------------------------------------------------");
			// SQL 문 실행 후, ResultSet을 통해 데이터 읽기
						ResultSet rsG = pstmtG.executeQuery();
						while(rsG.next()) {
							String subject = rsG.getString("SUBJECT");
							int score = rsG.getInt("SCORE");
							System.out.printf("%s\t%d\n", subject, score);
						}
						


						rsS.close();
						rsG.close();
			
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
	
	public void getStudentGrade() {
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
			String gradesSql = 
					"SELECT STUDENTNAME, SUBJECT, SCORE " +
					"FROM gradeTable ORDER BY STUDENTNAME, SUBJECT";
			String summarySql = 
					"SELECT STUDENTNAME, SUM(SCORE) AS TOTAL_SCORE, " +
					"AVG(SCORE) AS AVG_SCORE, " +
					"RANK() OVER (ORDER BY SUM(SCORE) DESC) AS RANK " +
					"FROM gradeTable GROUP BY STUDENTNAME";
			//PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmtG = conn.prepareStatement(gradesSql);
			PreparedStatement pstmtS = conn.prepareStatement(summarySql);
			System.out.println("학생명\t과목\t점수");
            System.out.println("---------------------------------------------------");
            String currentStudentName = "";
			// SQL 문 실행 후, ResultSet을 통해 데이터 읽기
						ResultSet rsG = pstmtG.executeQuery();
						while(rsG.next()) {
							String studentName = rsG.getString("STUDENTNAME");
							String subject = rsG.getString("SUBJECT");
							int score = rsG.getInt("SCORE");
							
							if(!studentName.equals(currentStudentName)) {
								currentStudentName = studentName;
								System.out.println("\n[학생: " + studentName + "]");
							}
							System.out.printf("\t%s\t%d\n", subject, score);
						}
						
						System.out.println("\n학생명\t총점\t평균\t등수");
			            System.out.println("-----------------------------");
						ResultSet rsS = pstmtS.executeQuery();
						while(rsS.next()) {
							String studentName = rsS.getString("STUDENTNAME");
							int totalScore = rsS.getInt("TOTAL_SCORE");
							double avgScore = rsS.getDouble("AVG_SCORE");
							int rank = rsS.getInt("RANK");
							
							// 학생별 요약 성적 출력
			                System.out.printf("%s\t%d\t%.2f\t%d\n", 
			                    studentName, totalScore, avgScore, rank);
						}
						rsS.close();
						rsG.close();
			
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
