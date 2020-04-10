package website.DAO;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfiguration {

    @Value("${dao.url}")
    private String url;
    @Value("${dao.user}")
    private String user;
    @Value("${dao.password}")
    private String password;

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Connection connection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(getUrl(),getUser(),getPassword());
        }catch (SQLException e){
            e.getMessage();
        } return conn;
    }

}
