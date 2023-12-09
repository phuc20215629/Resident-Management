package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import application.database.JDBCUtil;
import application.model.TamVang;

public class TamVangDAO {
    public static TamVangDAO getInstance() {
        return new TamVangDAO();
    }

    public boolean insert(TamVang tamVang) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "INSERT INTO TAMVANG (idNhanKhau, idHoKhau, tuNgayDangKy, denNgayDangKy, diaChiChuyenDen) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, tamVang.getIdNhanKhau());
                preparedStatement.setInt(2, tamVang.getIdHoKhau());
                preparedStatement.setDate(3, tamVang.getTuNgayDangKy());
                preparedStatement.setDate(4, tamVang.getDenNgayDangKy());
                preparedStatement.setString(5, tamVang.getDiaChiChuyenDen());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean update(TamVang tamVang) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "UPDATE TAMVANG " +
                    "SET tuNgayDangKy = ?, denNgayDangKy = ?, diaChiChuyenDen = ? "
                    + "WHERE idNhanKhau = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDate(1, tamVang.getTuNgayDangKy());
                preparedStatement.setDate(2, tamVang.getDenNgayDangKy());
                preparedStatement.setString(3, tamVang.getDiaChiChuyenDen());
                preparedStatement.setInt(4, tamVang.getIdNhanKhau());

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
            String sql = "DELETE FROM TAMVANG WHERE idNhanKhau = ?";

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

    public boolean deleteByHKID(int idHk) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "DELETE FROM TAMVANG WHERE idHoKhau = ?";

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

    public TamVang selectByNKID(int id) {
        TamVang tamVang = null;
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM TAMVANG WHERE idNhanKhau = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    tamVang = new TamVang();
                    tamVang.setMaTamVang(rs.getInt("maTamVang"));
                    tamVang.setIdNhanKhau(rs.getInt("idNhanKhau"));
                    tamVang.setIdHoKhau(rs.getInt("idHoKhau"));
                    tamVang.setTuNgayDangKy(rs.getDate("tuNgayDangKy"));
                    tamVang.setDenNgayDangKy(rs.getDate("denNgayDangKy"));
                    tamVang.setDiaChiChuyenDen(rs.getString("diaChiChuyenDen"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tamVang;
    }

    public ArrayList<TamVang> selectAll() {
        ArrayList<TamVang> danhSachTamVang = new ArrayList<>();

        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM TAMVANG";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    TamVang tamVang = new TamVang();
                    tamVang.setMaTamVang(resultSet.getInt("maTamVang"));
                    tamVang.setIdNhanKhau(resultSet.getInt("idNhanKhau"));
                    tamVang.setIdHoKhau(resultSet.getInt("idHoKhau"));
                    tamVang.setTuNgayDangKy(resultSet.getDate("tuNgayDangKy"));
                    tamVang.setDenNgayDangKy(resultSet.getDate("denNgayDangKy"));
                    tamVang.setDiaChiChuyenDen(resultSet.getString("diaChiChuyenDen"));

                    danhSachTamVang.add(tamVang);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachTamVang;
    }

    public ArrayList<TamVang> selectByTimeReturn() {
        ArrayList<TamVang> danhSachTamVang = new ArrayList<>();

        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM TAMVANG WHERE denNgayDangKy < CURRENT_TIMESTAMP";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    TamVang tamVang = new TamVang();
                    tamVang.setMaTamVang(resultSet.getInt("maTamVang"));
                    tamVang.setIdNhanKhau(resultSet.getInt("idNhanKhau"));
                    tamVang.setIdHoKhau(resultSet.getInt("idHoKhau"));
                    tamVang.setTuNgayDangKy(resultSet.getDate("tuNgayDangKy"));
                    tamVang.setDenNgayDangKy(resultSet.getDate("denNgayDangKy"));
                    tamVang.setDiaChiChuyenDen(resultSet.getString("diaChiChuyenDen"));

                    danhSachTamVang.add(tamVang);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachTamVang;
    }

    public ArrayList<TamVang> selectByTimeMove() {
        ArrayList<TamVang> danhSachTamVang = new ArrayList<>();

        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM TAMVANG WHERE tuNgayDangKy <= CURRENT_TIMESTAMP AND denNgayDangKy >= CURRENT_TIMESTAMP";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    TamVang tamVang = new TamVang();
                    tamVang.setMaTamVang(resultSet.getInt("maTamVang"));
                    tamVang.setIdNhanKhau(resultSet.getInt("idNhanKhau"));
                    tamVang.setIdHoKhau(resultSet.getInt("idHoKhau"));
                    tamVang.setTuNgayDangKy(resultSet.getDate("tuNgayDangKy"));
                    tamVang.setDenNgayDangKy(resultSet.getDate("denNgayDangKy"));
                    tamVang.setDiaChiChuyenDen(resultSet.getString("diaChiChuyenDen"));

                    danhSachTamVang.add(tamVang);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachTamVang;
    }
}
