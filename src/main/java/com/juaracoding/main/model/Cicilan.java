package com.juaracoding.main.model;

public class Cicilan {
	private int angsuranKe;
	private String tanggal;
	private double totalAngsuran;
	private double angsuranPokok;
	private double angsuranBunga;
	private double sisaPinjaman;
	
	public int getAngsuranKe() {
		return angsuranKe;
	}
	public void setAngsuranKe(int angsuranKe) {
		this.angsuranKe = angsuranKe;
	}
	public String getTanggal() {
		return tanggal;
	}
	public void setTanggal(String tanggal) {
		this.tanggal = tanggal;
	}
	public double getTotalAngsuran() {
		return totalAngsuran;
	}
	public void setTotalAngsuran(double totalAngsuran) {
		this.totalAngsuran = totalAngsuran;
	}
	public double getAngsuranPokok() {
		return angsuranPokok;
	}
	public void setAngsuranPokok(double angsuranPokok) {
		this.angsuranPokok = angsuranPokok;
	}
	public double getAngsuranBunga() {
		return angsuranBunga;
	}
	public void setAngsuranBunga(double angsuranBunga) {
		this.angsuranBunga = angsuranBunga;
	}
	public double getSisaPinjaman() {
		return sisaPinjaman;
	}
	public void setSisaPinjaman(double sisaPinjaman) {
		this.sisaPinjaman = sisaPinjaman;
	}
}
