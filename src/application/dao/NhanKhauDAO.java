package application.dao;

import java.util.ArrayList;
import application.database.JDBCUtil;
import application.model.NhanKhau;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class NhanKhauDAO implements DAOInterface<NhanKhau> {
    public static NhanKhauDAO getInstance(){
        return new NhanKhauDAO();
    }
    
    @Override
    public boolean insert(NhanKhau t) {
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String sql = "INSERT INTO NHANKHAU( HoKhauID, QuanHeVoiChuHo, "
                        + "HoTen, BiDanh, GioiTinh, NgaySinh, NoiSinh, NguyenQuan, DanToc, NgheNghiep,"
                        + " NoiLamViec, NgayCapID, NoiCapID, CCCD, ghiChu)"
                        + " VALUES(" + t.getHoKhauID()+ ", '" + t.getQhChuHo() 
                        + "', '"  + t.getHoTen() + "', '"
                        + t.getBiDanh() + "', '" + t.getGioiTinh() + "', '"
                        + t.getNgaySinh() + "', '" + t.getNoiSinh() + "', '" 
                        + t.getNguyenQuan() +"', '" + t.getDanToc() + "', '" 
                        + t.getNgheNghiep() + "', '" + t.getNoiLamViec() + "', '" 
                        + t.getNgayCapID()+ "', '" + t.getNoiCapID() + "', '" + t.getCccd() + "', '" + t.getGhiChu() + "');" ;
            int ans = st.executeUpdate(sql);
            JDBCUtil.closeConnection(connection);
            return ans > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(NhanKhau t) {
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String sql = "UPDATE NHANKHAU " +
                        "SET HoKhauID = " + t.getHoKhauID()
                        + ", QuanHeVoiChuHo = '" + t.getQhChuHo() 
                        + "', HoTen = '" + t.getHoTen()
                        + "', BiDanh = '" + t.getBiDanh()
                        + "', GioiTinh = '" + t.getGioiTinh()
                        + "', NgaySinh = '" + t.getNgaySinh()
                        + "', NoiSinh = '" + t.getNoiSinh()
                        + "', NguyenQuan = '" + t.getNguyenQuan()
                        + "', DanToc = '" + t.getDanToc()
                        + "', NgheNghiep = '" + t.getNgheNghiep()
                        + "', NoiLamViec = '" + t.getNoiLamViec()
                        + "', NgayCapID = '" + t.getNgayCapID()
                        + "', NoiCapID = '" + t.getNoiCapID()
                        + "', ghiChu = '" + t.getGhiChu()
                        + "', LaChuHo = " + t.getLaChuHo()
                        + " WHERE ID = " + t.getId() + ";" ;
            int ans = st.executeUpdate(sql);
            JDBCUtil.closeConnection(connection);
            return ans > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByID(int id) {
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String sql = "DELETE FROM NHANKHAU " +
                            "WHERE ID = " + id + ";";
            int ans = st.executeUpdate(sql);
            JDBCUtil.closeConnection(connection);
            return ans>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<NhanKhau> selectAll() {
        ArrayList<NhanKhau> li = new ArrayList<NhanKhau>();
        try {
            Connection connection =JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT * FROM NHANKHAU";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                int MaHoKhau = rs.getInt("HoKhauID");
                String QHChuHo = rs.getString("QuanHeVoiChuHo");
                int ID = rs.getInt("ID");
                String cccd = rs.getString("CCCD");
                String HoTen = rs.getString("HoTen");
                String BiDanh = rs.getString("BiDanh");
                String GioiTinh = rs.getString("GioiTinh");
                Date NgaySinh = rs.getDate("NgaySinh");
                String NoiSinh = rs.getString("NoiSinh");
                String NguyenQuan = rs.getString("NguyenQuan");
                String DanToc = rs.getString("DanToc");
                String NgheNghiep = rs.getString("NgheNghiep");
                String NoiLamViec = rs.getString("NoiLamViec");
                Date NgayCapID = rs.getDate("NgayCapID");
                String NoiCapID = rs.getString("NoiCapID");
                int laChuHo = rs.getInt("LaChuHo");
                String ghiChu = rs.getString("ghiChu");
                NhanKhau t = new NhanKhau(MaHoKhau, QHChuHo, laChuHo, ID, cccd, HoTen, BiDanh, 
                        GioiTinh, NgaySinh, NoiSinh, NguyenQuan, DanToc, 
                        NgheNghiep, NoiLamViec, NgayCapID, NoiCapID, ghiChu);
                li.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return li;
    }

    public ArrayList<NhanKhau> selectGroupByHKID() {
        ArrayList<NhanKhau> li = new ArrayList<NhanKhau>();
        try {
            Connection connection =JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT * FROM NHANKHAU ORDER BY HoKhauID";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                int MaHoKhau = rs.getInt("HoKhauID");
                String QHChuHo = rs.getString("QuanHeVoiChuHo");
                int ID = rs.getInt("ID");
                String cccd = rs.getString("CCCD");
                String HoTen = rs.getString("HoTen");
                String BiDanh = rs.getString("BiDanh");
                String GioiTinh = rs.getString("GioiTinh");
                Date NgaySinh = rs.getDate("NgaySinh");
                String NoiSinh = rs.getString("NoiSinh");
                String NguyenQuan = rs.getString("NguyenQuan");
                String DanToc = rs.getString("DanToc");
                String NgheNghiep = rs.getString("NgheNghiep");
                String NoiLamViec = rs.getString("NoiLamViec");
                Date NgayCapID = rs.getDate("NgayCapID");
                String NoiCapID = rs.getString("NoiCapID");
                int laChuHo = rs.getInt("LaChuHo");
                String ghiChu = rs.getString("ghiChu");
                NhanKhau t = new NhanKhau(MaHoKhau, QHChuHo, laChuHo, ID, cccd, HoTen, BiDanh, 
                        GioiTinh, NgaySinh, NoiSinh, NguyenQuan, DanToc, 
                        NgheNghiep, NoiLamViec, NgayCapID, NoiCapID, ghiChu);
                li.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return li;
    }

    @Override
    public NhanKhau selectById(int id) {
        NhanKhau u = null;
        try {
            Connection connection =JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT * FROM NHANKHAU WHERE ID = " + id + ";";
            ResultSet rs = st.executeQuery(query);
            if(rs.next()) {
                int MaHoKhau = rs.getInt("HoKhauID");
                String QHChuHo = rs.getString("QuanHeVoiChuHo");
                int ID = rs.getInt("ID");
                String cccd = rs.getString("CCCD");
                String HoTen = rs.getString("HoTen");
                String BiDanh = rs.getString("BiDanh");
                String GioiTinh = rs.getString("GioiTinh");
                Date NgaySinh = rs.getDate("NgaySinh");
                String NoiSinh = rs.getString("NoiSinh");
                String NguyenQuan = rs.getString("NguyenQuan");
                String DanToc = rs.getString("DanToc");
                String NgheNghiep = rs.getString("NgheNghiep");
                String NoiLamViec = rs.getString("NoiLamViec");
                Date NgayCapID = rs.getDate("NgayCapID");
                String NoiCapID = rs.getString("NoiCapID");
                int laChuHo = rs.getInt("LaChuHo");
                String ghiChu = rs.getString("ghiChu");
                u = new NhanKhau(MaHoKhau, QHChuHo, laChuHo, ID, cccd, HoTen, BiDanh, 
                        GioiTinh, NgaySinh, NoiSinh, NguyenQuan, DanToc, 
                        NgheNghiep, NoiLamViec, NgayCapID, NoiCapID, ghiChu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }  
    
    public ArrayList<NhanKhau> selectByHKId(int idHoKhau) {
        ArrayList<NhanKhau> list = new ArrayList<>();
        try {
            Connection connection =JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT * FROM NHANKHAU WHERE HoKhauID = " + idHoKhau + ";";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                int MaHoKhau = rs.getInt("HoKhauID");
                String QHChuHo = rs.getString("QuanHeVoiChuHo");
                int ID = rs.getInt("ID");
                String cccd = rs.getString("CCCD");
                String HoTen = rs.getString("HoTen");
                String BiDanh = rs.getString("BiDanh");
                String GioiTinh = rs.getString("GioiTinh");
                Date NgaySinh = rs.getDate("NgaySinh");
                String NoiSinh = rs.getString("NoiSinh");
                String NguyenQuan = rs.getString("NguyenQuan");
                String DanToc = rs.getString("DanToc");
                String NgheNghiep = rs.getString("NgheNghiep");
                String NoiLamViec = rs.getString("NoiLamViec");
                Date NgayCapID = rs.getDate("NgayCapID");
                String NoiCapID = rs.getString("NoiCapID");
                int laChuHo = rs.getInt("LaChuHo");
                String ghiChu = rs.getString("ghiChu");
                NhanKhau u = new NhanKhau(MaHoKhau, QHChuHo, laChuHo, ID, cccd, HoTen, BiDanh, 
                        GioiTinh, NgaySinh, NoiSinh, NguyenQuan, DanToc, 
                        NgheNghiep, NoiLamViec, NgayCapID, NoiCapID, ghiChu);
                list.add(u);
            }   
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<NhanKhau> selectByTen(String ten) {
        ArrayList<NhanKhau> list = new ArrayList<>();
        try {
            Connection connection =JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT * FROM NHANKHAU WHERE HoTen = '" + ten + "';";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                int MaHoKhau = rs.getInt("HoKhauID");
                String QHChuHo = rs.getString("QuanHeVoiChuHo");
                int ID = rs.getInt("ID");
                String cccd = rs.getString("CCCD");
                String HoTen = rs.getString("HoTen");
                String BiDanh = rs.getString("BiDanh");
                String GioiTinh = rs.getString("GioiTinh");
                Date NgaySinh = rs.getDate("NgaySinh");
                String NoiSinh = rs.getString("NoiSinh");
                String NguyenQuan = rs.getString("NguyenQuan");
                String DanToc = rs.getString("DanToc");
                String NgheNghiep = rs.getString("NgheNghiep");
                String NoiLamViec = rs.getString("NoiLamViec");
                Date NgayCapID = rs.getDate("NgayCapID");
                String NoiCapID = rs.getString("NoiCapID");
                int laChuHo = rs.getInt("LaChuHo");
                String ghiChu = rs.getString("ghiChu");
                NhanKhau u = new NhanKhau(MaHoKhau, QHChuHo, laChuHo, ID, cccd, HoTen, BiDanh, 
                        GioiTinh, NgaySinh, NoiSinh, NguyenQuan, DanToc, 
                        NgheNghiep, NoiLamViec, NgayCapID, NoiCapID, ghiChu);
                list.add(u) ;
            }   
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<NhanKhau> thongKe(int soLuong, String gioiTinh, int tuTuoi, int denTuoi, String trangThai, Date tuNgay, Date denNgay) {
        ArrayList<NhanKhau> list = new ArrayList<>();
        try {
            Connection connection =JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String query = new String();
            if(gioiTinh.equals("Tất cả")) {
                query = "SELECT TOP " + soLuong +  " * FROM NHANKHAU WHERE (2023 - YEAR(NGAYSINH)) BETWEEN " + tuTuoi + " AND " + denTuoi;
            }
            else {
                query = "SELECT TOP " + soLuong +  " * FROM NHANKHAU WHERE (2023 - YEAR(NGAYSINH)) BETWEEN " + tuTuoi + " AND " + denTuoi + " AND GioiTinh = '" + gioiTinh + " '";
            }
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                int MaHoKhau = rs.getInt("HoKhauID");
                String QHChuHo = rs.getString("QuanHeVoiChuHo");
                int ID = rs.getInt("ID");
                String cccd = rs.getString("CCCD");
                String HoTen = rs.getString("HoTen");
                String BiDanh = rs.getString("BiDanh");
                String GioiTinh = rs.getString("GioiTinh");
                Date NgaySinh = rs.getDate("NgaySinh");
                String NoiSinh = rs.getString("NoiSinh");
                String NguyenQuan = rs.getString("NguyenQuan");
                String DanToc = rs.getString("DanToc");
                String NgheNghiep = rs.getString("NgheNghiep");
                String NoiLamViec = rs.getString("NoiLamViec");
                Date NgayCapID = rs.getDate("NgayCapID");
                String NoiCapID = rs.getString("NoiCapID");
                int laChuHo = rs.getInt("LaChuHo");
                String ghiChu = rs.getString("ghiChu");
                NhanKhau u = new NhanKhau(MaHoKhau, QHChuHo, laChuHo, ID, cccd, HoTen, BiDanh, 
                        GioiTinh, NgaySinh, NoiSinh, NguyenQuan, DanToc, 
                        NgheNghiep, NoiLamViec, NgayCapID, NoiCapID, ghiChu);
                list.add(u) ;
            }   
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}