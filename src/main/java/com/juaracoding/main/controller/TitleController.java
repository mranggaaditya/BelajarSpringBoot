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

import com.juaracoding.main.model.Title;
import com.juaracoding.main.model.TitleRowMapper;

@RestController
@RequestMapping("/title")
public class TitleController {
	
	@Autowired
	JdbcTemplate jdbc;
		
	@GetMapping("")
	public String home() {
		return "Tidak ada apa-apa disini, bisa dengan title/data jika ingin melihat datanya. "
				+ "Jika ingin mengupdate data bisa dengan cara /title/{WORKER_REF_ID}. "
				+ "Jika ingin menghapus data bisa dengan cara /title/{WORKER_REF_ID}";
	}
		
	public List<Title> getTitle() {

		String sql = "Select * from title";

		// Meng-instance object baru dari object List
		List<Title> title = jdbc.query(sql, new TitleRowMapper());
		System.out.println(title);
		return title;
			
		}
		
		public int insertTitle(Title title) {
			return jdbc.update("insert into title(WORKER_REF_ID,WORKER_TITLE,AFFECTED_ROOM) values ("
					+ title.getWORKER_REF_ID() + ",'" + title.getWORKER_TITLE() + "','" + title.getAFFECTED_FROM() + "')");
		}

		public int updateTitle(int WORKER_REF_ID, Title title) {

			return jdbc.update("UPDATE title SET `WORKER_REF_ID`=" + title.getWORKER_REF_ID() + ",`WORKER_TITLE`='"
					+ title.getWORKER_TITLE() + "',`AFFECTED_FROM`='" + title.getAFFECTED_FROM() + "' WHERE WORKER_REF_ID = "
					+ title.getWORKER_REF_ID() + "");
		}

		public int deleteTitle(int WORKER_REF_ID) {
			return jdbc.update("DELETE FROM `title` WHERE `WORKER_REF_ID` = " + WORKER_REF_ID + ";");
		}

		
		@PostMapping("/")
		public String add(@RequestBody Title title) {
			if (this.insertTitle(title) == 1) {
				return "Insert data berhasil";
			} else {
				return "Insert data gagal";
			}
		}

		@DeleteMapping("/{WORKER_REF_ID}")
		public String delete(@PathVariable int WORKER_REF_ID) {
			if (this.deleteTitle(WORKER_REF_ID) == 1) {
				return "Berhasil Dihapus";
			} else {
				return "Data tidak ada";
			}
		}

		@GetMapping("/data")
		public List<Title> list() {
			return getTitle();
		}
		
		
		@PutMapping("/{WORKER_REF_ID}")
	    public ResponseEntity<?> update(@RequestBody Title title, @PathVariable int WORKER_REF_ID) {
		 try {
	            updateTitle(WORKER_REF_ID, title);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
		 
	 }

	
}
