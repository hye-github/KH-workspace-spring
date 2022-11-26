package kh.spring.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BoardDTO {
	private int seq;
	private String writer;
	private String title;
	private String contents;
	private Timestamp write_date;
	private int view_count;
	
	public BoardDTO(int seq, String writer, String title, String contents, Timestamp write_date, int view_count) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.write_date = write_date;
		this.view_count = view_count;
	}
	public BoardDTO() {
		super();
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Timestamp getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	
	
	public String getFormedDate() {

		long writeTime = this.write_date.getTime();
		long currentTime = System.currentTimeMillis();

		// System.currentTimeMillis()
		// 1970년 1월 1일부터 경과한 시간을 long값으로 리턴 1/1000 초값을리턴
		long timeGap = currentTime - writeTime;
//		System.out.println(timeGap);
		
		
		SimpleDateFormat sdf = null;
		sdf = new SimpleDateFormat("YYYY년 MM월 dd일");
		
		if (timeGap < 60 * 1000) {
			return "1분 이내";
		} else if (timeGap < 300 * 1000) {
			return "5분 이내";
		} else if (timeGap < 3600 * 1000) {
			return "1시간 이내";
			
//		} else if (timeGap < 86400 * 1000) { // 24시간 이내
		} else if (sdf.format(writeTime).equals(sdf.format(currentTime))) {	// == 은 안됨.. 주소값 비교라.. 하
			sdf = new SimpleDateFormat("HH시 mm분");
			return sdf.format(writeTime);
			
		} else {
			sdf = new SimpleDateFormat("MM월 dd일");
			return sdf.format(writeTime);
		}
		
	}
	
	public String getDetailSubFormedDate() {
		long writeTime = this.write_date.getTime();
		long currentTime = System.currentTimeMillis();
		long timeGap = currentTime - writeTime;
		
		if (timeGap < 86400 * 1000) {
			SimpleDateFormat sdf = new SimpleDateFormat("HH시 mm분");
			return sdf.format(writeTime);
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일");
			return sdf.format(writeTime);
		}
		
	}
	
	public String getDetailFormedDate() {
		long writeTime = this.write_date.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY년 MM월 dd일 hh시 mm분");
		return sdf.format(writeTime);

	}
	
	
	private int commentNum;

	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	

}
