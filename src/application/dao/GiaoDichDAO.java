package application.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import application.database.JDBCUtil;
import application.model.GiaoDich;
import application.model.HoKhau;

public class GiaoDichDAO implements DAOInterface<GiaoDich>{
    public static GiaoDichDAO getInstance(){
        return new GiaoDichDAO();
    }
    
    @Override
    public boolean insert(GiaoDich t){
       try {
            Connection connection = JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String sql = "INSERT INTO GIAODICH (KhoanPhiID, SoTien, HoKhauID, ThoiGianGiaoDich)" +
                    " VALUES (" + t.getMaKhoanPhi() + ", " + t.getSoTien()+ ", " + t.getMaHoKhau() + ", '" + t.getThoiGian() + "');";
//          ans: so dong bi thay doi trong sql
            int ans = st.executeUpdate(sql);
            JDBCUtil.closeConnection(connection);
            return ans>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean update(GiaoDich t) {
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String sql = "UPDATE GIAODICH " + 
                    "SET KhoanPhiID = " + t.getMaKhoanPhi()+ 
                    ", SoTien = " + t.getSoTien()+  
                    ", HoKhauID = " + t.getMaHoKhau() +
                    ", ThoiGianGiaoDich = '" + t.getThoiGian() +
                    "' WHERE GiaoDichID = " + t.getMaGiaoDich()+ ";" ;
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
            String sql="DELETE FROM GIAODICH "+
                    "WHERE GiaoDichID = "+ id + ";";
            int ans = st.executeUpdate(sql);
            JDBCUtil.closeConnection(connection);
            return ans > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public ArrayList<GiaoDich> selectAll() {
        ArrayList<GiaoDich> list= new ArrayList<GiaoDich>();
        try {
            Connection connection = JDBCUtil.getConnection();
            String query = "SELECT * FROM GIAODICH;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                int maGiaoDich = rs.getInt("GiaoDichID");
                int maKhoanPhi = rs.getInt("KhoanPhiID");
                int soTien = rs.getInt("SoTien");
                int maHoKhau = rs.getInt("HoKhauID");
                Date time = rs.getDate("ThoiGianGiaoDich");
                GiaoDich t = new GiaoDich(maGiaoDich, maKhoanPhi, soTien, maHoKhau, time);
                list.add(t);
            }
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public GiaoDich selectById(int id){
        GiaoDich u = null;
        try {
            Connection connection =JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT * FROM GIAODICH"
                           + " WHERE GiaoDichID = " + id + ";";
            ResultSet rs = st.executeQuery(query);
            rs.next();
            int maGiaoDich = rs.getInt("GiaoDichID");
            int maKhoanPhi = rs.getInt("KhoanPhiID");
            int soTien= rs.getInt("SoTien");
            int maHoKhau = rs.getInt("HoKhauID");
            Date time = rs.getDate("ThoiGianGiaoDich");
            u = new GiaoDich(maGiaoDich, maKhoanPhi, soTien, maHoKhau, time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }
    
    public ArrayList<GiaoDich> selectByPeriod(Date start, Date end){
        ArrayList<GiaoDich> list= new ArrayList<GiaoDich>();
        try {
            Connection connection = JDBCUtil.getConnection();
            String query = "SELECT * FROM GIAODICH " 
                    + "WHERE ThoiGianGiaoDich > " + "'" + start + "'"
                    + "AND " + "ThoiGianGiaoDich < " + "'" + end + "';";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                int maGiaoDich = rs.getInt("GiaoDichID");
                int maKhoanPhi = rs.getInt("KhoanPhiID");
                int soTien = rs.getInt("SoTien");
                int maHoKhau = rs.getInt("HoKhauID");
                Date time = rs.getDate("ThoiGianGiaoDich");
                GiaoDich t = new GiaoDich(maGiaoDich, maKhoanPhi, soTien, maHoKhau, time);
                list.add(t);
            }
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<GiaoDich> selectByFamily(HoKhau t){
        ArrayList<GiaoDich> list= new ArrayList<GiaoDich>();
        try {
            Connection connection = JDBCUtil.getConnection();
            String query = "SELECT * FROM GIAODICH " 
                    + "WHERE HoKhauID = " + t.getIdHoKhau() + ";";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                int maGiaoDich = rs.getInt("GiaoDichID");
                int maKhoanPhi = rs.getInt("KhoanPhiID");
                int soTien = rs.getInt("SoTien");
                int maHoKhau = rs.getInt("HoKhauID");
                Date time = rs.getDate("ThoiGianGiaoDich");
                GiaoDich u = new GiaoDich(maGiaoDich, maKhoanPhi, soTien, maHoKhau, time);
                list.add(u);
            }
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}