package com.juaracoding.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CicilanRowMapper implements RowMapper<Cicilan>{

	@Override
	public Cicilan mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cicilan cicilan = new Cicilan();
		cicilan.setAngsuranKe(rs.getInt("angsuranke"));
		cicilan.setTanggal(rs.getString("tanggal"));
		cicilan.setTotalAngsuran(rs.getDouble("totalAngsuran"));
		cicilan.setAngsuranPokok(rs.getDouble("angsuranPokok"));
		cicilan.setAngsuranBunga(rs.getDouble("angsuranBunga"));
		cicilan.setSisaPinjaman(rs.getDouble("sisaPinjaman"));
		return cicilan;
	}
}
