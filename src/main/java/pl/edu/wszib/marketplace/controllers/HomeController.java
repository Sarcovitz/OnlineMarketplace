package pl.edu.wszib.marketplace.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.marketplace.session.Session;

import javax.annotation.Resource;

@Controller
public class HomeController
{
    @Resource
    Session session;

    @RequestMapping(value = "/", method = RequestMethod.GET )
    public String main()
    {
        return  "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model)
    {
        model.addAttribute("logged", this.session.isLogged());
        return "main";
    }
}
