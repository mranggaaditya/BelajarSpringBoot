package com.juaracoding.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Nomor2RowMapper implements RowMapper<Nomor2>{
	
public Nomor2 mapRow(ResultSet rs, int rowNum) throws SQLException{
		
		Nomor2 nomor2 = new Nomor2();
		
		nomor2.setWORKER_ID(rs.getString("WORKER_ID"));
		nomor2.setFIRST_NAME(rs.getString("FIRST_NAME"));
		nomor2.setLAST_NAME(rs.getString("LAST_NAME"));
		nomor2.setSALARY(rs.getInt("SALARY"));
		nomor2.setJOINING_DATE(rs.getString("JOINING_DATE"));
		nomor2.setDEPARTMENT(rs.getString("DEPARTMENT"));
		return nomor2;

	}


}
