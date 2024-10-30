package GradeManagement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountDAO {
	
	public void edit_profile(String id, String password, String name, String phonenumber, String email, String memberType) {
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
			String sql = "";
			if(memberType.equals("S")) {
				sql = new StringBuilder()
						.append("UPDATE STUDENTTABLE Set ")
						.append("password=NVL(NULLIF(?, ''), password), ")
						.append("name=NVL(NULLIF(?, ''), name), ")
						.append("phonenumber=NVL(NULLIF(?, ''), phonenumber), ")
						.append("email=NVL(NULLIF(?, ''), email) ")
						.append("WHERE id = ?")
						.toString();
			} else {
				sql = new StringBuilder()
						.append("UPDATE TEACHERTABLE Set ")
						.append("password=NVL(NULLIF(?, ''), password), ")
						.append("name=NVL(NULLIF(?, ''), name), ")
						.append("phonenumber=NVL(NULLIF(?, ''), phonenumber), ")
						.append("email=NVL(NULLIF(?, ''), email) ")
						.append("WHERE id = ?")
						.toString();
			}
			
			// PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, password);
			pstmt.setString(2, name);
			pstmt.setString(3, phonenumber);
			pstmt.setString(4, email);
			pstmt.setString(5, id);
//			if(age.isBlank()) {
//				pstmt.setNull(3, java.sql.Types.INTEGER);
//			}else {
//				pstmt.setInt(3, Integer.parseInt(age));
//			}

			
			//SQL 문 실행
			pstmt.executeUpdate();
			System.out.println("성공적으로 개인정보를 수정했습니다.");
			
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
	
	public void page_account(String id, String password, String name, String stulevel, String stuclass, String position, String phoneNum, String email, String memberType) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			Scanner sc = new Scanner(System.in);
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","SYSTEM","oracle");
			
			//매개변수화된 호출문 작성과 CallableStatement 얻기
			String sql = "{call GRADE_USER_CREATE(?, ?, ?, ?, ?, ?, ?, ?,?)}";
			CallableStatement cstmt = conn.prepareCall(sql);

			//값 지정 및 리턴 타입 지정
			cstmt.setString(1, id);
			cstmt.setString(2,  password);
			cstmt.setString(3, name);
			cstmt.setString(4, phoneNum);
			cstmt.setString(5, email);
			cstmt.setInt(6, Integer.parseInt(stulevel));
			cstmt.setInt(7, Integer.parseInt(stuclass));
			cstmt.setString(8, position);
			cstmt.setString(9, memberType);
			
			//프로시저 실행 및 리턴값 얻기
			cstmt.execute();
			System.out.println("회원가입이 완료되었습니다.");
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
	
	public void delete_account(String id, String memberType) {
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
			String sql = "";
			if(memberType.equals("S")) {
				sql = "DELETE FROM STUDENTTABLE WHERE ID=?";
			} else {
				sql = "DELETE FROM TEACHERTABLE WHERE ID=?";
			}
			
			//PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			// SQL문 실행
			pstmt.executeUpdate();
			System.out.println("성공적으로 회원 탈퇴를 완료했습니다.");
			
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
