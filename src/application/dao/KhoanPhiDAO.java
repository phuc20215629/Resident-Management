package application.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import application.database.JDBCUtil;
import application.model.KhoanPhi;

public class KhoanPhiDAO implements DAOInterface<KhoanPhi> {
    public static KhoanPhiDAO getInstance() {
        return new KhoanPhiDAO();
    }

    @Override
    public boolean insert(KhoanPhi t) {
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            int trangThai = 1;
            if(t.getTrangThai().equals("Không hiệu lực")) trangThai = 0;
            String sql = "INSERT INTO KHOANPHI (TenKhoanPhi, LoaiKhoanPhi, SoTien, TuNgay, DenNgay, TrangThai)" +
                    " VALUES (N'" + t.getTenKhoanPhi() + "', N'" + t.getLoaiPhi() + "', " + t.getSoTien() + ", '" + t.getTuNgay() + "', '" + t.getDenNgay() + "', " + trangThai + ");";
            int ans = st.executeUpdate(sql);
            JDBCUtil.closeConnection(connection);
            return ans > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(KhoanPhi t) {
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            int trangThai = 1;
            if(t.getTrangThai().equals("Không hiệu lực")) trangThai = 0;
            String sql = "UPDATE KHOANPHI " +
                    "SET TenKhoanPhi = N'" + t.getTenKhoanPhi() +
                    "', LoaiKhoanPhi = N'" + t.getLoaiPhi() +
                    "', SoTien = " + t.getSoTien() +
                    ", TuNgay = '" + t.getTuNgay() +
                    "', DenNgay = '" + t.getDenNgay() + 
                    "', TrangThai = " + trangThai +
                    " WHERE KhoanPhiID = " + t.getMaKhoanPhi() + ";";
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
            String sql = "DELETE FROM KHOANPHI " +
                    "WHERE KhoanPhiID = " + id + ";";
            int ans = st.executeUpdate(sql);
            JDBCUtil.closeConnection(connection);
            return ans > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<KhoanPhi> selectAll() {
        ArrayList<KhoanPhi> list = new ArrayList<KhoanPhi>();
        try {
            Connection connection = JDBCUtil.getConnection();
            String query = "SELECT * FROM KHOANPHI";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int maKhoanPhi = rs.getInt("KhoanPhiID");
                String tenKhoanPhi = rs.getString("TenKhoanPhi");
                String loaiPhi = rs.getString("LoaiKhoanPhi");
                int soTien = rs.getInt("SoTien");
                Date tuNgay = rs.getDate("TuNgay");
                Date denNgay = rs.getDate("DenNgay");
                int trangThai = rs.getInt("TrangThai");
                String trangThaiStr = "Không hiệu lực";
                if(trangThai == 1) trangThaiStr = "Đang hiệu lực";
                list.add(new KhoanPhi(maKhoanPhi, tenKhoanPhi, loaiPhi, soTien, tuNgay, denNgay, trangThaiStr));
            }
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public KhoanPhi selectById(int id) {
        KhoanPhi u = null;
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT * FROM KHOANPHI"
                    + " WHERE KhoanPhiID = " + id + ";";
            ResultSet rs = st.executeQuery(query);
            if(rs.next()) {
                int maKhoanPhi = rs.getInt("KhoanPhiID");
                String tenKhoanPhi = rs.getString("TenKhoanPhi");
                String loaiPhi = rs.getString("LoaiKhoanPhi");
                int soTien = rs.getInt("SoTien");
                Date tuNgay = rs.getDate("TuNgay");
                Date denNgay = rs.getDate("DenNgay");
                int trangThai = rs.getInt("TrangThai");
                String trangThaiStr = "Không hiệu lực";
                if(trangThai == 1) trangThaiStr = "Đang hiệu lực";
                u = new KhoanPhi(maKhoanPhi, tenKhoanPhi, loaiPhi, soTien, tuNgay, denNgay, trangThaiStr);
            }
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public ArrayList<KhoanPhi> selectByTen(String ten) {
        ArrayList<KhoanPhi> list = new ArrayList<KhoanPhi>();
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT * FROM KHOANPHI"
                    + " WHERE TenKhoanPhi = N'" + ten + "';";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int maKhoanPhi = rs.getInt("KhoanPhiID");
                String tenKhoanPhi = rs.getString("TenKhoanPhi");
                String loaiPhi = rs.getString("LoaiKhoanPhi");
                int soTien = rs.getInt("SoTien");
                Date tuNgay = rs.getDate("TuNgay");
                Date denNgay = rs.getDate("DenNgay");
                int trangThai = rs.getInt("TrangThai");
                String trangThaiStr = "Không hiệu lực";
                if(trangThai == 1) trangThaiStr = "Đang hiệu lực";
                list.add(new KhoanPhi(maKhoanPhi, tenKhoanPhi, loaiPhi, soTien, tuNgay, denNgay, trangThaiStr));
            }
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}