package kh.spring.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CommentDTO {

	private int seq;
	private String writer;
	private String contents;
	private Timestamp write_date;
	private int parent_seq;
	
	
	public CommentDTO() {
		super();
	}


	public CommentDTO(int seq, String writer, String contents, Timestamp write_date, int parent_seq) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.contents = contents;
		this.write_date = write_date;
		this.parent_seq = parent_seq;
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


	public int getParent_seq() {
		return parent_seq;
	}


	public void setParent_seq(int parent_seq) {
		this.parent_seq = parent_seq;
	}
	
	public String getFormedDate() {
		long writeTime = this.write_date.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY년 MM월 dd일 hh시 mm분");
		return sdf.format(writeTime);

	}
	
}
