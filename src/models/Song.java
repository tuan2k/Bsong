package models;

import java.sql.Timestamp;

public class Song {
	private int id;
	private String name;
	private String preview;
	private String detail;
	private Timestamp date_create;
	private String pic;
	private int counter;
	private int cat_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Timestamp getDate_create() {
		return date_create;
	}
	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	public Song(int id, String name, String preview, String detail, Timestamp date_create, String pic, int counter,
			int cat_id) {
		super();
		this.id = id;
		this.name = name;
		this.preview = preview;
		this.detail = detail;
		this.date_create = date_create;
		this.pic = pic;
		this.counter = counter;
		this.cat_id = cat_id;
	}
	public Song(String name, String preview, String detail,String pic,
			int cat_id) {
		super();
		this.name = name;
		this.preview = preview;
		this.detail = detail;
		this.pic = pic;
		this.cat_id = cat_id;
	}
	public Song(int id, String name, String preview, String detail,String pic,
			int cat_id) {
		super();
		this.id = id;
		this.name = name;
		this.preview = preview;
		this.detail = detail;
		this.pic = pic;
		this.cat_id = cat_id;
	}
	public Song() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
