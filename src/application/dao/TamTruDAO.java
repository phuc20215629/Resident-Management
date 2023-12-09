package application.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import application.database.JDBCUtil;
import application.model.HoKhau;
import application.model.TamTru;

public class TamTruDAO {
    public static TamTruDAO getInstance() {
        return new TamTruDAO();
    }

    public boolean insert(TamTru tamTru) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "INSERT INTO TAMTRU (idNhanKhau, idHoKhau, tuNgayDangKy, denNgayDangKy, diaChiTruocChuyenDen) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, tamTru.getIdNhanKhau());
                preparedStatement.setInt(2, tamTru.getIdHoKhau());
                preparedStatement.setDate(3, tamTru.getTuNgayDangKy());
                preparedStatement.setDate(4, tamTru.getDenNgayDangKy());
                preparedStatement.setString(5, tamTru.getDiaChiTruocChuyenDen());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            return false;
        }
    }

    public boolean update(TamTru tamTru) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "UPDATE TAMTRU " +
                    "SET tuNgayDangKy = ?, denNgayDangKy = ?, diaChiTruocChuyenDen = ? "
                    + "WHERE idNhanKhau = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDate(1, tamTru.getTuNgayDangKy());
                preparedStatement.setDate(2, tamTru.getDenNgayDangKy());
                preparedStatement.setString(3, tamTru.getDiaChiTruocChuyenDen());
                preparedStatement.setInt(4, tamTru.getIdNhanKhau());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteByHKID(int idHk) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "DELETE FROM TAMTRU WHERE idHoKhau = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idHk);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteByNKID(int idNk) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "DELETE FROM TAMTRU WHERE idNhanKhau = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idNk);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteByTimeNotEffective() {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT idNhanKhau FROM TAMTRU WHERE denNgayDangKy < CURRENT_TIMESTAMP";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int idNhanKhau = rs.getInt("idNhanKhau");
                HoKhau hk = HoKhauDAO.getInstance()
                        .selectById(NhanKhauDAO.getInstance().selectById(idNhanKhau).getHoKhauID());
                NhanKhauDAO.getInstance().deleteByID(idNhanKhau);
                hk.setSoThanhVien(HoKhauDAO.getInstance().getSoThanhVien(hk.getIdHoKhau()));
                HoKhauDAO.getInstance().update(hk);

                String delSql = "DELETE FROM TAMTRU WHERE idNhanKhau = " + idNhanKhau;
                int rowsAffected = st.executeUpdate(delSql);
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public TamTru selectByNKID(int id) {
        TamTru tt = null;
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM TAMTRU WHERE idNhanKhau = " + id;

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    tt = new TamTru();
                    tt.setMaTamTru(resultSet.getInt("maTamTru"));
                    tt.setIdNhanKhau(resultSet.getInt("idNhanKhau"));
                    tt.setIdHoKhau(resultSet.getInt("idHoKhau"));
                    tt.setTuNgayDangKy(resultSet.getDate("tuNgayDangKy"));
                    tt.setDenNgayDangKy(resultSet.getDate("denNgayDangKy"));
                    tt.setDiaChiTruocChuyenDen(resultSet.getString("diaChiTruocChuyenDen"));

                    return tt;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tt;
    }

    public ArrayList<TamTru> selectByTimeEffective() {
        ArrayList<TamTru> danhSachTamTru = new ArrayList<>();

        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM TAMTRU WHERE tuNgayDangKy <= CURRENT_TIMESTAMP AND denNgayDangKy >= CURRENT_TIMESTAMP";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                TamTru tt = new TamTru();
                tt.setMaTamTru(rs.getInt("maTamTru"));
                tt.setIdNhanKhau(rs.getInt("idNhanKhau"));
                tt.setIdHoKhau(rs.getInt("idHoKhau"));
                tt.setTuNgayDangKy(rs.getDate("tuNgayDangKy"));
                tt.setDenNgayDangKy(rs.getDate("denNgayDangKy"));
                tt.setDiaChiTruocChuyenDen(rs.getString("diaChiTruocChuyenDen"));
                danhSachTamTru.add(tt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachTamTru;
    }

    public ArrayList<TamTru> selectAll() {
        ArrayList<TamTru> danhSachTamTru = new ArrayList<>();

        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM TAMTRU";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    TamTru tamTru = new TamTru();
                    tamTru.setMaTamTru(resultSet.getInt("maTamTru"));
                    tamTru.setIdNhanKhau(resultSet.getInt("idNhanKhau"));
                    tamTru.setIdHoKhau(resultSet.getInt("idHoKhau"));
                    tamTru.setTuNgayDangKy(resultSet.getDate("tuNgayDangKy"));
                    tamTru.setDenNgayDangKy(resultSet.getDate("denNgayDangKy"));
                    tamTru.setDiaChiTruocChuyenDen(resultSet.getString("diaChiTruocChuyenDen"));

                    danhSachTamTru.add(tamTru);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachTamTru;
    }
}
