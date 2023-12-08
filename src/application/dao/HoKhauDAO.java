package application.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import application.database.JDBCUtil;
import application.model.HoKhau;

public class HoKhauDAO implements DAOInterface<HoKhau>{
    public static HoKhauDAO getInstance(){
        return new HoKhauDAO();
    }
    
    @Override
    public boolean insert(HoKhau t){
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String sql = "INSERT INTO HOKHAU (TenChuHo, ChuHoID, SoThanhVien, SoNha, Duong, Phuong, Quan, GhiChu)" +
                    " VALUES ('" + t.getTenChuHo() + "'," + t.getIdChuHo() + "," + t.getSoThanhVien() +
                    ",'" + t.getSoNha() + "','" + t.getDuong() + "','" + t.getPhuong() + "','" + t.getQuan() + "','" + t.getGhiChu() + "');" ;
            //ans: so dong bi thay doi trong sql
            int ans = st.executeUpdate(sql);
            JDBCUtil.closeConnection(connection);
            return ans > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean update(HoKhau t){
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String sql = "UPDATE HOKHAU " + 
                    "SET TenChuHo = '" + t.getTenChuHo() + 
                    "', ChuHoID = " + t.getIdChuHo() + 
                    ", SoThanhVien = " + t.getSoThanhVien() + 
                    ", SoNha = " + t.getSoNha() + 
                    ", Duong = '" + t.getDuong() + 
                    "', Phuong = '" + t.getPhuong() + 
                    "', Quan = '" + t.getQuan() + 
                    "', GhiChu = '" + t.getGhiChu() + 
                    "' WHERE HoKhauID = " + t.getIdHoKhau() + ";" ;
            int ans = st.executeUpdate(sql);
            JDBCUtil.closeConnection(connection);
            return ans>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean deleteByID(int id){
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String sql = "DELETE FROM HOKHAU " +
                            "WHERE HoKhauID = '" + id + "';";
            int ans = st.executeUpdate(sql);
            JDBCUtil.closeConnection(connection);
            return ans>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override 
    public ArrayList<HoKhau> selectAll(){
        ArrayList<HoKhau> li = new ArrayList<HoKhau>();
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT * FROM HOKHAU";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                int MaHoKhau = rs.getInt("HoKhauID");
                String HoTenChuHo = rs.getString("TenChuHo");
                int CMNDChuHo = rs.getInt("ChuHoID");
                int SoThanhVien = rs.getInt("SoThanhvien");
                int soNha = rs.getInt("SoNha");
                String duong = rs.getString("Duong");
                String phuong = rs.getString("Phuong");
                String quan = rs.getString("Quan");
                String GhiChu = rs.getString("GhiChu");
                HoKhau t = new HoKhau(MaHoKhau, HoTenChuHo, CMNDChuHo, SoThanhVien, duong, phuong, quan, soNha, GhiChu);
                li.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return li;
    }
    
    @Override
    public HoKhau selectById(int id){
        HoKhau u = null;
        try {
            Connection connection =JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT * FROM HOKHAU"
                           + " WHERE HoKhauID = " + id + ";";
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                int MaHoKhau = rs.getInt("HoKhauID");
                String HoTenChuHo = rs.getString("TenChuHo");
                int CMNDChuHo = rs.getInt("ChuHoID");
                int SoThanhVien = rs.getInt("SoThanhvien");
                int soNha = rs.getInt("SoNha");
                String duong = rs.getString("Duong");
                String phuong = rs.getString("Phuong");
                String quan = rs.getString("Quan");
                String GhiChu = rs.getString("GhiChu");
                u = new HoKhau(MaHoKhau, HoTenChuHo, CMNDChuHo, SoThanhVien, duong, phuong, quan, soNha, GhiChu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public ArrayList<HoKhau> selectByTenChuHo(String ten){
        ArrayList<HoKhau> list = new ArrayList<>();
        try {
            Connection connection =JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT * FROM HOKHAU"
                           + " WHERE TenChuHo = '" + ten + "';";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                int MaHoKhau = rs.getInt("HoKhauID");
                String HoTenChuHo = rs.getString("TenChuHo");
                int CMNDChuHo = rs.getInt("ChuHoID");
                int SoThanhVien = rs.getInt("SoThanhvien");
                int soNha = rs.getInt("SoNha");
                String duong = rs.getString("Duong");
                String phuong = rs.getString("Phuong");
                String quan = rs.getString("Quan");
                String GhiChu = rs.getString("GhiChu");
                list.add(new HoKhau(MaHoKhau, HoTenChuHo, CMNDChuHo, SoThanhVien, duong, phuong, quan, soNha, GhiChu));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getLatestID() {
        try {
            Connection connection =JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT TOP 1 HoKhauID FROM HOKHAU ORDER BY HoKhauID DESC";
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                int MaHoKhau = rs.getInt("HoKhauID");
                return MaHoKhau;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getSoThanhVien(int id) {
        try {
            Connection connection =JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT COUNT(DISTINCT ID) AS CNT FROM NHANKHAU WHERE HoKhauID = " + id;
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                int count = rs.getInt("CNT");
                return count;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}