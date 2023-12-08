package application.model;

import java.sql.Date;

public class TamVang {
    private int maTamVang;
    private int idNhanKhau;
	private int idHoKhau;
    private Date tuNgayDangKy;
	private Date denNgayDangKy;
	private String diaChiChuyenDen;
    
	public int getMaTamVang() {
		return maTamVang;
	}
	public void setMaTamVang(int maTamVang) {
		this.maTamVang = maTamVang;
	}
	public int getIdHoKhau() {
		return idHoKhau;
	}
	public void setIdHoKhau(int idHoKhau) {
		this.idHoKhau = idHoKhau;
	}
	public int getIdNhanKhau() {
		return idNhanKhau;
	}
	public void setIdNhanKhau(int idNhanKhau) {
		this.idNhanKhau = idNhanKhau;
	}
	public Date getDenNgayDangKy() {
		return denNgayDangKy;
	}
	public void setDenNgayDangKy(Date denNgayDangKy) {
		this.denNgayDangKy = denNgayDangKy;
	}
	public Date getTuNgayDangKy() {
		return tuNgayDangKy;
	}
	public void setTuNgayDangKy(Date tuNgayDangKy) {
		this.tuNgayDangKy = tuNgayDangKy;
	}
	public String getDiaChiChuyenDen() {
		return diaChiChuyenDen;
	}
	public void setDiaChiChuyenDen(String diaChiChuyenDen) {
		this.diaChiChuyenDen = diaChiChuyenDen;
	}
	
	public TamVang(int idNhanKhau, int idHoKhau, Date tuNgayDangKy, Date denNgayDangKy, String diaChiChuyenDen) {
		this.idNhanKhau = idNhanKhau;
		this.idHoKhau = idHoKhau;
		this.tuNgayDangKy = tuNgayDangKy;
		this.denNgayDangKy = denNgayDangKy;
		this.diaChiChuyenDen = diaChiChuyenDen;
	}
	public TamVang(int maTamVang, int idNhanKhau, int idHoKhau, Date tuNgayDangKy, Date denNgayDangKy,
			String diaChiChuyenDen) {
		this.maTamVang = maTamVang;
		this.idNhanKhau = idNhanKhau;
		this.idHoKhau = idHoKhau;
		this.tuNgayDangKy = tuNgayDangKy;
		this.denNgayDangKy = denNgayDangKy;
		this.diaChiChuyenDen = diaChiChuyenDen;
	}
	public TamVang() {
		super();
	}
}
