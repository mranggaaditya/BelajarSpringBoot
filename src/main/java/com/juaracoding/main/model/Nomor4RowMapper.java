package com.juaracoding.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Nomor4RowMapper implements RowMapper<Nomor4> {
	
public Nomor4 mapRow(ResultSet rs, int rowNum) throws SQLException{
		
		Nomor4 nomor4 = new Nomor4();
		nomor4.setDEPARTMENT(rs.getString("DEPARTMENT"));
		nomor4.setJumlah_pegawai(rs.getInt("jumlah_pegawai"));
		return nomor4;

	}


}
