package website;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.List;


//TODO: Have users enter and submit profiles to access lessons for Josh to teach. This sends emails to Josh

@Controller

@RequestMapping("/")
public class HomeController {

    //TODO: Fix Homepage Picture on Mobile Devices and add mobile nav capacity

    @RequestMapping("/")
    public String homeIndex(Model model, HttpSession session) {
        // Commenting out Sessions and other items until Josh decides he wants to add this
        User user = new User();
        if(Objects.nonNull(session) && session.getAttribute("Visitor") == null) {
            session.setAttribute("Visitor", user);
        }
        model.addAttribute("Visitor",session.getAttribute("Visitor"));

        return "index";
    }

    @RequestMapping("/bio")
    public String bio() {

        return "bio";
    }

    //Give Josh functionality to add, edit, update, and delete posts. Each post will divert to it's own page
    @RequestMapping("/blog")
    public String blog(Model model) {

        BlogDAO blogDAO = new BlogDAO();
        List<Blog> blogPosts = blogDAO.allEntries();
        model.addAttribute("BlogPosts",blogPosts);
        return "blog";
    }

    @RequestMapping("/blogPost/{id}")
    public String blogPost(@PathVariable int id, Model model) {

        Blog blog = new BlogDAO().blogEntry(id);
        model.addAttribute("Blog",blog);
        return "blogPost";
    }

    @RequestMapping("/events")
    public String events(Model model) {

        //TODO:Add dropdown for past and current events and set up way to move current events to past events using date utils

        EventsDAO dao = new EventsDAO();

        List<Event> events = dao.setEvent();
        model.addAttribute("Events", events);

        return "events";
    }

    //Lesson Booking
    @RequestMapping("/lessons")
    public String lessons() {

        return "lessons";
    }

    // Removed Projects and addressing in the future
   /* @RequestMapping("/projects")
    public String projects() {

        return "projects";
    } */

   //TODO: Related to lessons company, location, rates, etc.,
    @RequestMapping("/teaching")
    public String teaching() {

        return "teaching";
    }

    // Commenting Out These until told to add

    @RequestMapping("/register")
    public String register(Model model) {

        User user = new User();
        model.addAttribute("person",user);
        return "register";

    }
    @RequestMapping(value = "/userAdd", method = RequestMethod.POST)
    public ModelAndView userAdd(@ModelAttribute User user, Model model, HttpSession session){

        model.addAttribute("person",user);
        session.setAttribute("Visitor",user);
        return new ModelAndView( "redirect:/");
    }

    @RequestMapping("/login")
    public String login(Model model, HttpSession session) {


        return "login";
    }



}
