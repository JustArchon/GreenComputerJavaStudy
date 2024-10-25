package BoardExample;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import SQL.Board;

public class BoardListDAO {
	public List<Board> getBoardList() throws Exception {
		Connection conn = null;
		List<Board> boardlist = new ArrayList<>();
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
							boardlist.add(board);
						}
						rs.close();
						return boardlist;
			
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		if(conn != null) {
			try {
				conn.close();
				}catch(SQLException e) {}
			}
		}
		return boardlist;
		
	}
}
