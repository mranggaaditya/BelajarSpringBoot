package com.juaracoding.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class WorkerRowMapper implements RowMapper<Worker> {
	
	public Worker mapRow(ResultSet rs, int rowNum) throws SQLException{
		
		Worker worker = new Worker();
		
		worker.setWORKER_ID(rs.getString("WORKER_ID"));
		worker.setFIRST_NAME(rs.getString("FIRST_NAME"));
		worker.setLAST_NAME(rs.getString("LAST_NAME"));
		worker.setSALARY(rs.getInt("SALARY"));
		worker.setJOINING_DATE(rs.getString("JOINING_DATE"));
		worker.setDEPARTMENT(rs.getString("DEPARTMENT"));
		return worker;

	}
	

}
