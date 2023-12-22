package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import application.database.JDBCUtil;
import application.model.TamVang;

public class TamVangDAO {
    public static TamVangDAO getInstance() {
        return new TamVangDAO();
    }

    public boolean insert(TamVang tamVang) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "INSERT INTO TAMVANG (idNhanKhau, idHoKhau, tuNgayDangKy, denNgayDangKy, diaChiChuyenDen) "
                    + "VALUES (" + tamVang.getIdNhanKhau() + ", " + tamVang.getIdHoKhau() + ", '"
                    + tamVang.getTuNgayDangKy() + "', '" + tamVang.getDenNgayDangKy() + "', N'"
                    + tamVang.getDiaChiChuyenDen() + "')";

            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(sql);
            return rowsAffected > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean update(TamVang tamVang) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "UPDATE TAMVANG " +
                    "SET tuNgayDangKy = '" + tamVang.getTuNgayDangKy() + "', denNgayDangKy = '"
                    + tamVang.getDenNgayDangKy() + "', diaChiChuyenDen = N'" + tamVang.getDiaChiChuyenDen()
                    + "' WHERE idNhanKhau = " + tamVang.getIdNhanKhau();

            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(sql);
            return rowsAffected > 0;
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

    public TamVang selectByHKID(int id) {
        TamVang tamVang = null;
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM TAMVANG WHERE idHoKhau = ?";

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
