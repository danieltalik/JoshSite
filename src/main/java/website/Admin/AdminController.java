package website.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import website.BlogDAO;
import website.Login.User;
import website.Login.UserDAO;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @RequestMapping(value = "/deleteBlog/{id}")
    public ModelAndView userAdd(@PathVariable int id, Model model, HttpSession session) {
        BlogDAO blogDAO =  new BlogDAO();
        String result = blogDAO.deleteBlogEntry(id);
        return new ModelAndView( "redirect:/"+result);
    }
}
