package BoardExample;

import java.sql.*;
import java.util.Scanner;

public class AccountDAO {
	public int page_login(String id, String password) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","SYSTEM","oracle");
			
			// 매개변수화된 호출문 작성과 CallableStatement 얻기
			String sql = "{? = call BOARD_USER_LOGIN(?, ?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			
			//? 값 지정 및 리턴 타입 지정
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setString(2, id);
			cstmt.setString(3, password);
			
			// 함수 실행 및 리턴값 얻기
			cstmt.execute();
			int result = cstmt.getInt(1);
			
			//CallableStatement 닫기
			cstmt.close();
			
			//로그인 결과(Switch Expressions 이용)
			String message = switch(result) {
				case 0 -> "로그인 성공";
				case 1 -> "비밀번호가 틀림";
				default -> "아이디가 존재하지 않음";
			};
			System.out.println(message);
			return result;
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
		return 2;
	}
	public void page_account(String id, String password, String name, String age, String email, String address) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			Scanner sc = new Scanner(System.in);
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","SYSTEM","oracle");
			
			//매개변수화된 호출문 작성과 CallableStatement 얻기
			String sql = "{call BOARD_USER_ACCOUNT(?, ?, ?, ?, ?, ?)}";
			CallableStatement cstmt = conn.prepareCall(sql);

			//값 지정 및 리턴 타입 지정
			cstmt.setString(1, id);
			cstmt.setString(2,  password);
			cstmt.setString(3, name);
			cstmt.setInt(4, Integer.parseInt(age));
			cstmt.setString(5, email);
			cstmt.setString(6, address);
			
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
	public void edit_profile(String id, String password, String name, String age, String email, String address) {
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
			if(age.isBlank()) {
				pstmt.setNull(3, java.sql.Types.INTEGER);
			}else {
				pstmt.setInt(3, Integer.parseInt(age));
			}
			pstmt.setString(4, email);
			pstmt.setString(5, address);
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
	public void delete_account(String id) {
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
			String sql = "DELETE FROM BOARD_ACCOUNT WHERE ID=?";
			
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