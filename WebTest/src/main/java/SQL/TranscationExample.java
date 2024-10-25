package SQL;

import java.sql.*;

public class TranscationExample {
	public static void main(String[] args) throws Exception {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe",
					"SYSTEM",
					"oracle");
			// 트랜잭션 시작 ------------------------
			//자동 커밋 기능 끄기
			conn.setAutoCommit(false);
			// 출금 작업
			String sql1 = "UPDATE accounts SET balance = balance-? WHERE ano =?";
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setInt(1,  10000);
			pstmt1.setString(2, "111-111-1111");
			int rows1 = pstmt1.executeUpdate();
			if(rows1 == 0) throw new Exception("출금되지 않았음");
			pstmt1.close();
			//입금 작업
			String sql2 = "UPDATE accounts SET balance=balance+? WHERE ano=?";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, 10000);
			pstmt2.setString(2,  "222-222-2222");
			int rows2 = pstmt2.executeUpdate();
			if(rows2 == 0) throw new Exception("입금되지 않았음");
			pstmt2.close();
			
			//수동 커밋 -> 모두 성공 처리
			conn.commit();
			System.out.println("계좌 이체 성공");
			// 트랜잭션 종료 ---------------------
		} catch (Exception e){
				try {
					//수동 롤백 -> 모두 실패 처리
					conn.rollback();
				}catch(SQLException e1) {}
				System.out.println("계좌 이체 실패");
				e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch(SQLException e) {}
			}
		}
	}
}
