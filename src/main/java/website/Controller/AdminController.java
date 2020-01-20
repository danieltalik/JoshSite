package website.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import website.Model.Blog;
import website.DAO.BlogDAO;
import website.Model.Event;
import website.DAO.EventsDAO;
import website.Model.User;

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
    @RequestMapping("/addBlogPost")
    public String addBlogPost(Model model, HttpSession session){
        setSession(model,session);
        Blog blog = new Blog();
        model.addAttribute("Blog",blog);
        return "addBlogPost";
    }

    @RequestMapping(value = "/submitBlogPost", method = RequestMethod.POST)
    public ModelAndView blogAdd(@ModelAttribute Blog blog, Model model, HttpSession session){
        String content = blog.getContent();
        String result = new BlogDAO().blogEntry(blog);
        return new ModelAndView( "redirect:/");
    }

    @RequestMapping("/addEvent")
    public String addEvent(Model model, HttpSession session){
        setSession(model,session);
        Event event = new Event();

        model.addAttribute("Event",event);
        return "addEvent";
    }

    @RequestMapping(value = "/submitEvent", method = RequestMethod.POST)
    public ModelAndView blogAdd(@ModelAttribute Event event, Model model, HttpSession session){
        String result = new EventsDAO().eventEntry(event);
        return new ModelAndView( "redirect:/");
    }
}
