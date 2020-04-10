package website.Controller;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import website.DAO.BlogDAO;
import website.DAO.DataAccessConfig;
import website.DAO.EventsDAO;
import website.Model.Blog;
import website.Model.Event;
import website.Model.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;


//TODO: Have users enter and submit profiles to access lessons for Josh to teach. This sends emails to Josh

@Controller

@RequestMapping("/")
public class HomeController {

    BlogDAO blogDAO;
    EventsDAO eventsDAO;

    public HomeController() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataAccessConfig.class);
        this.blogDAO = context.getBean("blogDao", BlogDAO.class);
        this.eventsDAO = context.getBean("eventsDao", EventsDAO.class);
    }


    private static void setSession(Model model, HttpSession session) {
        User user = new User();
        if (Objects.nonNull(session) && session.getAttribute("Visitor") == null) {
            session.setAttribute("Visitor", user);
        }
        model.addAttribute("Visitor", session.getAttribute("Visitor"));
    }

    //TODO: Add mobile nav capacity
    @RequestMapping("/")
    public String homeIndex(Model model, HttpSession session) {
        // Commenting out Sessions and other items until Josh decides he wants to add this
        setSession(model, session);

        return "index";
    }

    @RequestMapping("/bio")
    public String bio(Model model, HttpSession session) {
        setSession(model, session);
        return "bio";
    }

    @RequestMapping("/blog")
    public String blog(Model model, HttpSession session) {

        setSession(model, session);
        List<Blog> blogPosts = blogDAO.allEntries();
        model.addAttribute("BlogPosts", blogPosts);
        model.addAttribute("Visitor", session.getAttribute("Visitor"));
        return "blog";
    }


    @RequestMapping("/blogPost/{id}")
    public String blogPost(@PathVariable int id, Model model, HttpSession session) {
        setSession(model, session);
        Blog blog = blogDAO.blogEntry(id);
        model.addAttribute("Blog", blog);
        model.addAttribute("Visitor", session.getAttribute("Visitor"));
        return "blogPost";
    }

    @RequestMapping("/events")
    public String events(Model model, HttpSession session) {
        setSession(model, session);
        List<Event> events = eventsDAO.setUpcomingEvent();
        model.addAttribute("Events", events);
        model.addAttribute("Visitor", session.getAttribute("Visitor"));
        return "events";
    }

    @RequestMapping("/pastEvents")
    public String pastEvents(Model model, HttpSession session) {
        setSession(model, session);
        List<Event> events = eventsDAO.setPastEvent();
        model.addAttribute("Events", events);
        model.addAttribute("Visitor", session.getAttribute("Visitor"));
        return "pastEvents";
    }

    //Lesson Booking
    @RequestMapping("/lessons")
    public String lessons(Model model, HttpSession session) {
        setSession(model, session);
        return "lessons";
    }

    // Removed Projects and addressing in the future
   /* @RequestMapping("/projects")
    public String projects() {

        return "projects";
    } */

    //TODO: Related to lessons company, location, rates, etc.,
    @RequestMapping("/teaching")
    public String teaching(Model model, HttpSession session) {
        setSession(model, session);
        return "teaching";
    }

    @RequestMapping("/register")
    public String register(Model model, HttpSession session) {
        setSession(model, session);
        User user = new User();
        model.addAttribute("person", user);
        return "register";
    }

    @RequestMapping(value = "/userAdd", method = RequestMethod.POST)
    public ModelAndView userAdd(@ModelAttribute User user, Model model, HttpSession session) {
        setSession(model, session);
        model.addAttribute("person", user);
        session.setAttribute("Visitor", user);
        return new ModelAndView("redirect:/");
    }

}
