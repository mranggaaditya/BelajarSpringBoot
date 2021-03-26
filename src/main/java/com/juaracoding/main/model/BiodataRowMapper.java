package com.juaracoding.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BiodataRowMapper implements RowMapper<Biodata>{
	public Biodata mapRow(ResultSet rs, int rowNum) throws SQLException{
		Biodata biodata = new Biodata();
		biodata.setNik(rs.getString("nik"));
		biodata.setNama(rs.getString("nama"));
		biodata.setAlamat(rs.getString("alamat"));
		biodata.setId_salary(rs.getInt("id_salary"));
		return biodata;
	}
}
