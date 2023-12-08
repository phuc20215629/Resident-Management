package application.model;

import java.sql.Date;

public class TamVang {
    private int maTamVang;
    private int id;
    private Date tuNgayDangKy;
	private Date denNgayDangKy;
	private String diaChiChuyenDen;
    
	public int getMaTamVang() {
		return maTamVang;
	}
	public void setMaTamVang(int maTamVang) {
		this.maTamVang = maTamVang;
	}
	public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
	
	public TamVang(int maTamVang, int id, Date tuNgayDangKy, Date denNgayDangKy, String diaChi) {
		this.maTamVang = maTamVang;
		this.id = id;
		this.tuNgayDangKy = tuNgayDangKy;
		this.denNgayDangKy = denNgayDangKy;
		this.diaChiChuyenDen = diaChi;
	}
	public TamVang(int id, Date tuNgayDangKy, Date denNgayDangKy, String diaChi) {
		this.id = id;
		this.tuNgayDangKy = tuNgayDangKy;
		this.denNgayDangKy = denNgayDangKy;
		this.diaChiChuyenDen = diaChi;
	}
	public TamVang() {
		super();
	}
}
