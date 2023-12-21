package application.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import application.database.JDBCUtil;
import application.model.GiaoDich;

public class GiaoDichDAO implements DAOInterface<GiaoDich> {
    public static GiaoDichDAO getInstance() {
        return new GiaoDichDAO();
    }

    @Override
    public boolean insert(GiaoDich t) {
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String sql = "INSERT INTO GIAODICH (KhoanPhiID, SoTien, HoKhauID, ThoiGianGiaoDich, TenKhoanPhi)" +
                    " VALUES (" + t.getMaKhoanPhi() + ", " + t.getSoTien() + ", " + t.getMaHoKhau() + ", '"
                    + t.getThoiGian() + "', N'" + t.getTenKhoanPhi() + "');";
            // ans: so dong bi thay doi trong sql
            int ans = st.executeUpdate(sql);
            JDBCUtil.closeConnection(connection);
            return ans > 0;
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
                    "SET KhoanPhiID = " + t.getMaKhoanPhi() +
                    ", SoTien = " + t.getSoTien() +
                    ", HoKhauID = " + t.getMaHoKhau() +
                    ", ThoiGianGiaoDich = '" + t.getThoiGian() +
                    "', TenKhoanPhi = N'" + t.getTenKhoanPhi() +
                    "' WHERE GiaoDichID = " + t.getMaGiaoDich() + ";";
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
            String sql = "DELETE FROM GIAODICH " +
                    "WHERE GiaoDichID = " + id + ";";
            int ans = st.executeUpdate(sql);
            JDBCUtil.closeConnection(connection);
            return ans > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteByKPID(int id) {
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String sql = "DELETE FROM GIAODICH " +
                    "WHERE KhoanPhiID = " + id + ";";
            int ans = st.executeUpdate(sql);
            JDBCUtil.closeConnection(connection);
            return ans > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteByHKID(int id) {
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String sql = "DELETE FROM GIAODICH " +
                    "WHERE HoKhauID = " + id + ";";
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
        ArrayList<GiaoDich> list = new ArrayList<GiaoDich>();
        try {
            Connection connection = JDBCUtil.getConnection();
            String query = "SELECT * FROM GIAODICH;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int maGiaoDich = rs.getInt("GiaoDichID");
                int maKhoanPhi = rs.getInt("KhoanPhiID");
                int soTien = rs.getInt("SoTien");
                int maHoKhau = rs.getInt("HoKhauID");
                Date time = rs.getDate("ThoiGianGiaoDich");
                String tenKhoanPhi = rs.getString("TenKhoanPhi");
                GiaoDich t = new GiaoDich(maGiaoDich, maKhoanPhi, soTien, maHoKhau, time, tenKhoanPhi);
                list.add(t);
            }
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public GiaoDich selectById(int id) {
        GiaoDich u = null;
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT * FROM GIAODICH"
                    + " WHERE GiaoDichID = " + id + ";";
            ResultSet rs = st.executeQuery(query);
            rs.next();
            int maGiaoDich = rs.getInt("GiaoDichID");
            int maKhoanPhi = rs.getInt("KhoanPhiID");
            int soTien = rs.getInt("SoTien");
            int maHoKhau = rs.getInt("HoKhauID");
            Date time = rs.getDate("ThoiGianGiaoDich");
            String tenKhoanPhi = rs.getString("TenKhoanPhi");
            u = new GiaoDich(maGiaoDich, maKhoanPhi, soTien, maHoKhau, time, tenKhoanPhi);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public ArrayList<GiaoDich> selectByPeriodAndType(Date start, Date end, String loaiPhi, int maHo) {
        ArrayList<GiaoDich> list = new ArrayList<GiaoDich>();
        try {
            Connection connection = JDBCUtil.getConnection();
            String query;
            if (maHo == 0) {    // Thống kê tất cả các hộ
                query = "SELECT * FROM GIAODICH JOIN KHOANPHI ON GIAODICH.KhoanPhiID = KHOANPHI.KhoanPhiID"
                        + " WHERE KHOANPHI.LoaiKhoanPhi LIKE N'" + loaiPhi
                        + "' AND ThoiGianGiaoDich >= '" + start
                        + "' AND ThoiGianGiaoDich <= '" + end + "';";
                if (loaiPhi.equals("Tất cả")) {
                    query = "SELECT * FROM GIAODICH JOIN KHOANPHI ON GIAODICH.KhoanPhiID = KHOANPHI.KhoanPhiID"
                            + " WHERE ThoiGianGiaoDich >= '" + start
                            + "' AND ThoiGianGiaoDich <= '" + end + "';";
                }
            } else {        // Thống kê 1 hộ đươc yêu cầu
                query = "SELECT * FROM GIAODICH JOIN KHOANPHI ON GIAODICH.KhoanPhiID = KHOANPHI.KhoanPhiID"
                        + " WHERE HoKhauID = " + maHo + " AND KHOANPHI.LoaiKhoanPhi LIKE N'" + loaiPhi
                        + "' AND ThoiGianGiaoDich >= '" + start
                        + "' AND ThoiGianGiaoDich <= '" + end + "';";
                if (loaiPhi.equals("Tất cả")) {
                    query = "SELECT * FROM GIAODICH JOIN KHOANPHI ON GIAODICH.KhoanPhiID = KHOANPHI.KhoanPhiID"
                            + " WHERE HoKhauID = " + maHo + " AND ThoiGianGiaoDich >= '" + start
                            + "' AND ThoiGianGiaoDich <= '" + end + "';";
                }
            }
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int maGiaoDich = rs.getInt("GiaoDichID");
                int maKhoanPhi = rs.getInt("KhoanPhiID");
                int soTien = rs.getInt("SoTien");
                int maHoKhau = rs.getInt("HoKhauID");
                Date time = rs.getDate("ThoiGianGiaoDich");
                String tenKhoanPhi = rs.getString("TenKhoanPhi");
                GiaoDich t = new GiaoDich(maGiaoDich, maKhoanPhi, soTien, maHoKhau, time, tenKhoanPhi);
                list.add(t);
            }
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int countSoHoDaNop(Date start, Date end, String loaiPhi) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String query = "SELECT COUNT(DISTINCT HoKhauID) AS CNT FROM GIAODICH JOIN KHOANPHI ON GIAODICH.KhoanPhiID = KHOANPHI.KhoanPhiID"
                    + " WHERE KHOANPHI.LoaiKhoanPhi LIKE N'" + loaiPhi
                    + "' AND ThoiGianGiaoDich >= '" + start
                    + "' AND ThoiGianGiaoDich <= '" + end + "';";
            if (loaiPhi.equals("Tất cả")) {
                query = "SELECT COUNT(DISTINCT HoKhauID) AS CNT FROM GIAODICH JOIN KHOANPHI ON GIAODICH.KhoanPhiID = KHOANPHI.KhoanPhiID"
                        + " WHERE ThoiGianGiaoDich >= '" + start
                        + "' AND ThoiGianGiaoDich <= '" + end + "';";
            }
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                int count = rs.getInt("CNT");
                return count;
            }
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public ArrayList<GiaoDich> selectByHKID(int id) {
        ArrayList<GiaoDich> list = new ArrayList<GiaoDich>();
        try {
            Connection connection = JDBCUtil.getConnection();
            String query = "SELECT * FROM GIAODICH "
                    + "WHERE HoKhauID = " + id + ";";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int maGiaoDich = rs.getInt("GiaoDichID");
                int maKhoanPhi = rs.getInt("KhoanPhiID");
                int soTien = rs.getInt("SoTien");
                int maHoKhau = rs.getInt("HoKhauID");
                Date time = rs.getDate("ThoiGianGiaoDich");
                String tenKhoanPhi = rs.getString("TenKhoanPhi");
                GiaoDich t = new GiaoDich(maGiaoDich, maKhoanPhi, soTien, maHoKhau, time, tenKhoanPhi);
                list.add(t);
            }
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public GiaoDich selectByHK_KPID(int idHK, int idKP) {
        GiaoDich u = null;
        try {
            Connection connection = JDBCUtil.getConnection();
            String query = "SELECT * FROM GIAODICH "
                    + "WHERE HoKhauID = " + idHK + " AND KhoanPhiID = " + idKP + ";";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                int maGiaoDich = rs.getInt("GiaoDichID");
                int maKhoanPhi = rs.getInt("KhoanPhiID");
                int soTien = rs.getInt("SoTien");
                int maHoKhau = rs.getInt("HoKhauID");
                Date time = rs.getDate("ThoiGianGiaoDich");
                String tenKhoanPhi = rs.getString("TenKhoanPhi");
                u = new GiaoDich(maGiaoDich, maKhoanPhi, soTien, maHoKhau, time, tenKhoanPhi);
            }
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }
}