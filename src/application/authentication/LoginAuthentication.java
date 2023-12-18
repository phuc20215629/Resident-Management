package application.authentication;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import application.database.JDBCUtil;

public class LoginAuthentication {
    private String username;
    private String password;
    private String info;

    public LoginAuthentication(String username, String password, String info) {
        this.username = username;
        this.password = password;
        this.info = info;
    }

    public LoginAuthentication(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    public String getInfo() {
        return info;
    }

    public boolean checkPassword() {
        try {
            Connection c = JDBCUtil.getConnection();
            String query = "SELECT * FROM USERS WHERE USERNAME LIKE ? AND PASS LIKE ?;";
            PreparedStatement preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.password);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                return true;
            }
            else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkUsername() {
        try {
            Connection c = JDBCUtil.getConnection();
            String query = "SELECT * FROM USERS WHERE USERNAME LIKE ?;";
            PreparedStatement preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, this.username);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                return true;
            }
            else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean confirmAuthentication() {
        try {
            Connection c = JDBCUtil.getConnection();
            String query = "SELECT * FROM USERS WHERE INFO = ?";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1, this.info);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String updateQuery = "UPDATE USERS SET PASS = ? WHERE INFO = ?";
                PreparedStatement preparedStatement = c.prepareStatement(updateQuery);
                preparedStatement.setString(1, this.password);
                preparedStatement.setString(2, this.info);
                preparedStatement.executeUpdate();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
