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

import com.juaracoding.main.model.Bonus;
import com.juaracoding.main.model.BonusRowMapper;

@RestController
@RequestMapping("/bonus")
public class BonusController {
	
	@Autowired
	JdbcTemplate jdbc;
		
	@GetMapping("")
	public String home() {
		return "Tidak ada apa-apa disini, bisa dengan bonus/data jika ingin melihat datanya. "
				+ "Jika ingin mengupdate data bisa dengan cara /bonus/{BONUS_AMOUNT}. "
				+ "Jika ingin menghapus data bisa dengan cara /bonus/{BONUS_AMOUNT}";
		
	}
		
	public List<Bonus> getBonus() {

		String sql = "Select * from bonus";

		// Meng-instance object baru dari object List
		List<Bonus> bonus = jdbc.query(sql, new BonusRowMapper());
		System.out.println(bonus);
		return bonus;
			
		}
		
		public int insertBonus(Bonus bonus) {
			return jdbc.update("insert into bonus(WORKER_REF_ID,BONUS_DATE,BONUS_AMOUNT) values ("
					+ bonus.getWORKER_REF_ID() + ",'" + bonus.getBONUS_DATE() + "'," + bonus.getBONUS_AMOUNT() + ")");
		}

		public int updateBonus(int BONUS_AMOUNT, Bonus bonus) {

			return jdbc.update("UPDATE bonus SET `WORKER_REF_ID`=" + bonus.getWORKER_REF_ID() + ",`BONUS_DATE`='"
					+ bonus.getBONUS_DATE() + "',`BONUS_AMOUNT`=" + bonus.getBONUS_AMOUNT() + " WHERE BONUS_AMOUNT = "
					+ bonus.getBONUS_AMOUNT() + "");
		}

		public int deleteBonus(int BONUS_AMOUNT) {
			return jdbc.update("DELETE FROM `bonus` WHERE `BONUS_AMOUNT` = " + BONUS_AMOUNT + ";");
		}

		
		@PostMapping("/")
		public String add(@RequestBody Bonus bonus) {
			if (this.insertBonus(bonus) == 1) {
				return "Insert data berhasil";
			} else {
				return "Insert data gagal";
			}
		}

		@DeleteMapping("/{BONUS_AMOUNT}")
		public String delete(@PathVariable int BONUS_AMOUNT) {
			if (this.deleteBonus(BONUS_AMOUNT) == 1) {
				return "Berhasil Dihapus";
			} else {
				return "Data tidak ada";
			}
		}

		@GetMapping("/data")
		public List<Bonus> list() {
			return getBonus();
		}
		
		
		@PutMapping("/{BONUS_AMOUNT}")
	    public ResponseEntity<?> update(@RequestBody Bonus bonus, @PathVariable int BONUS_AMOUNT) {
		 try {
	            updateBonus(BONUS_AMOUNT, bonus);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
		 
	 }

}
