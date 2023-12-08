package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.database.JDBCUtil;
import application.model.TamVang;

public class TamVangDAO {
    public static TamVangDAO getInstance(){
        return new TamVangDAO();
    }

    public boolean insert(TamVang tamVang) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "INSERT INTO TamVang (id, tuNgayDangKy, denNgayDangKy, diaChi) VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, tamVang.getId());
                preparedStatement.setDate(2, tamVang.getTuNgayDangKy());
                preparedStatement.setDate(3, tamVang.getDenNgayDangKy());
                preparedStatement.setString(4, tamVang.getDiaChiChuyenDen());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteByNKID(int id) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "DELETE FROM TamVang WHERE ID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<TamVang> selectAll() {
        List<TamVang> danhSachTamVang = new ArrayList<>();

        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM TamVang";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    TamVang tamVang = new TamVang();
                    tamVang.setMaTamVang(resultSet.getInt("maTamVang"));
                    tamVang.setId(resultSet.getInt("ID"));
                    tamVang.setTuNgayDangKy(resultSet.getDate("tuNgayDangKy"));
                    tamVang.setDenNgayDangKy(resultSet.getDate("denNgayDangKy"));

                    danhSachTamVang.add(tamVang);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachTamVang;
    }
}
