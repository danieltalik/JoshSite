package website.DAO;

import website.Model.User;

import java.sql.*;

public class UserDAO{


    private ConnectionConfiguration config;
    private final String loginQuery = "SELECT username,name FROM users WHERE username = ? AND password = ?;";

    public UserDAO(ConnectionConfiguration config){
        this.config = config;
    }

    public User userLogin(String username, String password){

        User user = new User();
        try {
            config.connection();
            PreparedStatement ps = config.connection().prepareStatement(loginQuery);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
            }
        }catch (SQLException e){
            e.getMessage();
        }
        return user;
    }
}
