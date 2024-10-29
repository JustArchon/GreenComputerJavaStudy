package GradeManagement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class LoginDAO {
	public int login(String id, String password) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","SYSTEM","oracle");
			
			// 매개변수화된 호출문 작성과 CallableStatement 얻기
			String sql = "{? = call GRADE_USER_LOGIN(?, ?)}";
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
				case 1 -> "학생 로그인 성공";
				case 2 -> "교직원 로그인 성공";
				case 4 -> "비밀번호가 틀림";
				case 5 -> "관리자 로그인 성공";
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
		return 6;
	}
	
	public String getPosition(String id) {
		String position = "";
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
			String sql = "SELECT POSITION FROM teacherTable WHERE ID=?";
			
			//PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
	        if (rs.next()) {
	            position = rs.getString("POSITION");  // 열 이름 또는 인덱스 사용 가능
	        }
			//PreparedStatement 닫기
			rs.close();
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
		return position;
	}
}
