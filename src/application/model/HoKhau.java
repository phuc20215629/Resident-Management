package application.model;

public class HoKhau {
    private int idHoKhau;
    private String tenChuHo;
    private int idChuHo;
    private int soThanhVien;
    private String duong;
    private String phuong;
    private String quan;
    private int soNha;
    private String ghiChu;
    
    public HoKhau(String tenChuHo, int idChuHo, int soThanhVien, String duong, String phuong, String quan, int soNha, String ghiChu){
        this.tenChuHo = tenChuHo;
        this.idChuHo = idChuHo;
        this.soThanhVien = soThanhVien;
        this.duong = duong;
        this.phuong = phuong;
        this.quan = quan;
        this.soNha = soNha;
        this.ghiChu = ghiChu;
    }

    public HoKhau(int idHoKhau, String tenChuHo, int idChuHo, int soThanhVien, String duong, String phuong, String quan, int soNha, String ghiChu){
        this.tenChuHo = tenChuHo;
        this.idChuHo = idChuHo;
        this.soThanhVien = soThanhVien;
        this.duong = duong;
        this.phuong = phuong;
        this.quan = quan;
        this.soNha = soNha;
        this.ghiChu = ghiChu;
        this.idHoKhau = idHoKhau;
    }

    public HoKhau() {}
    
    public int getIdHoKhau(){
        return idHoKhau;
    }

    public String getDuong() {
        return duong;
    }

    public String getPhuong() {
        return phuong;
    }

    public String getQuan() {
        return quan;
    }
    
    public int getSoNha() {
        return soNha;
    }

    public String getTenChuHo(){
        return tenChuHo;
    }
    
    public int getIdChuHo(){
        return idChuHo;
    }
    
    public int getSoThanhVien(){
        return soThanhVien;
    }
    
    public String getGhiChu(){
        return ghiChu;
    }
    

    @Override
    public String toString() {
        return "HoKhau{" + "idHoKhau = " + idHoKhau + ", tenChuHo = " + tenChuHo 
                + ", idChuHo = " + idChuHo + ", soThanhVien = " + soThanhVien 
                + ", diaChi = " + soNha + " " + duong + ", " + phuong + ", " + quan + ", ghiChu = " + ghiChu + '}';
    }

    public void setIdHoKhau(int idHoKhau) {
        this.idHoKhau = idHoKhau;
    }

    public void setTenChuHo(String tenChuHo) {
        this.tenChuHo = tenChuHo;
    }

    public void setIdChuHo(int idChuHo) {
        this.idChuHo = idChuHo;
    }

    public void setSoThanhVien(int soThanhVien) {
        this.soThanhVien = soThanhVien;
    }

    public void setDuong(String duong) {
        this.duong = duong;
    }

    public void setPhuong(String phuong) {
        this.phuong = phuong;
    }

    public void setQuan(String quan) {
        this.quan = quan;
    }

    public void setSoNha(int soNha) {
        this.soNha = soNha;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}