package GradeManagement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountDAO {
	
	public void edit_profile(String id, String password, String name, String email) {
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
					.append("UPDATE BOARD_ACCOUNT Set ")
					.append("password=NVL(NULLIF(?, ''), password), ")
					.append("name=NVL(NULLIF(?, ''), name), ")
					.append("age=NVL(?, age), ")
					.append("email=NVL(NULLIF(?, ''), email), ")
					.append("address=NVL(NULLIF(?, ''), address) ")
					.append("WHERE id = ?")
					.toString();
			
			// PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, name);
//			if(age.isBlank()) {
//				pstmt.setNull(3, java.sql.Types.INTEGER);
//			}else {
//				pstmt.setInt(3, Integer.parseInt(age));
//			}
			pstmt.setString(4, email);
			pstmt.setString(6, id);
			
			//SQL 문 실행
			pstmt.executeUpdate();
			System.out.println("성공적으로 회원정보를 수정했습니다.");
			
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
}
