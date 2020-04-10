package website.DAO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import website.Model.Blog;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class BlogDAO{

    private ConnectionConfiguration config;

    @Value("${dao.allBlogPosts}")
    private String allQuery;
    @Value("${dao.blogById}")
    private String idQuery;
    @Value("${dao.deleteBlog}")
    private String deleteQuery;
    @Value("${dao.addBlog}")
    private String insertQuery;

    public BlogDAO(ConnectionConfiguration config){
        this.config = config;
    }

    public List<Blog> allEntries(){
        List<Blog> blogPosts = new ArrayList<>();

        try {
            config.connection();
            Statement st = config.connection().createStatement();
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
            config.connection();
            PreparedStatement ps = config.connection().prepareStatement(idQuery);
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
    public String deleteBlogEntry(int id){

        try {
            config.connection();
            PreparedStatement ps = config.connection().prepareStatement(deleteQuery);
            ps.setInt(1,id);
            ps.executeUpdate();

        }catch (SQLException e){
            e.getMessage();
        }
        return "blog";
    }

    public String blogEntry(Blog blog){

        LocalDateTime ldt = LocalDateTime.now();
        blog.setPostDate(Timestamp.valueOf(ldt));

        try {
            config.connection();
            PreparedStatement ps = config.connection().prepareStatement(insertQuery);
            ps.setTimestamp(1,blog.getPostDate());
            ps.setString(2,blog.getTitle());
            ps.setString(3,blog.getContent());

            ps.executeUpdate();

        }catch (SQLException e){
            e.getMessage();
        }
        return "blog";
    }
}
