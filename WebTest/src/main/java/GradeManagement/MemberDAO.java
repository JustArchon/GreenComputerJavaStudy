package GradeManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
	public void getStudentList() throws Exception {
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
					"SELECT name, stulevel, stuclassnum, phonenumber, email "+
					"FROM studentTable";
			//PreparedStatement 얻기 및 값 지정
			System.out.println("\n학생명\t학년\t반\t휴대폰번호\t\t이메일");
            System.out.println("-----------------------------------------------");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// SQL 문 실행 후, ResultSet을 통해 데이터 읽기
						ResultSet rs = pstmt.executeQuery();
						while(rs.next()) {
							String studentName = rs.getString("name");
							int stulevel = rs.getInt("stulevel");
							int stuclassnum = rs.getInt("stuclassnum");
							String phonenumber = rs.getString("phonenumber");
							String email = rs.getString("email");
							// 학생별 요약 성적 출력
			                System.out.printf("%s\t%d\t%d\t%s\t%s\n", 
			                    studentName, stulevel, stuclassnum, phonenumber, email);

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
}
