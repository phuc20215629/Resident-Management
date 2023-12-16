package application.model;

import java.sql.Date;

public class KhoanPhi {
    private int maKhoanPhi;
    private String tenKhoanPhi;
    private String loaiPhi;
    private int soTien;
    private Date tuNgay;
    private Date denNgay;
    private String trangThai;
    private String dinhMucStr;

    public KhoanPhi(String tenKhoanPhi, String loaiPhi, int soTien, Date tuNgay, Date denNgay, String trangThai) {
        this.tenKhoanPhi = tenKhoanPhi;
        this.loaiPhi = loaiPhi;
        this.soTien = soTien;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.trangThai = trangThai;
        if(loaiPhi.equals("Bắt buộc theo người")) {
            this.dinhMucStr = Integer.toString(soTien) + "/người";
        } else if(loaiPhi.equals("Bắt buộc theo hộ")) {
            this.dinhMucStr = Integer.toString(soTien) + "/hộ";
        } else {
            this.dinhMucStr = "Không giới hạn";
        }
    }

    public KhoanPhi(int maKhoanPhi, String tenKhoanPhi, String loaiPhi, int soTien, Date tuNgay, Date denNgay, String trangThai) {
        this.maKhoanPhi = maKhoanPhi;
        this.tenKhoanPhi = tenKhoanPhi;
        this.loaiPhi = loaiPhi;
        this.soTien = soTien;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.trangThai = trangThai;
        if(loaiPhi.equals("Bắt buộc theo người")) {
            this.dinhMucStr = Integer.toString(soTien) + "/người";
        } else if(loaiPhi.equals("Bắt buộc theo hộ")) {
            this.dinhMucStr = Integer.toString(soTien) + "/hộ";
        } else {
            this.dinhMucStr = "Không giới hạn";
        }
    }

    public String getDinhMucStr() {
        if(loaiPhi.equals("Bắt buộc theo người")) {
            this.dinhMucStr = Integer.toString(soTien) + "/người";
        } else if(loaiPhi.equals("Bắt buộc theo hộ")) {
            this.dinhMucStr = Integer.toString(soTien) + "/hộ";
        } else {
            this.dinhMucStr = "Không giới hạn";
        }
        return dinhMucStr;
    }

    public void setDinhMucStr(String dinhMucStr) {
        this.dinhMucStr = dinhMucStr;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public Date getDenNgay() {
        return denNgay;
    }

    public Date getTuNgay() {
        return tuNgay;
    }

    public int getMaKhoanPhi() {
        return maKhoanPhi;
    }

    public String getTenKhoanPhi() {
        return tenKhoanPhi;
    }

    public String getLoaiPhi() {
        return loaiPhi;
    }

    public int getSoTien() {
        return soTien;
    }

    @Override
    public String toString() {
        return "KhoanPhi{" + "maKhoanPhi = " + maKhoanPhi + ", tenKhoanPhi = " + tenKhoanPhi + ", loaiPhi = " + loaiPhi + ", soTien = " + soTien + '}';
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void setMaKhoanPhi(int maKhoanPhi) {
        this.maKhoanPhi = maKhoanPhi;
    }

    public void setTenKhoanPhi(String tenKhoanPhi) {
        this.tenKhoanPhi = tenKhoanPhi;
    }

    public void setLoaiPhi(String loaiPhi) {
        this.loaiPhi = loaiPhi;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public void setDenNgay(Date denNgay) {
        this.denNgay = denNgay;
    }

    public void setTuNgay(Date tuNgay) {
        this.tuNgay = tuNgay;
    }
}