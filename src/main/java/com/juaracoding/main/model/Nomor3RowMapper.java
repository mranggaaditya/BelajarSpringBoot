package com.juaracoding.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Nomor3RowMapper implements RowMapper<Nomor3> {
	
public Nomor3 mapRow(ResultSet rs, int rowNum) throws SQLException{
		
		Nomor3 nomor3 = new Nomor3();
		
		nomor3.setWORKER_ID(rs.getString("WORKER_ID"));
		nomor3.setFIRST_NAME(rs.getString("FIRST_NAME"));
		nomor3.setLAST_NAME(rs.getString("LAST_NAME"));
		nomor3.setSALARY(rs.getInt("SALARY"));
		nomor3.setJOINING_DATE(rs.getString("JOINING_DATE"));
		nomor3.setDEPARTMENT(rs.getString("DEPARTMENT"));
		return nomor3;

	}


}
