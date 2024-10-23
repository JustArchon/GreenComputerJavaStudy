package SQL;

import java.sql.Blob;
import java.util.Date;

public class Board {
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public String getBwriter() {
		return bwriter;
	}
	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	public String getBfilename() {
		return bfilename;
	}
	public void setBfilename(String bfilename) {
		this.bfilename = bfilename;
	}
	public Blob getBfiledata() {
		return bfiledata;
	}
	public void setBfiledata(Blob bfiledata) {
		this.bfiledata = bfiledata;
	}
	private Date bdate;
	private String bfilename;
	private Blob bfiledata;
	
	@Override
	public String toString() {
        return String.format("게시글번호 : %d, 제목 : %s, 내용: %s, 작성자: %s, 작성날짜: %s, 첨부사진: %s", this.bno, this.btitle, this.bcontent, this.bwriter, this.bdate, this.bfilename);
    }

}
