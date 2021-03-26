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

import com.juaracoding.main.model.Nomor2;
import com.juaracoding.main.model.Nomor2RowMapper;
import com.juaracoding.main.model.Nomor3;
import com.juaracoding.main.model.Nomor3RowMapper;
import com.juaracoding.main.model.Nomor4;
import com.juaracoding.main.model.Nomor4RowMapper;
import com.juaracoding.main.model.Worker;
import com.juaracoding.main.model.WorkerRowMapper;

@RestController
@RequestMapping("/worker")
public class WorkerController {
	@Autowired
	JdbcTemplate jdbc;
		
	@GetMapping("")
	public String home() {
		return "Tidak ada apa-apa disini, bisa dengan worker/data jika ingin melihat datanya. "
				+ "Jika ingin mengupdate data bisa dengan cara /worker/{WORKER_ID}. "
				+ "Jika ingin menghapus data bisa dengan cara /worker/{WORKER_ID}. "
				+ "Jika ingin ke data ujian bisa dengan cara /worker/(nomor soal ujiannya). "
				+ "Contoh : worker/2";
	}
		
	public List<Worker> getWorker() {

		String sql = "Select * from worker";

		// Meng-instance object baru dari object List
		List<Worker> worker = jdbc.query(sql, new WorkerRowMapper());
		System.out.println(worker);
		return worker;
			
		}

		public List<Worker> getWorkerid(String WORKER_ID) {

			String sql = "Select * from worker where WORKER_ID = '" + WORKER_ID + "' ";

			// Meng-instance object baru dari object List
			List<Worker> worker = jdbc.query(sql, new WorkerRowMapper());

			return worker;

		}
		
		public List<Nomor2> getnomor2() {

			String sql = "SELECT * FROM worker ORDER BY SALARY DESC LIMIT 5";

			List<Nomor2> nomor2 = jdbc.query(sql, new Nomor2RowMapper());
			System.out.println(nomor2);
			return nomor2;
				
		}
		
		public List<Nomor3> getnomor3() {

			String sql = "SELECT * FROM worker WHERE SALARY IN (SELECT SALARY FROM worker GROUP BY SALARY HAVING COUNT(*) > 1)";

			List<Nomor3> nomor3 = jdbc.query(sql, new Nomor3RowMapper());
			System.out.println(nomor3);
			return nomor3;
				
		}
		
		public List<Nomor4> getnomor4() {

			String sql = "SELECT DEPARTMENT, COUNT(*) AS jumlah_pegawai FROM Worker GROUP BY DEPARTMENT";

			List<Nomor4> nomor4 = jdbc.query(sql, new Nomor4RowMapper());
			System.out.println(nomor4);
			return nomor4;
				
		}
		
		public int insertWorker(Worker worker) {
			return jdbc.update(
					"insert into worker(WORKER_ID,FIRST_NAME,LAST_NAME,SALARY,JOINING_DATE,DEPARTMENT) values ('"
							+ worker.getWORKER_ID() + "','" + worker.getFIRST_NAME() + "','" + worker.getLAST_NAME()
							+ "'," + worker.getSALARY() + ",'" + worker.getJOINING_DATE() + "','"
							+ worker.getDEPARTMENT() + "')");
		}

		public int updateWorker(String WORKER_ID, Worker worker) {

			return jdbc.update("UPDATE worker SET `FIRST_NAME`='" + worker.getFIRST_NAME() + "',`LAST_NAME`='"
					+ worker.getLAST_NAME() + "',`SALARY`=" + worker.getSALARY() + ", `JOINING_DATE`='"
					+ worker.getJOINING_DATE() + "',`DEPARTMENT`='" + worker.getDEPARTMENT() + "' WHERE WORKER_ID = '"
					+ worker.getWORKER_ID() + "'");
		}

		public int deleteWorker(String WORKER_ID) {
			return jdbc.update("DELETE FROM `worker` WHERE `WORKER_ID` = '" + WORKER_ID + "';");
		}

		
		@PostMapping("/")
		public String add(@RequestBody Worker worker) {
			if (this.insertWorker(worker) == 1) {
				return "Insert data berhasil";
			} else {
				return "Insert data gagal";
			}
		}

		@DeleteMapping("/{WORKER_ID}")
		public String delete(@PathVariable String WORKER_ID) {
			if (this.deleteWorker(WORKER_ID) == 1) {
				return "Berhasil Dihapus";
			} else {
				return "Data tidak ada";
			}
		}

		@GetMapping("/data")
		public List<Worker> list() {
			return getWorker();
		}
		
		@GetMapping("/2")
		public List<Nomor2> nomor2() {
			return getnomor2();
		}
		
		@GetMapping("/3")
		public List<Nomor3> nomor3() {
			return getnomor3();
		}
		
		@GetMapping("/4")
		public List<Nomor4> nomor4() {
			return getnomor4();
		}

		
		@PutMapping("/{WORKER_ID}")
	    public ResponseEntity<?> update(@RequestBody Worker worker, @PathVariable String WORKER_ID) {
		 try {
	            updateWorker(WORKER_ID, worker);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
		 
	 }
}

