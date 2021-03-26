package com.juaracoding.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.main.model.Absensi;
import com.juaracoding.main.model.AbsensiRowMapper;



@RestController
@RequestMapping("/absensi")
public class AbsensiController {
	@Autowired
	JdbcTemplate jdbc;
	
	@GetMapping("")
	public String home() {
		return "";
	}
	
	public List<Absensi> getAbsensi() {

		String sql = "Select * from absensi";

		// Meng-instance object baru dari object List
		List<Absensi> absensi = jdbc.query(sql, new AbsensiRowMapper());
		System.out.println(absensi);
		return absensi;
		
	}

	public List<Absensi> getNik(String nik) {

		String sql = "Select * from absensi where nik = '" + nik + "' ";

		// Meng-instance object baru dari object List
		List<Absensi> absensi = jdbc.query(sql, new AbsensiRowMapper());

		return absensi;

	}

	public List<Absensi> getStartYear(String start_date) {

		String sql = "Select * from absensi where year(start_date) = '" + start_date + "' ";

		// Meng-instance object baru dari object List
		List<Absensi> absensi = jdbc.query(sql, new AbsensiRowMapper());

		return absensi;

	}

	public List<Absensi> getEndYear(String end_date) {

		String sql = "Select * from absensi where year(end_date) = '" + end_date + "' ";

		// Meng-instance object baru dari object List
		List<Absensi> absensi = jdbc.query(sql, new AbsensiRowMapper());

		return absensi;	

	}
	
	public int insertAbsensi(Absensi absensi) {
//		System.out.println(absensi.getStart_date());
		return jdbc.update("insert into Absensi(id,nik,start_date,end_date) values (" + absensi.getId() + ",'"
				+ absensi.getNik() + "','" + absensi.getStart_date() + "','" + absensi.getEnd_date() + "')");
	}

	public int updateAbsensi(String nik, Absensi absensi) {

		return jdbc
				.update("UPDATE `absensi` SET `id`=" + absensi.getId() + "  WHERE `nik` = '" + absensi.getNik() + "';");

	}

	public int deleteAbsensi(String nik) {
		return jdbc.update("DELETE FROM `Absensi` WHERE `nik` = '" + nik + "';");
	}

	/*
	 * @RequestBody maksudnya untuk menginput data dalam bentuk objek sesuai engan 
	 */
	
	@PostMapping("/")
	public String add(@RequestBody Absensi absensi) {
		if (this.insertAbsensi(absensi) == 1) {
			return "Insert data berhasil";
		} else {
			return "Insert data gagal";
		}
	}

	@DeleteMapping("/{nik}")
	public String delete(@PathVariable String nik) {
		if (this.deleteAbsensi(nik) == 1) {
			return "Berhasil Dihapus";
		} else {
			return "Data tidak ada";
		}
	}

	@GetMapping("/data")
	public List<Absensi> list() {
		return getAbsensi();
	}
	
	
	@GetMapping("/{nik}")
	public List<Absensi> cariNik(@PathVariable String nik) {
		return getNik(nik);
	}

	// Tugas
	@GetMapping("/tahunawal/{start_date}")
	public List<Absensi> cariTahunAwal(@PathVariable String start_date) {
		return getStartYear(start_date);
	}

	@GetMapping("/tahunakhir/{end_date}")
	public List<Absensi> cariTahunAkhir(@PathVariable String end_date) {
		return getEndYear(end_date);
	}

//	@PutMapping("/{nik}")
//	public ResponseEntity<?> update(@RequestBody Absensi Absensi, @PathVariable String nik) {
//		try {
//			updateAbsensi(nik, Absensi);
//			return new ResponseEntity<>(HttpStatus.OK);
//		} catch (NoSuchElementException e) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//
//	}
	
	@PutMapping("/{nik}")
	public String update(@RequestBody Absensi Absensi, @PathVariable String nik) {
			updateAbsensi(nik, Absensi);
			return "Data telah berhasil di update";
	}
	public static void main (String args[]) {
		AbsensiController test = new AbsensiController();
		System.out.println(test.getAbsensi());
		System.out.println(test.list());
	}
}


