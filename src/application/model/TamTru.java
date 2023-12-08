package application.model;

import java.sql.Date;

public class TamTru {
    private int maTamTru;
    private int idNhanKhau;
	private int idHoKhau;
    private Date tuNgayDangKy;
	private Date denNgayDangKy;
    private String diaChiTruocChuyenDen;
	
    public int getMaTamTru() {
		return maTamTru;
	}
	public void setMaTamTru(int maTamTru) {
		this.maTamTru = maTamTru;
	}
	public int getIdNhanKhau() {
        return idNhanKhau;
    }
    public void setIdNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }
	public int getIdHoKhau() {
		return idHoKhau;
	}
	public void setIdHoKhau(int idHoKhau) {
		this.idHoKhau = idHoKhau;
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
	public String getDiaChiTruocChuyenDen() {
		return diaChiTruocChuyenDen;
	}
	public void setDiaChiTruocChuyenDen(String diaChiTruocChuyenDen) {
		this.diaChiTruocChuyenDen = diaChiTruocChuyenDen;
	}
	
	
	public TamTru(int maTamTru, int idNhanKhau, int idHoKhau, Date tuNgayDangKy, Date denNgayDangKy,
			String diaChiTruocChuyenDen) {
		this.maTamTru = maTamTru;
		this.idNhanKhau = idNhanKhau;
		this.idHoKhau = idHoKhau;
		this.tuNgayDangKy = tuNgayDangKy;
		this.denNgayDangKy = denNgayDangKy;
		this.diaChiTruocChuyenDen = diaChiTruocChuyenDen;
	}
	public TamTru(int idNhanKhau, int idHoKhau, Date tuNgayDangKy, Date denNgayDangKy, String diaChiTruocChuyenDen) {
		this.idNhanKhau = idNhanKhau;
		this.idHoKhau = idHoKhau;
		this.tuNgayDangKy = tuNgayDangKy;
		this.denNgayDangKy = denNgayDangKy;
		this.diaChiTruocChuyenDen = diaChiTruocChuyenDen;
	}
	public TamTru() {
		super();
	}

}
