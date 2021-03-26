package com.juaracoding.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AbsensiRowMapper implements RowMapper<Absensi>{
	
	/**
	 * Untuk mengiterasi table, kita dapat menggunakan interface
	 * Dimana penggunaan interface tersebut dapat kita pakai dalam penggunaan query untuk menampilkan hasil data dari table
	 * Object class AbsensiRowMapper digunakan untuk me-mapping bentuk table dari suatu database
	 * Menggunakan interface dari RowMapper 
	 * Dalam hal ini, bayangan saya tentang RowMapper adalah sebuah alat untuk scan bentuk table
	 * Digunakan untuk me-mapping object Absensi
	 */
	
	public Absensi mapRow(ResultSet rs, int rowNum) throws SQLException{
		
		//Menginstance dari class Absensi
		Absensi absensi = new Absensi();
		
		/*
		 * Code di bawah ini untuk mengeset nilai2 data member untuk object baru dengan mengambil
		 * kolom dari tabel suatu database
		 */
		
		absensi.setId(rs.getInt("id"));
		absensi.setNik(rs.getString("nik"));
		absensi.setStart_date(rs.getString("start_date"));
		absensi.setEnd_date(rs.getString("end_date"));
		return absensi;
		
		
	}
}
