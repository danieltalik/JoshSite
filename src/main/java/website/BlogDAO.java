package website;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogDAO {

    private final String url = "jdbc:postgresql://localhost:5432/JoshSite";
    private final String user = "postgres";
    private final String password = "password";
    private final String allQuery = "SELECT * FROM blog ORDER BY blog_date DESC;";
    private final String idQuery = "SELECT blog_date,blog_title,blog_content FROM blog WHERE blog_id = ?;";


    public Connection connection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            e.getMessage();
        } return conn;
    }
    public List<Blog> allEntries(){
        List<Blog> blogPosts = new ArrayList<>();

        try {
            connection();
            Statement st = connection().createStatement();
            ResultSet rs = st.executeQuery(allQuery);
            while (rs.next()){
                Blog blog = new Blog();

                blog.setId(rs.getInt("blog_id"));
                blog.setDate(rs.getTimestamp("blog_date"));
                blog.setTitle(rs.getString("blog_title"));
                blog.setContent(rs.getString("blog_content"));

                blogPosts.add(blog);
            }
        }catch (SQLException e){
            e.getMessage();
        }
        return blogPosts;
    }

    public Blog blogEntry(int id){

        Blog blog = new Blog();
        try {
            connection();
            PreparedStatement ps = connection().prepareStatement(idQuery);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                blog.setDate(rs.getTimestamp("blog_date"));
                blog.setTitle(rs.getString("blog_title"));
                blog.setContent(rs.getString("blog_content"));
            }
        }catch (SQLException e){
            e.getMessage();
        }
        return blog;
    }
}