package website.Login;

import website.Blog;

import java.sql.*;

public class UserDAO {

    private final String url = "jdbc:postgresql://localhost:5432/JoshSite";
    private final String user = "postgres";
    private final String password = "password";
    private final String loginQuery = "SELECT username,name FROM users WHERE username = ? AND password = ?;";

    public Connection connection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            e.getMessage();
        } return conn;
    }

    public User userLogin(String username, String password){

        User user = new User();
        try {
            connection();
            PreparedStatement ps = connection().prepareStatement(loginQuery);
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
