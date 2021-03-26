package com.juaracoding.main.controller;

import java.util.List;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.main.model.Biodata;
import com.juaracoding.main.model.BiodataRowMapper;



@RestController
@RequestMapping("/biodata")
public class BiodataController {

	@Autowired
	JdbcTemplate jdbc;
	
	@GetMapping("")
	public String home() {
		return "";
	}
	
	public List<Biodata> getBiodata() {

		String sql = "Select * from Biodata";

		List<Biodata> biodata = jdbc.query(sql, new BiodataRowMapper());

		return biodata;

	}

	public int insertBiodata(Biodata biodata) {
		return jdbc.update("insert into biodata(nik,nama,alamat,id_salary) values ('" + biodata.getNik() + "','"
				+ biodata.getNama() + "','" + biodata.getAlamat() + "'," + biodata.getId_salary() + ")");

	}

	// PR
	public int updateBiodata(String nik, Biodata biodata) {

		return jdbc.update("UPDATE biodata SET `nama`='" + biodata.getNama() + "',`alamat`='" + biodata.getAlamat()
				+ "',`id_salary`=" + biodata.getId_salary() + " WHERE nik = '" + biodata.getNik() + "'");

	}

	public int deleteBiodata(String nik) {
		return jdbc.update("DELETE FROM `biodata` WHERE `nik` = '" + nik + "';");
	}

	
	

	 @PostMapping("/")
	    public String add(@RequestBody Biodata biodata) {
			if (this.insertBiodata(biodata) == 1) {
				return "Insert data berhasil";
			} else {
				return "Insert data gagal";
			}
	    }
	 
	 
	 
	 @DeleteMapping("/{nik}")
	    public void delete(@PathVariable String nik) {
		 	deleteBiodata(nik);
	 }
	 
	 
	 @GetMapping("/data")
	    public List<Biodata> list() {
	        return getBiodata();
	    }
	 
	 @PutMapping("/{nik}")
	    public ResponseEntity<?> update(@RequestBody Biodata biodata, @PathVariable String nik) {
		 try {
	            updateBiodata(nik, biodata);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
		 
	 }
	

}