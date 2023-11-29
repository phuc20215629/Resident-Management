package application.model;

import java.sql.Date;

public class NhanKhau {
    private int hoKhauID;
    private String qhChuHo;
    private int laChuHo;
    private int id;
    private String cccd;
    private String hoTen;
    private String biDanh;
    private String gioiTinh;
    private Date ngaySinh;
    private String noiSinh;
    private String nguyenQuan;
    private String danToc;
    private String ngheNghiep;
    private String noiLamViec;
    private Date ngayCapID;
    private String noiCapID;
    private String ghiChu;

    public NhanKhau(int hoKhauID, String qhChuHo, int laChuHo, int id, String cccd, String hoTen, String biDanh,
            String gioiTinh, Date ngaySinh, String noiSinh, String nguyenQuan, 
            String danToc, String ngheNghiep, String noiLamViec, Date ngayCapID,
            String noiCapID, String ghiChu) {
        this.hoKhauID = hoKhauID;
        this.qhChuHo = qhChuHo;
        this.laChuHo = laChuHo;
        this.id = id;
        this.cccd = cccd;
        this.hoTen = hoTen;
        this.biDanh = biDanh;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.noiSinh = noiSinh;
        this.nguyenQuan = nguyenQuan;
        this.danToc = danToc;
        this.ngheNghiep = ngheNghiep;
        this.noiLamViec = noiLamViec;
        this.ngayCapID = ngayCapID;
        this.noiCapID = noiCapID;
        this.ghiChu = ghiChu;
    }

    public NhanKhau() {}

    public NhanKhau(int id) {
        this.id = id;
    }

    public NhanKhau(int hoKhauID, int id, String hoTen) {
        this.hoKhauID = hoKhauID;
        this.id = id;
        this.hoTen = hoTen;
    }

    public void setHoKhauID(int hoKhauID) {
        this.hoKhauID = hoKhauID;
    }

    public void setQhChuHo(String qhChuHo) {
        this.qhChuHo = qhChuHo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setBiDanh(String biDanh) {
        this.biDanh = biDanh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setNoiSinh(String noiSinh) {
        this.noiSinh = noiSinh;
    }

    public void setNguyenQuan(String nguyenQuan) {
        this.nguyenQuan = nguyenQuan;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public void setNoiLamViec(String noiLamViec) {
        this.noiLamViec = noiLamViec;
    }

    public void setNgayCapID(Date ngayCapID) {
        this.ngayCapID = ngayCapID;
    }

    public void setNoiCapID(String noiCapID) {
        this.noiCapID = noiCapID;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    public String getGhiChu() {
        return ghiChu;
    }

    public String getCccd() {
        return cccd;
    }

    public String getQhChuHo() {
        return qhChuHo;
    }

    public int getLaChuHo() {
        return laChuHo;
    }

    public int getHoKhauID() {
        return hoKhauID;
    }

    public int getId() {
        return id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getBiDanh() {
        return biDanh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public String getNoiSinh() {
        return noiSinh;
    }

    public String getNguyenQuan() {
        return nguyenQuan;
    }

    public String getDanToc() {
        return danToc;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public String getNoiLamViec() {
        return noiLamViec;
    }

    public Date getNgayCapID() {
        return ngayCapID;
    }

    public String getNoiCapID() {
        return noiCapID;
    } 

    public void setLaChuHo(int laChuHo) {
        this.laChuHo = laChuHo;
    }

    @Override
    public String toString() {
        return "NhanKhau{" + "hoKhauID = " + hoKhauID + ", qhChuHo = " + qhChuHo 
                + ", id = " + id + ", hoTen = " + hoTen + ", biDanh = " + biDanh 
                + ", gioiTinh = " + gioiTinh + ", ngaySinh = " + ngaySinh 
                + ", noiSinh = " + noiSinh + ", nguyenQuan = " + nguyenQuan 
                + ", danToc = " + danToc + ", ngheNghiep = " + ngheNghiep 
                + ", noiLamViec = " + noiLamViec + ", ngayCapID = " + ngayCapID 
                + ", noiCapID = " + noiCapID + '}';
    }

}