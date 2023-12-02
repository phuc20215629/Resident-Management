package application.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import application.database.JDBCUtil;
import application.model.LichSuThayDoi;

public class LichSuThayDoiDAO {
    public static LichSuThayDoiDAO getInstance() {
        return new LichSuThayDoiDAO();
    }

    public boolean insert(LichSuThayDoi t) {
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String sql = "INSERT INTO LICHSUTHAYDOI (HoKhauID, ThayDoi)" +
                    " VALUES (" + t.getHoKhauID() + ", '" + t.getThayDoi() + "');";
            int ans = st.executeUpdate(sql);
            JDBCUtil.closeConnection(connection);
            return ans > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<LichSuThayDoi> selectByHKId(int id) {
        ArrayList<LichSuThayDoi> list = new ArrayList<>();
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT * FROM LICHSUTHAYDOI"
                    + " WHERE HoKhauID = " + id + ";";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                int hoKhauID = rs.getInt("HoKhauID");
                String thayDoi = rs.getString("ThayDoi");
                list.add(new LichSuThayDoi(hoKhauID, thayDoi));
            }
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean deleteByID(int id){
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String sql = "DELETE FROM LICHSUTHAYDOI WHERE HoKhauID = " + id + ";";
            int ans = st.executeUpdate(sql);
            JDBCUtil.closeConnection(connection);
            return ans>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
