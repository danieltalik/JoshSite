package website.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import website.Blog;
import website.BlogDAO;
import website.Login.User;
import website.Login.UserDAO;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class AdminController {

    private static void setSession(Model model,HttpSession session){
        User user = new User();
        if(Objects.nonNull(session) && session.getAttribute("Visitor") == null) {
            session.setAttribute("Visitor", user);
        }
        model.addAttribute("Visitor",session.getAttribute("Visitor"));
    }

    @RequestMapping(value = "/deleteBlog/{id}")
    public ModelAndView userAdd(@PathVariable int id, Model model, HttpSession session) {
        setSession(model,session);
        BlogDAO blogDAO =  new BlogDAO();
        String result = blogDAO.deleteBlogEntry(id);
        return new ModelAndView( "redirect:/"+result);
    }
    @RequestMapping("addBlogPost")
    public String addBlogPost(Model model, HttpSession session){
        setSession(model,session);
        Blog blog = new Blog();
        model.addAttribute("Blog",blog);
        return "addBlogPost";
    }

    @RequestMapping(value = "/submitBlogPost", method = RequestMethod.POST)
    public ModelAndView blogAdd(@ModelAttribute Blog blog, Model model, HttpSession session){
        String result = new BlogDAO().blogEntry(blog);
        return new ModelAndView( "redirect:/");
    }
}
