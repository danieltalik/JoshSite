package website.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import website.Model.User;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class WebsiteErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView errorMapping(Model model, HttpSession session) {
        setSession(model, session);
        return new ModelAndView("redirect:/");
    }

    @Override
    public String getErrorPath() {

        return "/index";
    }

    private static void setSession(Model model, HttpSession session) {
        User user = new User();
        if (Objects.nonNull(session) && session.getAttribute("Visitor") == null) {
            session.setAttribute("Visitor", user);
        }
        model.addAttribute("Visitor", session.getAttribute("Visitor"));
    }


}
