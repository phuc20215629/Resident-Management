package application.model;

public class KhoanPhi {
    private int maKhoanPhi;
    private String tenKhoanPhi;
    private String loaiPhi;
    private int soTien;

    public KhoanPhi(String tenKhoanPhi, String loaiPhi, int soTien) {
        this.tenKhoanPhi = tenKhoanPhi;
        this.loaiPhi = loaiPhi;
        this.soTien = soTien;
    }

    public KhoanPhi(int maKhoanPhi, String tenKhoanPhi, String loaiPhi, int soTien) {
        this.maKhoanPhi = maKhoanPhi;
        this.tenKhoanPhi = tenKhoanPhi;
        this.loaiPhi = loaiPhi;
        this.soTien = soTien;
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
}