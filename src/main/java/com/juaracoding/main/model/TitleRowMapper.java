package com.juaracoding.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TitleRowMapper implements RowMapper<Title> {
	
	public Title mapRow(ResultSet rs, int rowNum) throws SQLException{
		
		Title title = new Title();
		
		title.setWORKER_REF_ID(rs.getInt("WORKER_REF_ID"));
		title.setWORKER_TITLE(rs.getString("WORKER_TITLE"));
		title.setAFFECTED_FROM(rs.getString("AFFECTED_FROM"));
		return title;

	}

}
