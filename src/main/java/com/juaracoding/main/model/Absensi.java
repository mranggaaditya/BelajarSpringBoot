package com.juaracoding.main.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Absensi {
//	private String pattern = "yyyy-MM-dd";
//	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	/**
	 * Object class Absensi berisi method2 untuk set dan get data member bertipe integer dan string
	 * id, nik, start_date, end_date adalah data member 
	 * Dimana nama-nama tersebut sama persis dengan nama kolom di table absensi database latihan
	 */
	private int id;
	private String nik;
	private String start_date;
	private String end_date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

}
