package application.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class GiaoDich {
    private int maGiaoDich;
    private int maKhoanPhi;
    private int soTien;
    private int maHoKhau;
    private Date thoiGian;

    public GiaoDich(int maGiaoDich, int maKhoanPhi, int soTien, int maHoKhau, Date thoiGian) {
        this.maGiaoDich = maGiaoDich;
        this.maKhoanPhi = maKhoanPhi;
        this.soTien = soTien;
        this.maHoKhau = maHoKhau;
        this.thoiGian = thoiGian;
    }

    public GiaoDich(int maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
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

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return "GiaoDich{" + "maGiaoDich = " + maGiaoDich + ", maKhoanPhi = " + 
                maKhoanPhi + ", soTien = " + soTien + ", maHoKhau = " + maHoKhau + ", "
                + "thoiGian = " + formatter.format(thoiGian) + '}';
    }
    
    
}