package website.Controller;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import website.DAO.DataAccessConfig;
import website.Model.User;
import website.DAO.UserDAO;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class LoginController {


    private static void setSession(Model model,HttpSession session){
        User user = new User();
        if(Objects.nonNull(session) && session.getAttribute("Visitor") == null) {
            session.setAttribute("Visitor", user);
        }
        model.addAttribute("Visitor",session.getAttribute("Visitor"));
    }

    @RequestMapping("/login")
    public String logIn(Model model, HttpSession session) {
        setSession(model,session);
        User user = (User)model.getAttribute("Visitor");
        model.addAttribute("Visitor",user);
        return "login";
    }

    @RequestMapping(value = "/logging", method = RequestMethod.POST)
    public ModelAndView userAdd(@ModelAttribute User user, Model model, HttpSession session){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataAccessConfig.class);
        UserDAO userDAO = context.getBean("userDao", UserDAO.class);
        user = userDAO.userLogin(user.getUsername(),user.getPassword());

        if(user.getName().isEmpty()){
            return new ModelAndView("redirect:login");
        }
        user.setLoggedIn(true);
        model.addAttribute("Visitor",user);
        session.setAttribute("Visitor",user);
        context.close();
        return new ModelAndView( "redirect:/");
    }
    @RequestMapping("/logout")
    public ModelAndView logOut(Model model, HttpSession session) {
        session.removeAttribute("Visitor");
        return new ModelAndView("redirect:/");
    }
}
