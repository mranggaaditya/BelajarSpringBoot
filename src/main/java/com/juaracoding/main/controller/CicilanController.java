package com.juaracoding.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.main.model.Cicilan;
import com.juaracoding.main.model.CicilanRowMapper;

@RestController
@RequestMapping("/cicilan")
public class CicilanController {
	
	@Autowired
	JdbcTemplate jdbc;
	
//	public int insertCicilan(Cicilan cicilan) {
//		return jdbc.update("insert into cicilan(id,nik,start_date,end_date) values (" + cicilan.getId() + ",'"
//				+ cicilan.getNik() + "','" + cicilan.getStart_date() + "','" + cicilan.getEnd_date() + "')");
//	}
	
	
	
	
	
	public int insertCicilan() {
		return jdbc.update("CALL ulangBulan ('2020-01-01',20000000,1.2,15)");
	}
	
	public List<Cicilan> getCicilan() {

		String sql = "Select * from dummy";

		// Meng-instance object baru dari object List
		List<Cicilan> cicilan = jdbc.query(sql, new CicilanRowMapper());
//		System.out.println(absensi);
		return cicilan;
		
	}
	
	@PostMapping("")
//	List<Cicilan> post(@Param("dateFrom"),@Param("platfon"),@Param("bunga"),@Param("lamapinjam") String dateFrom, Integer platfon, Integer bunga, Integer lamapinjam);
	public String add() {
		if (this.insertCicilan() >= 1) {
			return "Insert data berhasil";
		} else {
			return "Insert data gagal";
		}
	}
	
	@GetMapping("/data")
	public List<Cicilan> list() {
		return getCicilan();
	}
	

}
