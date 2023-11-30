package application.model;

import java.sql.Date;

public class GiaoDich {
    private int maGiaoDich;
    private int maKhoanPhi;
    private int soTien;
    private int maHoKhau;
    private Date thoiGian;
    private String tenKhoanPhi;

    public GiaoDich(int maGiaoDich, int maKhoanPhi, int soTien, int maHoKhau, Date thoiGian, String tenKhoanPhi) {
        this.maGiaoDich = maGiaoDich;
        this.maKhoanPhi = maKhoanPhi;
        this.soTien = soTien;
        this.maHoKhau = maHoKhau;
        this.thoiGian = thoiGian;
        this.tenKhoanPhi = tenKhoanPhi;
    }

    public GiaoDich(int maKhoanPhi, int soTien, int maHoKhau, Date thoiGian, String tenKhoanPhi) {
        this.maKhoanPhi = maKhoanPhi;
        this.soTien = soTien;
        this.maHoKhau = maHoKhau;
        this.thoiGian = thoiGian;
        this.tenKhoanPhi = tenKhoanPhi;
    }

    public GiaoDich(int maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
    }

    public String getTenKhoanPhi() {
        return tenKhoanPhi;
    }
    
    public int getMaGiaoDich() {
        return maGiaoDich;
    }

    public int getMaKhoanPhi() {
        return maKhoanPhi;
    }

    public int getSoTien() {
        return soTien;
    }

    public int getMaHoKhau() {
        return maHoKhau;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setMaGiaoDich(int maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
    }

    public void setMaKhoanPhi(int maKhoanPhi) {
        this.maKhoanPhi = maKhoanPhi;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public void setMaHoKhau(int maHoKhau) {
        this.maHoKhau = maHoKhau;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public void setTenKhoanPhi(String tenKhoanPhi) {
        this.tenKhoanPhi = tenKhoanPhi;
    }

    


    
    
}