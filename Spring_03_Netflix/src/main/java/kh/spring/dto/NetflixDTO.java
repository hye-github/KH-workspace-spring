package kh.spring.dto;

import java.sql.Timestamp;

public class NetflixDTO {

	private int seq;
	private String title;
	private String genre;
	private Timestamp reg_date;
	
	
	public NetflixDTO() {
		super();
	}
	
	public NetflixDTO(int seq, String title, String genre, Timestamp reg_date) {
		super();
		this.seq = seq;
		this.title = title;
		this.genre = genre;
		this.reg_date = reg_date;
	}
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	
	
}
